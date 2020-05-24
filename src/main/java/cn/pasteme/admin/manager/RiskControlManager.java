package cn.pasteme.admin.manager;

import cn.pasteme.admin.dto.RiskCheckResultDTO;
import cn.pasteme.admin.enumeration.RiskCheckResultType;
import cn.pasteme.algorithm.pair.Pair;
import cn.pasteme.common.utils.result.Response;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 风控接口
 *
 * @author Lucien
 * @version 1.2.2
 */
public interface RiskControlManager {

    /**
     * 对 text 进行风险扫描
     *
     * @param text 文本
     * @return 扫描结果
     */
    Response<List<Pair<String, Long>>> riskCheck(@NotNull String text);

    /**
     * 根据 key 进行风险扫描，结果会持久化至 DB
     *
     * @param key Paste 主键
     * @return 扫描结果
     */
    Response riskCheck(@NotNull Long key);

    /**
     * 重构 AhoCorasick
     *
     * @param dictionary 字典
     * @return boolean
     */
    Response setRiskDictionary(@NotNull List<String> dictionary);

    /**
     * 文本分词计数
     *
     * @param text 文本
     * @return 计数结果
     */
    Response<List<Pair<String, Long>>> tokenCount(@NotNull String text);

    /**
     * 文本分词计数并持久化到 DB
     *
     * @param key 文本
     * @return boolean
     */
    Response tokenCount(@NotNull Long key);

    /**
     * 设置 nlp stop words
     *
     * @param stopWords stop words
     * @return boolean
     */
    Response setStopWords(@NotNull List<String> stopWords);

    /**
     * 分页获取 Check 结果
     *
     * @param pageIndex 页下标
     * @param pageSize 一页的大小
     * @param type 结果类型
     * @return List
     */
    Response<List<RiskCheckResultDTO>> getCheckResult(@NotNull Long pageIndex,
                                                      @NotNull Long pageSize,
                                                      @NotNull RiskCheckResultType type);

    /**
     * 获取某类结果的数量
     *
     * @param type 类别
     * @return 数量
     */
    Response<Long> count(@NotNull RiskCheckResultType type);

    /**
     * 文本分类
     * @param key 主键
     * @return 0 或 1，0 代表 Normal，1 代表 Risk
     */
    Response<Integer> classify(@NotNull Long key);

    /**
     * 异步文本分类
     *
     * @param key 主键
     * @return 没有返回值
     */
    Response<Void> asyncClassify(@NotNull Long key);
}
