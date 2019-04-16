plugins {
	base
	id("io.spring.dependency-management") version "1.0.7.RELEASE" apply false
	id("org.springframework.boot") version "2.1.4.RELEASE" apply false
}

subprojects {
	extra.set("profile", properties.getOrDefault("profile", "redis"))

	apply(plugin = "java")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "org.springframework.boot")

	configure<JavaPluginConvention> {
		sourceCompatibility = JavaVersion.VERSION_11
	}

	repositories {
		mavenCentral()
	}

	val developmentOnly: Configuration by configurations.creating

	configurations {
		"runtimeClasspath" {
			extendsFrom(configurations["developmentOnly"])
		}
	}

	dependencies {
		"implementation"("org.springframework.boot:spring-boot-starter-actuator")
		"implementation"("org.springframework.boot:spring-boot-starter-mustache")
		"implementation"("org.springframework.boot:spring-boot-starter-security")

		"implementation"("org.springframework.session:spring-session-core")

		developmentOnly("org.springframework.boot:spring-boot-devtools")

		"testImplementation"("org.springframework.boot:spring-boot-starter-test")
	}
}
