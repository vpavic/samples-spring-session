dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")

	when (rootProject.extra.get("profile")) {
		"hazelcast" -> {
			implementation("org.springframework.session:spring-session-hazelcast")
		}
		"jdbc" -> {
			implementation("org.springframework.boot:spring-boot-starter-jdbc")
			implementation("org.springframework.session:spring-session-jdbc")

			implementation("com.h2database:h2")
			implementation("com.microsoft.sqlserver:mssql-jdbc")
			implementation("com.zaxxer:HikariCP")
			implementation("mysql:mysql-connector-java")
			implementation("org.apache.derby:derby")
			implementation("org.hsqldb:hsqldb")
			implementation("org.mariadb.jdbc:mariadb-java-client")
			implementation("org.postgresql:postgresql")
		}
		"mongodb" -> {
			implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
			implementation("org.springframework.session:spring-session-data-mongodb")
		}
		else -> {
			implementation("org.springframework.boot:spring-boot-starter-data-redis")
			implementation("org.springframework.session:spring-session-data-redis")
		}
	}
}
