plugins {
    kotlin("jvm") version "1.9.22"
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}
javafx {
    version = "11.0.2"
    modules("javafx.controls")
}

group = "io.vdartsabvile"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("org.openjfx:javafx-controls:21") // Замените на актуальную версию
    implementation("org.openjfx:javafx-fxml:21")
    implementation("org.openjfx:javafx-base:21")

    implementation("io.github.mkpaz:atlantafx-base:2.0.1")

    implementation("io.ktor:ktor-server-core:2.3.13")
    implementation("io.ktor:ktor-server-websockets:2.3.13")
    implementation("io.ktor:ktor-server-netty:2.3.13")

    implementation("org.java-websocket:Java-WebSocket:1.5.2")


}
application {
    mainClass.set("io.vdartsabvile.MainApp")  // Изменяем пакет на io.vdartsabvile
}
tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "io.vdartsabvile.MainApp" // Укажите правильный путь к вашему классу с методом main
        )
    }
}