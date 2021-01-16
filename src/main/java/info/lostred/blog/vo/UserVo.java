package info.lostred.blog.vo;

import info.lostred.blog.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserVo extends User {
    private String status;

    @Override
    public String toString() {
        return super.toString() + "+UserVo(" +
                "status='" + status + '\'' +
                ')';
    }
}
