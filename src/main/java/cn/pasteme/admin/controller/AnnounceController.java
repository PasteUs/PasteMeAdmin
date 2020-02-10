package cn.pasteme.admin.controller;

import cn.pasteme.admin.dto.AnnounceRequestDTO;
import cn.pasteme.admin.entity.AnnounceDO;
import cn.pasteme.admin.manager.annouce.AnnounceManager;
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
@RequestMapping("/api")
public class AnnounceController {

    @Autowired
    private AnnounceManager announceManager;

    @RequestMapping(value = "/announcement", method = RequestMethod.POST)
    boolean createAnnouncement(AnnounceRequestDTO ardto) {
        return announceManager.createAnnouncement(ardto.getTitle(), ardto.getContent(), ardto.getLink(), ardto.getType());
    }

    @RequestMapping(value = "/announcement", method = RequestMethod.DELETE)
    boolean deleteAnnouncement(Long id) {
        return announceManager.deleteAnnouncement(id);
    }

    @RequestMapping(value = "/announcement", method = RequestMethod.PATCH)
    boolean updateAnnouncement(Long id, AnnounceRequestDTO ardto) {
        return announceManager.updateAnnouncement(id, ardto.getTitle(), ardto.getContent(), ardto.getLink(), ardto.getType());
    }

    @RequestMapping(path = "/announcement/page", method = RequestMethod.GET)
    int countPage() {
        return announceManager.countPage();
    }

    @RequestMapping(path = "/announcement", method = RequestMethod.GET)
    List<AnnounceDO> getAnnouncement(int page) {
        return announceManager.getAnnouncement(page);
    }

}
