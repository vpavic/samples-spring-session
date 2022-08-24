plugins {
	id("sample.java-convention")
	alias(libs.plugins.spring.boot)
}

dependencies {
	implementation(projects.sampleSupportWebsession)
	implementation(libs.spring.boot.starter.data.mongodb.reactive)
	implementation(libs.spring.boot.starter.mustache)
	implementation(libs.spring.boot.starter.security)
	implementation(libs.spring.boot.starter.webflux)
	implementation(libs.spring.session.data.mongodb)

	testImplementation(projects.sampleSupportTest)
	testImplementation(libs.spring.boot.starter.test)
}
