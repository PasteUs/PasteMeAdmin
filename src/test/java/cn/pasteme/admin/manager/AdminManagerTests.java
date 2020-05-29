package cn.pasteme.admin.manager;

import cn.pasteme.admin.dto.AbstractRiskCheckResultDTO;
import cn.pasteme.admin.dto.AnnounceRequestDTO;
import cn.pasteme.admin.dto.AnnounceResultDTO;
import cn.pasteme.admin.entity.RiskDictionaryDO;
import cn.pasteme.admin.enumeration.RiskCheckResultType;
import cn.pasteme.admin.enumeration.RiskDictionaryType;
import cn.pasteme.admin.manager.impl.RiskControlManagerImpl;
import cn.pasteme.admin.mapper.RiskCheckResultMapper;
import cn.pasteme.admin.mapper.RiskDictionaryMapper;
import cn.pasteme.admin.mapper.RiskStateMapper;
import cn.pasteme.algorithm.ac.AhoCorasick;
import cn.pasteme.algorithm.model.TextRiskClassification;
import cn.pasteme.algorithm.nlp.NaturalLanguageProcessing;
import cn.pasteme.algorithm.pair.Pair;
import cn.pasteme.common.dto.PasteResponseDTO;
import cn.pasteme.common.manager.PermanentManager;
import cn.pasteme.common.utils.result.Response;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Lucien, Acerkoo
 * @version 1.2.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminManagerTests {

    @Autowired
    private AhoCorasick ahoCorasick;

    @Mock
    private PermanentManager permanentManager;

    @Mock
    private RiskDictionaryMapper riskDictionaryMapper;

//    @Mock
    @Autowired
    private RiskCheckResultMapper riskCheckResultMapper;

//    @Mock
    @Autowired
    private RiskStateMapper riskStateMapper;

    @Autowired
    private NaturalLanguageProcessing nlp;

    private RiskControlManager riskControlManager;

    @Autowired
    private AnnouncementManager announcementManager;

    @Mock
    private TextRiskClassification textRiskClassification;

    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);

        // riskDictionaryMapper
        {
            when(riskDictionaryMapper.updateDictionary(notNull(), anyList())).thenReturn(true);

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

        /*
        // riskCheckResultMapper
        {
            when(riskCheckResultMapper.createDO(notNull())).thenReturn(true);
            when(riskCheckResultMapper.createDO(null)).thenThrow(new NullPointerException());
            when(riskCheckResultMapper.getResultsByType(notNull(), notNull(), notNull())).thenReturn(new ArrayList<>());
            when(riskCheckResultMapper.getCountByType(notNull())).thenReturn(0L);
            when(riskCheckResultMapper.updateResult(notNull())).thenReturn(true);
            when(riskCheckResultMapper.updateResult(null)).thenThrow(new NullPointerException());
        }

        // riskStateMapper
        {
            when(riskStateMapper.countByKey(notNull())).thenReturn(0);
            when(riskStateMapper.countByKey(100L)).thenReturn(1);

            when(riskStateMapper.updateDO(notNull())).thenReturn(true);
            when(riskStateMapper.insertDO(notNull())).thenReturn(true);
        }
         */

        // textClassification
        {
            try {
                when(textRiskClassification.inference(notNull())).thenReturn(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        riskControlManager = new RiskControlManagerImpl(
                ahoCorasick,
                riskDictionaryMapper,
                permanentManager,
                riskCheckResultMapper,
                riskStateMapper,
                nlp,
                textRiskClassification,
                threadPoolExecutor);
    }

    /**
     * tokenCount(text)
     */
    @Test
    @Transactional
    public void test_01_tokenCountByText() {
        // set stop words
        List<String> stopWords = Arrays.asList("，", "！");

        assertTrue(riskControlManager.setStopWords(stopWords).isSuccess());

        Response<List<Pair<String, Long>>> response = riskControlManager.tokenCount("你好，世界！");

        List<Pair<String, Long>> list = Arrays.asList(new Pair<>("世界", 1L), new Pair<>("你好", 1L));

        assertTrue(response.isSuccess());
        assertEquals(2, response.getData().size());
        assertEquals(list, response.getData());
    }

    /**
     * tokenCount(key)
     */
    @Test
    @Transactional
    public void test_02_tokenCountByKey() {
        Response response = riskControlManager.tokenCount(100L);
        assertTrue(response.isSuccess());
    }

    /**
     * riskCheck(text)
     */
    @Test
    @Transactional
    public void test_03_riskCheckByText() {
        Response<List<Pair<String, Long>>> response = riskControlManager.riskCheck("测试测试，TestTest.");

        List<Pair<String, Long>> result = Arrays.asList(new Pair<>("Test", 2L), new Pair<>("测试", 2L));

        assertTrue(response.isSuccess());
        assertEquals(2, response.getData().size());
        assertEquals(result, response.getData());
    }

    /**
     * riskCheck(key)
     */
    @Test
    @Transactional
    public void test_04_riskCheckByKey() {
        Response response = riskControlManager.riskCheck(100L);
        assertTrue(response.isSuccess());
    }

    /**
     * tokenCount without stop words
     */
    @Test
    @Transactional
    public void test_05_tokenCountWithoutStopWords() {
        List<Pair<String, Long>> list = Arrays.asList(new Pair<>("世界", 1L), new Pair<>("你好", 1L), new Pair<>("！", 1L), new Pair<>("，", 1L));

        assertTrue(riskControlManager.setStopWords(new ArrayList<>()).isSuccess());

        Response<List<Pair<String, Long>>> response = riskControlManager.tokenCount("你好，世界！");

        assertTrue(response.isSuccess());

        assertEquals(list, response.getData());
    }

    /**
     * riskCheck without risk dictionary
     */
    @Test
    @Transactional
    public void test_06_riskCheckWithoutRiskDictionary() {
        assertTrue(riskControlManager.setRiskDictionary(new ArrayList<>()).isSuccess());

        Response<List<Pair<String, Long>>> response = riskControlManager.riskCheck("测试测试，TestTest.");

        assertTrue(response.isSuccess());

        assertEquals(new ArrayList<>(), response.getData());
    }

    /**
     * getCheckResult
     */
    @Test
    @Transactional
    public void test_07_getCheckResult() {
        Response<List<AbstractRiskCheckResultDTO>> response = riskControlManager.getPairListCheckResult(0L, 1L, RiskCheckResultType.KEYWORD_COUNT);

        assertTrue(response.isSuccess());
        assertEquals(new ArrayList<>(), response.getData());
    }

    /**
     * Test Count
     */
    @Test
    @Transactional
    public void test_08_getCountOfKeyWordCount() {
        Response<Long> response = riskControlManager.count(RiskCheckResultType.KEYWORD_COUNT);

        assertTrue(response.isSuccess());
        assertEquals(Long.valueOf(0), response.getData());

    }

    /**
     * Announcement
     */
    @Test
    @Transactional
    public void test_09_announcement() {
        try {
            assertTrue(announcementManager.countPage(3) >= 0);
            AnnounceRequestDTO announceRequestDTO = new AnnounceRequestDTO();
            announceRequestDTO.setTitle("Manager_Test");
            announceRequestDTO.setType(0);
            assertTrue(announcementManager.createAnnouncement(announceRequestDTO));

            List<AnnounceResultDTO> list = announcementManager.getAnnouncement(1, 3);
            AnnounceResultDTO announceResultDTO = list.get(0);

            assertTrue(announceResultDTO.getType().getValue() < 3 && announceResultDTO.getType().getValue() >= 0);
            assertTrue(announcementManager.deleteAnnouncement(announceResultDTO.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步的时候不会回滚
     */
    @Test
    @Ignore
    @Transactional
    public void test_10_asyncClassify() {
        riskControlManager.asyncClassify(100L);
    }

    @Test
    @Transactional
    public void test_11_classify() {
        Response<Integer> response = riskControlManager.classify(100L);
        assertTrue(response.isSuccess());
        assertEquals(Integer.valueOf(0), response.getData());
    }
}
