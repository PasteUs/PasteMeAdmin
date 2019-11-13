package cn.pasteme.admin.controller;

import cn.pasteme.admin.manager.risk.RiskControlManager;
import cn.pasteme.common.utils.result.Response;
import cn.pasteme.common.utils.result.ResponseCode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 风控相关的 API 请求
 *
 * @author Lucien
 * @version 1.0.0
 */
@Slf4j
@RestController
@RequestMapping(path = "/api/risk")
public class RiskApiController {

    private final RiskControlManager riskControlManager;

    public RiskApiController(RiskControlManager riskControlManager) {
        this.riskControlManager = riskControlManager;
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.POST)
    public Response checkByKey(@PathVariable("key") Long key) {
        try {
            return riskControlManager.riskCheck(key);
        } catch (Exception e) {
            log.error("error = ", e);
            return Response.error(ResponseCode.SERVER_ERROR);
        }
    }
}
