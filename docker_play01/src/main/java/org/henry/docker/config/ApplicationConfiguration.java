package org.henry.docker.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author henry
 * @date 2024/5/24 11:48
 */
@Configuration
@MapperScan(basePackages = {"org.henry.docker.db.mapper"})
@ComponentScan(basePackages = {"org.henry.docker"})
public class ApplicationConfiguration implements WebMvcConfigurer {
}
