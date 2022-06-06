package tank;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Description
 * @Author rdm
 * @data 2022/4/14 - 23:28
 */
public class TankGameStart extends JFrame {
    MyPanel mp = null;

    public static void main(String[] args) {
        new TankGameStart();
    }

    //构造器
    public TankGameStart() {
       mp = new MyPanel();
        Thread thread = new Thread(mp); //启动mp线程
        thread.start();
        this.add(mp);
       this.setSize(1300, 950);
       this.addKeyListener(mp);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setVisible(true);
       Recorder.readRecord();
       //关闭窗口监听
       this.addWindowListener(new WindowAdapter() {
           @Override
           public void windowClosing(WindowEvent e) {
               Recorder.saveRecord();
               System.exit(0);
           }
       });
    }
}
