plugins {
	id("sample.java-convention")
	alias(libs.plugins.spring.boot)
}

dependencies {
	implementation(projects.sampleSupportHttpsession)
	implementation(libs.spring.boot.starter.actuator)
	implementation(libs.spring.boot.starter.jdbc)
	implementation(libs.spring.boot.starter.mustache)
	implementation(libs.spring.boot.starter.security)
	implementation(libs.spring.boot.starter.web)
	implementation(libs.spring.session.jdbc)

	runtimeOnly(libs.hikaricp)
	runtimeOnly(libs.jdbc.db2)
	runtimeOnly(libs.jdbc.mariadb)
	runtimeOnly(libs.jdbc.mysql)
	runtimeOnly(libs.jdbc.oracle)
	runtimeOnly(libs.jdbc.postgresql)
	runtimeOnly(libs.jdbc.sqlserver)

	testImplementation(projects.sampleSupportTest)
	testImplementation(libs.spring.boot.starter.test)
}
