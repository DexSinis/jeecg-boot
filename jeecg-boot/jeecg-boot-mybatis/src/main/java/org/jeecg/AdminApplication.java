package org.jeecg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(value={"org.jeecg.dao","org.jeecg.module.**.mapper"})
public class AdminApplication  {


	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}
}
