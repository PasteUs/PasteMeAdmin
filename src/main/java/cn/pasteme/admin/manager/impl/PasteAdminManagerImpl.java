package cn.pasteme.admin.manager.impl;

import cn.pasteme.admin.entity.RiskStateDO;
import cn.pasteme.admin.enumeration.PasteState;
import cn.pasteme.admin.enumeration.PasteType;
import cn.pasteme.admin.manager.PasteAdminManager;
import cn.pasteme.admin.mapper.AccessCountMapper;
import cn.pasteme.admin.mapper.RiskStateMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Lucien
 * @version 1.2.0
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
            log.error("{} key = {}, error = ", key, e);
            return false;
        }
    }

    @Override
    public boolean createRecord(Long key) {
        try {
            RiskStateDO riskStateDO = new RiskStateDO(key);
            return riskStateMapper.insertDO(riskStateDO);
        } catch (Exception e) {
            log.error("{} key = {}, error = ", key, e);
            return false;
        }
    }

    @Override
    public boolean changeTypeAndStateByKey(Long key, PasteType type, PasteState state) {
        try {
            RiskStateDO riskStateDO = riskStateMapper.getDoByKey(key);
            log.warn("riskStateDO = {}", riskStateDO);
            riskStateDO.setType(type);
            riskStateDO.setState(state);
            riskStateMapper.updateDO(riskStateDO);
            return true;
        } catch (Exception e) {
            log.error("error = ", e);
            return false;
        }
    }
}
