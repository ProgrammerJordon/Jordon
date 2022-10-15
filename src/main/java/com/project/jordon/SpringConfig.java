package com.project.jordon;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

public class SpringConfig {
    DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }


}