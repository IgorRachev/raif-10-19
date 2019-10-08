package com.luxoft.bnc;


import org.hamcrest.*;

public class DividedBy extends TypeSafeMatcher {



    @Override
    protected boolean matchesSafely(Object o) {
        int k = (Integer)o;
        return k%2==0;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("return true if even");
    }

    @Factory
    public static Matcher<Integer> isDividedBy2(){
        return new DividedBy();
    }
}
