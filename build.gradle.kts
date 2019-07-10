buildscript {
	extra.set("profile", properties.getOrDefault("profile", "redis"))
}

plugins {
	base
	id("io.spring.dependency-management") version "1.0.8.RELEASE" apply false
	id("org.springframework.boot") version "2.1.6.RELEASE" apply false
}

subprojects {
	apply(plugin = "java")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "org.springframework.boot")

	configure<JavaPluginConvention> {
		sourceCompatibility = JavaVersion.VERSION_11
	}

	repositories {
		mavenCentral()
	}

	dependencies {
		"implementation"("org.springframework.boot:spring-boot-starter-actuator")
		"implementation"("org.springframework.boot:spring-boot-starter-mustache")
		"implementation"("org.springframework.boot:spring-boot-starter-security")

		"implementation"("org.springframework.session:spring-session-core")

		"testImplementation"("org.springframework.boot:spring-boot-starter-test")
	}
}
