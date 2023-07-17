plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.postgresql:postgresql:42.6.0")
    implementation("org.slf4j:slf4j-log4j12:2.0.5")
    implementation("org.flywaydb:flyway-core:9.20.0")

}

tasks.test {
    useJUnitPlatform()
}