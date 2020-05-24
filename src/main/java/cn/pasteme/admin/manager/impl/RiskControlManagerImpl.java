package cn.pasteme.admin.manager.impl;

import cn.pasteme.admin.dto.RiskCheckResultDTO;
import cn.pasteme.admin.entity.RiskCheckDO;
import cn.pasteme.admin.entity.RiskCheckResultDO;
import cn.pasteme.admin.entity.RiskDictionaryDO;
import cn.pasteme.admin.enumeration.RiskCheckResultType;
import cn.pasteme.admin.enumeration.RiskDictionaryType;
import cn.pasteme.admin.enumeration.RiskStateDoState;
import cn.pasteme.admin.manager.RiskControlManager;
import cn.pasteme.admin.mapper.RiskCheckResultMapper;
import cn.pasteme.admin.mapper.RiskDictionaryMapper;
import cn.pasteme.admin.mapper.RiskStateMapper;
import cn.pasteme.algorithm.ac.AhoCorasick;
import cn.pasteme.algorithm.ac.impl.NormalAhoCorasick;
import cn.pasteme.algorithm.dictionary.Dictionary;
import cn.pasteme.algorithm.model.TextRiskClassification;
import cn.pasteme.algorithm.nlp.NaturalLanguageProcessing;
import cn.pasteme.algorithm.nlp.impl.NormalNaturalLanguageProcessing;
import cn.pasteme.algorithm.pair.Pair;
import cn.pasteme.algorithm.trie.Trie;
import cn.pasteme.algorithm.trie.impl.NormalTrie;
import cn.pasteme.common.dto.PasteResponseDTO;
import cn.pasteme.common.manager.PermanentManager;
import cn.pasteme.common.utils.result.Response;
import cn.pasteme.common.utils.result.ResponseCode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * @author Lucien
 * @version 1.3.1
 */
@Data
@Slf4j
@Service
public class RiskControlManagerImpl implements RiskControlManager {

    private AhoCorasick ahoCorasick;

    private RiskDictionaryMapper riskDictionaryMapper;

    private PermanentManager permanentManager;

    private RiskCheckResultMapper riskCheckResultMapper;

    private RiskStateMapper riskStateMapper;

    private NaturalLanguageProcessing nlp;

    private TextRiskClassification textRiskClassification;

    private ThreadPoolExecutor threadPoolExecutor;

    public RiskControlManagerImpl(
            @Autowired AhoCorasick ahoCorasick,
            @Autowired RiskDictionaryMapper riskDictionaryMapper,
            @Autowired PermanentManager permanentManager,
            @Autowired RiskCheckResultMapper riskCheckResultMapper,
            @Autowired RiskStateMapper riskStateMapper,
            @Autowired NaturalLanguageProcessing nlp,
            @Autowired TextRiskClassification textRiskClassification,
            @Autowired ThreadPoolExecutor threadPoolExecutor) {
        this.ahoCorasick = ahoCorasick;
        this.riskDictionaryMapper = riskDictionaryMapper;
        this.permanentManager = permanentManager;
        this.riskCheckResultMapper = riskCheckResultMapper;
        this.riskStateMapper = riskStateMapper;
        this.nlp = nlp;
        this.textRiskClassification = textRiskClassification;
        this.threadPoolExecutor = threadPoolExecutor;

        List<String> dictionary = new ArrayList<>();

        try {
            RiskDictionaryDO riskDictionaryDO = riskDictionaryMapper.getLatestDictionary(RiskDictionaryType.RISK_WORD);
            if (null != riskDictionaryDO) {
                log.warn("Empty risk dictionary");
                dictionary = riskDictionaryDO.getDictionary();
            }
        } catch (Exception e) {
            log.error("Load risk dictionary from db error = ", e);
        }

        ahoCorasick.build(dictionary);

        List<String> stopWords = new ArrayList<>();

        try {
            RiskDictionaryDO riskDictionaryDO = riskDictionaryMapper.getLatestDictionary(RiskDictionaryType.STOP_WORD);
            if (null != riskDictionaryDO) {
                log.warn("Empty stop words");
                stopWords = riskDictionaryDO.getDictionary();
            }
        } catch (Exception e) {
            log.error("Load stopWords from db error = ", e);
        }

        nlp.addStopWords(stopWords);
    }

