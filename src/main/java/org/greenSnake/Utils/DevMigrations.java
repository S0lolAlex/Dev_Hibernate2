package org.greenSnake.Utils;

import org.flywaydb.core.Flyway;

public class DevMigrations {
    public static void migrate(String url){
        Flyway flyway = Flyway
                .configure()
                .dataSource(url,null,null)
                .load();
        flyway.migrate();
    }
}
