package cn.pasteme.admin.manager.risk;

import cn.pasteme.algorithm.pair.Pair;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 风控接口
 *
 * @author Lucien
 * @version 1.1.0
 */
public interface RiskControlManager {

    /**
     * 对 text 进行风险扫描
     *
     * @param text 文本
     * @return 扫描结果
     */
    List<Pair<String, Long>> riskCheck(@NotNull String text);

    /**
     * 根据 key 进行风险扫描，结果会持久化至 DB
     *
     * @param key Paste 主键
     * @return 扫描结果
     */
    boolean riskCheck(@NotNull Long key);

    /**
     * 重构 AhoCorasick
     *
     * @param dictionary 字典
     * @return boolean
     */
    boolean rebuild(@NotNull List<String> dictionary);

    /**
     * 文本分词计数
     *
     * @param text 文本
     * @return 计数结果
     */
    List<Pair<String, Long>> tokenCount(@NotNull String text);

    /**
     * 文本分词计数
     *
     * @param key 文本
     * @return boolean
     */
    boolean tokenCount(@NotNull Long key);
}
