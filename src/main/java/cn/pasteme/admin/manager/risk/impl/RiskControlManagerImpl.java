package cn.pasteme.admin.manager.risk.impl;

import cn.pasteme.admin.dto.RiskCheckResultDTO;
import cn.pasteme.admin.entity.RiskCheckResultDO;
import cn.pasteme.admin.entity.RiskDictionaryDO;
import cn.pasteme.admin.enumeration.RiskCheckResultType;
import cn.pasteme.admin.enumeration.RiskDictionaryType;
import cn.pasteme.admin.mapper.RiskCheckResultMapper;
import cn.pasteme.admin.mapper.RiskDictionaryMapper;
import cn.pasteme.admin.manager.risk.RiskControlManager;
import cn.pasteme.algorithm.ac.AhoCorasick;
import cn.pasteme.algorithm.ac.impl.NormalAhoCorasick;
import cn.pasteme.algorithm.nlp.NLP;
import cn.pasteme.algorithm.pair.Pair;
import cn.pasteme.common.entity.PermanentDO;
import cn.pasteme.common.mapper.PermanentMapper;
import cn.pasteme.common.utils.result.Response;
import cn.pasteme.common.utils.result.ResponseCode;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lucien
 * @version 1.2.2
 */
@Data
@Slf4j
@Service
public class RiskControlManagerImpl implements RiskControlManager {

    private AhoCorasick ahoCorasick;

    private RiskDictionaryMapper riskDictionaryMapper;

    private PermanentMapper permanentMapper;

    private RiskCheckResultMapper riskCheckResultMapper;

    private NLP nlp;

    public RiskControlManagerImpl(
            AhoCorasick ahoCorasick,
            RiskDictionaryMapper riskDictionaryMapper,
            PermanentMapper permanentMapper,
            RiskCheckResultMapper riskCheckResultMapper,
            NLP nlp) {
        this.ahoCorasick = ahoCorasick;
        this.riskDictionaryMapper = riskDictionaryMapper;
        this.permanentMapper = permanentMapper;
        this.riskCheckResultMapper = riskCheckResultMapper;
        this.nlp = nlp;

        List<String> dictionary = new ArrayList<>();

        try {
            RiskDictionaryDO riskDictionaryDO = riskDictionaryMapper.getLatestDictionary(RiskDictionaryType.RISK_WORD);
            dictionary = riskDictionaryDO.getDictionary();
        } catch (Exception e) {
            log.error("Load risk dictionary from db error = ", e);
        }

        ahoCorasick.build(dictionary);

        try {
            RiskDictionaryDO riskDictionaryDO = riskDictionaryMapper.getLatestDictionary(RiskDictionaryType.STOP_WORD);
            dictionary = riskDictionaryDO.getDictionary();
        } catch (Exception e) {
            log.error("Load stopWords from db error = ", e);
        }
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

    @Override
    public Response riskCheck(@NotNull Long key) {
        try {
            PermanentDO permanentDO = permanentMapper.getByKey(key);
            List<Pair<String, Long>> result = ahoCorasick.countMatch(permanentDO.getContent());
            log.info("key = {}, result = {}", key, result);

            RiskCheckResultDO riskCheckResultDO = new RiskCheckResultDO();
            riskCheckResultDO.setKey(key);
            riskCheckResultDO.setType(RiskCheckResultType.KEYWORDS_COUNT);
            riskCheckResultDO.setResult(result);
            riskCheckResultMapper.createDO(riskCheckResultDO);
            return Response.success();
        } catch (Exception e) {
            log.error("key = {}, error = ", key, e);
            return Response.error(ResponseCode.SERVER_ERROR);
        }
    }

    @Override
    public Response rebuild(@NotNull List<String> dictionary) {
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
            PermanentDO permanentDO = permanentMapper.getByKey(key);
            List<Pair<String, Long>> result = nlp.countToken(permanentDO.getContent());

            RiskCheckResultDO riskCheckResultDO = new RiskCheckResultDO();
            riskCheckResultDO.setKey(key);
            riskCheckResultDO.setType(RiskCheckResultType.TOKENS_COUNT);
            riskCheckResultDO.setResult(result);

            riskCheckResultMapper.createDO(riskCheckResultDO);
            return Response.success();
        } catch (Exception e) {
            log.error("key = {}, error = ", key, e);
            return Response.error(ResponseCode.SERVER_ERROR);
        }
    }

    @Override
    public Response<List<RiskCheckResultDTO>> getCheckResult(@NotNull Long page, @NotNull Long pageSize, @NotNull RiskCheckResultType type) {
        try {
            List<RiskCheckResultDO> riskCheckResultDoList = riskCheckResultMapper.getResultsByType(type, pageSize, (page - 1) * pageSize);
            RiskCheckResultDTO buffer = new RiskCheckResultDTO();
            return Response.success(riskCheckResultDoList.stream().map(each -> {
                BeanUtils.copyProperties(each, buffer);
                return buffer;
            }).collect(Collectors.toList()));
        } catch (Exception e) {
            log.error("pageIndex = {}, pageSize = {}, type = {}, error = ", page, pageSize, type, e);
            return Response.error(ResponseCode.SERVER_ERROR);
        }
    }

    @Override
    public Response<Long> count(@NotNull RiskCheckResultType riskCheckResultType) {
        try {
            return Response.success(riskCheckResultMapper.getTypeCount(riskCheckResultType));
        } catch (Exception e) {
            log.error("riskCheckResultType = {}, error = ", riskCheckResultType, e);
            return Response.error(ResponseCode.SERVER_ERROR);
        }
    }
}
