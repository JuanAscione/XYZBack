package com.example.xyzhotelddd.domain.amenities;

import jakarta.persistence.Embeddable;

@Embeddable
public class RoomFeatures {

    private boolean hasDoubleBed;
    private boolean hasWifi;
    private boolean hasTV;
    private boolean hasMinibar;
    private boolean hasAirConditioner;
    private boolean hasBathtub;
    private boolean hasTerrace;

    public RoomFeatures(RoomType roomType) {
        if(roomType == RoomType.STANDARD) {
            this.hasDoubleBed = false;
            this.hasWifi = true;
            this.hasTV = true;
            this.hasMinibar = false;
            this.hasAirConditioner = false;
            this.hasBathtub = false;
            this.hasTerrace = false;
        }
        else if (roomType == RoomType.SUPERIOR) {
            this.hasDoubleBed = true;
            this.hasWifi = true;
            this.hasTV = true;
            this.hasMinibar = true;
            this.hasAirConditioner = true;
            this.hasBathtub = false;
            this.hasTerrace = false;
        }
        else if (roomType == RoomType.SUITE) {
            this.hasDoubleBed = true;
            this.hasWifi = true;
            this.hasTV = true;
            this.hasMinibar = true;
            this.hasAirConditioner = true;
            this.hasBathtub = true;
            this.hasTerrace = true;
        }
    }

    public RoomFeatures() {
    }

    public boolean isHasDoubleBed() {
        return hasDoubleBed;
    }

    public void setHasDoubleBed(boolean hasDoubleBed) {
        this.hasDoubleBed = hasDoubleBed;
    }

    public boolean isHasWifi() {
        return hasWifi;
    }

    public void setHasWifi(boolean hasWifi) {
        this.hasWifi = hasWifi;
    }

    public boolean isHasTV() {
        return hasTV;
    }

    public void setHasTV(boolean hasTV) {
        this.hasTV = hasTV;
    }

    public boolean isHasMinibar() {
        return hasMinibar;
    }

    public void setHasMinibar(boolean hasMinibar) {
        this.hasMinibar = hasMinibar;
    }

    public boolean isHasAirConditioner() {
        return hasAirConditioner;
    }

    public void setHasAirConditioner(boolean hasAirConditioner) {
        this.hasAirConditioner = hasAirConditioner;
    }

    public boolean isHasBathtub() {
        return hasBathtub;
    }

    public void setHasBathtub(boolean hasBathtub) {
        this.hasBathtub = hasBathtub;
    }

    public boolean isHasTerrace() {
        return hasTerrace;
    }

    public void setHasTerrace(boolean hasTerrace) {
        this.hasTerrace = hasTerrace;
    }
}
