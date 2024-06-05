

plugins {
    id("java")
    id("application")
}

application {
    mainClass.set("com.example.TictactoeGame")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(fileTree("libs"))
    implementation("org.openjfx:javafx-controls:20")
    implementation("org.openjfx:javafx-base:20")
    implementation("org.openjfx:javafx-fxml:20")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}