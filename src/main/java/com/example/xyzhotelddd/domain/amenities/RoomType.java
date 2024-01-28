package com.example.xyzhotelddd.domain.amenities;

public enum RoomType {
    STANDARD,
    SUPERIOR,
    SUITE;

    public static RoomType fromString(String value) {
        try {
            return RoomType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }
}
