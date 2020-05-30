package cn.pasteme.admin.manager.impl;

import cn.pasteme.admin.bo.PasteAccessCountBO;
import cn.pasteme.admin.entity.RiskCheckStateDO;
import cn.pasteme.admin.enumeration.AccessDateType;
import cn.pasteme.admin.enumeration.RiskCheckStateTypeEnum;
import cn.pasteme.admin.enumeration.RiskCheckStateEnum;
import cn.pasteme.admin.manager.PasteAdminManager;
import cn.pasteme.admin.mapper.AccessCountMapper;
import cn.pasteme.admin.mapper.RiskStateMapper;
import cn.pasteme.admin.util.DateConverter;
import cn.pasteme.common.annotation.RequestLogging;
import cn.pasteme.common.utils.result.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Lucien, Moyu
 * @version 1.3.2
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
    public boolean changeTypeAndStateByKey(Long key, RiskCheckStateEnum type, RiskCheckStateTypeEnum state) {
        try {
            RiskCheckStateDO riskCheckStateDO = riskStateMapper.getDoByKey(key);
            log.warn("riskCheckStateDO = {}", riskCheckStateDO);
            riskCheckStateDO.setType(type);
            riskCheckStateDO.setState(state);
            riskStateMapper.updateDO(riskCheckStateDO);
            return true;
        } catch (Exception e) {
            log.error("error = ", e);
            return false;
        }
    }

    @Override
    @RequestLogging
    public Response<Integer> countPastePeriod(Long key, Date date, String type) {
        DateConverter dateConverter = AccessDateType.createConverter(type);
        Date startDate = dateConverter.getStartDate(date);
        Date endDate = dateConverter.getEndDate(date);
        int visitTimes = accessCountMapper.countRecord(key, startDate, endDate);
        log.warn("visitTimes = {}", visitTimes);
        return Response.success(visitTimes);
    }

    @Override
    public Response<Integer> countPasteTotal(Long key) {
        int visitTimes = accessCountMapper.countRecord(key, null, null);
        log.warn("visitTimes = {}", visitTimes);
        return Response.success(visitTimes);
    }

    @Override
    public Response<Integer> countSitePeriod(Date date, String type) {
        return this.countPastePeriod(0L, date, type);
    }

    @Override
    public Response<Integer> countSiteTotal() {
        return this.countPasteTotal(0L);
    }

    @Override
    public Response<List<PasteAccessCountBO>> rankPastePeriod(Date date, String type) {
        DateConverter dateConverter = AccessDateType.createConverter(type);
        Date startDate = dateConverter.getStartDate(date);
        Date endDate = dateConverter.getEndDate(date);
        List<PasteAccessCountBO> pasteAccessCountBOList = accessCountMapper.rankPasteQuantity(startDate, endDate);
        log.warn("pasteAccessCountBOList = {}", pasteAccessCountBOList);
        return Response.success(pasteAccessCountBOList);
    }

    @Override
    public Response<List<PasteAccessCountBO>> rankPasteTotal() {
        List<PasteAccessCountBO> pasteAccessCountBOList = accessCountMapper.rankPasteQuantity(null, null);
        log.warn("pasteAccessCountBOList = {}", pasteAccessCountBOList);
        return Response.success(pasteAccessCountBOList);
    }
}
