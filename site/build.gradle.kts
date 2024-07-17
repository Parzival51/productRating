import com.varabyte.kobweb.gradle.application.extensions.AppBlock.LegacyRouteRedirectStrategy
import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kotlin.serialization)
    // alias(libs.plugins.kobwebx.markdown)
}

group = "com.example.productrating"
version = "1.0-SNAPSHOT"

kobweb {
    app {
        index {
            description.set("Powered by Kobweb")
        }

        legacyRouteRedirectStrategy.set(LegacyRouteRedirectStrategy.DISALLOW)
    }
}

kotlin {
    configAsKobwebApplication("productrating", includeServer = true)

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.ktor.client.core)




            }
        }

        val jsMain by getting {
            dependencies {
                implementation(compose.html.core)
                implementation(libs.kobweb.core)
                implementation(libs.kobweb.silk)
                implementation(libs.silk.icons.fa)
                implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.7.3")

            }
        }

        val jvmMain by getting {
            dependencies {
                compileOnly(libs.kobweb.api)
                implementation(libs.mongodb.kotlin.driver)
                implementation(libs.ktor.server.core)
                implementation(libs.ktor.server.swagger)
                implementation(libs.ktor.server.content.negotiation)
                implementation(libs.ktor.serialization.gson)
                implementation(libs.ktor.server.tomcat)
                implementation("at.favre.lib:bcrypt:0.9.0")





            }
        }
    }
}
