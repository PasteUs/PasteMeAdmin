package cn.pasteme.admin.mapper;

import cn.pasteme.admin.entity.RiskCheckDO;
import cn.pasteme.admin.entity.RiskCheckResultDO;
import cn.pasteme.admin.entity.RiskDictionaryDO;
import cn.pasteme.admin.enumeration.RiskDictionaryType;
import cn.pasteme.admin.enumeration.RiskStateType;
import cn.pasteme.admin.enumeration.RiskStateState;
import cn.pasteme.admin.enumeration.RiskCheckResultType;
import cn.pasteme.algorithm.pair.Pair;

import static org.junit.Assert.*;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Lucien
 * @version 1.2.4
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Slf4j
public class AdminMapperTests {

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

    private List<String> getLatestDictionary() {
        RiskDictionaryDO riskDictionaryDO = riskDictionaryMapper.getLatestDictionary(RiskDictionaryType.RISK_WORD);
        assertNotNull(riskDictionaryDO);
        return riskDictionaryDO.getDictionary();
    }

    private void updateAndCheck(List<String> dictionary) {
        assertTrue(riskDictionaryMapper.updateDictionary(RiskDictionaryType.RISK_WORD, dictionary));
        assertEquals(dictionary, getLatestDictionary());
    }

    @Test
    public void riskStateMapperTest() {
        pasteAdminTestMapper.delete("pasteme_admin_risk_state", 100L);

        assertEquals(0, riskStateMapper.countByKey(100L));
        RiskCheckDO riskCheckDO = new RiskCheckDO(100L);
        assertTrue(riskStateMapper.insertDO(riskCheckDO));
        assertEquals(1, riskStateMapper.countByKey(100L));

        riskCheckDO = riskStateMapper.getDoByKey(100L);
        assertNotNull(riskCheckDO);

        riskCheckDO.setState(RiskStateType.CHECKED);
        riskCheckDO.setType(RiskStateState.PORN);
        assertTrue(riskStateMapper.updateDO(riskCheckDO));
        riskCheckDO = riskStateMapper.getDoByKey(100L);
        assertNotNull(riskCheckDO);
        assertEquals(RiskStateType.CHECKED, riskCheckDO.getState());
        assertEquals(RiskStateState.PORN, riskCheckDO.getType());
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

        riskCheckResultDO.setResult(expect);

        assertTrue(riskCheckResultMapper.updateResult(riskCheckResultDO));
        riskCheckResultDO = riskCheckResultMapper.getResultByKeyAndType(100L, RiskCheckResultType.KEYWORDS_COUNT);
        assertNotNull(riskCheckResultDO);
        actually = riskCheckResultDO.getResult();
        assertEquals(expect, actually);
    }
}
