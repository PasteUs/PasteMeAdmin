package cn.pasteme.admin.manager;

/**
 * @author Lucien
 * @version 1.0.0
 */
public interface PasteAdminManager {

    /**
     * 代表被访问过
     *
     * @param key 主键
     * @return boolean
     */
    boolean access(Long key);
}