    @Override
    public Response<List<Pair<String, Long>>> riskCheck(@NotNull String text) {
        try {
            return Response.success(ahoCorasick.countMatch(text));
        } catch (Exception e) {
            log.error("error = ", e);
            return Response.error(ResponseCode.SERVER_ERROR);
        }
    }

    private Response<Void> insertOrUpdateCheckResult(RiskCheckDO riskCheckDO, RiskCheckResultDO riskCheckResultDO) {

        Long key = riskCheckDO.getKey();

        if (riskStateMapper.countByKey(key) > 0) {
            if (!riskStateMapper.updateDO(riskCheckDO)) {
                log.error("error = 'riskStateMapper.updateDO() failed'");
                return Response.error(ResponseCode.SERVER_ERROR);
            }

            if (!riskCheckResultMapper.updateResult(riskCheckResultDO)) {
                log.error("error = 'riskCheckResultMapper.updateResult() failed'");
                return Response.error(ResponseCode.SERVER_ERROR);
            }
        } else {
            if (!riskStateMapper.insertDO(riskCheckDO)) {
                log.error("error = 'riskStateMapper.insertDO() failed'");
                return Response.error(ResponseCode.SERVER_ERROR);
            }

            if (!riskCheckResultMapper.createDO(riskCheckResultDO)) {
                log.error("error = 'riskCheckResultMapper.createDO() failed'");
                return Response.error(ResponseCode.SERVER_ERROR);
            }
        }

        return Response.success(null);
    }

    @Override
    public Response riskCheck(@NotNull Long key) {
        try {
            Response<PasteResponseDTO> response = permanentManager.get(key.toString());
            if (!response.isSuccess()) {
                return Response.error(response);
            }
            List<Pair<String, Long>> result = ahoCorasick.countMatch(response.getData().getContent());
            log.info("key = {}, result = {}", key, result);

            RiskCheckDO riskCheckDO = new RiskCheckDO(key);

            RiskCheckResultDO riskCheckResultDO = new RiskCheckResultDO();
            riskCheckResultDO.setKey(key);
            riskCheckResultDO.setType(RiskCheckResultType.KEYWORD_COUNT);
            riskCheckResultDO.setPairListResult(result);

            return insertOrUpdateCheckResult(riskCheckDO, riskCheckResultDO);
        } catch (Exception e) {
            log.error("key = {}, error = ", key, e);
            return Response.error(ResponseCode.SERVER_ERROR);
        }
    }

    @Override
    public Response setRiskDictionary(@NotNull List<String> dictionary) {
        try {
            riskDictionaryMapper.updateDictionary(RiskDictionaryType.RISK_WORD, dictionary);
            AhoCorasick ahoCorasick = new NormalAhoCorasick();
            ahoCorasick.build(dictionary);
            this.ahoCorasick = ahoCorasick;
            return Response.success();
        } catch (Exception e) {
            log.error("dictionary = {}, error = ", dictionary, e);
            return Response.error(ResponseCode.SERVER_ERROR);
        }
    }

    @Override
    public Response setStopWords(@NotNull List<String> stopWords) {
        try {
            riskDictionaryMapper.updateDictionary(RiskDictionaryType.STOP_WORD, stopWords);
            Trie trie = new NormalTrie();
            trie.addAll(stopWords);
            nlp = new NormalNaturalLanguageProcessing(new Dictionary(trie));
            return Response.success();
        } catch (Exception e) {
            log.error("stopWords = {}, error = ", stopWords, e);
            return Response.error(ResponseCode.SERVER_ERROR);
        }
    }

    @Override
    public Response<List<Pair<String, Long>>> tokenCount(@NotNull String text) {
        try {
            return Response.success(nlp.countToken(text));
        } catch (Exception e) {
            log.error("error = ", e);
            return Response.error(ResponseCode.SERVER_ERROR);
        }
    }

