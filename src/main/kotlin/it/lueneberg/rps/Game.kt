package it.lueneberg.rps

import java.io.PrintStream
import arrow.*
import arrow.core.extensions.*
import arrow.core.extensions.sequence.foldable.foldLeft
import arrow.typeclasses.*

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
        generateSequence { p1.nextAction() to p2.nextAction() }
            .take(iterations)
            .foldLeft(GameResult.GameResultMonoid.empty()) { agg, (a1, a2) ->
                GameResult.GameResultMonoid.run {
                    agg.combine(a1.evaluateToGameResult(a2))
                }
            }

}

data class GameResult(val p1Wins: Int, val p1Loses: Int, val p1Draws: Int) {
    val p2Wins = p1Loses
    val p2Draws = p1Draws
    val p2Loses = p1Wins

    companion object {
        fun fromResult(res: Result): GameResult =
            when (res) {
                Result.WIN -> GameResult(1, 0, 0 )
                Result.LOSE -> GameResult(0, 1, 0 )
                Result.DRAW -> GameResult(0, 0, 1 )
            }

        val empty = GameResult(0, 0, 0)
        val GameResultMonoid = object : Monoid<GameResult> {
            override fun empty(): GameResult = empty
            override fun GameResult.combine(b: GameResult): GameResult =
                this.copy(
                    p1Wins = this.p1Wins + b.p1Wins,
                    p1Loses = this.p1Loses + b.p1Loses,
                    p1Draws = this.p1Draws + b.p1Draws
                )

        }
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