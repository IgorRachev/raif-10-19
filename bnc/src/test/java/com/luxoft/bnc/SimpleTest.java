package com.luxoft.bnc;

import org.junit.jupiter.api.*;

import static com.luxoft.bnc.DividedBy.isDividedBy2;
import static com.luxoft.bnc.HasInside.hasSpace;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasValue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

/**
 * If option 'Per Method' is chosen, separated instance of SimpleTest
 * would be created for each test method. Need for isolation and security reasons.
 */
//@TestInstance(TestInstance.Lifecycle.PER_METHOD)

public class SimpleTest {

    private  String testString;
    private int testInt;

    @BeforeAll
    void fillInValues(){
        this.testInt = 15;
        this.testString = "first test String";
    }

    @Test
    void testIntValue(){
        Assertions.assertEquals(testInt, 15);
        assertThat("reason", testInt, isDividedBy2());
    }

    @Test
    void testStringValue(){
        Assertions.assertTrue(testString.length()>10);
    }

    @Test
    void testSpace(){
        assertThat(testString, hasSpace());
    }
}
