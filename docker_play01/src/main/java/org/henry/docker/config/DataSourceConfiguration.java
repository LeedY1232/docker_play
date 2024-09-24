package org.henry.docker.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * create at 2019-05-10 00:50
 *
 * @author Russ
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class DataSourceConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfiguration.class);

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;


    @Bean
    public HikariDataSource hikariDataSource() {

        HikariConfig config = new HikariConfig();
        config.addDataSourceProperty("cachePrepStmts", "true");         //是否自定义配置，为true时下面两个参数才生效
        config.addDataSourceProperty("prepStmtCacheSize", "250");       //连接池大小默认25，官方推荐250-500
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");  //单条语句最大长度默认256，官方推荐2048
        config.addDataSourceProperty("useServerPrepStmts", "true");     //新版本MySQL支持服务器端准备，开启能够得到显著性能提升
        config.addDataSourceProperty("useLocalSessionState", "true");
        config.addDataSourceProperty("useLocalTransactionState", "true");
        config.addDataSourceProperty("rewriteBatchedStatements", "true");
        config.addDataSourceProperty("cacheResultSetMetadata", "true");
        config.addDataSourceProperty("cacheServerConfiguration", "true");
        config.addDataSourceProperty("elideSetAutoCommits", "true");
        config.addDataSourceProperty("maintainTimeStats", "false");
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(20);
        config.setMinimumIdle(10);
        config.setMaxLifetime(30 * 60 * 1000);   // 默认 30 min
        config.setIdleTimeout(10 * 60 * 1000);   // 默认 10 min
        config.setConnectionTimeout(20 * 1000); // 默认 30 sec

        logger.info("Init HikariDataSource, url: " + url);
        return new HikariDataSource(config);
    }

    // 事务管理器 manager
    @Bean
    DataSourceTransactionManager DataSourceTransactionManager() {
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(hikariDataSource());
        return manager;
    }
}
