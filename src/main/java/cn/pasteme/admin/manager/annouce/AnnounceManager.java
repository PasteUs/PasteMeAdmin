package cn.pasteme.admin.manager.annouce;

import cn.pasteme.admin.dto.AnnounceRequestDTO;
import cn.pasteme.admin.entity.AnnounceDO;

import java.util.List;


/**
 *
 * @author Acerkoo
 * @version 1.0.0
 */

public interface AnnounceManager {

    boolean createAnnouncement(AnnounceRequestDTO node);

    boolean deleteAnnouncement(Long id);

    boolean updateAnnouncement(Long id, AnnounceRequestDTO node);

    int countPage(int pageSize);

    List<AnnounceDO> getAnnouncement(int page, int pageSize);

}
