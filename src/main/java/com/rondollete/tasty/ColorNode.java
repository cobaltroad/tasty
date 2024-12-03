package com.rondollete.tasty;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class ColorNode {

    @Getter
    private int locX;

    @Getter
    private int locY;

    public ColorNode(int locX, int locY) {
        this.locX = locX;
        this.locY = locY;
    }
}
