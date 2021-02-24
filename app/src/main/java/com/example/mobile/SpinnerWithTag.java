package com.example.mobile;

public class SpinnerWithTag {
    public String string;
    public Integer tag;

    public SpinnerWithTag(String stringPart, Integer tagPart) {
        string = stringPart;
        tag = tagPart;
    }

    @Override
    public String toString() {
        return string;
    }
}
