package com.example.photoeditor.tools;

public class ToolModel {private int toolIconRs;
    private ToolType toolType;

    public ToolModel(int toolIconRs, ToolType toolType) {
        this.toolIconRs = toolIconRs;
        this.toolType = toolType;
    }

    public int getToolIconRs() {
        return toolIconRs;
    }

    public void setToolIconRs(int toolIconRs) {
        this.toolIconRs = toolIconRs;
    }

    public ToolType getToolType() {
        return toolType;
    }

    public void setToolType(ToolType toolType) {
        this.toolType = toolType;
    }

}
