package tank;

import com.sun.javafx.robot.FXRobotImage;
import sun.java2d.pipe.DrawImage;
import sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.file.FileAlreadyExistsException;
import java.util.Vector;
import java.util.concurrent.ForkJoinPool;

/**
 * @Description
 * 绘图区域
 * @Author rdm
 * @data 2022/4/14 - 23:24
 */
//为了监听键盘实现keylistener
//为了实现子弹的不停刷新模拟射击，需要把MyPanel 实现runnable接口当线程使用
public class MyPanel extends JPanel implements KeyListener , Runnable{
    //定义我的坦克
    PlayerTank hero = null;
    //定义敌方坦克
    Vector<EnemyTank> enemyTanks = new Vector<>(); //放到集合里，并考虑到用多线程选择vector
    int enemyTankSize = 8;
    //定义爆炸特效
    //当子弹击中坦克就加入一个对象
    Vector<Bomb> bombs = new Vector<>();
    //爆炸的三张特效图
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;
    Image image4 = null;
    Image image5 = null;
    Image image6 = null;

    //构造器
    public MyPanel() {
        //初始化主角坦克
        hero = new PlayerTank(500,680);
        //初始化敌方坦克
        for (int i = 0; i < enemyTankSize; i++) {
            EnemyTank enemyTank = new EnemyTank((100 * i), 0);
            enemyTank.setEnemyTanks(enemyTanks);
            //设置方向
            enemyTank.setDirect(3);
            //启动线程
            new Thread(enemyTank).start();
            //给坦克初始化一颗子弹
            enemyTank.setEnemyShot();
            enemyTank.shots.add(enemyTank.shot);
            //加入
            enemyTanks.add(enemyTank);
        }
        //初始化爆炸图片对象
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/b1.png"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/b2.png"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/b3.png"));
        image4 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/b4.png"));
        image5 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/b5.png"));
        image6 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/b6.png"));
    }

