package tank;

import java.util.Vector;

/**
 * @Description
 * @Author rdm
 * @data 2022/4/14 - 23:23
 */
public class PlayerTank extends Tank{
    //定义一个shot对象,表示一个射击行为（线程）
    Shot shot = null;
    //可以发射多颗子弹
    Vector<Shot> shots = new Vector<>();

    public PlayerTank(int x, int y) {
        super(x, y);
    }

    public void shotEnemy() {
        //子弹限制
        if (shots.size() == 5){
            return;
        }
        //创建shot对象,根据当前坦克的位置和方向来创建
        switch (getDirect()) {
            case 0:
                shot = new Shot(getX() + 15, getY(), 0);
                break;
            case 1:
                shot = new Shot(getX() + 15, getY() + 60, 1);
                break;
            case 2:
                shot = new Shot(getX(), getY() + 15, 2);
                break;
            case 3:
                shot = new Shot(getX() + 60, getY() + 15, 3);
                break;
        }
        //新建的shot放入shots中
        shots.add(shot);
        //启动射击线程
        new Thread(shot).start();
    }
}
