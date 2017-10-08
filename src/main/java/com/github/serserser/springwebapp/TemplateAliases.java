package com.github.serserser.springwebapp;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum TemplateAliases {
    ADMIN("admin/admin.html"),
    HOME("home"),
    WELCOME("welcome"),
    REGISTRATION("registration"),
    LOGIN("login"),
    USERS("admin/users"),
    USER_DETAILS("admin/userDetails"),
    USER_MODIFY("admin/userModify"),

    DENIED("denied")
    ;

    private final String relativeFileLocation;

    TemplateAliases(String relativeFileLocation) {
        this.relativeFileLocation = relativeFileLocation;
    }

    private String getAlias() {
        return name();
    }

    private String getRelativeFileLocation() {
        return relativeFileLocation;
    }

    public static Map<String, String> getValues() {
        return Arrays.stream(values())
                .collect(Collectors.toMap(TemplateAliases::getAlias, TemplateAliases::getRelativeFileLocation));
    }
}