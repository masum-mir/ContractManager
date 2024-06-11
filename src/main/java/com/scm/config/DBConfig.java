package com.scm.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DBConfig {

    @Bean(name="coreDataSource")
    public DataSource coreDataSource() {
        HikariConfig config = new HikariConfig();
        config.setUsername(EnvConfig.getString(ENV.DB.CORE.USERNAME, "root"));
        config.setPassword(EnvConfig.getString(ENV.DB.CORE.PASSWORD, "root"));
        config.setJdbcUrl(EnvConfig.getString(ENV.DB.CORE.URL, "jdbc:mysql://localhost:3306/scm"));
        config.setDriverClassName(EnvConfig.getString(ENV.DB.CORE.DRIVER, "com.mysql.cj.jdbc.Driver"));

        return new HikariDataSource(config);
    }

    @Bean(name="coreJdbcTemplate")
    public JdbcTemplate coreJdbcTemplate(@Qualifier("coreDataSource") DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

    public NamedParameterJdbcTemplate coreNamedParameterJdbcTemplate(@Qualifier("coreDataSource") DataSource dataSource) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        return namedParameterJdbcTemplate;
    }

}
