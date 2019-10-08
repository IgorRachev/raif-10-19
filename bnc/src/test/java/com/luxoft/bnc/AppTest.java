package com.luxoft.bnc;

import static com.luxoft.bnc.HasInside.hasBullInside;
import static org.hamcrest.Matchers.*;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

/**
 * Unit test for simple App.
 */
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@DisplayName("2 first tests. they show ")
public class AppTest {
    private static String hiddenValue="";

    @BeforeAll
    public static void preparation(){
        Hider hider = new Hider();
        hiddenValue= hider.getTestHiddenValue();
    }

    @RepeatedTest(2)
    @DisplayName("2 same results because before all worked only once")
    public void test4Digits(){
        System.out.println(hiddenValue);
        assertEquals( hiddenValue.length(), 4 , "optional message");
        assertAll(  () -> Assertions.assertEquals(4, hiddenValue.length()),
                    () -> Assertions.assertFalse(hiddenValue.contains("0"), () -> "Must be false " + "because of rules"));

    }

    @DisplayName("2 different results")
    @RepeatedTest(value = 2, name = "#{currentRepetition} of {totalRepetitions}")
    void test4DigitsRepeat(RepetitionInfo repetitionInfo){
        Hider hider = new Hider();
        hiddenValue= hider.getTestHiddenValue();
        System.out.println(hiddenValue + " " + repetitionInfo.toString());
        assertEquals( hiddenValue.length(), 4, "optional message" );
    }

    @ParameterizedTest()
    @ValueSource(ints = {1423, 6875, 1289})
    void testCowsNumber(int arg){
        Comparator com = new Comparator();
        String hid= "8654";
        assumeTrue(5<2);
        com.setTestAttemptValue(arg);
        assertEquals(com.getCows(hid), 1);

    }

    @ParameterizedTest()
    @CsvSource({"1423,1", "6875,3", "1289,1"})
    void testCowsNumberSource(int a, int arg){
        Comparator com = new Comparator();
        String hid= "8654";
        com.setTestAttemptValue(a);
        assertEquals(com.getCows(hid), arg);
    }

    @ParameterizedTest()
    //package resources under test. In same depth with "java" folder
    @CsvFileSource(resources="/data1.csv", numLinesToSkip=3)
    void testCowsNumberSofurce(int a, int arg){
        Comparator com = new Comparator();
        String hid= "8654";
        com.setTestAttemptValue(a);
        assertEquals(com.getCows(hid), arg);
    }

    @ParameterizedTest()
    @MethodSource("providerMethod")
    void testCowsNumberSourceMeth(int a, int arg){
        Comparator com = new Comparator();
        String hid= "8654";
        com.setTestAttemptValue(a);
        assertEquals(com.getCows(hid), arg);
    }

    private static Stream<Arguments> providerMethod(){
        return Stream.of(
                Arguments.of(1423,1),
                Arguments.of(6875,3),
                Arguments.of(1289,1)
        );
    }

    @ParameterizedTest()
    @EnumSource(value = TestData.class, names = {"data1", "data2", "data3"})
    void testCowsNumberSourceMeth(TestData data){
        Comparator com = new Comparator();
        String hid= data.getHidden();
        com.setTestAttemptValue(data.getAttempt());
        assertEquals(com.getCows(hid), data.getExpectedCows());
        assertThat(com.getCows(hid), equalTo(data.getExpectedCows()));
        assertThat(Arrays.asList("r", "0", "t", "a"), containsInAnyOrder("t", "r", "0", "a"));
    }

    @Test
    void testHiddenisAString(){
        Hider hider = new Hider();
        assertThat(hider.getTestHiddenValue(), is(String.class));

    }

    @Test
    void testHiddenis4Digits(){
        assertThat(Integer.parseInt(hiddenValue), lessThan(9999));
        assumingThat(System.getProperty("os.name").equalsIgnoreCase("linux"),
                //perform this assertion if linux
                () -> assertThat("BULLSHIT", hasBullInside()));
    }

    @Test
    void testSuccessMessage(){
        assertThat("BULLSHIT", hasBullInside());
        GameProcessor gameProcessor = new GameProcessor();
        gameProcessor.isWin("1234", "1234", new Comparator());
        assertThat(gameProcessor.congratsString, hasBullInside());
        assertThat(gameProcessor.congratsString, startsWith("4"));
    }

    @Test
    @DisplayName("233435")
    public void test4(){
        Hider hider = new Hider();
        assertAll( "Hider",
                () -> Assertions.assertEquals(4, hider.getTestHiddenValue().length()),
                () -> Assertions.assertFalse(hider.testHiddenValue.contains("0"), () -> "Must be false " + hider.testHiddenValue));

    }

    @TestFactory
    Collection<DynamicTest> dynamicTestsFromCollection() {
        return Arrays.asList(
                dynamicTest("1st dynamic test", () -> assertThat("BULLSHIT", hasBullInside())),
                dynamicTest("2nd dynamic test", () -> assertTrue(5<3)
        ));
    }

    @TestFactory
    Iterable<DynamicTest> dynamicsTestsFromCollection() {
        return Arrays.asList(
                dynamicTest("3rd dynamic test", () -> assertThat("BULLSHIT", hasBullInside())),
                dynamicTest("4th dynamic test", () -> assertTrue(5>3)
                ));
    }

}
