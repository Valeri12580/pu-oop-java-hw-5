package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Board extends JFrame implements MouseListener {
    private Phone[] phones = new Phone[5];
    private CustomStructure<Phone> brokenPhones = new CustomStructure<>(State.BURNED);
    private CustomStructure<Phone> healthyPhones = new CustomStructure<>(State.HEALTHY);

    private int phoneIndex = 0;


    private Phone currentPhone;

    private Pixel chosenPixel;

    private int clickCounter;

    public Board() throws HeadlessException {
        super.addMouseListener(this);
        generatePhones();
    }


    /**
     * start
     */
    public void start() {
        currentPhone = phones[phoneIndex++];
        initWindow();


    }

    /*
    init of windows
     */
    private void initWindow() {
        super.setSize(800, 800);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setTitle("Serial number --- " + currentPhone.getSerialNumber());
    }

    /*
    generation of phones
     */
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

    /**
     * generate color from array of colors
     * @param colors array of colors
     * @param random random instance
     * @return random color
     */
    private Color generateRandomColor(Color[] colors, Random random) {
        int i = random.nextInt(3);
        return colors[i];
    }

    /**
     * generate state from array of states
     * @param state array of states
     * @param random random instance
     * @return random state
     */
    private State generateRandomState(State[] state, Random random) {
        int i = random.nextInt(3);
        return state[i];
    }

    /**
     * select next phone
     */
    private void selectNextPhone() {
        if (phoneIndex == 5) {
            System.out.println(brokenPhones.toString());
            System.out.println(healthyPhones.toString());
            return;
        }
        this.start();
        super.repaint();
    }


    /**
     * event handler
     * @param e event
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        int row = e.getY() / Pixel.PIXEL_SIZE;
        int col = e.getX() / Pixel.PIXEL_SIZE;

        Pixel clickedPixel = currentPhone.getPixels()[row][col];

        if (!clickedPixel.equals(chosenPixel)) {
            chosenPixel = clickedPixel;
            clickCounter = 1;
        } else if (!chosenPixel.getColor().equals(Color.BLACK)) {
            clickCounter++;

            if (clickCounter == 3) {
                currentPhone.increaseTotalClickCount();

                if (chosenPixel.getState().equals(State.HALF_BURNED) || chosenPixel.getState().equals(State.BURNED)) {
                    chosenPixel.setColor(Color.BLACK);
                    currentPhone.increaseBurnedPixelsCount();
                    checkForBrokenPhoneConditions();
                }
                checkForHealthyPhoneConditions();
                super.repaint();
            }
        }


    }

    /**
     * check for healthy phone conditions
     */
    private void checkForHealthyPhoneConditions() {
        if (currentPhone.getTotalCount() == 64) {
            showStatusOfThePhone("healthy");
            this.healthyPhones.addData(currentPhone);
            this.selectNextPhone();
        }
    }

    /**
     * check for broken phone conditions
     */
    private void checkForBrokenPhoneConditions() {
        if (currentPhone.getBurnedPixels() >= 32) {
            showStatusOfThePhone("broken");
            this.brokenPhones.addData(currentPhone);
            this.selectNextPhone();
        }
    }

    /**
     * show the status of the phone
     * @param status broken or healthy
     */
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
