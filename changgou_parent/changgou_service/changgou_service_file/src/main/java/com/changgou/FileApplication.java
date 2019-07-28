package com.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @作者:qipeiqing
 * @时间:2019/07/27 15:35
 *
 * exclude = {DataSourceAutoConfiguration.class}:这里禁止了DataSource的加载创建。
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaClient
public class FileApplication {

    public static void main(String[] args) {

        SpringApplication.run(FileApplication.class, args);
    }
}
