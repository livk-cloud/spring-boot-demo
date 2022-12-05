package com.livk.boot.info

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.tasks.bundling.Jar
import org.gradle.util.GradleVersion

/**
 * <p>
 * JarPlugin
 * </p>
 *
 * @author livk
 *
 */
abstract class ManifestPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.pluginManager.apply(JavaPlugin.class)
        (project.tasks.named(JavaPlugin.JAR_TASK_NAME).get() as Jar)
                .manifest.attributes(
                [
                        "Implementation-Group"  : project.group,
                        "Implementation-Title"  : project.name,
                        "Implementation-Version": project.version,
                        "Created-By"            : System.getProperty("java.version") + " (" +
                                (System.getProperty("java.specification.vendor")) + ")",
                        "Gradle-Version"        : GradleVersion.current()
                ]
        )
    }
}
