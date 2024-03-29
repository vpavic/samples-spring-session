plugins {
	`java-library`
	id("sample.java-convention")
}

dependencies {
	implementation(libs.jakarta.annotation.api)
	implementation(libs.spring.framework.context)
	implementation(libs.spring.framework.web)
	implementation(libs.spring.security.config)
	implementation(libs.spring.security.web)
	implementation(libs.spring.session.core)
	implementation(libs.slf4j.api)

	compileOnly(libs.jakarta.servlet.api)
}
