package cn.pasteme.admin.controller;

import cn.pasteme.admin.manager.PasteAdminManager;
import cn.pasteme.common.utils.result.Response;
import cn.pasteme.common.utils.result.ResponseCode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * API 请求
 *
 * @author Lucien
 * @version 1.1.0
 */
@Slf4j
@RestController
@RequestMapping(path = "/api")
public class APIController {

    private PasteAdminManager pasteAdminManager;

    public APIController(PasteAdminManager pasteAdminManager) {
        this.pasteAdminManager = pasteAdminManager;
    }

    @RequestMapping(path = "/paste/{key}", method = RequestMethod.PATCH)
    public Response increase(@PathVariable Long key) {
        return pasteAdminManager.increaseCountByKey(key) ? Response.success(null) : Response.error(ResponseCode.SERVER_ERROR);
    }

    @RequestMapping(path = "/paste/{key}", method = RequestMethod.PUT)
    public Response create(@PathVariable Long key) {
        return pasteAdminManager.createRecord(key) ? Response.success(null) : Response.error(ResponseCode.SERVER_ERROR);
    }
}
