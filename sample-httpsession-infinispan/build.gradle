plugins {
	id("sample.java-convention")
	alias(libs.plugins.spring.boot)
}

dependencies {
	modules {
		module("org.infinispan:infinispan-commons") {
			replacedBy("org.infinispan:infinispan-commons-jakarta")
		}
		module("org.infinispan:infinispan-core") {
			replacedBy("org.infinispan:infinispan-core-jakarta")
		}
	}

	implementation(projects.sampleSupportHttpsession)
	implementation(libs.spring.boot.starter.actuator)
	implementation(libs.spring.boot.starter.mustache)
	implementation(libs.spring.boot.starter.security)
	implementation(libs.spring.boot.starter.web)
	implementation(libs.infinispan.commons)
	implementation(libs.infinispan.core)
	implementation(libs.infinispan.spring.boot.starter.embedded)

	testImplementation(projects.sampleSupportTest)
	testImplementation(libs.spring.boot.starter.test)
}
