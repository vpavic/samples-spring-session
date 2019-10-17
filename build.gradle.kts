buildscript {
	extra.set("profile", properties.getOrDefault("profile", "redis"))
}

plugins {
	base
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
