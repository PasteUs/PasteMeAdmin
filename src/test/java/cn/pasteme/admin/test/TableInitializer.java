package cn.pasteme.admin.test;

import cn.pasteme.admin.mapper.TableMapper;

/**
 * @author Lucien
 * @version 1.0.0
 */
public class TableInitializer {
    public static void init(TableMapper tableMapper) {
        tableMapper.createPasteMeAdminRiskState();
        tableMapper.createPasteMeAdminDictionary();
        tableMapper.createPasteMeAdminAccessCount();
        tableMapper.createPasteMeAdminRiskCheckResult();
    }
}
