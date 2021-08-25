package com.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class PrintPlugin implements Plugin<Project>{
    @Override
    void apply(Project project) {
        def extension = project.extensions.create("wangzh", PrintExtension)
        project.afterEvaluate {
            println "afterEvaluate and extension.name = ${extension.name}"
        }
        println "begin PrintPlugin"
    }
}