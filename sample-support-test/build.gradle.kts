plugins {
	`java-library`
	id("sample.java-convention")
}

dependencies {
	api(platform(libs.testcontainers.bom))
	api(libs.testcontainers.mongodb)
	api(libs.testcontainers.postgresql)

	compileOnly(libs.hikaricp)
	compileOnly(libs.spring.boot.starter.test)
	compileOnly(libs.spring.data.mongodb)
	compileOnly(libs.spring.data.redis)
}
