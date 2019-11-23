package cn.pasteme.admin.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Moyu
 * @version 1.0.0
 */
@Data
public class AccessCountRequestDTO {

    @NotNull
    private Date date;

    @NotNull
    private String type;
}
