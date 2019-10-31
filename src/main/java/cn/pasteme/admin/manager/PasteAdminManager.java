package cn.pasteme.admin.manager;

/**
 * @author Lucien
 * @version 1.1.0
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
}
