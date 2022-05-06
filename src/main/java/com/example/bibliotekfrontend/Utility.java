package com.example.bibliotekfrontend;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Utility {

    public String encodeToURL(String inputString) {
        String encodedString = URLEncoder.encode(inputString, StandardCharsets.UTF_8);
        return encodedString;
    }
}
