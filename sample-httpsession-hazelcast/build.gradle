plugins {
	id("sample.java-convention")
	alias(libs.plugins.spring.boot)
}

dependencies {
	implementation(projects.sampleSupportHttpsession)
	implementation(libs.spring.boot.starter.actuator)
	implementation(libs.spring.boot.starter.mustache)
	implementation(libs.spring.boot.starter.security)
	implementation(libs.spring.boot.starter.web)
	implementation(libs.spring.session.hazelcast)

	testImplementation(projects.sampleSupportTest)
	testImplementation(libs.spring.boot.starter.test)
}
