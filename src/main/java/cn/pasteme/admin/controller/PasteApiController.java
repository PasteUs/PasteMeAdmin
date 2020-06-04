package cn.pasteme.admin.controller;

import cn.pasteme.admin.bo.PasteAccessCountBO;
import cn.pasteme.admin.dto.AccessCountRequestDTO;
import cn.pasteme.admin.manager.PasteAdminManager;
import cn.pasteme.common.annotation.ErrorLogging;
import cn.pasteme.common.annotation.RequestLogging;
import cn.pasteme.common.manager.PermanentManager;
import cn.pasteme.common.utils.result.Response;
import cn.pasteme.common.utils.result.ResponseCode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Paste 相关的 API 请求
 *
 * @author Lucien, Moyu
 * @version 1.4.2
 */
@Slf4j
@RestController
@RequestMapping(path = "/api/paste")
public class PasteApiController {

    private final PasteAdminManager pasteAdminManager;

    private final PermanentManager permanentManager;

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

    @GetMapping(path = "/count/period/{key}")
    @RequestLogging(withResponse = true)
    @ErrorLogging
    public Response<Integer> countPastePeriod(@PathVariable Long key, @Valid AccessCountRequestDTO accessCountRequestDTO) {
        return pasteAdminManager.countPastePeriod(key, accessCountRequestDTO.getDate(), accessCountRequestDTO.getType());
    }

    @GetMapping(path = "/count/total/{key}")
    @RequestLogging(withResponse = true)
    @ErrorLogging
    public Response<Integer> countPasteTotal(@PathVariable Long key) {
        return pasteAdminManager.countPasteTotal(key);
    }

    @GetMapping(path = "/count/period")
    @RequestLogging(withResponse = true)
    @ErrorLogging
    public Response<Integer> countSitePeriod(@Valid AccessCountRequestDTO accessCountRequestDTO) {
        return pasteAdminManager.countSitePeriod(accessCountRequestDTO.getDate(), accessCountRequestDTO.getType());
    }

    @GetMapping(path = "/count/total")
    @RequestLogging(withResponse = true)
    @ErrorLogging
    public Response<Integer> countSiteTotal() {
        return pasteAdminManager.countSiteTotal();
    }

    @GetMapping(path = "/rank/period")
    @RequestLogging(withResponse = true)
    @ErrorLogging
    public Response<List<PasteAccessCountBO>> rankPastePeriod(@Valid AccessCountRequestDTO accessCountRequestDTO) {
        return pasteAdminManager.rankPastePeriod(accessCountRequestDTO.getDate(), accessCountRequestDTO.getType());
    }

    @GetMapping(path = "/rank/total")
    @RequestLogging(withResponse = true)
    @ErrorLogging
    public Response<List<PasteAccessCountBO>> rankPasteTotal() {
        return pasteAdminManager.rankPasteTotal();
    }
}
