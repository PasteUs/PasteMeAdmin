package cn.pasteme.admin.manager.test;

import cn.pasteme.admin.mapper.RiskDictionaryMapper;
import cn.pasteme.admin.mapper.TableMapper;
import cn.pasteme.admin.manager.risk.RiskControlManager;

import cn.pasteme.admin.test.TableInitializer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Lucien
 * @version 1.1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
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

    @Test
    public void test() {

    }
}
