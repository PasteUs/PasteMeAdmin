package cn.pasteme.admin.manager.impl;

import cn.pasteme.admin.entity.RiskCheckDO;
import cn.pasteme.admin.enumeration.RiskStateType;
import cn.pasteme.admin.enumeration.RiskStateState;
import cn.pasteme.admin.manager.PasteAdminManager;
import cn.pasteme.admin.mapper.AccessCountMapper;
import cn.pasteme.admin.mapper.RiskStateMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Lucien
 * @version 1.2.1
 */
@Slf4j
@Service
public class PasteAdminManagerImpl implements PasteAdminManager {

    private RiskStateMapper riskStateMapper;

    private AccessCountMapper accessCountMapper;

    public PasteAdminManagerImpl(RiskStateMapper riskStateMapper, AccessCountMapper accessCountMapper) {
        this.riskStateMapper = riskStateMapper;
        this.accessCountMapper = accessCountMapper;
    }

    @Override
    public boolean increaseCountByKey(Long key) {
        try {
            return accessCountMapper.increaseCountByKey(key);
        } catch (Exception e) {
            log.error("key = {}, error = ", key, e);
            return false;
        }
    }

    @Override
    public boolean createRecord(Long key) {
        try {
            return accessCountMapper.createRecord(key);
        } catch (Exception e) {
            log.error("key = {}, error = ", key, e);
            return false;
        }
    }

    @Override
    public boolean changeTypeAndStateByKey(Long key, RiskStateState type, RiskStateType state) {
        try {
            RiskCheckDO riskCheckDO = riskStateMapper.getDoByKey(key);
            log.warn("riskCheckDO = {}", riskCheckDO);
            riskCheckDO.setType(type);
            riskCheckDO.setState(state);
            riskStateMapper.updateDO(riskCheckDO);
            return true;
        } catch (Exception e) {
            log.error("error = ", e);
            return false;
        }
    }
}
