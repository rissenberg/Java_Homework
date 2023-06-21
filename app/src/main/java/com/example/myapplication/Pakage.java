package com.example.myapplication;

public class Pakage {
    private int vol;
    private boolean isFragile;
    private String req;
    private PakType type;

    public int getVol() {
        return vol;
    }

    public void setVol(int vol) {
        this.vol = vol;
    }

    public boolean isFragile() {
        return isFragile;
    }

    public void setFragile(boolean fragile) {
        isFragile = fragile;
    }

    public String getReq() {
        return req;
    }

    public void setReq(String req) {
        this.req = req;
    }

    public PakType getType() {
        return type;
    }

    public void setType(PakType type) {
        this.type = type;
    }

    public Pakage() {
        vol = 0;
        isFragile = false;
        req = "";
        type = PakType.DOC;
    }

    public Pakage(int vol, boolean isFragile, String req, PakType type) {
        this.vol = vol;
        this.isFragile = isFragile;
        this.req = req;
        this.type = type;
    }
}
