package com.jamp.io.model.dbmigration;

import org.flywaydb.core.api.migration.spring.SpringJdbcMigration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Migration script for Flyway to create default used
 * Not used right now as Flyway doesn't meet requirements
 */
public class V2__moveDBStateToV2 implements SpringJdbcMigration {

	@Override
	public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
		jdbcTemplate.execute("INSERT INTO User ('name', 'password') VALUES ('yura', '111')");
		
	}

}
