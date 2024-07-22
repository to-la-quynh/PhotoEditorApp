package com.example.photoeditor.filters;

public class FilterModel {
    String filterName;
    int filterPreviewImg;
    FilterType filterType;

    public FilterModel(String filterName, int filterPreviewImg, FilterType filterType) {
        this.filterName = filterName;
        this.filterPreviewImg = filterPreviewImg;
        this.filterType = filterType;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public int getFilterPreviewImg() {
        return filterPreviewImg;
    }

    public void setFilterPreviewImg(int filterPreviewImg) {
        this.filterPreviewImg = filterPreviewImg;
    }

    public FilterType getFilterType() {
        return filterType;
    }

    public void setFilterType(FilterType filterType) {
        this.filterType = filterType;
    }
}
