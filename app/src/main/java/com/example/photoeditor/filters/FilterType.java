package com.example.photoeditor.filters;

import androidx.annotation.NonNull;

public enum FilterType {
    ORIGINAL("Original", 0), AUTO("Auto", 1), CREAM("Cream", 2), DEEP("Deep", 3), FOREST("Forest", 4), CLASSIC("Classic", 5), COZY("Cozy", 6), MEMORY("Menory", 7), GREY_SCALE("Grey scale", 9);

    private String filterName;
    private int val;
    FilterType(String filterName, int val) {
        this.filterName = filterName;
        this.val = val;
    }

    @NonNull
    @Override
    public String toString() {
        return filterName;
    }
}
