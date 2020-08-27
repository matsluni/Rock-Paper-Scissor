package it.lueneberg.rps

import io.kotest.core.spec.style.StringSpec
import io.kotest.property.forAll
import io.kotest.matchers.*
import io.kotest.property.Arb
import io.kotest.property.arbitrary.create


class ActionGeneratorTest : StringSpec({
    "const generator should always produce same Action" {
        val generator = ActionGenerator.const(Action.PAPER)
        val actionArb = Arb.create{generator.action()}

        forAll(100, actionArb) { a ->
            a == Action.PAPER
        }
    }

    "random generator should produce different Actions" {
        val generator = ActionGenerator.random()
        val allActions = Action.values().toMutableSet()
        for (i in 1..100) {
            allActions.remove(generator.action())
        }
        allActions shouldBe emptySet<Action>()
    }
})