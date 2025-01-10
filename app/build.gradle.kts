plugins {
    id("java")
    application
}

dependencies {
    implementation("com.sparkjava:spark-core:2.9.3")
    implementation("com.google.code.gson:gson:2.8.9")
    implementation(project(":client-microservice"))
    implementation(project(":tour-microservice"))
    implementation(project(":booking-microservice"))

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

application {
    mainClass.set("dev.denismasterherobrine.tripmanager.app.Main")
}
