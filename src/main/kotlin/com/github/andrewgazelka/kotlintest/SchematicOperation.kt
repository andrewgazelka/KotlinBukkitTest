package com.github.andrewgazelka.kotlintest

import com.sk89q.worldedit.CuboidClipboard
import com.sk89q.worldedit.Vector
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.entity.Entity
import java.io.File

class SchematicOperation(file: File) {
    private val clipboard: CuboidClipboard = CuboidClipboard.loadSchematic(file)
    private val size: Vector

    init {
        size = clipboard.size
        println("size: $size")
        println("offset: ${clipboard.offset}")
        println("origin: ${clipboard.origin}")
        val map: Map<Int, () -> Unit>
    }


    fun paste(world: World) {
        (0 until size.blockY).iteratePeriod(100,{
            for (x in 0 until size.blockX) {
                for (z in 0 until size.blockZ) {
                    val block = clipboard.getBlock(Vector(x, it, z))
                    var material = Material.getMaterial(block.type)

                    if(material.isSolid.not())
                    {
                        material = Material.DIRT
                    }


                    val location = Location(world, x.toDouble()+200, it.toDouble() + 150.0, z.toDouble())
                    val fallingBlock = world.spawnFallingBlock(location, material, block.data.toByte())

                    fallingBlock.onDeath {
                        val location1 = fallingBlock.location
                        location1.world.createExplosion(location1,4.0F)
                    }

//                    if(it-4 >= 0)
//                    {
//                        val blockBelow = clipboard.getBlock(Vector(x,it-4,z))
//                        val blockBelowMaterial = Material.getMaterial(blockBelow.type)
//                        if(blockBelowMaterial.isSolid.not())
//                        {
//                            val locationBelow = Location(world, x.toDouble(), it.toDouble() + 150.0-4.0, z.toDouble())
//                            val bukkitBlockBelow = locationBelow.block
//                            bukkitBlockBelow.type = blockBelowMaterial
//                            bukkitBlockBelow.data = blockBelow.data.toByte()
//                        }
//                    }
                }
            }
        })
    }
}

private var Entity.a: () -> Unit
    get() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    set(function) {
        entityId
    }

