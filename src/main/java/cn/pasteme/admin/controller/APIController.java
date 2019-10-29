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
 * @author Lucien
 * @version 1.0.0
 */
@Slf4j
@RestController
@RequestMapping(path = "/api")
public class APIController {

    private PasteAdminManager pasteAdminManager;

    public APIController(PasteAdminManager pasteAdminManager) {
        this.pasteAdminManager = pasteAdminManager;
    }

    @RequestMapping(path = "/access/{key}", method = RequestMethod.POST)
    public Response increase(@PathVariable Long key) {
        return pasteAdminManager.access(key) ? Response.success(null) : Response.error(ResponseCode.SERVER_ERROR);
    }
}
