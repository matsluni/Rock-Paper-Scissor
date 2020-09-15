package it.lueneberg.rps

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.create
import io.kotest.property.forAll
import kotlin.random.Random

class PlayerActionTest : StringSpec({
    "const generator should always produce same Action" {
        val actionArb = Arb.create{Action.PAPER}

        forAll(100, actionArb) { a ->
            a == Action.PAPER
        }
    }

    "random generator should produce different Actions" {
        val generator = generateSequence { Action.values()[Random.nextInt(Action.values().size)] }.iterator()
        val allActions = Action.values().toMutableSet()
        for (i in 1..100) {
            allActions.remove(generator.next())
        }
        allActions shouldBe emptySet<Action>()
    }
})