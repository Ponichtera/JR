package gameOf2048;

import java.awt.*;

public class Tile {
    private int value;

    public Tile() {
        value = 0;
    }

    public Tile(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isEmpty() {
        return value == 0;
    }

    public Color getFontColor() {
        return value < 16 ? new Color(0x776E65) : new Color(0xF9F6F2);
    }

    public Color getTileColor() {
        switch (value) {
            case 0 :
                return new Color(0xCDC1B4);
            case 2 :
                return new Color(0xEEE4DA);
            case 4 :
                return new Color(0xEDE0C8);
            case 8 :
                return new Color(0xF2B179);
            case 16 :
                return new Color(0xF59563);
            case 32 :
                return new Color(0xF67C5F);
            case 64 :
                return new Color(0xF65E3B);
            case 128 :
                return new Color(0xEDCF72);
            case 256:
                return new Color(0xEDCC61);
            case 512 :
                return new Color(0xEDC850);
            case 1024 :
                return new Color(0xEDC53F);
            case 2048 :
                return new Color(0xEDC22E);
            default:
                return new Color(0xFF0000);
        }
    }
}
