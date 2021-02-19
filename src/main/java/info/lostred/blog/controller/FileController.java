package info.lostred.blog.controller;

import info.lostred.blog.dto.Response;
import info.lostred.blog.properties.UploadFileProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

/**
 * <p>
 * 文件上传 前端控制器
 * </p>
 *
 * @author lostred
 * @since 2021-01-16
 */
@Api(tags = "文件模块")
@RestController
@RequestMapping("/blog/file")
public class FileController {
    @Resource
    private UploadFileProperties uploadFileProperties;

    @ApiOperation("上传文件")
    @PostMapping("/upload")
    public Response<Object> upload(@ApiIgnore HttpServletRequest request, @RequestPart("file") MultipartFile file) {
        if (file == null || file.getOriginalFilename() == null) {
            return Response.paramError("上传文件不能为空!");
        }
        String savePath = uploadFileProperties.getUploadFolder();
        String filename = file.getOriginalFilename();
        String subPath = "";
        Calendar calendar = Calendar.getInstance();
        subPath += calendar.get(Calendar.YEAR);
        subPath += "/" + (calendar.get(Calendar.MONTH) + 1);
        subPath += "/" + calendar.get(Calendar.DATE);
        String[] filenames = filename.split("\\.");
        String suffix = filenames[filenames.length - 1];
        File dir = new File(savePath + subPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String uuid = UUID.randomUUID().toString();
        String saveFilename = uuid + "." + suffix;
        try {
            file.transferTo(new File(savePath + subPath, saveFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String staticAccessPath = uploadFileProperties.getStaticAccessPath();
        String staticPath = staticAccessPath.substring(0, staticAccessPath.lastIndexOf("/") + 1);
        String host = request.getScheme() + "://" + request.getLocalAddr() + ":" + request.getLocalPort();
        String url = host + staticPath + subPath + "/" + saveFilename;
        return Response.ok(url);
    }

    @ApiOperation("删除文件")
    @ApiImplicitParam(name = "filePath", value = "文件路径", required = true)
    @DeleteMapping("/delete")
    public Response<Object> delete(@ApiIgnore HttpServletRequest request, String filePath) {
        if (filePath == null) {
            return Response.paramError("文件路径不能为空!");
        }
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        if (!filePath.contains(host)) {
            return Response.serviceError("文件删除失败!");
        }
        filePath = filePath.replace(host, "");
        String staticAccessPath = uploadFileProperties.getStaticAccessPath();
        String staticPath = staticAccessPath.substring(0, staticAccessPath.lastIndexOf("/") + 1);
        if (!filePath.contains(staticPath)) {
            return Response.serviceError("文件删除失败!");
        }
        filePath = filePath.substring(filePath.indexOf("/"));
        filePath = filePath.substring(staticPath.length());
        String savePath = uploadFileProperties.getUploadFolder();
        File file = new File(savePath + filePath);
        //判断目录或者文件是否存在且是否为文件
        boolean flag = false;
        if (file.exists() && file.isFile()) {
            flag = file.delete();
        }
        if (!flag) {
            return Response.serviceError("文件删除失败!");
        }
        return Response.ok();
    }
}
