import org.springframework.boot.gradle.plugin.SpringBootPlugin

apply<SpringBootPlugin>()

dependencies {
	implementation(platform(SpringBootPlugin.BOM_COORDINATES))

	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-mustache")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-webflux")

	testImplementation("org.springframework.boot:spring-boot-starter-test")

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
