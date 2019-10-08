package com.luxoft.bnc;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.core.SubstringMatcher;

public class HasInside extends SubstringMatcher {

    protected HasInside(String substring) {
        super(substring);
    }

    @Override
    protected boolean evalSubstringOf(String s) {
        return s.contains(substring);
    }

    @Override
    protected String relationship() {
        return null;
    }

    @Factory
    public static Matcher<String> hasBullInside(){
        return new HasInside("BULL");
    }

    public static Matcher<String> hasSpace(){
        return new HasInside(" ");
    }
}
