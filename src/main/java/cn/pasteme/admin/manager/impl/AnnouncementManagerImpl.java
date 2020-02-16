package cn.pasteme.admin.manager.impl;

import cn.pasteme.admin.dto.AnnounceRequestDTO;
import cn.pasteme.admin.dto.AnnounceResultDTO;
import cn.pasteme.admin.entity.AnnounceDO;
import cn.pasteme.admin.enumeration.AnnounceType;
import cn.pasteme.admin.manager.AnnouncementManager;
import cn.pasteme.admin.mapper.AnnouncementMapper;
import cn.pasteme.common.utils.converter.DoToDtoConverter;
import cn.pasteme.common.utils.converter.DtoToDoConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
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
        Date date = new Date();
        DtoToDoConverter.convert(announceRequestDTO, announceDO);
        announceDO.setCreateTime(date);
        announceDO.setUpdateTime(date);
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
        DtoToDoConverter.convert(announceRequestDTO, announceDO);
        announceDO.setId(id);
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
        List<AnnounceDO> announceDOList = announcementMapper.getAnnouncementByPage((page - 1) * pageSize, pageSize);
        List<AnnounceResultDTO> announceResultDTOList = new ArrayList<>();
        for (AnnounceDO announceDO: announceDOList) {
            AnnounceResultDTO announceResultDTO = new AnnounceResultDTO();
            DoToDtoConverter.convert(announceDO, announceResultDTO);
            announceResultDTO.setTime(announceDO.getUpdateTime());
            announceResultDTOList.add(announceResultDTO);
        }
        log.info("Announcement list = {}", announceResultDTOList);
        return announceResultDTOList;
    }
}
