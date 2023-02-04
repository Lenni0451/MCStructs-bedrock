# MCStructs
A minecraft bedrock data structure library with the goal of being compliant with the vanilla client.\
This is the bedrock version of [MCStructs](https://github.com/Lenni0451/MCStructs).

# Modules

| Module | Description                                                    |
|--------|----------------------------------------------------------------|
| all    | Gradle module with all modules                                 |
| nbt    | Implementation of the read and write code for nbt in bedrock   |
| text   | Implementation of all text components with read and write code |

## Releases
### Gradle/Maven
To use MCStructs-bedrock with Gradle/Maven you can follow the instructions on [maven central](https://central.sonatype.com/search?q=net.lenni0451.mcstructs-bedrock).\
Gradle Template:
`````groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation "net.lenni0451.mcstructs-bedrock:all:x.x.x"
}
`````
You should check [maven central](https://central.sonatype.com/search?q=net.lenni0451.mcstructs-bedrock) for the latest version.
### Jar File
You can download the jar files for all modules from my [Jenkins](https://build.lenni0451.net/job/MCStructs-bedrock/) server.\
Module dependencies are not included in the jar files. You have to add them yourself.

## Usage
Check the [wiki](https://github.com/Lenni0451/MCStructs-bedrock/wiki/) for more information.
