package info.lostred.blog.aspect;

import info.lostred.blog.dto.Response;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>校验参数通知</p>
 *
 * @author lostred
 * @since 2021-02-07
 */
@ControllerAdvice
public class ValidArgsAdvice {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Response<Object> exceptionHandle(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        StringBuilder errorMsg = new StringBuilder();
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            fieldErrors.forEach(error -> {
                System.err.println("errorArg: " + error.getField() + ", errorMsg: " + error.getDefaultMessage());
                errorMsg.append(error.getDefaultMessage()).append("!");
            });
        }
        exception.printStackTrace();
        return Response.paramError(errorMsg.toString());
    }
}
