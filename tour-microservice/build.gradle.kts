plugins {
    id("java")
    application
}

dependencies {
    implementation("com.sparkjava:spark-core:2.9.3")
    implementation("com.google.code.gson:gson:2.8.9")
}

application {
    mainClass.set("dev.denismasterherobrine.tripmanager.tour.TourMicroservice")
}
