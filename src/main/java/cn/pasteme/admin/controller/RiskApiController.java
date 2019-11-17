package cn.pasteme.admin.controller;

import cn.pasteme.admin.dto.RiskCheckResultDTO;
import cn.pasteme.admin.enumeration.RiskCheckResultType;
import cn.pasteme.admin.manager.risk.RiskControlManager;
import cn.pasteme.common.utils.result.Response;
import cn.pasteme.common.utils.result.ResponseCode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 风控相关的 API 请求
 *
 * @author Lucien
 * @version 1.1.0
 */
@Slf4j
@RestController
@RequestMapping(path = "/api/risk")
public class RiskApiController {

    /**
     * 查询 result 时的分页 size
     */
    private Long pageSize = 20L;

    private final RiskControlManager riskControlManager;

    public RiskApiController(RiskControlManager riskControlManager) {
        this.riskControlManager = riskControlManager;
    }

    @RequestMapping(value = "/check/{key}", method = RequestMethod.POST)
    public Response checkByKey(@PathVariable("key") Long key) {
        return riskControlManager.riskCheck(key);
    }

    @RequestMapping(value = "/tokenCount/{key}", method = RequestMethod.POST)
    public Response tokenCountByKey(@PathVariable("key") Long key) {
        return riskControlManager.tokenCount(key);
    }

    @RequestMapping(value = "/tokenCount", method = RequestMethod.GET)
    public Response<List<RiskCheckResultDTO>> getTokenCount(@RequestParam("page") Long pageIndex) {
        return riskControlManager.getCheckResult(pageIndex, pageSize, RiskCheckResultType.TOKEN_COUNT);
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public Response<List<RiskCheckResultDTO>> getCheck(@RequestParam("page") Long pageIndex) {
        return riskControlManager.getCheckResult(pageIndex, pageSize, RiskCheckResultType.KEYWORD_COUNT);
    }
}
