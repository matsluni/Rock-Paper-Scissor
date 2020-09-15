package it.lueneberg.rps

enum class Result {
    WIN, DRAW, LOSE
}

enum class Action {
    ROCK, PAPER, SCISSOR, LIZARD, SPOCK;

    fun evaluate(other: Action): Result =
        if (this == other) Result.DRAW
        else {
            if (winsAgainst[this]?.contains(other)!!) Result.WIN
            else Result.LOSE
        }


    val winsAgainst by lazy {
        mapOf(ROCK to setOf(SCISSOR, LIZARD),
              PAPER to setOf(ROCK, SPOCK),
              SCISSOR to setOf(PAPER, LIZARD),
              LIZARD to setOf(SPOCK, PAPER),
              SPOCK to setOf(SCISSOR, ROCK)
            )
    }


}

class Player(private val actionGenerator: ActionGenerator) {
    fun nextAction(): Action = actionGenerator.action()
}