package org.momoven.project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform