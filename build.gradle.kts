buildscript {
	extra.set("profile", properties.getOrDefault("profile", "redis"))
}

plugins {
	base
	id("org.springframework.boot") version "2.2.0.RC1" apply false
}

subprojects {
	apply<JavaPlugin>()

	configure<JavaPluginConvention> {
		sourceCompatibility = JavaVersion.VERSION_11
	}

	repositories {
		mavenCentral()
		maven(url = "https://repo.spring.io/libs-milestone")
	}
}
