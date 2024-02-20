package com.licoding.oceanpulse.data.constants

import com.licoding.oceanpulse.data.models.Fact

val facts = arrayOf(
    Fact(
        title = "Over 50% of oxygen on the planet comes from oceans.",
        description = "Hello"
    ),
    Fact(
        title = "Over 50% of oxygen on the planet comes from oceans.",
        description = "Hello"
    ),
    Fact(
        title = "Over 50% of oxygen on the planet comes from oceans.",
        description = "Hello"
    )
)


fun getFact(facts: Array<Fact>): Fact {
    return facts.random()
}