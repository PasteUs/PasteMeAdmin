package cn.pasteme.admin.manager.risk;

import java.util.List;

/**
 * 风控接口
 *
 * @author Lucien
 * @version 1.0.0
 */
public interface RiskControlManager {

    /**
     * 判断文本是否有风险
     *
     * @param text 文本
     * @return boolean
     */
    boolean isRisky(String text);

    /**
     * 重构 AhoCorasick
     *
     * @param dictionary 字典
     * @return boolean
     */
    boolean rebuild(List<String> dictionary);
}
