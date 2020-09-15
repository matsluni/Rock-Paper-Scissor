package it.lueneberg.rps

enum class Result {
    WIN, DRAW, LOSE
}

enum class Action {

    ROCK {
        override val beats by lazy {setOf(SCISSOR, LIZARD)}
    },

    PAPER {
        override val beats by lazy {setOf(ROCK, SPOCK)}
    },

    SCISSOR {
        override val beats by lazy {setOf(PAPER, LIZARD)}
    },

    LIZARD {
        override val beats by lazy {setOf(SPOCK, PAPER)}
    },

    SPOCK {
        override val beats by lazy {setOf(SCISSOR, ROCK)}
    };

    abstract val beats: Set<Action>

    fun evaluate(other: Action): Result =
        if (this == other) Result.DRAW
        else {
            if (beats.contains(other)) Result.WIN
            else Result.LOSE
        }
}

class Player(private val f: () -> Action) {
    fun nextAction(): Action = generateSequence { f() }.take(1).last()
}