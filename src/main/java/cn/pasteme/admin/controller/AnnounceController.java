package cn.pasteme.admin.controller;

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
@RequestMapping("/api/announcement")
public class AnnounceController {

    @Autowired
    private AnnounceManager announceManager;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    boolean postAnnounce(String title, String content, String link, int type) {
        return announceManager.createAnnouncement(title, content, link, type);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    boolean deleteAnnouncement(Long id) {
        return announceManager.deleteAnnouncement(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PATCH)
    boolean updateAnnouncement(Long id, String title, String content, String link, int type) {
        return announceManager.updateAnnouncement(id, title, content, link, type);
    }

    @RequestMapping(path = "/count/page", method = RequestMethod.GET)
    int countPage() {
        return announceManager.countPage();
    }

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    List<AnnounceDO> getAnnouncement(int page) {
        return announceManager.getAnnouncement(page);
    }

}
