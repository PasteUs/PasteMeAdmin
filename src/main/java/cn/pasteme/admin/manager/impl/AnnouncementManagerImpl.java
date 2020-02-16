package cn.pasteme.admin.manager.impl;

import cn.pasteme.admin.dto.AnnounceRequestDTO;
import cn.pasteme.admin.dto.AnnounceResultDTO;
import cn.pasteme.admin.entity.AnnounceDO;
import cn.pasteme.admin.enumeration.AnnounceType;
import cn.pasteme.admin.manager.AnnouncementManager;
import cn.pasteme.admin.mapper.AnnouncementMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
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
    public boolean createAnnouncement(@Valid AnnounceRequestDTO announceRequestDTO) throws Exception {
        AnnounceDO announceDO = new AnnounceDO();
        announceDO.setTitle(announceRequestDTO.getTitle());
        announceDO.setContent(announceRequestDTO.getContent());
        announceDO.setCreateTime(new Date());
        announceDO.setUpdateTime(new Date());
        announceDO.setLink(announceRequestDTO.getLink());
        announceDO.setType(AnnounceType.value2Type(announceRequestDTO.getType()));
        return announcementMapper.createAnnouncement(announceDO);
    }

    @Override
    public boolean deleteAnnouncement(Long id) throws Exception {
        return announcementMapper.deleteAnnouncement(id);
    }

    @Override
    public boolean updateAnnouncement(Long id, @Valid AnnounceRequestDTO announceRequestDTO) throws Exception {
        AnnounceDO announceDO = new AnnounceDO();
        announceDO.setId(id);
        announceDO.setTitle(announceRequestDTO.getTitle());
        announceDO.setContent(announceRequestDTO.getContent());
        announceDO.setLink(announceRequestDTO.getLink());
        announceDO.setUpdateTime(new Date());
        announceDO.setType(AnnounceType.value2Type(announceRequestDTO.getType()));
        return announcementMapper.updateAnnouncement(announceDO);
    }

    @Override
    public int countPage(int pageSize) throws Exception {
        //Take up the whole
        return (announcementMapper.countAnnouncement() + pageSize - 1) / pageSize;
    }

    @Override
    public List<AnnounceResultDTO> getAnnouncement(int page, int pageSize) throws Exception {
        List<AnnounceResultDTO> list = announcementMapper.getAnnouncementByPage((page - 1) * pageSize, pageSize);
        log.info("Announcement list = {}", list);
        return list;
    }
}
