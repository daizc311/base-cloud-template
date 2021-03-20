package cn.dreamccc.common.web.swagger;

import cn.dreamccc.common.core.properties.ProjectProperties;
import cn.hutool.core.text.StrFormatter;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * <h2>SwaggerConfig.java</h2>
 *
 * @author Daizc-kl
 * @date 2020/11/24 19:55
 */
@Profile({"dev", "test", "local"})
@EnableSwagger2WebMvc
@EnableKnife4j
@Configuration
public class SwaggerConfig {

    private final ProjectProperties projectProperties;
    private final Environment environment;

    public SwaggerConfig(ProjectProperties projectProperties, Environment environment) {
        this.projectProperties = projectProperties;
        this.environment = environment;
    }

    private String getApplicationDescription() {
        return Optional.ofNullable(projectProperties.getDescription()).orElse("暂无描述");
    }

    private String getApplicationName() {
        return Optional.ofNullable(projectProperties.getName()).orElse("unknown application");
    }

    private String getApplicationVersion() {
        return Optional.ofNullable(projectProperties.getName()).orElse("unknown version");
    }

    private String[] getActiveProfiles() {
        return environment.getActiveProfiles();
    }

    @Bean("docket")
    public Docket docket() {

        // 返回文档摘要信息
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
            .globalOperationParameters(globalOperationParameters())
                ;
    }

    private List<Parameter> globalOperationParameters() {
        String hostAddress = "127.0.0.1";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ignore) {
        }
        return Lists.newArrayList(
                new ParameterBuilder()
                        .name("Authorization")
                        .modelRef(new ModelRef("String"))
                        .parameterType("header")
                        .required(false)
                        .allowEmptyValue(true)
                        .build(),
                new ParameterBuilder()
                        .name("X-REAL-IP")
                        .modelRef(new ModelRef("String"))
                        .parameterType("header")
                        .required(false)
                        .allowEmptyValue(true)
                        .defaultValue(hostAddress)
                        .build()
        );
    }


    // 生成接口信息，包括标题、联系人等
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(StrFormatter.format("[{}]{}", Stream.of(getActiveProfiles()).reduce((s, s2) -> s + "," + s2).orElse("default"), getApplicationName()))
                .description(getApplicationDescription())
                .version(getApplicationVersion())
                .build();
    }

    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeyList = Lists.newArrayListWithExpectedSize(1);
        apiKeyList.add(new ApiKey("Authorization", "认证参数", "header"));
        return apiKeyList;
    }
}