    @Override
    public Response tokenCount(@NotNull Long key) {
        try {
            Response<PasteResponseDTO> response = permanentManager.get(key.toString());
            if (!response.isSuccess()) {
                return response;
            }
            List<Pair<String, Long>> result = nlp.countToken(response.getData().getContent());

            RiskCheckResultDO riskCheckResultDO = new RiskCheckResultDO();
            riskCheckResultDO.setKey(key);
            riskCheckResultDO.setType(RiskCheckResultType.TOKEN_COUNT);
            riskCheckResultDO.setPairListResult(result);

            riskCheckResultMapper.createDO(riskCheckResultDO);
            return Response.success();
        } catch (Exception e) {
            log.error("key = {}, error = ", key, e);
            return Response.error(ResponseCode.SERVER_ERROR);
        }
    }

    @Override
    public Response<List<RiskCheckResultDTO>> getCheckResult(@NotNull Long pageIndex,
                                                             @NotNull Long pageSize,
                                                             @NotNull RiskCheckResultType type) {
        try {
            Long count = riskCheckResultMapper.getCountByType(type);

            if (count < 1) {
                return Response.success(new ArrayList<>());
            }

            if (pageIndex < 1 || (count + pageSize - 1) / pageSize < pageSize) {
                return Response.error(ResponseCode.PARAM_ERROR);
            }

            List<RiskCheckResultDO> riskCheckResultDoList = riskCheckResultMapper.getResultsByType(type,
                    pageSize, (pageIndex - 1) * pageSize);
            RiskCheckResultDTO buffer = new RiskCheckResultDTO();
            return Response.success(riskCheckResultDoList.stream().map(each -> {
                BeanUtils.copyProperties(each, buffer);
                return buffer;
            }).collect(Collectors.toList()));
        } catch (Exception e) {
            log.error("pageIndex = {}, pageSize = {}, type = {}, error = ", pageIndex, pageSize, type, e);
            return Response.error(ResponseCode.SERVER_ERROR);
        }
    }

    @Override
    public Response<Long> count(@NotNull RiskCheckResultType riskCheckResultType) {
        try {
            return Response.success(riskCheckResultMapper.getCountByType(riskCheckResultType));
        } catch (Exception e) {
            log.error("riskCheckResultType = {}, error = ", riskCheckResultType, e);
            return Response.error(ResponseCode.SERVER_ERROR);
        }
    }

    @Override
    public Response<Integer> classify(@NotNull Long key) {
        try {
            Response<PasteResponseDTO> response = permanentManager.get(key.toString());
            if (!response.isSuccess()) {
                return Response.error(response);
            }

            String content = response.getData().getContent();

            int classifyResult = textRiskClassification.inference(content);

            // 风险检测的状态
            RiskCheckDO riskCheckDO = new RiskCheckDO(key);

            if (classifyResult == 1) {
                riskCheckDO.setState(RiskStateDoState.REQUEST_REVIEW);
            }

            // 风险检测的结果
            RiskCheckResultDO riskCheckResultDO = new RiskCheckResultDO();
            riskCheckResultDO.setKey(key);
            riskCheckResultDO.setType(RiskCheckResultType.TEXT_CLASSIFICATION);
            riskCheckResultDO.setIntegerResult(classifyResult);

            Response<Void> insertOrUpdateCheckResult = insertOrUpdateCheckResult(riskCheckDO, riskCheckResultDO);

            if (insertOrUpdateCheckResult.isSuccess()) {
                return Response.success(classifyResult);
            }
            return Response.error(insertOrUpdateCheckResult);
        } catch (Exception e) {
            return Response.error(ResponseCode.SERVER_ERROR);
        }
    }

    @Override
    public Response<Void> asyncClassify(@NotNull Long key) {
        this.threadPoolExecutor.submit(() -> {
            classify(key);
        });
        return Response.success(null);
    }
}
