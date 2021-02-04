package info.lostred.blog.controller;

import info.lostred.blog.dto.Response;
import info.lostred.blog.properties.UploadFileProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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
@Api(tags = "文件上传模块")
@RestController
@RequestMapping("/blog/file")
public class FileController {
    @Resource
    private UploadFileProperties uploadFileProperties;

    @ApiOperation("上传文件")
    @PostMapping("/upload")
    public Response<Object> upload(@RequestPart("file") MultipartFile file) {
        if (file == null || file.getOriginalFilename() == null) {
            return Response.paramError("上传文件不能为空");
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
        String url = savePath + subPath + "/" + saveFilename;
        return Response.ok(url);
    }
}