    //绘画区
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);
        //历史击杀信息
        historicalKill(g);

        //画出坦克
        if(hero != null && hero.isLive) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);
        }
        //画敌方坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            //取出坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.isLive) { //只有坦克是活得才重绘
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
                //画出所有子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    Shot shot = enemyTank.shots.get(j);
                    if (shot != null && shot.isLive) {
                        drawBullet(shot.x, shot.y, g);
                    } else {
                        enemyTank.shots.remove(j);
                    }
                }
            }
        }

        //画主角射出弹药
        for (int i = 0; i < hero.shots.size(); i++) {
            Shot shot = hero.shots.get(i);
            if (shot != null && shot.isLive) {
                drawBullet(shot.x, shot.y, g);
            } else {
                hero.shots.remove(shot);
            }
        }

        //画爆炸特效
        //防止第一个不爆炸
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < bombs.size(); i++) {
            Bomb b = bombs.get(i);
            if (b.life > 15) {
                g.drawImage(image1, b.x, b.y,60, 60 ,this);
            }else if (b.life > 12){
                g.drawImage(image2, b.x, b.y,60, 60 ,this);
            }else if (b.life > 9){
                g.drawImage(image3, b.x, b.y,60, 60 ,this);
            }else if (b.life > 6){
                g.drawImage(image4, b.x, b.y, 60,60 ,this);
            }else if (b.life > 3){
                g.drawImage(image5, b.x, b.y, 60,60 ,this);
            }else {
                g.drawImage(image6, b.x, b.y, 60,60 ,this);
            }
            b.lifeDown();
            if(b.life == 0) {
                bombs.remove(b);
            }
        }

        if(enemyTanks.size() == 0) {
            g.setColor(Color.red);
            Font font = new Font("宋体", Font.BOLD, 100);
            g.setFont(font);
            g.drawString("胜利！", 350, 300);
        }

        if(hero.isLive == false) {
            g.setColor(Color.red);
            Font font = new Font("宋体", Font.BOLD, 100);
            g.setFont(font);
            g.drawString("失败~", 350, 300);
        }
    }
    /**
     * 画出坦克
     * @param x 坦克左上角x坐标
     * @param y 坦克左上角y坐标
     * @param g 画笔
     * @param direct 坦克方向
     * @param type 坦克类型
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        //根据不同类型的坦克设置颜色
        switch (type){
            case 0: //自己的坦克
                g.setColor(Color.green);
                break;
            case 1://敌人坦克
                g.setColor(Color.yellow);
                break;
        }

        //根据坦克方向绘制坦克
        /**
         * 0：上  1：下  2：左  3：右
         */
        switch (direct) {
            case 0: //表示向上
                g.fill3DRect(x ,y ,10 ,60 ,false); //左轮
                g.fill3DRect(x + 10, y + 10, 20, 40, false); //驱干
                g.fill3DRect(x + 30, y, 10, 60, false); //右轮
                g.fillOval(x + 10, y + 20, 20, 20); //炮台
                g.drawLine(x + 20, y + 30, x + 20, y); //炮筒
                break;
            case 1:
                g.fill3DRect(x ,y ,10 ,60 ,false); //左轮
                g.fill3DRect(x + 10, y + 10, 20, 40, false); //驱干
                g.fill3DRect(x + 30, y, 10, 60, false); //右轮
                g.fillOval(x + 10, y + 20, 20, 20); //炮台
                g.drawLine(x + 20, y + 30, x + 20, y + 60); //炮筒
                break;
            case 2:
                g.fill3DRect(x ,y ,60,10 ,false); //上轮
                g.fill3DRect(x + 10, y + 10, 40, 20, false); //驱干
                g.fill3DRect(x ,y +30, 60, 10, false); //下轮
                g.fillOval(x + 20, y + 10, 20, 20); //炮台
                g.drawLine(x + 40, y + 20, x, y + 20); //炮筒
                break;
            case 3:
                g.fill3DRect(x ,y ,60,10 ,false); //上轮
                g.fill3DRect(x + 10, y + 10, 40, 20, false); //驱干
                g.fill3DRect(x ,y +30, 60, 10, false); //下轮
                g.fillOval(x + 20, y + 10, 20, 20); //炮台
                g.drawLine(x + 40, y + 20, x + 60, y + 20); //炮筒
                break;
        }
    }

    //画子弹
    public void drawBullet(int x, int y, Graphics g){
        g.setColor(Color.white);
        g.fillOval(x, y, 10, 10);
    }

    //三个键盘响应方法
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            hero.setDirect(0);
            hero.moveUp();
        }else if (e.getKeyCode() == KeyEvent.VK_S){
            hero.setDirect(1);
            hero.moveDown();
        }else if (e.getKeyCode() == KeyEvent.VK_A){
            hero.setDirect(2);
            hero.moveleft();
        }else if (e.getKeyCode() == KeyEvent.VK_D){
            hero.setDirect(3);
            hero.moveRight();
        }

        //如果按下J发射子弹
        if(e.getKeyCode() == KeyEvent.VK_J){
            hero.shotEnemy();
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    //敌人坦克被击中的方法
    //在重绘里判断是否击中了敌人坦克
    public boolean hitTank(Shot s , Tank e) {
        //击中坦克的判断
        switch (e.getDirect()) {
            case 0:
            case 1:
                if (s.x > e.getX() && s.x < e.getX() + 40 && s.y > e.getY() && s.y < e.getY() + 60){
                    s.isLive = false;
                    e.isLive = false;
                    if(e instanceof EnemyTank){
                        Recorder.addKillNum();
                    }
                    Bomb bomb = new Bomb(e.getX(), e.getY());
                    bombs.add(bomb);
                    return true;
                }
            case 2:
            case 3:
                if(s.x > e.getX() && s.x < e.getX() + 60 && s.y > e.getY() && s.y < e.getY() + 40){
                    s.isLive = false;
                    e.isLive = false;
                    if(e instanceof EnemyTank){
                        Recorder.addKillNum();
                    }
                    Bomb bomb = new Bomb(e.getX(), e.getY());
                    bombs.add(bomb);
                    return true;
                }
        }
        return false;
    }

    //展示历史击杀数
    public void historicalKill(Graphics g) {
        g.setColor(Color.black);
        Font font = new Font("微软雅黑",Font.BOLD, 25);
        g.setFont(font);
        g.drawString("您 的 历 史 总 击 杀 为 ", 1020, 30);
        drawTank(1020, 60, g, 0, 1);
        g.setColor(Color.black);
        g.drawString(Recorder.getKillNum() + "", 1080, 100);
    }

    //每隔50ms重绘
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //判断是否击中敌人坦克,不确定击中那个坦克，只有遍历
             /*
            在我们可以发射多颗子弹的情况下，我们需要将shots集合里的子弹全部取出
            和所有敌方坦克进行匹配
             */
            for (int i = 0; i < hero.shots.size(); i++) {
                Shot shot = hero.shots.get(i);
                if(shot != null && shot.isLive) {
                    for (int j = 0; j < enemyTanks.size(); j++) {
                        EnemyTank e = enemyTanks.get(j);
                        boolean hit = hitTank(shot, e);
                        if(hit){
                            enemyTanks.remove(e);
                            enemyTankSize++;
                        }
                    }
                }
            }

            //判断敌人子弹是否击中我方坦克
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank e = enemyTanks.get(i);
                for (int j = 0; j < e.shots.size(); j++) {
                    Shot s = e.shots.get(j);
                    boolean hit = hitTank(s, hero);
                    if(hit) {
                        hero.isLive = false;
                    }
                }
            }
            this.repaint();
        }
    }
}
