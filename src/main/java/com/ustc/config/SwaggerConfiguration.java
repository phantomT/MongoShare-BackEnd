package com.ustc.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 田宝宁
 * @date 2022/03/07
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private static final String SPLITOR = ";";

    /**
     * 切割扫描的包生成Predicate<RequestHandler>
     * @param basePackage   basePackage
     * @return  Predicate<RequestHandler>
     */
    public static Predicate<RequestHandler> scanBasePackage(final String basePackage) {
        if (StringUtils.isBlank(basePackage)) {
            throw new NullPointerException("basePackage不能为空，多个包扫描使用" + SPLITOR + "分隔");
        }
        String[] controllerPack = basePackage.split(SPLITOR);
        Predicate<RequestHandler> predicate = null;
        for (int i = controllerPack.length - 1; i >= 0; i--) {
            String strBasePackage = controllerPack[i];
            if (StringUtils.isNotBlank(strBasePackage)) {
                Predicate<RequestHandler> tempPredicate = RequestHandlerSelectors.basePackage(strBasePackage);
                predicate = predicate == null ? tempPredicate : Predicates.or(tempPredicate, predicate);
            }
        }
        if (predicate == null) {
            throw new NullPointerException("basePackage配置不正确，多个包扫描使用" + SPLITOR + "分隔");
        }
        return predicate;
    }

    @Bean
    public Docket createRestApi() {
        //文档类型,Swagger
        return new Docket(DocumentationType.SWAGGER_2)
                // 设置API信息
                .apiInfo(this.apiInfo())
                // 扫描controller, 获取API接口
                .select()
                .apis(scanBasePackage("com.ustc.upload.controller" + SPLITOR +
                        "com.ustc.download.controller" + SPLITOR +
                        "com.ustc.fileCommon.controller" + SPLITOR +
                        "com.ustc.login.controller"))
                .paths(PathSelectors.any())
                // 构建出Docket对象
                .build();
    }

    /**
     * 创建API基本信息
     *
     * @return API信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("上传、下载接口文档")
                .description("上传、下载功能接口文档")
                .version("1.0")
                .build();
    }
}
