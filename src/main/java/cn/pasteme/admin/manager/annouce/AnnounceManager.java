package cn.pasteme.admin.manager.annouce;

import cn.pasteme.admin.entity.AnnounceDO;

import java.util.List;


/**
 *
 * @author Acerkoo
 * @version 1.0.0
 */

public interface AnnounceManager {

    boolean createAnnouncement(String title, String content, String link, int type);

    boolean deleteAnnouncement(Long id);

    boolean updateAnnouncement(Long id, String title, String content, String link, int type);

    int countPage();

    List<AnnounceDO> getAnnouncement(int page);

}
