package cn.pasteme.admin.manager.annouce.impl;

import cn.pasteme.admin.entity.AnnounceDO;
import cn.pasteme.admin.enumeration.AnnounceType;
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

    private int pageSize = 3;

    @Override
    public boolean createAnnouncement(String title, String content, String link, int type) {
        AnnounceDO announceDO = new AnnounceDO();
        announceDO.setTitle(title);
        announceDO.setContent(content);
        announceDO.setTime(new Date());
        announceDO.setLink(link);
        announceDO.setType(type);
        return announceMapper.createAnnouncement(announceDO);
    }

    @Override
    public boolean deleteAnnouncement(Long id) {
        try {
            announceMapper.deleteAnnouncement(id);
            return true;
        } catch (Exception e) {
            log.error("When delete announcement, error = ", e);
        }
        return false;
    }

    @Override
    public boolean updateAnnouncement(Long id, String title, String content, String link, int type) {
        return announceMapper.updateAnnouncement(id, title, content, link, type, new Date());
    }

    @Override
    public int countPage() {
        try {
            //Take up the whole
            return (announceMapper.countAnnouncement() + pageSize - 1) / pageSize;
        } catch (Exception e) {
            log.error("Announce All error = ", e);
        }
        return 0;
    }

    @Override
    public List<AnnounceDO> getAnnouncement(int page) {
        try {
            return announceMapper.getAnnouncementByPage((page-1) * pageSize, pageSize);
        } catch (Exception e) {
            log.error(", error = ", e);
        }
        return new ArrayList<>();
    }

}
