plugins {
	`java-library`
	id("sample.java-convention")
}

dependencies {
	implementation(libs.reactor.core)
	implementation(libs.spring.framework.context)
	implementation(libs.spring.framework.web)
	implementation(libs.spring.security.config)
	implementation(libs.spring.security.web)
	implementation(libs.spring.session.core)
}
