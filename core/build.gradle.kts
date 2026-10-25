import net.labymod.labygradle.common.extension.LabyModAnnotationProcessorExtension.ReferenceType
import org.gradle.jvm.tasks.Jar

dependencies {
    labyProcessor()
    labyApi("api")
}

labyModAnnotationProcessor {
    referenceType = ReferenceType.DEFAULT
}

tasks.named<Jar>("jar") {
    archiveBaseName.set("camera-plus")
    destinationDirectory.set(rootProject.layout.buildDirectory.dir("libs"))
}
