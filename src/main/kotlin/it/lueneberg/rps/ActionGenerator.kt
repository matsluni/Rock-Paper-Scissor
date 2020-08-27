package it.lueneberg.rps

import kotlin.random.Random

interface ActionGenerator {
    fun action(): Action

    companion object {
        fun generator(f: () -> Action): ActionGenerator = object : ActionGenerator {
            override fun action(): Action {
                return f()
            }
        }

        fun const(c: Action): ActionGenerator =
            generator { c }

        fun random(maxSeed: Int = Int.MAX_VALUE): ActionGenerator {
            val rnd = Random(Random.nextInt(maxSeed))
            return generator { Action.values()[rnd.nextInt(Action.values().size)] }
        }
    }
}