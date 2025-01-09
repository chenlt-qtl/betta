package betta.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan(value={"betta.mapper"})
public class MybatisPlusConfig {

}
