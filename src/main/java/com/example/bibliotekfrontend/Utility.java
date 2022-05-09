package com.example.bibliotekfrontend;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Utility {

    public String encodeToURL(String inputString) {
        return URLEncoder.encode(inputString, StandardCharsets.UTF_8);
    }
}
