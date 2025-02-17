/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.test.fixtures.build

import org.gradle.test.fixtures.file.TestFile

class BuildTestFile extends TestFile {
    private final String projectName

    BuildTestFile(TestFile rootDir, String projectName) {
        super(rootDir)
        this.projectName = projectName
    }

    String getRootProjectName() {
        projectName
    }

    TestFile getBuildFile() {
        file("build.gradle")
    }

    void buildFile(String script) {
        buildFile << script
    }

    TestFile getSettingsFile() {
        file("settings.gradle")
    }

    TestFile getGradlePropertiesFile() {
        file("gradle.properties")
    }

    void addChildDir(String name) {
        file(name).file("build.gradle") << "// Dummy child build"
    }

    BuildTestFile project(String name) {
        return new BuildTestFile(file(name), name)
    }

    BuildTestFile includedBuild(String name) {
        settingsFile << "includeBuild '$name'"
        return new BuildTestFile(file(name), name).tap {
            file("src/main/java/Dummy.java") << "public class Dummy {}"
            settingsFile << "rootProject.name = '$projectName'"
        }
    }
}
