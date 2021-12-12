//package com.javaee.framework.configuration;
//
//@Configuration
//@EnableSwagger2 //开启Swagger2
//public class Swagger2 {
//
//    //是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置
//    @Value(value = "${swagger.enabled}")
//    Boolean swaggerEnabled;
//    @Bean
//    public Docket createRestApi(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.xxxxxx.xxxxx")) //你的项目基础包名
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo apiInfo(){
//        return new ApiInfoBuilder()
//                .title("标题")
//                .description("api接口文档")
//                .version("1.0") //版本
//                .build();
//    }
//
//}
//
