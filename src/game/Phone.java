package game;

import java.awt.*;
import java.util.Random;

public class Phone {
    private Pixel[][]pixels;
    private String serialNumber;
    private int burnedPixels=0;
    private int totalCount=0;

    public Phone(Pixel[][] pixels) {
        this.pixels = pixels;
       setSerialNumber();
    }

    public void render(Graphics g){
        for (Pixel[] row : pixels) {
            for (Pixel col : row) {
                col.render(g);
            }
        }
    }

    public void setSerialNumber() {
        Random random=new Random();
        String randomS="abcdefg36765323543";
        StringBuilder result=new StringBuilder();
        for (int i = 0; i <10 ; i++) {
            result.append(randomS.charAt(random.nextInt(randomS.length())));
        }
        this.serialNumber = result.toString();
    }

    public Pixel[][] getPixels() {
        return pixels;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void increaseBurnedPixelsCount(){
        this.burnedPixels++;
    }

    public void increaseTotalCount(){
        this.burnedPixels++;
    }

    public int getBurnedPixels() {
        return burnedPixels;
    }

    public int getTotalCount() {
        return totalCount;
    }
}