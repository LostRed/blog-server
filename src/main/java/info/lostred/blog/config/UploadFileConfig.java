package info.lostred.blog.config;

import info.lostred.blog.properties.UploadFileProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;

/**
 * <p>文件上传配置</p>
 *
 * @author lostred
 * @since 2021-02-03
 */
@Configuration
@EnableConfigurationProperties(UploadFileProperties.class)
public class UploadFileConfig implements WebMvcConfigurer {
    @Resource
    private UploadFileProperties uploadFileProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(uploadFileProperties.getStaticAccessPath())
                .addResourceLocations("file:/" + uploadFileProperties.getUploadFolder());
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation(uploadFileProperties.getUploadFolder());
        // 单次请求最大上传文件大小
        factory.setMaxRequestSize(DataSize.ofMegabytes(10));
        return factory.createMultipartConfig();
    }
}
