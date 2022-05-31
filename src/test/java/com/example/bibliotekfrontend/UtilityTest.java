package com.example.bibliotekfrontend;

import org.junit.jupiter.api.Test;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {

    Utility utility = new Utility();

    @Test
    void encodeToURL() {
    }

    @Test
    void trimResponse() {
    }

    @Test
    void getIsbnFromString() {
    }

    @Test
    void getTitleFromString() {
    }

    @Test
    void getIsbnFromSelectedString() {
    }

    @Test
    void getBookIDFromSelectedString() {
    }

    public String encodeToURL(String inputString) {
        String encodedString = URLEncoder.encode(inputString, StandardCharsets.UTF_8);
        return encodedString;
    }

    /*
    public String encodeToURL(String inputString) {
        String encodedString = encodeToURL(inputString, StandardCharsets.UTF_8);
        return encodedString;
    }
     */

    @org.junit.jupiter.api.Test
    void encodeToURLTest01() {
        String input = "Wizard Gandolf";
        String output = encodeToURL(input);
        assertEquals(output, "Wizard+Gandolf");
    }

    @org.junit.jupiter.api.Test
    void encodeToURLTest02() {
        String input = "Wizard&Gandolf";
        String output = encodeToURL(input);
        assertEquals(output, "Wizard%26Gandolf");
    }

    @org.junit.jupiter.api.Test
    void encodeToURLTest03() {
        String input = "Wizard-Gandolf";
        String output = encodeToURL(input);
        assertEquals(output, "Wizard-Gandolf");
    }

    @org.junit.jupiter.api.Test
    void encodeToURLTest04() {
        String input = "Wizard+Gandolf";
        String output = encodeToURL(input);
        assertEquals(output, "Wizard%2BGandolf");
    }

    @org.junit.jupiter.api.Test
    void encodeToURLTest05() {
        String input = "Wizard/Gandolf";
        String output = encodeToURL(input);
        assertEquals(output, "Wizard%2FGandolf");
    }

    @org.junit.jupiter.api.Test
    void encodeToURLTest06() {
        String input = "Wizard_Gandolf";
        String output = encodeToURL(input);
        assertEquals(output, "Wizard_Gandolf");
    }

    @org.junit.jupiter.api.Test
    void encodeToURLTest07() {
        String input = "Wizard:Gandolf";
        String output = encodeToURL(input);
        assertEquals(output, "Wizard%3AGandolf");
    }

    @org.junit.jupiter.api.Test
    void encodeToURLTest08() {
        String input = "Wizard;Gandolf";
        String output = encodeToURL(input);
        assertEquals(output, "Wizard%3BGandolf");
    }

    @org.junit.jupiter.api.Test
    void encodeToURLTest09() {
        String input = "Wizard.Gandolf";
        String output = encodeToURL(input);
        assertEquals(output, "Wizard.Gandolf");
    }

    @org.junit.jupiter.api.Test
    void encodeToURLTest10() {
        String input = "Wizard,Gandolf";
        String output = encodeToURL(input);
        assertEquals(output, "Wizard%2CGandolf");
    }

    @org.junit.jupiter.api.Test
    void encodeToURLTest11() {
        String input = "Wizard*Gandolf";
        String output = encodeToURL(input);
        assertEquals(output, "Wizard*Gandolf");
    }
}