package cn.pasteme.admin.manager;

import cn.pasteme.admin.dto.AnnounceRequestDTO;
import cn.pasteme.admin.entity.AnnounceDO;
import cn.pasteme.common.utils.result.Response;

import javax.validation.Valid;
import java.util.List;


/**
 *
 * @author Acerkoo
 * @version 1.0.1
 */
public interface AnnouncementManager {

    /**
     * 新增一条 Announcement
     *
     * @param announceRequestDTO 新增 Announcement 的信息
     * @return 是否插入成功
     */
    boolean createAnnouncement(@Valid AnnounceRequestDTO announceRequestDTO);

    /**
     * 删除指定 Announcement
     *
     * @param id 主键
     * @return 是否删除成功
     */
    boolean deleteAnnouncement(Long id);

    /**
     * 修改指定 Announcement
     *
     * @param id 主键
     * @param announceRequestDTO 修改后的信息
     * @return 是否更新成功
     */
    boolean updateAnnouncement(Long id, @Valid AnnounceRequestDTO announceRequestDTO);

    /**
     * 查询指定 pageSize 后的页数，向上取整
     *
     * @param pageSize 每页数量
     * @return 公告以 pageSize 为页大小的页数
     */
    int countPage(int pageSize);

    /**
     * 查询第 page 页的内容
     *
     * @param page 页码
     * @param pageSize 每页数量
     * @return 指定页码的公告信息列表
     */
    List<AnnounceDO> getAnnouncement(int page, int pageSize);
}
