import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("org.springframework.boot") version "2.2.2.RELEASE"
  id("io.spring.dependency-management") version "1.0.8.RELEASE"
  kotlin("jvm") version "1.3.61"
  kotlin("plugin.spring") version "1.3.61"
}

group = "com.kyj"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

val developmentOnly by configurations.creating
configurations {
  runtimeClasspath {
    extendsFrom(developmentOnly)
  }
}

repositories {
  mavenCentral()
  jcenter()
}

dependencies {
//  implementation("com.graphql-java:graphql-spring-boot-starter:5.0.2")
/*  implementation("com.graphql-java-kickstart:graphiql-spring-boot-starter:6.0.0")
  runtimeOnly("com.graphql-java-kickstart:graphiql-spring-boot-starter:6.0.0")*/
/*  implementation("com.graphql-java:graphql-spring-boot-starter:3.6.0")
  implementation("com.graphql-java:graphql-java-tools:3.2.0")*/
  implementation("org.jetbrains.exposed:exposed:0.17.7")
  implementation("org.jetbrains.exposed:spring-transaction:0.17.7")
  implementation("org.springframework.boot:spring-boot-starter-webflux")
  // graphql-spring-boot-starter 가 spring-boot-starter-webflux 보다 위에 있으면 @SpringBootApplication 에러남.
  implementation("com.graphql-java-kickstart:graphql-spring-boot-starter:6.0.1")
//  implementation("com.graphql-java-kickstart:graphql-java-tools:5.7.1")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

  developmentOnly("org.springframework.boot:spring-boot-devtools")
  runtimeOnly("com.graphql-java-kickstart:graphiql-spring-boot-starter:6.0.1")
  runtimeOnly("mysql:mysql-connector-java")
  testImplementation("org.springframework.boot:spring-boot-starter-test") {
    exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
  }
  testImplementation("io.projectreactor:reactor-test")
}

tasks.withType<Test> {
  useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "1.8"
  }
}
