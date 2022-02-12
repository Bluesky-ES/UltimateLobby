package me.bluesky.plugin.ultimatelobby.utils;

public enum TimeType {
    DAY(1000),
    NIGHT(18000);

    private int tick;

    private TimeType(int tick) {
        this.tick = tick;
    }

    public int toTick() {
        return this.tick;
    }
}
