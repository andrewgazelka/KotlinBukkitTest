package com.github.andrewgazelka.kotlintest

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class ConfigManager(private val file: File) {

    val yaml = YamlConfiguration()

    init {
        file.parentFile.mkdirs()
        file.createNewFile()
        load()
    }

    /**
     * @param index The path of the configuration option
     * @param default The default value which will be set if
     *     1) The path $index does not exist
     *     2) The value at $index is not of type $default
     *
     */
    inline fun <reified T> getDNESet(index: String, default: T): GetDNESetResult<T> {
        val get = yaml.get(index)
        return when (get) {
            is T -> GetDNESetResult(get, false)
            else -> {
                yaml.set(index, default)
                GetDNESetResult(default, true)
            }
        }
    }

    fun load() {
        yaml.load(file)
    }

    fun save() {
        yaml.save(file)
    }

    data class GetDNESetResult<out A>(val result: A, val overwrote: Boolean)


    operator

    fun get(index: String): Any {
        return yaml.get(index)
    }

    operator fun set(index: String, value: Any): Any {
        return yaml.set(index, value)
    }
}