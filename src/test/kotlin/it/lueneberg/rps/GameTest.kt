package it.lueneberg.rps

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.lang.IllegalArgumentException

class GameTest : StringSpec({
    "Game cannot be create with less than 100 iterations" {
        val p1 = Player(ActionGenerator.const(Action.PAPER))
        val p2 = Player(ActionGenerator.const(Action.PAPER))
        shouldThrow<IllegalArgumentException> {
            Game(p1, p2, 99)
        }
    }

    "GameResult should have 100 wins for player 1" {
        val p1 = Player(ActionGenerator.const(Action.PAPER))
        val p2 = Player(ActionGenerator.const(Action.ROCK))
        val result = Game(p1, p2, 100).play()
        result.p1Wins shouldBe 100
    }

    "GameResult should have 100 wins for player 2" {
        val p1 = Player(ActionGenerator.const(Action.ROCK))
        val p2 = Player(ActionGenerator.const(Action.PAPER))
        val result = Game(p1, p2, 100).play()
        result.p2Wins shouldBe 100
    }

    "GameResult should have 100 draws for player 1 and player 2" {
        val p1 = Player(ActionGenerator.const(Action.ROCK))
        val p2 = Player(ActionGenerator.const(Action.ROCK))
        val result = Game(p1, p2, 100).play()
        result.p1Draws shouldBe 100
        result.p2Draws shouldBe 100
    }

    "GameResult have inverted numbers for player 1/2 wins/loses" {
        val gameResult = GameResult.empty.copy(p1Wins = 5)
        gameResult.p1Wins shouldBe 5
        gameResult.p2Loses shouldBe 5

        // others
        gameResult.p1Loses shouldBe 0
        gameResult.p1Draws shouldBe 0
        gameResult.p2Wins shouldBe 0
        gameResult.p2Draws shouldBe 0
    }

    "GameResult have same numbers for player 1 and 2 draws" {
        val gameResult = GameResult.empty.copy(p1Draws = 5)
        gameResult.p1Draws shouldBe 5
        gameResult.p2Draws shouldBe 5

        // others
        gameResult.p1Loses shouldBe 0
        gameResult.p1Loses shouldBe 0
        gameResult.p2Wins shouldBe 0
        gameResult.p2Loses shouldBe 0
    }
})