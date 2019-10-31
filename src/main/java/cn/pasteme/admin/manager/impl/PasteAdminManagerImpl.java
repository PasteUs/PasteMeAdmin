package cn.pasteme.admin.manager.impl;

import cn.pasteme.admin.manager.PasteAdminManager;
import cn.pasteme.admin.mapper.PasteAdminMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Lucien
 * @version 1.0.0
 */
@Slf4j
@Service
public class PasteAdminManagerImpl implements PasteAdminManager {

    private PasteAdminMapper pasteAdminMapper;

    public PasteAdminManagerImpl(PasteAdminMapper pasteAdminMapper) {
        this.pasteAdminMapper = pasteAdminMapper;
    }

    @Override
    public boolean access(Long key) {
        try {
            return pasteAdminMapper.increaseCountByKey(key);
        } catch (Exception e) {
            log.error("{} key = {}, error = ", key, e);
            return false;
        }
    }
}
