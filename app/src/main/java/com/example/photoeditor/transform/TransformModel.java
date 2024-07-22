package com.example.photoeditor.transform;

public class TransformModel {
    private int transformIconRs;
    private TransformType transFormType;

    public TransformModel(int transformIconRs, TransformType transFormType) {
        this.transformIconRs = transformIconRs;
        this.transFormType = transFormType;
    }

    public int getTransformIconRs() {
        return transformIconRs;
    }

    public void setTransformIconRs(int transformIconRs) {
        this.transformIconRs = transformIconRs;
    }

    public TransformType getTransFormType() {
        return transFormType;
    }

    public void setTransFormType(TransformType transFormType) {
        this.transFormType = transFormType;
    }
}
