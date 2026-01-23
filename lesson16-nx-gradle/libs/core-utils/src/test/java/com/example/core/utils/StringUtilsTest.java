package com.example.core.utils;

public class StringUtilsTest {
    @org.junit.Test
    public void testCapitalize() {
        org.junit.Assert.assertEquals("Hello", StringUtils.capitalize("hello"));
    }

    @org.junit.Test
    public void testReverse() {
        org.junit.Assert.assertEquals("olleh", StringUtils.reverse("hello"));
    }
}
