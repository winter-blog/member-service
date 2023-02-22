package com.devwinter.memberservice.fixture;

import net.datafaker.Faker;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import java.time.LocalDate;

import static org.jeasy.random.FieldPredicates.*;
import static org.jeasy.random.FieldPredicates.inClass;

public class MemberJpaEntityFixtureFactory {

    public static EasyRandom get() {

        Faker faker = new Faker();

        EasyRandomParameters parameters = new EasyRandomParameters()
                .randomize(named("email"), () -> faker.internet().emailAddress())
                .randomize(named("password"), () -> "$" + faker.internet().password());

        return new EasyRandom(parameters);
    }
}
