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
    public boolean createAnnouncement(@Valid AnnounceRequestDTO node) {
        try {
            AnnounceDO announceDO = new AnnounceDO();
            announceDO.setTitle(node.getTitle());
            announceDO.setContent(node.getContent());
            announceDO.setTime(new Date());
            announceDO.setLink(node.getLink());
            announceDO.setType(AnnounceType.value2Type(node.getType()));
            return announceMapper.createAnnouncement(announceDO);
        } catch (Exception e) {
            log.error("Create Announcement error=", e);
        }
        return false;
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
    public boolean updateAnnouncement(Long id, @Valid AnnounceRequestDTO node) {
        try {
            AnnounceDO announceDO = new AnnounceDO();
            announceDO.setId(id);
            announceDO.setTitle(node.getTitle());
            announceDO.setContent(node.getContent());
            announceDO.setLink(node.getLink());
            announceDO.setTime(new Date());
            announceDO.setType(AnnounceType.value2Type(node.getType()));
            return announceMapper.updateAnnouncement(announceDO);
        } catch (Exception e) {
            log.error("updateAnnouncement error=", e);
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
        log.warn("Announcement list = {}", list);
        return Response.success(list);
    }
}
