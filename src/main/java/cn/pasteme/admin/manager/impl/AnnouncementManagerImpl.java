package cn.pasteme.admin.manager.impl;

import cn.pasteme.admin.dto.AnnounceRequestDTO;
import cn.pasteme.admin.entity.AnnounceDO;
import cn.pasteme.admin.enumeration.AnnounceType;
import cn.pasteme.admin.manager.AnnouncementManager;
import cn.pasteme.admin.mapper.AnnouncementMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Acerkoo
 * @version 1.0.1
 */
@Slf4j
@Service
public class AnnouncementManagerImpl implements AnnouncementManager {

    private final AnnouncementMapper announcementMapper;

    public AnnouncementManagerImpl(@Autowired AnnouncementMapper announcementMapper) {
        this.announcementMapper = announcementMapper;
    }

    @Override
    public boolean createAnnouncement(@Valid AnnounceRequestDTO announceRequestDTO) {
        try {
            AnnounceDO announceDO = new AnnounceDO();
            announceDO.setTitle(announceRequestDTO.getTitle());
            announceDO.setContent(announceRequestDTO.getContent());
            announceDO.setTime(new Date());
            announceDO.setLink(announceRequestDTO.getLink());
            announceDO.setType(AnnounceType.value2Type(announceRequestDTO.getType()));
            return announcementMapper.createAnnouncement(announceDO);
        } catch (Exception e) {
            log.error("Create Announcement error = ", e);
        }
        return false;
    }

    @Override
    public boolean deleteAnnouncement(Long id) {
        try {
            return announcementMapper.deleteAnnouncement(id);
        } catch (Exception e) {
            log.error("When delete announcement, error = ", e);
        }
        return false;
    }

    @Override
    public boolean updateAnnouncement(Long id, @Valid AnnounceRequestDTO announceRequestDTO) {
        try {
            AnnounceDO announceDO = new AnnounceDO();
            announceDO.setId(id);
            announceDO.setTitle(announceRequestDTO.getTitle());
            announceDO.setContent(announceRequestDTO.getContent());
            announceDO.setLink(announceRequestDTO.getLink());
            announceDO.setTime(new Date());
            announceDO.setType(AnnounceType.value2Type(announceRequestDTO.getType()));
            return announcementMapper.updateAnnouncement(announceDO);
        } catch (Exception e) {
            log.error("Update Announcement error = ", e);
        }
        return false;
    }

    @Override
    public int countPage(int pageSize) {
        try {
            //Take up the whole
            return (announcementMapper.countAnnouncement() + pageSize - 1) / pageSize;
        } catch (Exception e) {
            log.error("Announce All error = ", e);
        }
        return 0;
    }

    @Override
    public List<AnnounceDO> getAnnouncement(int page, int pageSize){
        try {
            List<AnnounceDO> list = announcementMapper.getAnnouncementByPage((page - 1) * pageSize, pageSize);
            log.info("Announcement list = {}", list);
            return list;
        } catch (Exception e) {
            log.error("get Announcement, error = ", e);
        }
        return new ArrayList<>();
    }
}
