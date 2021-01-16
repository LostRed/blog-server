package info.lostred.blog.vo;

import info.lostred.blog.entity.Admin;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "AdminVo对象", description = "管理员")
public class AdminVo extends Admin {
    @ApiModelProperty(value = "状态名")
    private String status;

    @Override
    public String toString() {
        return super.toString() + "+AdminVo(" +
                "status='" + status + '\'' +
                ')';
    }
}
