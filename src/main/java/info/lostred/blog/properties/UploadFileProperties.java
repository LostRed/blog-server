package info.lostred.blog.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "file")
public class UploadFileProperties {
    private String staticAccessPath;
    private String uploadFolder;
    private String host;
}
