plugins {
    id("java")
    application
}

dependencies {
    implementation("com.sparkjava:spark-core:2.9.3")
    implementation("com.google.code.gson:gson:2.8.9")
    implementation(project(":client-microservice"))
    implementation(project(":tour-microservice"))
}

application {
    mainClass.set("com.tripmanager.booking.BookingMicroservice")
}
