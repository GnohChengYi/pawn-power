package com.pawnpower;

public class Square {
    private final int x;
    private final int y;

    public Square(String s) {
        this.y = Character.getNumericValue(s.charAt(1));
        this.x = Character.getNumericValue(s.charAt(2));
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
