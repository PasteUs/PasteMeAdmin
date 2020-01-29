package cn.pasteme.admin.manager.annouce;

import cn.pasteme.admin.entity.AnnounceDO;

import java.util.List;

public interface AnnounceManager {

    boolean postAnnouncement(String title, String content, String link, int type);

    boolean deleteAnnouncement(int id);

    boolean putAnnouncement(Long id, String title, String content, String link, int type);

    List<AnnounceDO> getAll();

    List<AnnounceDO> getAnnouncement(int left, int right);
}
