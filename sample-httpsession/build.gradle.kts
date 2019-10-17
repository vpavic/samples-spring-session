import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
	id("org.springframework.boot")
}

dependencies {
	implementation(platform(SpringBootPlugin.BOM_COORDINATES))

	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-mustache")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")

	testImplementation("org.springframework.boot:spring-boot-starter-test")

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
