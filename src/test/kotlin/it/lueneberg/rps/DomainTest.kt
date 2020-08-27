package it.lueneberg.rps

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.*
import it.lueneberg.rps.Action.*
import it.lueneberg.rps.Result.*

class DomainTest : StringSpec({
    "Scissor beats Paper" {
        SCISSOR.evaluate(PAPER) shouldBe WIN
    }

    "Scissor loses against Rock" {
        SCISSOR.evaluate(ROCK) shouldBe LOSE
    }

    "Scissor plays draw against itself" {
        SCISSOR.evaluate(SCISSOR) shouldBe DRAW
    }

    "Paper beats Rock" {
        PAPER.evaluate(ROCK) shouldBe WIN
    }

    "Paper loses against Scissor" {
        PAPER.evaluate(SCISSOR) shouldBe LOSE
    }

    "Paper plays draw against itself" {
        PAPER.evaluate(PAPER) shouldBe DRAW
    }

    "Rock beats Scissor" {
        ROCK.evaluate(SCISSOR) shouldBe WIN
    }

    "Rock loses against Paper" {
        ROCK.evaluate(PAPER) shouldBe LOSE
    }

    "Rock plays draw against itself" {
        ROCK.evaluate(ROCK) shouldBe DRAW
    }
})