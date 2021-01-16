package info.lostred.blog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Response对象", description = "服务响应格式")
public class Response<T> {
    @ApiModelProperty(value = "响应状态码")
    private int code;
    @ApiModelProperty(value = "响应消息")
    private String msg;
    @ApiModelProperty(value = "响应数据内容")
    private T data;

    private Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private final static int OK = 200;
    private final static int PARAM_ERROR = 400;
    private final static int VERIFY_ERROR = 401;
    private final static int SERVICE_ERROR = 500;

    public static <T> Response<T> ok() {
        return ok(null);
    }

    public static <T> Response<T> ok(T data) {
        return new Response<>(OK, "请求成功", data);
    }

    public static <T> Response<T> paramError(String msg) {
        return new Response<>(PARAM_ERROR, msg);
    }

    public static <T> Response<T> verifyError(String msg) {
        return new Response<>(VERIFY_ERROR, msg);
    }

    public static <T> Response<T> serviceError(String msg) {
        return new Response<>(SERVICE_ERROR, msg);
    }
}
