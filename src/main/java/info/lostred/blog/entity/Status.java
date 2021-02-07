package info.lostred.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 状态
 * </p>
 *
 * @author lostred
 * @since 2021-01-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Status对象", description = "状态")
public class Status implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "状态名")
    private String name;

    @ApiModelProperty(value = "状态类型")
    private String type;

    @ApiModelProperty(value = "状态值")
    private Integer value;
}
