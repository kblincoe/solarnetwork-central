/* ==================================================================
 * MigratorConfig.java - Nov 22, 2013 2:19:16 PM
 * 
 * Copyright 2007-2013 SolarNetwork.net Dev Team
 * 
 * This program is free software; you can redistribute it and/or 
 * modify it under the terms of the GNU General Public License as 
 * published by the Free Software Foundation; either version 2 of 
 * the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License 
 * along with this program; if not, write to the Free Software 
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 
 * 02111-1307 USA
 * ==================================================================
 */

package net.solarnetwork.central.cassandra;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Configuration for {@link Migrator} app.
 * 
 * @author matt
 * @version 1.0
 */
@Configuration
@Import({ JdbcConfig.class, CassandraConfig.class })
public class MigratorConfig {

	@Autowired
	private JdbcConfig jdbcConfig;

	@Autowired
	private CassandraConfig cassandraConfig;

	@Value("${task.threads}")
	private Integer taskThreads;

	@Value("${datum.maxProcess}")
	private Integer maxProcess;

	private void setupMigrateDatumSupport(MigrateDatumSupport t) {
		t.setCluster(cassandraConfig.cassandraCluster());
		t.setJdbcOperations(jdbcConfig.jdbcOperations());
		t.setMaxResults(maxProcess);
	}

	private MigrateConsumptionDatum migrateConsumptionDatum() {
		MigrateConsumptionDatum t = new MigrateConsumptionDatum();
		setupMigrateDatumSupport(t);
		return t;
	}

	private MigratePowerDatum migratePowerDatum() {
		MigratePowerDatum t = new MigratePowerDatum();
		setupMigrateDatumSupport(t);
		return t;
	}

	private MigratePriceDatum migratePriceDatum() {
		MigratePriceDatum t = new MigratePriceDatum();
		setupMigrateDatumSupport(t);
		return t;
	}

	private MigrateWeatherDatum migrateWeatherDatum() {
		MigrateWeatherDatum t = new MigrateWeatherDatum();
		setupMigrateDatumSupport(t);
		return t;
	}

	@Bean
	public Migrator migrator() throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		List<MigrationTask> tasks = new ArrayList<MigrationTask>();
		tasks.add(migrateConsumptionDatum());
		tasks.add(migratePowerDatum());
		tasks.add(migratePriceDatum());
		tasks.add(migrateWeatherDatum());
		Migrator m = new Migrator(cassandraConfig.cassandraCluster(), executorService(), tasks);
		return m;
	}

	@Bean
	public ExecutorService executorService() {
		return Executors.newFixedThreadPool(taskThreads);
	}
}