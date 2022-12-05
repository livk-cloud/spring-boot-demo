package com.livk.boot.dependency

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPluginExtension

/**
 * <p>
 * OptionalPlugin
 * </p>
 *
 * @author livk
 *
 */
abstract class OptionalPlugin implements Plugin<Project> {

    public static final String OPTIONAL = "optional"

    @Override
    void apply(Project project) {
        def configurations = project.configurations
        def optional = configurations.create(OPTIONAL)
        optional.canBeConsumed = false
        optional.canBeResolved = false
        project.getPlugins().withType(JavaPlugin.class).configureEach {
            project.extensions.getByType(JavaPluginExtension.class).sourceSets.every { sourceSet ->
                configurations.named(sourceSet.getCompileClasspathConfigurationName()).get().extendsFrom(optional)
                configurations.named(sourceSet.getRuntimeClasspathConfigurationName()).get().extendsFrom(optional)
            }
        }
    }
}
