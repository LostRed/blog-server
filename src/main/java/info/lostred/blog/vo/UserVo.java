package info.lostred.blog.vo;

import info.lostred.blog.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UserVo对象", description = "用户")
public class UserVo extends User {
    @ApiModelProperty(value = "状态名")
    private String status;

    @Override
    public String toString() {
        return super.toString() + "+UserVo(" +
                "status='" + status + '\'' +
                ')';
    }
}
