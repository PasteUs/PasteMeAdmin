package cn.pasteme.admin.manager.annouce.impl;

import cn.pasteme.admin.entity.AnnounceDO;
import cn.pasteme.admin.manager.annouce.AnnounceManager;
import cn.pasteme.admin.mapper.AnnounceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Acerkoo
 * @version 1.0.0
 */

@Slf4j
@Service
public class AnnounceManagerImpl implements AnnounceManager {

    @Autowired
    private AnnounceMapper announceMapper;

    @Override
    public boolean postAnnouncement(String title, String content, String link, int type) {
        Date date = new Date();
        return announceMapper.postAnnouncement(title, content, link, type, date);
    }

    @Override
    public boolean deleteAnnouncement(int id) {
        return announceMapper.deleteAnnouncement(id);
    }

    @Override
    public boolean putAnnouncement(Long id, String title, String content, String link, int type) {
        Date date = new Date();
        return announceMapper.putAnnouncement(id, title, content,
                                                 link, type, date);
//        return false;
    }

    @Override
    public List<AnnounceDO> getAll() {
        try {
            return announceMapper.getAll();
        } catch (Exception e) {
            log.error("Announce All error = ", e);
        }
        return new ArrayList<>();
    }

    @Override
    public List<AnnounceDO> getAnnouncement(int left, int right) {

        if (left >= right) {
            log.error("When left >= right");
        } else {
            try {
                return announceMapper.getAnnouncementByLR(left, right - 1);
            } catch (Exception e) {
                log.error("Get Announcement [left, right), error = ", e);
            }
        }
        return new ArrayList<>();
    }
}
