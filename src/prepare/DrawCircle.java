package prepare;

import javax.swing.*;
import java.awt.*;

/**
 * @Description
 * 在面板上画圆
 * @Author rdm
 * @data 2022/4/14 - 22:34
 */
@SuppressWarnings("all")
public class DrawCircle extends JFrame{  //jframe对应窗口
    private MyPanel mp = null;

    public static void main(String[] args) {
        new DrawCircle();
    }


    public DrawCircle() {
        //初始化面板
        mp = new MyPanel();
        //把面板放入到窗口里
        this.add(mp);
        //设置窗口大小
        this.setSize(1920, 1080);
        //点击x程序退出
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true); //可以显示
    }

}

//1.先定义一个面板MyPanel，继承Jpanel类，画图形，就在面板上画
class MyPanel extends JPanel{
    @Override
    public void paint(Graphics g) {             //绘图方法，Graphics g 理解成画笔,自动调用
        super.paint(g);         //调用父类的方法完成初始化
        //System.out.println("方法被调用");
        g.drawOval(100, 100, 100, 100);

        //绘制不同图形
        g.drawLine(10, 10, 100, 100);  //起点到终点
        g.drawRect(10, 10, 100, 100);
        //设置画笔颜色
        g.setColor(Color.blue);
        g.fillRect(10, 10, 100, 100);
        g.setColor(Color.red);
        g.fillOval(100, 100, 100, 100);

        // (/地平线5.jpeg)表示在根目录的图片
        Image image = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/地平线5.jpeg"));
        g.drawImage(image, 0, 0, 1920,1080,this);

        g.setColor(Color.green);
        g.setFont(new Font("宋体", Font.BOLD, 50));

        //100 100 是左下角
        g.drawString("山西总冠军", 100, 100);
    }
}
