package cn.pasteme.admin.manager.impl;

import cn.pasteme.admin.entity.PasteAdminDO;
import cn.pasteme.admin.manager.PasteAdminManager;
import cn.pasteme.admin.mapper.PasteAdminMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Lucien
 * @version 1.1.0
 */
@Slf4j
@Service
public class PasteAdminManagerImpl implements PasteAdminManager {

    private PasteAdminMapper pasteAdminMapper;

    public PasteAdminManagerImpl(PasteAdminMapper pasteAdminMapper) {
        this.pasteAdminMapper = pasteAdminMapper;
    }

    @Override
    public boolean increaseCountByKey(Long key) {
        try {
            return pasteAdminMapper.increaseCountByKey(key);
        } catch (Exception e) {
            log.error("{} key = {}, error = ", key, e);
            return false;
        }
    }

    @Override
    public boolean createRecord(Long key) {
        try {
            PasteAdminDO pasteAdminDO = new PasteAdminDO(key);
            return pasteAdminMapper.insertDO(pasteAdminDO);
        } catch (Exception e) {
            log.error("{} key = {}, error = ", key, e);
            return false;
        }
    }
}
