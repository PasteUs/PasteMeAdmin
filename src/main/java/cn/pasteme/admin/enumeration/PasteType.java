package cn.pasteme.admin.enumeration;

import lombok.Getter;

/**
 * PasteAdminDO.state 值域
 * 同时也是 label
 *
 * @author Lucien
 * @version 1.0.0
 */
public enum PasteType implements PasteEnum {

    NORMAL(0), CODE(1), TEXT(2), PORN(3);

    @Getter
    private int value;

    PasteType(int value) {
        this.value = value;
    }
}
