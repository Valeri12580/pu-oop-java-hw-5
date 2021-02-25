package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class GameBoard extends JFrame implements MouseListener {
    private Phone[] phones = new Phone[5];
    private CustomStructure<Phone> brokenPhones = new CustomStructure<>(State.BURNED);
    private CustomStructure<Phone> healthyPhones = new CustomStructure<>(State.HEALTHY);

    private int index = 0;


    private Phone currentPhone;

    private Pixel chosenPixel;
    private int counter;

    public GameBoard() throws HeadlessException {
        super.addMouseListener(this);
        generatePhones();
    }


    public void start() {
        initWindow();
        currentPhone = phones[index++];

    }

    private void initWindow() {
        super.setSize(800, 800);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void generatePhones() {
        Random random = new Random();

        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE};
        State[] states = {State.HEALTHY, State.HALF_BURNED, State.BURNED};

        for (int i = 0; i < 5; i++) {
            Phone phone;
            Pixel[][] pixels = new Pixel[8][8];

            for (int row = 0; row < pixels.length; row++) {
                for (int col = 0; col < pixels[row].length; col++) {
                    Color randomColor = generateRandomColor(colors, random);
                    State randomState = generateRandomState(states, random);

                    Pixel pixel = new Pixel(row, col, randomColor, randomState);
                    pixels[row][col] = pixel;
                }
            }

            phone = new Phone(pixels);
            phones[i] = phone;
        }


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        currentPhone.render(g);
    }

    private Color generateRandomColor(Color[] colors, Random random) {
        int i = random.nextInt(3);
        return colors[i];
    }

    private State generateRandomState(State[] state, Random random) {
        int i = random.nextInt(3);
        return state[i];
    }

    private void selectNextPhone() {
        if (index == 5) {
            System.out.println(brokenPhones.toString());
            System.out.println(healthyPhones.toString());
            return;
        }
        this.start();
        super.repaint();
    }


    @Override
    public void mouseClicked(MouseEvent e) {

        int row = e.getY() / Pixel.PIXEL_SIZE;
        int col = e.getX() / Pixel.PIXEL_SIZE;

        Pixel clickedPixel = currentPhone.getPixels()[row][col];

        if (!clickedPixel.equals(chosenPixel)) {
            chosenPixel = clickedPixel;
            counter = 1;
        } else if (!chosenPixel.getColor().equals(Color.BLACK)) {
            counter++;

            if (counter == 3) {
                currentPhone.increaseTotalCount();
                System.out.println(currentPhone.getTotalCount());
                if (chosenPixel.getState().equals(State.HALF_BURNED) || chosenPixel.getState().equals(State.BURNED)) {
                    chosenPixel.setColor(Color.BLACK);
                    currentPhone.increaseBurnedPixelsCount();

                    if (currentPhone.getBurnedPixels() >= 32) {
                        showStatusOfThePhone("broken");
                        this.brokenPhones.addData(currentPhone);
                        this.selectNextPhone();
                    }
                }
                ;

                if (currentPhone.getTotalCount() == 64) {
                    showStatusOfThePhone("healthy");
                    this.healthyPhones.addData(currentPhone);
                    this.selectNextPhone();
                }
                super.repaint();
            }
        }


    }

    private void showStatusOfThePhone(String status) {
        JDialog dialog = new JDialog(this, true);
        dialog.setLayout(new FlowLayout());
        dialog.add(new JLabel(String.format("The phone is: %s", status)));
        dialog.setSize(400, 400);
        dialog.setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
