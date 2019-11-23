package cn.pasteme.admin.controller;

import cn.pasteme.admin.dto.AccessCountRequestDTO;
import cn.pasteme.admin.manager.PasteAdminManager;
import cn.pasteme.common.manager.PermanentManager;
import cn.pasteme.common.utils.result.Response;
import cn.pasteme.common.utils.result.ResponseCode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Paste 相关的 API 请求
 *
 * @author Lucien, Moyu
 * @version 1.4.0
 */
@Slf4j
@RestController
@RequestMapping(path = "/api/paste")
public class PasteApiController {

    private PasteAdminManager pasteAdminManager;

    private PermanentManager permanentManager;

    public PasteApiController(PasteAdminManager pasteAdminManager,
                              PermanentManager permanentManager) {
        this.pasteAdminManager = pasteAdminManager;
        this.permanentManager = permanentManager;
    }

    @RequestMapping(path = "/{key}", method = RequestMethod.GET)
    public Response increase(@PathVariable Long key, HttpServletRequest httpServletRequest) {
        String ip = httpServletRequest.getRemoteAddr();
        return pasteAdminManager.accessKey(key, ip) ? Response.success() : Response.error(ResponseCode.SERVER_ERROR);
    }

    @RequestMapping(path = "/current", method = RequestMethod.GET)
    public Response<Long> current() {
        return permanentManager.getCurrentMaximumKey();
    }

    @GetMapping(path = "/count/{key}")
    public Response countPaste(@PathVariable Long key, AccessCountRequestDTO accessCountRequestDTO) {
        return pasteAdminManager.countPastePeriod(key, accessCountRequestDTO.getDate(), accessCountRequestDTO.getType());
    }
}
