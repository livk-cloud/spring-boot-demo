plugins {
    com.livk.service
}

dependencies {
    implementation(project(":spring-boot-extension-starters:mybatis-plugins-spring-boot-starter"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.mysql:mysql-connector-j")
    implementation("com.github.pagehelper:pagehelper-spring-boot-starter")
    testImplementation("com.h2database:h2")
}
