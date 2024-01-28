package com.example.xyzhotelddd.domain.amenities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class Room {

    @Enumerated(EnumType.STRING)
    private RoomType type;

    private Integer pricePerNight;

    @Embedded
    private RoomFeatures features;

    public Room(RoomType type) {
        this.type = type;
        if(type == RoomType.STANDARD) {
            this.pricePerNight = 50;
        }
        else if (type == RoomType.SUPERIOR) {
            this.pricePerNight = 100;
        }
        else if (type == RoomType.SUITE) {
            this.pricePerNight = 200;
        }
        this.features = new RoomFeatures(type);
    }

    public Room() {
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public Integer getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Integer pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public RoomFeatures getFeatures() {
        return features;
    }

    public void setFeatures(RoomFeatures features) {
        this.features = features;
    }
}
