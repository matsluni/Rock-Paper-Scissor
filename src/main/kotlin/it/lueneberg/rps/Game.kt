package it.lueneberg.rps

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
}