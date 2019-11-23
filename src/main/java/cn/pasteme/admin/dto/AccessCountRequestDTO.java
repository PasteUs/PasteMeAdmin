package cn.pasteme.admin.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author Moyu
 * @version 1.0.0
 */
@Data
public class AccessCountRequestDTO {

    @NotBlank
    private Date date;

    @NotBlank
    private String type;
}
