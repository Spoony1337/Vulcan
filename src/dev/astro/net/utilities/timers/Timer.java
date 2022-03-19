package dev.astro.net.utilities.timers;

import lombok.Getter;

public abstract class Timer {

    @Getter protected long defaultCooldown;

    public Timer(long defaultCooldown) {
        this.defaultCooldown = defaultCooldown;
    }
}