package com.github.serserser.springwebapp.db;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Component
public class FlywayDBUpdater {

    private static final Logger logger = LoggerFactory.getLogger(FlywayDBUpdater.class);

    private DataSource datasource;

    @Autowired
    public FlywayDBUpdater(DataSource datasource) {
        this.datasource = datasource;
    }

    @PostConstruct
    private void updateDB() {
        logger.info("Starting db upgrade with Flyway");
        Flyway flyway = new Flyway();
        flyway.setLocations("classpath:/db/migrations");
        flyway.setTable("T_UPGRADES");
        flyway.setDataSource(datasource);
        flyway.setSqlMigrationPrefix("v_");
        flyway.baseline();
        flyway.migrate();
        logger.info("Successfully finished db upgrade");
    }
}
