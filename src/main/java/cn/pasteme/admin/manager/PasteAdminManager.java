package cn.pasteme.admin.manager;

import cn.pasteme.admin.enumeration.RiskStateDoState;
import cn.pasteme.admin.enumeration.RiskStateDoType;

/**
 * @author Lucien
 * @version 1.2.2
 */
public interface PasteAdminManager {

    /**
     * 用户访问了 Paste
     *
     * @param key 主键
     * @param ip IP
     * @return boolean
     */
    boolean accessKey(Long key, String ip);

    /**
     * 改变 Paste 的类型和状态
     *
     * @param key 主键
     * @param type 类型
     * @param state 状态
     * @return boolean
     */
    boolean changeTypeAndStateByKey(Long key, RiskStateDoType type, RiskStateDoState state);
}
