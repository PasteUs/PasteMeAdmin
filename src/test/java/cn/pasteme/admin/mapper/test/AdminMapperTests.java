package cn.pasteme.admin.mapper.test;

import cn.pasteme.admin.entity.RiskCheckResultDO;
import cn.pasteme.admin.entity.RiskStateDO;
import cn.pasteme.admin.entity.RiskDictionaryDO;
import cn.pasteme.admin.enumeration.PasteState;
import cn.pasteme.admin.enumeration.PasteType;
import cn.pasteme.admin.enumeration.RiskCheckResultType;
import cn.pasteme.admin.mapper.AccessCountMapper;
import cn.pasteme.admin.mapper.RiskCheckResultMapper;
import cn.pasteme.admin.mapper.RiskStateMapper;
import cn.pasteme.admin.mapper.RiskDictionaryMapper;
import cn.pasteme.admin.mapper.TableMapper;
import cn.pasteme.admin.mapper.PasteAdminTestMapper;
import cn.pasteme.algorithm.pair.Pair;

import static org.junit.Assert.*;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Lucien
 * @version 1.2.1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AdminMapperTests {

    @Autowired
    private TableMapper tableMapper;

    @Autowired
    private AccessCountMapper accessCountMapper;

    @Autowired
    private RiskStateMapper riskStateMapper;

    @Autowired
    private PasteAdminTestMapper pasteAdminTestMapper;

    @Autowired
    private RiskDictionaryMapper riskDictionaryMapper;

    @Autowired
    private RiskCheckResultMapper riskCheckResultMapper;

    @Before
    public void before() {
        tableMapper.createPasteMeAdminRiskState();
        tableMapper.createPasteMeAdminDictionary();
        tableMapper.createPasteMeAdminAccessCount();
        tableMapper.createPasteMeAdminRiskCheckResult();
    }

    private List<String> getLatestDictionary() {
        RiskDictionaryDO riskDictionaryDO = riskDictionaryMapper.getLatestDictionary();
        assertNotNull(riskDictionaryDO);
        return riskDictionaryDO.getDictionary();
    }

    private void updateAndCheck(List<String> dictionary) {
        assertTrue(riskDictionaryMapper.updateDictionary(dictionary));
        assertEquals(dictionary, getLatestDictionary());
    }

    private void assertPairListEquals(@NotNull List<Pair<String, Long>> expect,
                                      @NotNull List<Pair<String, Long>> actually) {
        if (expect.size() != actually.size()) {
            assertEquals(expect, actually);
        } else {
            for (int i = 0; i < expect.size(); i++) {
                Pair<String, Long> expectPair = expect.get(i);
                Pair<String, Long> actuallyPair = actually.get(i);
                assertEquals(expectPair, actuallyPair);
            }
        }
    }

    @Test
    public void riskStateMapperTest() {
        pasteAdminTestMapper.delete("pasteme_admin_risk_state", 100L);

        assertEquals(0, riskStateMapper.countByKey(100L));
        RiskStateDO riskStateDO = new RiskStateDO(100L);
        assertTrue(riskStateMapper.insertDO(riskStateDO));
        assertEquals(1, riskStateMapper.countByKey(100L));

        riskStateDO = riskStateMapper.getDoByKey(100L);
        assertNotNull(riskStateDO);

        riskStateDO.setState(PasteState.CHECKED);
        riskStateDO.setType(PasteType.PORN);
        assertTrue(riskStateMapper.updateDO(riskStateDO));
        riskStateDO = riskStateMapper.getDoByKey(100L);
        assertNotNull(riskStateDO);
        assertEquals(PasteState.CHECKED, riskStateDO.getState());
        assertEquals(PasteType.PORN, riskStateDO.getType());
    }

    @Test
    public void riskDictionaryMapperTest() {
        updateAndCheck(Arrays.asList("词典", "测试"));
    }

    @Test
    public void accessCountMapperTest() {
        pasteAdminTestMapper.delete("pasteme_admin_access_count", 100L);
        assertTrue(accessCountMapper.createRecord(100L));
        assertTrue(accessCountMapper.increaseCountByKey(100L));
        assertEquals(1, accessCountMapper.getAccessCountByKey(100L));
    }

    @Test
    public void riskCheckResultMapperTest() {
        pasteAdminTestMapper.delete("pasteme_admin_risk_check_result", 100L);
        RiskCheckResultDO riskCheckResultDO = new RiskCheckResultDO();
        riskCheckResultDO.setKey(100L);
        riskCheckResultDO.setType(RiskCheckResultType.KEYWORDS_COUNT);

        List<Pair<String, Long>> expect, actually;
        expect = new ArrayList<>();
        expect.add(new Pair<>("English Test", 1L));
        expect.add(new Pair<>("中文测试", 2L));
        riskCheckResultDO.setResult(expect);

        assertTrue(riskCheckResultMapper.createDO(riskCheckResultDO));
        riskCheckResultDO = riskCheckResultMapper.getResultByKeyAndType(100L, RiskCheckResultType.KEYWORDS_COUNT);
        assertNotNull(riskCheckResultDO);
        actually = riskCheckResultDO.getResult();
        assertEquals(expect, actually);

        expect.add(new Pair<>("Hello World!", 10086L));
        expect.add(new Pair<>("你好，世界！", 10010L));
        assertTrue(riskCheckResultMapper.updateResult(100L, RiskCheckResultType.KEYWORDS_COUNT, expect));
        riskCheckResultDO = riskCheckResultMapper.getResultByKeyAndType(100L, RiskCheckResultType.KEYWORDS_COUNT);
        assertNotNull(riskCheckResultDO);
        actually = riskCheckResultDO.getResult();
        assertEquals(expect, actually);
    }
}
