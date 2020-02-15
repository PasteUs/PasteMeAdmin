package cn.pasteme.admin.manager;

import cn.pasteme.admin.dto.RiskCheckResultDTO;
import cn.pasteme.admin.entity.RiskDictionaryDO;
import cn.pasteme.admin.enumeration.RiskCheckResultType;
import cn.pasteme.admin.enumeration.RiskDictionaryType;
import cn.pasteme.admin.manager.impl.RiskControlManagerImpl;
import cn.pasteme.admin.mapper.RiskCheckResultMapper;
import cn.pasteme.admin.mapper.RiskDictionaryMapper;
import cn.pasteme.admin.mapper.RiskStateMapper;
import cn.pasteme.algorithm.ac.AhoCorasick;
import cn.pasteme.algorithm.nlp.NLP;
import cn.pasteme.algorithm.pair.Pair;
import cn.pasteme.common.dto.PasteResponseDTO;
import cn.pasteme.common.manager.PermanentManager;
import cn.pasteme.common.utils.result.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Lucien, Acerkoo
 * @version 1.2.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AdminManagerTests {

    @Autowired
    private AhoCorasick ahoCorasick;

    @Mock
    private PermanentManager permanentManager;

    @Mock
    private RiskDictionaryMapper riskDictionaryMapper;

    @Mock
    private RiskCheckResultMapper riskCheckResultMapper;

    @Mock
    private RiskStateMapper riskStateMapper;

    @Autowired
    private NLP nlp;

    private RiskControlManager riskControlManager;

    @Autowired
    private AnnounceManager announceManager;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);

        // riskDictionaryMapper
        {
            when(riskDictionaryMapper.updateDictionary(any(), anyList())).thenReturn(true);

            {
                RiskDictionaryDO stopWords = mock(RiskDictionaryDO.class);

                when(stopWords.getDictionary()).thenReturn(Arrays.asList("，", "。"));

                when(riskDictionaryMapper.getLatestDictionary(RiskDictionaryType.STOP_WORD)).thenReturn(stopWords);
            }

            {
                RiskDictionaryDO riskWords = mock(RiskDictionaryDO.class);

                when(riskWords.getDictionary()).thenReturn(Arrays.asList("测试", "Test"));

                when(riskDictionaryMapper.getLatestDictionary(RiskDictionaryType.RISK_WORD)).thenReturn(riskWords);
            }
        }

        // permanentManager
        {
            PasteResponseDTO pasteResponseDTO = mock(PasteResponseDTO.class);

            when(pasteResponseDTO.getContent()).thenReturn("你好，世界！");

            when(permanentManager.get("100")).thenReturn(Response.success(pasteResponseDTO));
        }

        // riskCheckResultMapper
        {
            when(riskCheckResultMapper.createDO(any())).thenReturn(true);
            when(riskCheckResultMapper.getResultsByType(any(), any(), any())).thenReturn(new ArrayList<>());
            when(riskCheckResultMapper.getCountByType(any())).thenReturn(0L);
            when(riskCheckResultMapper.updateResult(any())).thenReturn(true);
        }

        // riskStateMapper
        {
            when(riskStateMapper.countByKey(any())).thenReturn(0);
            when(riskStateMapper.countByKey(100L)).thenReturn(1);

            when(riskStateMapper.updateDO(any())).thenReturn(true);
            when(riskStateMapper.insertDO(any())).thenReturn(true);
        }

        riskControlManager = new RiskControlManagerImpl(
                ahoCorasick,
                riskDictionaryMapper,
                permanentManager,
                riskCheckResultMapper,
                riskStateMapper,
                nlp);
    }

    @Test
    public void main() {
        // set stop words
        {
            List<String> stopWords = Arrays.asList("，", "！");

            assertTrue(riskControlManager.setStopWords(stopWords).isSuccess());
        }

        // tokenCount(text)
        {
            Response<List<Pair<String, Long>>> response = riskControlManager.tokenCount("你好，世界！");

            List<Pair<String, Long>> list = Arrays.asList(new Pair<>("世界", 1L), new Pair<>("你好", 1L));

            assertTrue(response.isSuccess());
            assertEquals(2, response.getData().size());
            assertEquals(list, response.getData());
        }

        // tokenCount(key)
        {
            Response response = riskControlManager.tokenCount(100L);
            assertTrue(response.isSuccess());
        }

        // riskCheck(test)
        {
            Response<List<Pair<String, Long>>> response = riskControlManager.riskCheck("测试测试，TestTest.");

            List<Pair<String, Long>> result = Arrays.asList(new Pair<>("Test", 2L), new Pair<>("测试", 2L));

            assertTrue(response.isSuccess());
            assertEquals(2, response.getData().size());
            assertEquals(result, response.getData());
        }

        // riskCheck(key)
        {
            Response response = riskControlManager.riskCheck(100L);
            assertTrue(response.isSuccess());
        }

        // tokenCount without stop words
        {
            List<Pair<String, Long>> list = Arrays.asList(new Pair<>("世界", 1L), new Pair<>("你好", 1L), new Pair<>("！", 1L), new Pair<>("，", 1L));

            assertTrue(riskControlManager.setStopWords(new ArrayList<>()).isSuccess());

            Response<List<Pair<String, Long>>> response = riskControlManager.tokenCount("你好，世界！");

            assertTrue(response.isSuccess());

            assertEquals(list, response.getData());
        }

        // riskCheck without risk dictionary
        {
            assertTrue(riskControlManager.setRiskDictionary(new ArrayList<>()).isSuccess());

            Response<List<Pair<String, Long>>> response = riskControlManager.riskCheck("测试测试，TestTest.");

            assertTrue(response.isSuccess());

            assertEquals(new ArrayList<>(), response.getData());
        }

        // getCheckResult
        {
            Response<List<RiskCheckResultDTO>> response = riskControlManager.getCheckResult(0L, 1L, RiskCheckResultType.KEYWORD_COUNT);

            assertTrue(response.isSuccess());
            assertEquals(new ArrayList<>(), response.getData());
        }

        {
            Response<Long> response = riskControlManager.count(RiskCheckResultType.KEYWORD_COUNT);

            assertTrue(response.isSuccess());
            assertEquals(Long.valueOf(0), response.getData());
        }

        {
            assertTrue(announceManager.countPage(3).isSuccess());
            assertTrue(announceManager.getAnnouncement(1, 3).isSuccess());
        }
    }
}
