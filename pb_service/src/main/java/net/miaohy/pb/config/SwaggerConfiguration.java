package net.miaohy.pb.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import net.miaohy.pb.common.utils.JwtTokenUtil;
import net.miaohy.pb.common.utils.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
@Profile({"dev", "test"})
public class SwaggerConfiguration {

    /**
     * 按照模块分注解
     * swagger-bootstrap-ui github地址 ：https://github.com/xiaoymin/swagger-bootstrap-ui/blob/master/README_zh.md
     * 使用说明：https://www.jianshu.com/p/f30e0c646c63
     * @return
     */
    @Bean
    public Docket createRestShoptemApi() {
        return createDocker("modules","net.liangyihui.dtbos.modules.controller");
    }
    private Docket createDocker(String groupName,String basePackage) {
        return new Docket(DocumentationType.SWAGGER_2).groupName(groupName)
                .apiInfo(createDegaultApiInfo())
                .globalOperationParameters(setHeaderToken())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any()) //过滤的接口
                .build();
    }

    private ApiInfo createDegaultApiInfo() {
        return createApiInfo("Swagger2Config 构建RESTful API","DOCBOS API 描述","1.0");
    }

    private ApiInfo createApiInfo(String title,String description,String version) {
        return new ApiInfoBuilder()
                .title(title) //大标题
                .description(description) //详细描述
                .contact(new Contact("良医汇", "http://www.liangyihui.net/", " jinma.xu@liangyihui.net ")) //作者
                .version(version)
                .build();
    }

    private List<Parameter> setHeaderToken() {
        List<Parameter> pars = new ArrayList<>();

        // token请求头
        String testTokenValue = JwtUtil.generateToken("mage", "666666", Duration.ofSeconds(24*60*60*1000));
        System.out.println("----------"+testTokenValue+"-----------");
        ParameterBuilder tokenPar = new ParameterBuilder();
        Parameter tokenParameter = tokenPar
                .name(JwtTokenUtil.getTokenName())
                .description("Token Request Header")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .defaultValue(testTokenValue)
                .build();
        pars.add(tokenParameter);
        return pars;
    }
}
