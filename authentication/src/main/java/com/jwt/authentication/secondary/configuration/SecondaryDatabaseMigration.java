package com.jwt.authentication.secondary.configuration;

import jakarta.annotation.PostConstruct;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SecondaryDatabaseMigration {

    @Autowired
    @Qualifier("secondaryDbDataSource")
    private DataSource secondaryDataSource;

    @Value("${spring.flyway.secondary.locations}")
    private String locations;

    @PostConstruct
    public void secondaryMigration(){
        Flyway.configure().dataSource(secondaryDataSource).
                locations(locations).
                baselineOnMigrate(true).
                load().
                migrate();
    }
}