package org.gradle.github.dependency.extractor.internal

import com.fasterxml.jackson.module.kotlin.jsonMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.gradle.github.dependency.extractor.internal.json.GitHubRepositorySnapshot

object JacksonJsonSerializer {

    private val mapper = jsonMapper {
        serializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
        addModule(kotlinModule())
    }

    fun serializeToJson(ghDependencyGraph: GitHubRepositorySnapshot): String {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ghDependencyGraph)
    }
}
