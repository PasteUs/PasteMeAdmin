package cn.pasteme.admin;

import cn.pasteme.admin.entity.PasteAdminDO;
import cn.pasteme.admin.entity.RiskDictionaryDO;
import cn.pasteme.admin.mapper.PasteAdminMapper;
import cn.pasteme.admin.mapper.RiskDictionaryMapper;
import cn.pasteme.admin.risk.RiskController;
import cn.pasteme.admin.mapper.PasteAdminTestMapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminApplicationTests {

    @Autowired
    private PasteAdminMapper pasteAdminMapper;

    @Autowired
    private PasteAdminTestMapper pasteAdminTestMapper;

    @Autowired
    private RiskDictionaryMapper riskDictionaryMapper;

    @Autowired
    private RiskController riskController;

    @Test
    public void mapperTest() {
        pasteAdminMapper.createTable();
        pasteAdminTestMapper.delete(100L);
        Assert.assertTrue(pasteAdminMapper.createDo(100L, 200L, 0, 0));
        Assert.assertTrue(pasteAdminMapper.increaseCountByKey(100L));
        PasteAdminDO pasteAdminDO = pasteAdminMapper.getDoByKey(100L);
        Assert.assertNotNull(pasteAdminDO);
        Assert.assertEquals(Long.valueOf(201), pasteAdminDO.getCount());
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
        riskDictionaryMapper.createTable();
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
}
