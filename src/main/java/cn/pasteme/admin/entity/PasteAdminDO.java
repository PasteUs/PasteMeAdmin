package cn.pasteme.admin.entity;

import cn.pasteme.admin.enumeration.PasteState;
import cn.pasteme.admin.enumeration.PasteType;

import lombok.Data;

/**
 * Paste Admin 实体
 *
 * @author Lucien
 * @version 1.1.0
 */
@Data
public class PasteAdminDO {

    /**
     * 主键
     */
    private Long key;

    /**
     * 访问计数，代表这个 Paste 被访问的次数
     */
    private Long count;

    /**
     * 这篇 Paste 的类型，同时也是 Classify label
     */
    private PasteType type;

    /**
     * 这篇的状态
     */
    private PasteState state;

    public PasteAdminDO(Long key) {
        this.key = key;
        this.count = 0L;
        this.type = PasteType.NORMAL;
        this.state = PasteState.UNCHECKED;
    }
}
