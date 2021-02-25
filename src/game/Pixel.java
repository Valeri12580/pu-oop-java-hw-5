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

    /**
     * render method
     * @param g instance of Graphics
     */
    public void render(Graphics g) {
        fill(g);
        draw(g);
    }

    /**
     * fill method
     * @param g instance of Graphics
     */
    private void fill(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, PIXEL_SIZE, PIXEL_SIZE);
    }

    /**
     * drawing method
     * @param g instance of Graphics
     */
    private void draw(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(x, y, PIXEL_SIZE, PIXEL_SIZE);
    }

    private void setY(int y) {
        this.y = y * PIXEL_SIZE;
    }

    private void setX(int x) {
        this.x = x * PIXEL_SIZE;
    }

    public Color getColor() {
        return color;
    }

    public State getState() {
        return state;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
