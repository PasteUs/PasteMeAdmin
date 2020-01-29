package generate;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * pasteme_admin_announce
 * @author 
 */
@Data
public class PastemeAdminAnnounce implements Serializable {
    private Long id;

    private String title;

    private String content;

    private String link;

    private Integer type;

    /**
     * 创建时间
     */
    private Date date;

    private static final long serialVersionUID = 1L;
}