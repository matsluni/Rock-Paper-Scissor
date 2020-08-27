package it.lueneberg.rps

import java.io.PrintStream

class Game {
    private val p1: Player
    private val p2: Player
    private val iterations: Int

    constructor(p1: Player, p2: Player, iterations: Int) {
        require(iterations >= 100) {"Number of games must be >= 100."}
        this.p1 = p1
        this.p2 = p2
        this.iterations = iterations
    }

    fun play(): GameResult =
        (1..iterations)
            .toList()
            .fold(GameResult.empty) { agg, g ->
                val p1Action = p1.nextAction()
                val p2Action = p2.nextAction()
                when (p1Action.evaluate(p2Action)) {
                    Result.WIN -> agg.copy(p1Wins = agg.p1Wins + 1)
                    Result.DRAW -> agg.copy(p1Draws = agg.p1Draws + 1)
                    Result.LOSE -> agg.copy(p1Loses = agg.p1Loses + 1)
                }
            }

}

data class GameResult(val p1Wins: Int, val p1Loses: Int, val p1Draws: Int) {
    val p2Wins = p1Loses
    val p2Draws = p1Draws
    val p2Loses = p1Wins

    companion object {
        val empty = GameResult(0, 0, 0)
    }

    fun print(printStream: PrintStream = System.out): Unit {
        printStream.println(
            """
            Game result
            ###########
            
            Player 1 Wins : $p1Wins
            Player 1 Draws: $p1Draws
            Player 1 Loses: $p1Loses
    
            Player 2 Wins : $p2Wins
            Player 2 Draws: $p2Draws
            Player 2 Loses: $p2Loses
            """.trimIndent()
        )

    }
}