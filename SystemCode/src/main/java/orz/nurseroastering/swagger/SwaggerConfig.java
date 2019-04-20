package orz.nurseroastering.swagger;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket postsApi() {
        // TODO release here to use below 2 methods config
//		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
//				.apiInfo(apiInfo()).select().paths(postPaths()).build();
        // TODO release here to use default config
        return new Docket(DocumentationType.SWAGGER_2)
                .select()  // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.any()) // 对所有api进行监控
                .paths(PathSelectors.any()) // 对所有路径进行监控
                .build();
    }

    private Predicate<String> postPaths() {
        return or(regex("/solveRostering.*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Nurse Rostering")
                .description("ai.Orz Nurse Rostering Project API")
                .termsOfServiceUrl("https://github.com/guofeng201507/NurseRoastering/tree/master/src/main/java/orz/nurseroastering")
                .contact("").license("ai.Orz")
                .licenseUrl("https://github.com/guofeng201507/NurseRoastering/tree/master/src/main/java/orz/nurseroastering").version("1.0").build();
    }
}
