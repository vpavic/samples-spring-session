dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-webflux")

	when (extra.get("profile")) {
		"mongodb" -> {
			implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
			implementation("org.springframework.session:spring-session-data-mongodb")
		}
		"redis" -> {
			implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")
			implementation("org.springframework.session:spring-session-data-redis")
		}
	}

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}
