package game;

import java.awt.*;

public class Pixel {
    public static final int PIXEL_SIZE = 100;
    private int y;
    private int x;
    private Color color;
    private State state;

    public Pixel(int y, int x, Color color, State state) {
        setY(y);
        setX(x);
        this.color = color;
        this.state = state;
    }

    public void render(Graphics g) {
        fill(g);
        draw(g);
    }

    private void fill(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, PIXEL_SIZE, PIXEL_SIZE);
    }

    private void draw(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(x, y, PIXEL_SIZE, PIXEL_SIZE);
    }

    public void setY(int y) {
        this.y = y * PIXEL_SIZE;
    }

    public void setX(int x) {
        this.x = x * PIXEL_SIZE;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setState(State state) {
        this.state = state;
    }
}
