package it.lueneberg.rps

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.nio.charset.StandardCharsets

class GameTest : StringSpec({
    "Game cannot be create with less than 100 iterations" {
        val p1 = Player{Action.PAPER}
        val p2 = Player{Action.PAPER}
        shouldThrow<IllegalArgumentException> {
            Game(p1, p2, 99)
        }
    }

    "GameResult should have 100 wins for player 1" {
        val p1 = Player{Action.PAPER}
        val p2 = Player{Action.ROCK}
        val result = Game(p1, p2, 100).play()
        result.p1Wins shouldBe 100
    }

    "GameResult should have 100 wins for player 2" {
        val p1 = Player{Action.ROCK}
        val p2 = Player{Action.PAPER}
        val result = Game(p1, p2, 100).play()
        result.p2Wins shouldBe 100
    }

    "GameResult should have 100 draws for player 1 and player 2" {
        val p1 = Player{Action.ROCK}
        val p2 = Player{Action.ROCK}
        val result = Game(p1, p2, 100).play()
        result.p1Draws shouldBe 100
        result.p2Draws shouldBe 100
    }

    "GameResult should have inverted numbers for player 1/2 wins/loses" {
        val gameResult = GameResult.empty.copy(p1Wins = 5)
        gameResult.p1Wins shouldBe 5
        gameResult.p2Loses shouldBe 5

        // others
        gameResult.p1Loses shouldBe 0
        gameResult.p1Draws shouldBe 0
        gameResult.p2Wins shouldBe 0
        gameResult.p2Draws shouldBe 0
    }

    "GameResult should have same numbers for player 1 and 2 draws" {
        val gameResult = GameResult.empty.copy(p1Draws = 5)
        gameResult.p1Draws shouldBe 5
        gameResult.p2Draws shouldBe 5

        // others
        gameResult.p1Loses shouldBe 0
        gameResult.p1Loses shouldBe 0
        gameResult.p2Wins shouldBe 0
        gameResult.p2Loses shouldBe 0
    }

    "GameResult should correctly print the result" {
        val gameResult = GameResult.empty.copy(p1Draws = 5, p1Wins = 4, p1Loses = 3)
        val outContent = ByteArrayOutputStream()
        PrintStream(outContent, true, StandardCharsets.UTF_8.name()).use { ps -> gameResult.print(ps) }

        val expected = """
                        Game result
                        ###########
                        
                        Player 1 Wins : 4
                        Player 1 Draws: 5
                        Player 1 Loses: 3
                        
                        Player 2 Wins : 3
                        Player 2 Draws: 5
                        Player 2 Loses: 4
                       """.trimIndent().replace("\n", "")

        val actual: String = outContent.toString(StandardCharsets.UTF_8.name()).replace("\n", "")

        actual shouldBe expected
    }
})