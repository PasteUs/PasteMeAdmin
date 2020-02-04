package cn.pasteme.admin.manager.annouce;

import cn.pasteme.admin.entity.AnnounceDO;

import java.util.List;


/**
 *
 * @author Acerkoo
 * @version 1.0.0
 */

public interface AnnounceManager {

    boolean postAnnouncement(String title, String content, String link, int type);

    boolean deleteAnnouncement(int id);

    boolean putAnnouncement(Long id, String title, String content, String link, int type);

    int getAll();

    List<AnnounceDO> getAnnouncement(int left, int right);

}
