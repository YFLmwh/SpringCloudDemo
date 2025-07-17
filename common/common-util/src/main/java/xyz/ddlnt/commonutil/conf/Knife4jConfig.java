package xyz.ddlnt.commonutil.conf;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZZULI_SE 210910
 * @data 2025/7/8 20:30
 */
@Configuration
public class Knife4jConfig {

//    /**
//     * @description: 分组1
//     *
//     * @return: org.springdoc.core.models.GroupedOpenApi
//     */
//    @Bean
//    public GroupedOpenApi webApi() {
//        return GroupedOpenApi.builder()
//                .group("分组1")
//                .pathsToMatch("/test/**")
//                .build();
//    }


    /**
     * @description: 接口调试控制台界面信息
     *
     * @return: io.swagger.v3.oas.models.OpenAPI
     */
    @Bean
    public OpenAPI userOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("鲁班微服务demo")
                        .version("1.0")
                        .description("鲁班微服务demoAPI接口文档")
                        .contact(new Contact().name("ZZULI_SE 210910")));
    }
}