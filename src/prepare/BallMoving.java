package prepare;

import javafx.scene.input.KeyCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @Description
 * 小球移动
 * @Author rdm
 * @data 2022/4/15 - 9:03
 */
public class BallMoving extends JFrame {
    private MyPanel2 mp2 = null;

    public static void main(String[] args) {
        new BallMoving();
    }

    public BallMoving(){
        mp2 = new MyPanel2();

        this.add(mp2);
        this.setSize(800,600);
        //接受键盘信号
        this.addKeyListener(mp2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}


class MyPanel2 extends JPanel implements KeyListener {
    //为了让小球移动，将x y设置成变量
    int x = 10;
    int y = 10;
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x, y,100,100);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            y += 5;
        }else if(e.getKeyCode() == KeyEvent.VK_UP){
            y -= 5;
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            x -= 5;
        }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            x += 5;
        }

        //面板重绘必须操作
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

