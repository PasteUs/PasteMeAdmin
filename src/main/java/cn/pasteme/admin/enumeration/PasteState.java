package cn.pasteme.admin.enumeration;

import lombok.Getter;

/**
 * PasteAdminDO.type 值域
 * 可用于 supervise
 *
 * @author Lucien
 * @version 1.0.0
 */
public enum PasteState implements PasteEnum {

    CHECKED(0), UNCHECKED(1008611), NEED_CHECK(2);

    @Getter
    private int value;

    PasteState(int value) {
        this.value = value;
    }
}
