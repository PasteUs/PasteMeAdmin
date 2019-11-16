package cn.pasteme.admin.controller;

import cn.pasteme.admin.manager.PasteAdminManager;
import cn.pasteme.common.utils.result.Response;
import cn.pasteme.common.utils.result.ResponseCode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Paste 相关的 API 请求
 *
 * @author Lucien
 * @version 1.2.0
 */
@Slf4j
@RestController
@RequestMapping(path = "/api/paste")
public class PasteApiController {

    private PasteAdminManager pasteAdminManager;

    public PasteApiController(PasteAdminManager pasteAdminManager) {
        this.pasteAdminManager = pasteAdminManager;
    }

    @RequestMapping(path = "/{key}", method = RequestMethod.GET)
    public Response increase(@PathVariable Long key, HttpServletRequest httpServletRequest) {
        String ip = httpServletRequest.getRemoteAddr();
        return pasteAdminManager.accessKey(key, ip) ? Response.success() : Response.error(ResponseCode.SERVER_ERROR);
    }
}
