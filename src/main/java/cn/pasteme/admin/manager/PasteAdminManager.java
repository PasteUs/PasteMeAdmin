package cn.pasteme.admin.manager;

import cn.pasteme.admin.enumeration.PasteState;
import cn.pasteme.admin.enumeration.PasteType;

/**
 * @author Lucien
 * @version 1.2.0
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
    boolean changeTypeAndStateByKey(Long key, PasteType type, PasteState state);
}
