### 概述
    常用注解说明
    1、@repository,@service,@RestControl,@component  注解组件，在spring容器中生成对应单例
    2、@Autowired,@Resource  注解属性，会获取到已经注入到spring容器的单例对象
    3、@Configuration,@Bean,@Scope,@Value    对于一些实体bean对象统一管理获取
    4、@RequestMapping,@RequestParam  url、请求参数获取
    5、@MapperScan({"com.springboot.springinit.dao"})  mybatis使用注解标注sql语句