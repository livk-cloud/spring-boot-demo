plugins {
	com.livk.common
}

dependencies {
	api(project(":spring-boot-extension-starters:redis-spring-boot-starter"))
	api("com.fasterxml.jackson.core:jackson-databind")
	api("org.apache.commons:commons-pool2")
}
