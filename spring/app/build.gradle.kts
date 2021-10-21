plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter:2.5.0")

    testImplementation("org.junit.jupiter:junit-jupiter:5.7.2")
}

application {
    // Define the main class for the application.
    mainClass.set("com.github.rafaelsilvestri.spring.App")
}

tasks.test {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}
