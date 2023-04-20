package com;

public class ModeSetting {

    private int mode;

    public void setMode(boolean normalMode) {
        if (normalMode){
            this.mode = 100000;
        }
        else{
            this.mode = 1000; 
        }
    }

    public int getMode() {
        return this.mode;
    }
}
