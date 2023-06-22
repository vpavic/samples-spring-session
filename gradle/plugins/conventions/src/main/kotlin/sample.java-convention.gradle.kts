plugins {
	java
}

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(properties.getOrDefault("toolchainVersion", 17).toString()))
	}
}

repositories {
	mavenCentral()
	maven {
		url = uri("https://repo.spring.io/milestone/")
	}
	maven {
		url = uri("https://repo.spring.io/snapshot/")
	}
}

// https://github.com/gradle/gradle/issues/15383
val libs = the<VersionCatalogsExtension>().named("libs")
dependencies {
	implementation(platform(libs.findLibrary("spring.boot.dependencies").get()))
}

testing {
	suites {
		@Suppress("UNUSED_VARIABLE", "UnstableApiUsage")
		val test by getting(JvmTestSuite::class) {
			useJUnitJupiter()
		}
	}
}
