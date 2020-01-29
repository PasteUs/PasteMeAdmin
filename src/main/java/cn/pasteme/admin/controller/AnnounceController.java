package cn.pasteme.admin.controller;

import cn.pasteme.admin.entity.AnnounceDO;
import cn.pasteme.admin.manager.annouce.AnnounceManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/announcement")
public class AnnounceController {

    @Autowired
    private AnnounceManager announceManager;

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    List<AnnounceDO> getAnnouncement(int left, int right) {
        return announceManager.getAnnouncement(left, right);
    }

    @RequestMapping(value = "/post", method = RequestMethod.GET)
    boolean postAnnounce(String title, String content, String link, String type) {
        System.out.println(title + " " + content + " " + link + " " + type);
        int tp = 0;
        if (type.charAt(0) == '1') {
            tp = 1;
        } else if (type.charAt(0) == '2') {
            tp = 2;
        }
        return announceManager.postAnnouncement(title, content, link, tp);
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    List<AnnounceDO> getAll() {
        return announceManager.getAll();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    boolean deleteAnnouncement(int id) {
        return announceManager.deleteAnnouncement(id);
    }

    @RequestMapping(value = "/put", method = RequestMethod.GET)
    boolean putAnnouncement(Long id, String title, String content, String link, int type) {
        return announceManager.putAnnouncement(id, title, content, link, type);
    }
}
