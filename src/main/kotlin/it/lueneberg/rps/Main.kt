@file:JvmName("Main")
package it.lueneberg.rps

import kotlin.random.Random

fun main() {
    val game = Game(Player{Action.values()[Random.nextInt(Action.values().size)]}, Player{Action.ROCK}, 100)
    val gameResult = game.play()
    gameResult.print()
}