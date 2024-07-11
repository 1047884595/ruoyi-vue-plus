package org.dromara.telegram.bo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GroupInfoBo {
    @NotBlank(message = "群名称不能为空")
    private String groupName;


    @NotBlank(message = "群号不能为空")
    private String groupId;
}
