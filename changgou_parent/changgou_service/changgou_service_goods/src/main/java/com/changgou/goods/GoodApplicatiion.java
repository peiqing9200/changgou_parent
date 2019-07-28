package com.changgou.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @作者:qipeiqing
 * @时间:2019/07/26 16:51
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.changgou.goods.dao"})  //通用mapper
public class GoodApplicatiion {

    public static void main(String[] args) {
        SpringApplication.run(GoodApplicatiion.class, args);
    }
}
