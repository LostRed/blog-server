package info.lostred.blog.vo;

import info.lostred.blog.entity.Admin;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AdminVo extends Admin {
    private String status;

    @Override
    public String toString() {
        return super.toString() + "+AdminVo(" +
                "status='" + status + '\'' +
                ')';
    }
}
