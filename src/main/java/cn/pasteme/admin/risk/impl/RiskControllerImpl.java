package cn.pasteme.admin.risk.impl;

import cn.pasteme.admin.entity.RiskDictionaryDO;
import cn.pasteme.admin.mapper.RiskDictionaryMapper;
import cn.pasteme.admin.risk.RiskController;
import cn.pasteme.algorithm.ac.AhoCorasick;
import cn.pasteme.algorithm.ac.impl.NormalAhoCorasick;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lucien
 * @version 1.0.1
 */
@Data
@Slf4j
@Service
public class RiskControllerImpl implements RiskController {

    private AhoCorasick ahoCorasick;

    private RiskDictionaryMapper riskDictionaryMapper;

    public RiskControllerImpl(RiskDictionaryMapper riskDictionaryMapper) {
        this.ahoCorasick = new NormalAhoCorasick();
        this.riskDictionaryMapper = riskDictionaryMapper;

        List<String> dictionary = new ArrayList<>();

        try {
            RiskDictionaryDO riskDictionaryDO = riskDictionaryMapper.getLatestDictionary();
            dictionary = riskDictionaryDO.getDictionary();
        } catch (Exception e) {
            log.error("Load from db error = ", e);
        }

        ahoCorasick.build(dictionary);
    }

    @Override
    public boolean rebuild(@NotNull List<String> dictionary) {
        try {
            riskDictionaryMapper.updateDictionary(dictionary);
            AhoCorasick ahoCorasick = new NormalAhoCorasick();
            ahoCorasick.build(dictionary);
            this.ahoCorasick = ahoCorasick;
            return true;
        } catch (Exception e) {
            log.error("error = ", e);
            return false;
        }
    }

    @Override
    public boolean isRisky(String text) {
        List<String> result = this.ahoCorasick.match(text);
        return !result.isEmpty();
    }
}
