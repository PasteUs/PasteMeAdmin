package cn.pasteme.admin.manager.impl;

import cn.pasteme.admin.entity.RiskCheckDO;
import cn.pasteme.admin.enumeration.RiskStateDoState;
import cn.pasteme.admin.enumeration.RiskStateDoType;
import cn.pasteme.admin.manager.PasteAdminManager;
import cn.pasteme.admin.mapper.AccessCountMapper;
import cn.pasteme.admin.mapper.RiskStateMapper;
import cn.pasteme.common.utils.result.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    public boolean accessKey(Long key, String ip) {
        try {
            return accessCountMapper.createRecord(key, new Date(), ip);
        } catch (Exception e) {
            log.error("key = {}, error = ", key, e);
            return false;
        }
    }

    @Override
    public boolean changeTypeAndStateByKey(Long key, RiskStateDoType type, RiskStateDoState state) {
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

    @Override
    public Response countPastePeriod(Long key, Date date, String type) {
        return null;
    }

    @Override
    public Response countPasteTotal(Long key) {
        return null;
    }

    @Override
    public Response countSitePeriod(Date date, String type) {
        return null;
    }

    @Override
    public Response countSiteTotal() {
        return null;
    }

    @Override
    public Response rankPastePeriod(Date date, String type) {
        return null;
    }

    @Override
    public Response rankPasteTotal() {
        return null;
    }
}
