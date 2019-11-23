package cn.pasteme.admin.manager;

import cn.pasteme.admin.enumeration.RiskStateDoState;
import cn.pasteme.admin.enumeration.RiskStateDoType;
import cn.pasteme.common.utils.result.Response;

import java.util.Date;

/**
 * @author Lucien, Moyu
 * @version 1.2.2
 */
public interface PasteAdminManager {

    /**
     * 用户访问了 Paste
     *
     * @param key 主键
     * @param ip IP
     * @return boolean
     */
    boolean accessKey(Long key, String ip);

    /**
     * 改变 Paste 的类型和状态
     *
     * @param key 主键
     * @param type 类型
     * @param state 状态
     * @return boolean
     */
    boolean changeTypeAndStateByKey(Long key, RiskStateDoType type, RiskStateDoState state);

    /**
     * 计数单条 Paste 的周期（年/月/日）访问次数
     * 用 DateConverter 将日期根据 type 转换为起始时间
     *
     * @param key 主键
     * @param date 日期
     * @param type 日期类型 年/月/日
     * @return int 单条 Paste 访问次数
     */
    Response countPastePeriod(Long key, Date date, String type);

    /**
     * 计数单条 Paste 的总访问次数
     *
     * @param key 主键
     * @return int 单条 Paste 总访问次数
     */
    Response countPasteTotal(Long key);

    /**
     * 计数站点的周期（年/月/日）访问次数
     * 用 DateConverter 将日期根据 type 转换为起始时间
     *
     * @param date 日期
     * @param type 日期类型 年/月/日
     * @return int Site 访问次数
     */
    Response countSitePeriod(Date date, String type);

    /**
     * 计数站点的总访问次数
     *
     * @return int Site 总访问次数
     */
    Response countSiteTotal();

    /**
     * 按周期（年/月/日）访问次数排名
     * 用 DateConverter 将日期根据 type 转换为起始时间
     *
     * @param date 日期
     * @param type 日期类型 年/月/日
     * @return Paste 及访问数量列表 List<PasteAccessCountBO>
     */
    Response rankPastePeriod(Date date, String type);

    /**
     * 按总访问次数排名
     *
     * @return Paste 及访问数量列表 List<PasteAccessCountBo>
     */
    Response rankPasteTotal();
}
