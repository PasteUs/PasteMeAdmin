package cn.pasteme.admin.bo;

import lombok.Data;

/**
 * @author Moyu
 * @version 1.0.0
 */
@Data
public class PasteAccessCountBO {

    private Long key;

    private Integer count;

    public PasteAccessCountBO(Long key, Integer count) {
        this.key = key;
        this.count = count;
    }
}
