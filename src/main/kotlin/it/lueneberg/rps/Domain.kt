package it.lueneberg.rps

enum class Result {
    WIN, DRAW, LOSE
}

enum class Action {
    ROCK, PAPER, SCISSOR;

    fun evaluate(other: Action): Result =
        when (this) {
            ROCK -> when (other) {
                ROCK -> Result.DRAW
                PAPER -> Result.LOSE
                SCISSOR -> Result.WIN
            }
            PAPER -> when (other) {
                ROCK -> Result.WIN
                PAPER -> Result.DRAW
                SCISSOR -> Result.LOSE
            }
            SCISSOR -> when (other) {
                ROCK -> Result.LOSE
                PAPER -> Result.WIN
                SCISSOR -> Result.DRAW
            }
        }

}

class Player(private val actionGenerator: ActionGenerator) {
    fun nextAction(): Action = actionGenerator.action()
}