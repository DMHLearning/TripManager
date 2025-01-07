plugins {
    id("java")
}

group = "dev.denismasterherobrine.tripmanager"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.sparkjava:spark-core:2.7.2")
    implementation("com.google.code.gson:gson:2.8.9")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
}

tasks.test {
    useJUnitPlatform()
}