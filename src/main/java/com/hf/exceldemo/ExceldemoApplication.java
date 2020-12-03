package com.hf.exceldemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//Spring Boot 使用事务非常简单，首先使用注解 @EnableTransactionManagement 开启事务支持后，然后在访问数据库的Service方法上添加注解 @Transactional 便可。
@SpringBootApplication
@MapperScan("com.hf.exceldemo.dao")//在接口类上添加了@Mapper，在编译之后会生成相应的接口实现类
@EnableTransactionManagement// 启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
public class ExceldemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExceldemoApplication.class, args);
    }

}
