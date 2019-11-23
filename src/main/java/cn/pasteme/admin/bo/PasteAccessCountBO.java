package cn.pasteme.admin.bo;

import lombok.Data;

/**
 * @author Moyu
 * @version 1.0.1
 */
@Data
public class PasteAccessCountBO implements Comparable {

    private Long key;

    private Integer count;

    public PasteAccessCountBO(Long key, Integer count) {
        this.key = key;
        this.count = count;
    }

    @Override
    public int compareTo(Object o) {
        PasteAccessCountBO tmp = (PasteAccessCountBO) o;
        return tmp.count > count ? 1 : (tmp.count.equals(count) ? 0 : -1);
    }
}
