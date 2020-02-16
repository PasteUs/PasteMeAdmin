package cn.pasteme.admin.controller;

import cn.pasteme.admin.dto.AnnounceRequestDTO;
import cn.pasteme.admin.dto.AnnounceResultDTO;
import cn.pasteme.admin.manager.AnnouncementManager;
import cn.pasteme.common.annotation.ErrorLogging;
import cn.pasteme.common.annotation.RequestLogging;
import cn.pasteme.common.utils.result.Response;
import cn.pasteme.common.utils.result.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Announcement 的增删改查
 *
 * @author Acerkoo
 * @version 1.0.1
 */
@Slf4j
@RestController
@RequestMapping("/api/announcement")
public class AnnounceController {

    private final AnnouncementManager announcementManager;

    public AnnounceController(@Autowired AnnouncementManager announcementManager) {
        this.announcementManager = announcementManager;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @RequestLogging(withResponse = true)
    @ErrorLogging
    Response<Void> createAnnouncement(@Valid AnnounceRequestDTO announceRequestDTO) throws Exception {
        return announcementManager.createAnnouncement(announceRequestDTO)? Response.success(): Response.error(ResponseCode.SERVER_ERROR);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @RequestLogging(withResponse = true)
    @ErrorLogging
    Response<Void> deleteAnnouncement(Long id) throws Exception {
        return announcementManager.deleteAnnouncement(id)? Response.success(): Response.error(ResponseCode.SERVER_ERROR);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @RequestLogging(withResponse = true)
    @ErrorLogging
    Response<Void> updateAnnouncement(Long id, @Valid AnnounceRequestDTO announceRequestDTO) throws Exception {
        return announcementManager.updateAnnouncement(id, announceRequestDTO)? Response.success(): Response.error(ResponseCode.PARAM_ERROR);
    }

    @RequestMapping(path = "/page", method = RequestMethod.GET)
    @RequestLogging(withResponse = true)
    @ErrorLogging
    Response<Integer> countPage(int pageSize) throws Exception {
        return Response.success(announcementManager.countPage(pageSize));
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    @RequestLogging(withResponse = true)
    @ErrorLogging
    Response<List<AnnounceResultDTO>> getAnnouncement(int page, int pageSize) throws Exception {
        return Response.success(announcementManager.getAnnouncement(page, pageSize));
    }
}
