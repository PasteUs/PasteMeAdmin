package cn.pasteme.admin.manager.test;

import cn.pasteme.admin.entity.RiskDictionaryDO;
import cn.pasteme.admin.mapper.RiskDictionaryMapper;
import cn.pasteme.admin.mapper.TableMapper;
import cn.pasteme.admin.manager.risk.RiskControlManager;

import static org.junit.Assert.*;

import cn.pasteme.admin.test.TableInitializer;
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
 * @version 1.0.1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminManagerTests {

    @Autowired
    private TableMapper tableMapper;

    @Autowired
    private RiskControlManager riskControlManager;

    @Autowired
    private RiskDictionaryMapper riskDictionaryMapper;

    @Before
    public void before() {
        TableInitializer.init(tableMapper);
    }

    private List<String> getLatestDictionary() {
        RiskDictionaryDO riskDictionaryDO = riskDictionaryMapper.getLatestDictionary();
        assertNotNull(riskDictionaryDO);
        return riskDictionaryDO.getDictionary();
    }

    @Test
    public void riskControllerTest() {
        List<String> dictionary = new ArrayList<>();
        dictionary.add("World!");
        dictionary.add("世界！");

        assertTrue(riskControlManager.rebuild(dictionary));
        assertFalse(riskControlManager.isRisky("这段文本不会被匹配"));
        assertFalse(riskControlManager.isRisky("This text would not be hit"));
        assertTrue(riskControlManager.isRisky("Hello World!"));
        assertTrue(riskControlManager.isRisky("你好，世界！"));

        assertEquals(dictionary, getLatestDictionary());
    }
}
