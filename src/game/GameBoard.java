package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class GameBoard extends JFrame implements MouseListener {

    private Pixel[][]pixels=new Pixel[8][8];

    public GameBoard() throws HeadlessException {
        super.addMouseListener(this);
    }



    public void start(){
        generateFields();
        initWindow();

    }

    private void initWindow(){
        super.setSize(800, 800);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void generateFields(){
        Random random=new Random();

        Color[]colors={Color.RED,Color.GREEN,Color.BLUE};
        State[]states={State.HEALTHY,State.HALF_BURNED,State.BURNED};

        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[row].length; col++) {
                Color randomColor=generateRandomColor(colors,random);
                State randomState=generateRandomState(states,random);
                Pixel pixel=new Pixel(row,col,randomColor,randomState);
                pixels[row][col]=pixel;
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Pixel[] row : pixels) {
            for (Pixel col : row) {
                col.render(g);
            }
        }
    }

    private Color generateRandomColor(Color[]colors, Random random){
        int i = random.nextInt(3);
        return colors[i];
    }

    private State generateRandomState(State[]state,Random random){
        int i = random.nextInt(3);
        return state[i];
    }


    @Override
    public void mouseClicked(MouseEvent e) {

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
