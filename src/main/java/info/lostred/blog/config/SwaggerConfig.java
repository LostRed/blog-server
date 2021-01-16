package info.lostred.blog.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * <p>Swagger配置</p>
 *
 * @author lostred
 * @since 2020-12-25
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {
    /**
     * <p>配置Swagger的Docket对象，并加入Spring容器</p>
     *
     * @return Docket对象
     */
    @Bean
    public Docket createRestApi(Environment env) {
        // 设置要显示swagger的环境
        Profiles of = Profiles.of("dev", "test");
        // 判断当前是否处于该环境
        // 通过enable()接收此参数判断是否要显示
        boolean flag = env.acceptsProfiles(of);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //配置是否启用Swagger，如果是false，在浏览器将无法访问
                .enable(flag)
                //通过.select()方法，去配置扫描接口
                .select()
                //RequestHandlerSelectors配置如何扫描接口
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                //配置如何通过path过滤,即这里只扫描请求以/blog开头的接口
                .paths(PathSelectors.ant("/blog/**"))
                .build();
    }

    /**
     * <p>配置Swagger的ApiInfo对象</p>
     *
     * @return ApiInfo对象
     */
    private ApiInfo apiInfo() {
        Contact contact = new Contact("LostRed", "https://github.com/LostRed", "");
        return new ApiInfo(
                "博客后台管理系统",
                "博客后台管理系统的API接口。",
                "1.0",
                "https://github.com/LostRed",
                contact,
                "Apache 2.0 许可",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()
        );
    }
}
