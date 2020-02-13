package cn.pasteme.admin.manager.annouce;

import cn.pasteme.admin.dto.AnnounceRequestDTO;
import cn.pasteme.admin.entity.AnnounceDO;
import cn.pasteme.common.utils.result.Response;

import java.util.List;


/**
 *
 * @author Acerkoo
 * @version 1.0.1
 */

public interface AnnounceManager {

    /**
     * 新增一条 Announcement
     *
     * @param announceRequestDTO 新增 Announcement 的信息
     * @return
     */

    boolean createAnnouncement(AnnounceRequestDTO announceRequestDTO);

    /**
     * 删除指定 Announcement
     *
     * @param id 主键
     * @return
     */

    boolean deleteAnnouncement(Long id);

    /**
     * 修改指定 Announcement
     *
     * @param id 主键
     * @param announceRequestDTO 修改后的信息
     * @return
     */
    boolean updateAnnouncement(Long id, AnnounceRequestDTO announceRequestDTO);

    /**
     * 查询指定 pageSize 后的页数，向上取整
     *
     * @param pageSize 每页数量
     * @return
     */
    Response<Integer> countPage(int pageSize);

    /**
     * 查询第 page 页的内容
     *
     * @param page 页码
     * @param pageSize 每页数量
     * @return
     */
    Response<List<AnnounceDO>> getAnnouncement(int page, int pageSize);

}
