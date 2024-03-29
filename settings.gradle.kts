pluginManagement {
	repositories {
		mavenCentral()
		maven {
			url = uri("https://repo.spring.io/milestone/")
		}
		maven {
			url = uri("https://repo.spring.io/snapshot/")
		}
		gradlePluginPortal()
	}

	resolutionStrategy {
		eachPlugin {
			if (requested.id.id == "org.springframework.boot") {
				useModule("org.springframework.boot:spring-boot-gradle-plugin:${requested.version}")
			}
		}
	}
}

rootProject.name = "samples-spring-session"

includeBuild("gradle/plugins")

include("sample-support-httpsession")
include("sample-support-test")
include("sample-support-websession")

include("sample-httpsession-hazelcast")
include("sample-httpsession-infinispan")
include("sample-httpsession-jdbc")
include("sample-httpsession-mongodb")
include("sample-httpsession-redis")

include("sample-websession-mongodb")
include("sample-websession-redis")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
