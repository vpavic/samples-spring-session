pluginManagement {
	val springBootVersion = "2.2.1.RELEASE"

	resolutionStrategy {
		eachPlugin {
			when (requested.id.id) {
				"org.springframework.boot" -> useModule("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
			}
		}
	}

	repositories {
		gradlePluginPortal()
		maven(url = "https://repo.spring.io/libs-milestone")
	}
}

rootProject.name = "spring-session-samples"

include("sample-httpsession")
include("sample-websession")
