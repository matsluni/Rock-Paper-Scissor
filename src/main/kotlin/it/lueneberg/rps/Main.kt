@file:JvmName("Main")
package it.lueneberg.rps

fun main() {
    val rockAction = ActionGenerator.const(Action.ROCK)
    val rndAction = ActionGenerator.random()
    val game = Game(Player(rndAction), Player(rockAction), 100)
    val gameResult = game.play()
    gameResult.print()
}