package com.jwt.authentication.primary.configuration;

import jakarta.annotation.PostConstruct;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PrimaryDataBaseMigration {

    @Autowired
    @Qualifier("primaryDbDataSource")
    private DataSource primaryDataSource;

    @Value("${spring.flyway.primary.locations}")
    private String locations;

    @PostConstruct
    public void secondaryMigration(){
        Flyway.configure().dataSource(primaryDataSource).
                locations(locations).
                baselineOnMigrate(true).
                load().
                migrate();
    }
}