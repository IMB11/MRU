package com.mineblock11.mru;

public class Utilities {
    public static float lerp(float delta, float start, float end) {
        return (1 - delta) * start + delta * end;
    }
}
