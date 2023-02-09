import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.0"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	id("com.github.davidmc24.gradle.plugin.avro") version "1.5.0"
	id("com.google.protobuf") version "0.9.1"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
	maven {
		url = uri("https://packages.confluent.io/maven")
	}
}

dependencies {
	implementation(project(":kafka-shared"))

	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.kafka:spring-kafka-test")
	testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.9.0")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
	testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.9.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

protobuf {
	// Configure the protoc executable
	protoc {
		// Download from repositories
		artifact = "com.google.protobuf:protoc:3.21.12"
	}
}
