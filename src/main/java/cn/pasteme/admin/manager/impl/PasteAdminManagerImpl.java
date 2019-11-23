package cn.pasteme.admin.manager.impl;

import cn.pasteme.admin.bo.PasteAccessCountBO;
import cn.pasteme.admin.entity.RiskCheckDO;
import cn.pasteme.admin.enumeration.RiskStateDoState;
import cn.pasteme.admin.enumeration.RiskStateDoType;
import cn.pasteme.admin.manager.PasteAdminManager;
import cn.pasteme.admin.mapper.AccessCountMapper;
import cn.pasteme.admin.mapper.RiskStateMapper;
import cn.pasteme.admin.util.DateConverter;
import cn.pasteme.admin.util.strategy.date.ConverterDayStart;
import cn.pasteme.admin.util.strategy.date.ConverterMonthStart;
import cn.pasteme.admin.util.strategy.date.ConverterYearStart;
import cn.pasteme.common.annotation.RequestLogging;
import cn.pasteme.common.utils.result.Response;
import cn.pasteme.common.utils.result.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Lucien, Moyu
 * @version 1.3.1
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
    @RequestLogging
    public Response countPastePeriod(Long key, Date date, String type) {
        DateConverter dateConverter = DateConverter.getPeriodConverter(type);
        Date startDate = dateConverter.getStartDate(date);
        Date endDate = dateConverter.getEndDate(date);
        int visitTimes = accessCountMapper.countRecord(key, startDate, endDate);
        log.warn("visitTimes = {}", visitTimes);
        return Response.success(visitTimes);
    }

    @Override
    public Response countPasteTotal(Long key) {
        int visitTimes = accessCountMapper.countRecord(key, null, null);
        log.warn("visitTimes = {}", visitTimes);
        return Response.success(visitTimes);
    }

    @Override
    public Response countSitePeriod(Date date, String type) {
        return this.countPastePeriod(0L, date, type);
    }

    @Override
    public Response countSiteTotal() {
        return this.countPasteTotal(0L);
    }

    @Override
    public Response rankPastePeriod(Date date, String type) {
        DateConverter dateConverter = DateConverter.getPeriodConverter(type);
        Date startDate = dateConverter.getStartDate(date);
        Date endDate = dateConverter.getEndDate(date);
        List<PasteAccessCountBO> pasteAccessCountBOList = accessCountMapper.rankPasteQuantity(startDate, endDate);
        log.warn("pasteAccessCountBOList = {}", pasteAccessCountBOList);
        return Response.success(pasteAccessCountBOList);
    }

    @Override
    public Response rankPasteTotal() {
        List<PasteAccessCountBO> pasteAccessCountBOList = accessCountMapper.rankPasteQuantity(null, null);
        log.warn("pasteAccessCountBOList = {}", pasteAccessCountBOList);
        return Response.success(pasteAccessCountBOList);
    }
}
