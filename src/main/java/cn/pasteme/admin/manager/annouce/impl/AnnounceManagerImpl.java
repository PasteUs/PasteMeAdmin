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

    private List<AnnounceDO> list;

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
    public int getAll() {
        try {
            list = announceMapper.getAll();
            return list.size();
        } catch (Exception e) {
            log.error("Announce All error = ", e);
        }
        return 0;
    }

    @Override
    public List<AnnounceDO> getAnnouncement(int left, int right) {

        if (left >= right) {
            log.error("When left >= right");
        } else {
            try {
                if (list.isEmpty()) getAll();
                return list.subList(left-1, right);
            } catch (Exception e) {
                log.error("Get Announcement [left, right), error = ", e);
            }
        }
        return new ArrayList<>();
    }

}
