package cn.pasteme.admin.manager.impl;

import cn.pasteme.admin.dto.AnnounceRequestDTO;
import cn.pasteme.admin.entity.AnnounceDO;
import cn.pasteme.admin.enumeration.AnnounceType;
import cn.pasteme.admin.manager.AnnounceManager;
import cn.pasteme.admin.mapper.AnnounceMapper;
import cn.pasteme.common.utils.result.Response;
import cn.pasteme.common.utils.result.ResponseCode;
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
public class AnnounceManagerImpl implements AnnounceManager {

    private final AnnounceMapper announceMapper;

    public AnnounceManagerImpl(@Autowired AnnounceMapper announceMapper) {
        this.announceMapper = announceMapper;
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
            return announceMapper.createAnnouncement(announceDO);
        } catch (Exception e) {
            log.error("Create Announcement error = ", e);
        }
        return false;
    }

    @Override
    public boolean deleteAnnouncement(Long id) {
        try {
            return announceMapper.deleteAnnouncement(id);
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
            return announceMapper.updateAnnouncement(announceDO);
        } catch (Exception e) {
            log.error("Update Announcement error = ", e);
        }
        return false;
    }

    @Override
    public Response<Integer> countPage(int pageSize) {
        try {
            //Take up the whole
            return Response.success((announceMapper.countAnnouncement() + pageSize - 1) / pageSize);
        } catch (Exception e) {
            log.error("Announce All error = ", e);
        }
        return Response.error(ResponseCode.PARAM_ERROR);
    }

    @Override
    public Response<List<AnnounceDO>> getAnnouncement(int page, int pageSize) {
        List<AnnounceDO> list = announceMapper.getAnnouncementByPage((page-1) * pageSize, pageSize);
        log.info("Announcement list = {}", list);
        return Response.success(list);
    }
}
