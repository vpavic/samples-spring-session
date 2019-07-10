dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")

	when (rootProject.extra.get("profile")) {
		"mongodb" -> {
			implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
			implementation("org.springframework.session:spring-session-data-mongodb")
		}
		else -> {
			implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")
			implementation("org.springframework.session:spring-session-data-redis")
		}
	}
}
