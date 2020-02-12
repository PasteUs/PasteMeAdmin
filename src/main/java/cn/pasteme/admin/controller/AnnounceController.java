package cn.pasteme.admin.controller;

import cn.pasteme.admin.dto.AnnounceRequestDTO;
import cn.pasteme.admin.entity.AnnounceDO;
import cn.pasteme.admin.manager.annouce.AnnounceManager;
import cn.pasteme.common.annotation.ErrorLogging;
import cn.pasteme.common.annotation.RequestLogging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Annoucement 的增删改查
 *
 * @author Acerkoo
 * @version 1.0.0
 */

@Slf4j
@RestController
@RequestMapping("/api/announcement")
public class AnnounceController {

    @Autowired
    private AnnounceManager announceManager;

    @RequestMapping(value = "", method = RequestMethod.POST)
    boolean createAnnouncement(AnnounceRequestDTO ardto) {
        return announceManager.createAnnouncement(ardto);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    @RequestLogging(withResponse = true)
    @ErrorLogging
    boolean deleteAnnouncement(Long id) {
        return announceManager.deleteAnnouncement(id);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @RequestLogging(withResponse = true)
    @ErrorLogging
    boolean updateAnnouncement(Long id, AnnounceRequestDTO ardto) {
        return announceManager.updateAnnouncement(id, ardto);
    }

    @RequestMapping(path = "/page", method = RequestMethod.GET)
    @RequestLogging(withResponse = true)
    @ErrorLogging
    int countPage(int pageSize) {
        return announceManager.countPage(pageSize);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    List<AnnounceDO> getAnnouncement(int page, int pageSize) {
        return announceManager.getAnnouncement(page, pageSize);
    }

}
