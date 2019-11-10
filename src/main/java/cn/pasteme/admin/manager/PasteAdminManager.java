package cn.pasteme.admin.manager;

import cn.pasteme.admin.enumeration.RiskStateType;
import cn.pasteme.admin.enumeration.RiskStateState;

/**
 * @author Lucien
 * @version 1.2.1
 */
public interface PasteAdminManager {

    /**
     * 代表被访问过
     *
     * @param key 主键
     * @return boolean
     */
    boolean increaseCountByKey(Long key);

    /**
     * 新增一条记录
     *
     * @param key 主键
     * @return boolean
     */
    boolean createRecord(Long key);

    /**
     * 改变 Paste 的类型和状态
     *
     * @param key 主键
     * @param type 类型
     * @param state 状态
     * @return boolean
     */
    boolean changeTypeAndStateByKey(Long key, RiskStateState type, RiskStateType state);
}
