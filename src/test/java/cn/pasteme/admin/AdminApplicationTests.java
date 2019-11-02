package cn.pasteme.admin;

import cn.pasteme.admin.entity.RiskStateDO;
import cn.pasteme.admin.entity.RiskDictionaryDO;
import cn.pasteme.admin.enumeration.PasteState;
import cn.pasteme.admin.enumeration.PasteType;
import cn.pasteme.admin.mapper.AccessCountMapper;
import cn.pasteme.admin.mapper.RiskStateMapper;
import cn.pasteme.admin.mapper.RiskDictionaryMapper;
import cn.pasteme.admin.mapper.TableMapper;
import cn.pasteme.admin.risk.RiskController;
import cn.pasteme.admin.mapper.PasteAdminTestMapper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lucien
 * @version 1.2.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminApplicationTests {

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
    private RiskController riskController;

    @Before
    public void before() {
        tableMapper.createPasteMeAdminRiskState();
        tableMapper.createPasteMeAdminDictionary();
        tableMapper.createPasteMeAdminAccessCount();
    }

    @Test
    public void mapperTest() {
        pasteAdminTestMapper.delete("pasteme_admin_risk_state", 100L);

        Assert.assertEquals(0, riskStateMapper.countByKey(100L));
        RiskStateDO riskStateDO = new RiskStateDO(100L);
        Assert.assertTrue(riskStateMapper.insertDO(riskStateDO));
        Assert.assertEquals(1, riskStateMapper.countByKey(100L));

        riskStateDO = riskStateMapper.getDoByKey(100L);
        Assert.assertNotNull(riskStateDO);

        riskStateDO.setState(PasteState.CHECKED);
        riskStateDO.setType(PasteType.PORN);
        Assert.assertTrue(riskStateMapper.updateDO(riskStateDO));
        riskStateDO = riskStateMapper.getDoByKey(100L);
        Assert.assertNotNull(riskStateDO);
        Assert.assertEquals(PasteState.CHECKED, riskStateDO.getState());
        Assert.assertEquals(PasteType.PORN, riskStateDO.getType());
    }

    private List<String> getLatestDictionary() {
        RiskDictionaryDO riskDictionaryDO = riskDictionaryMapper.getLatestDictionary();
        Assert.assertNotNull(riskDictionaryDO);
        return riskDictionaryDO.getDictionary();
    }

    private void updateAndCheck(List<String> dictionary) {
        Assert.assertTrue(riskDictionaryMapper.updateDictionary(dictionary));
        Assert.assertEquals(dictionary, getLatestDictionary());
    }

    public void riskDictionaryMapperTest() {
        List<String> dictionary = new ArrayList<>();
        dictionary.add("你好");
        updateAndCheck(dictionary);
        dictionary.add("Hello");
        updateAndCheck(dictionary);
    }

    @Test
    public void riskControllerTest() {
        riskDictionaryMapperTest();

        List<String> dictionary = new ArrayList<>();
        dictionary.add("World!");
        dictionary.add("世界！");

        Assert.assertTrue(riskController.rebuild(dictionary));
        Assert.assertFalse(riskController.isRisky("这段文本不会被匹配"));
        Assert.assertFalse(riskController.isRisky("This text would not be hit"));
        Assert.assertTrue(riskController.isRisky("Hello World!"));
        Assert.assertTrue(riskController.isRisky("你好，世界！"));

        Assert.assertEquals(dictionary, getLatestDictionary());
    }

    @Test
    public void accessCountTest() {
        pasteAdminTestMapper.delete("pasteme_admin_access_count", 100L);
        Assert.assertTrue(accessCountMapper.createRecord(100L));
        Assert.assertTrue(accessCountMapper.increaseCountByKey(100L));
        Assert.assertEquals(1, accessCountMapper.getAccessCountByKey(100L));
    }
}
