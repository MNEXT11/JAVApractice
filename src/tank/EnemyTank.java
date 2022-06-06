package tank;

import java.util.Vector;

/**
 * @Description
 * @Author rdm
 * @data 2022/4/15 - 12:06
 */
public class EnemyTank extends Tank implements Runnable{
    Vector<Shot> shots = new Vector<>();
    Shot shot = null;
    //敌人坦克相互可以取得队友的vector
    Vector<EnemyTank> enemyTanks = new Vector<>();

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    public Shot setEnemyShot() {
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
        new Thread(shot).start();
        return shot;
    }

    //将MyPanel的敌方坦克合集中的成员取得
    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    //编写方法判断当前敌人坦克是否和其他坦克发生重叠
    public boolean isTouchEnemyTank () {
        switch (this.getDirect()) {
            case 0:
                for (int i = 0; i < enemyTanks.size(); i++){
                    EnemyTank e = enemyTanks.get(i);
                    if (e != this) {
                        if (e.getDirect() == 0 || e.getDirect() == 1){
                            if (this.getX() >= e.getX()
                                    && this.getX() <= e.getX() + 40
                                    && this.getY() >= e.getY()
                                    && this.getY() <= e.getY() + 60){
                                return true;
                            }
                            if(this.getX() + 40 >= e.getX()
                                    && this.getX() + 40 <= e.getX() + 40
                                    && this.getY() >= e.getY()
                                    && this.getY() <= e.getY() + 60){
                                return true;
                            }
                        }
                        if (e.getDirect() == 2 || e.getDirect() == 3){
                            if(this.getX() + 40 >= e.getX()
                                    && this.getX() + 40 <= e.getX() + 60
                                    && this.getY() >= e.getY()
                                    && this.getY() <= e.getY() + 40){
                                return true;
                            }
                            if(this.getX() >= e.getX()
                                    && this.getX() <= e.getX() + 60
                                    && this.getY() >= e.getY()
                                    && this.getY() <= e.getY() + 40){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1:
                for (int i = 0; i < enemyTanks.size(); i++){
                    EnemyTank e = enemyTanks.get(i);
                    if (e != this) {
                        if (e.getDirect() == 0 || e.getDirect() == 1){
                            if (this.getX() >= e.getX()
                                    && this.getX() <= e.getX() + 40
                                    && this.getY() + 60 >= e.getY()
                                    && this.getY() + 60 <= e.getY() + 60){
                                return true;
                            }
                            if(this.getX() + 40 >= e.getX()
                                    && this.getX() + 40 <= e.getX() + 40
                                    && this.getY() + 60 >= e.getY()
                                    && this.getY() + 60 <= e.getY() + 60){
                                return true;
                            }
                        }
                        if (e.getDirect() == 2 || e.getDirect() == 3){
                            if(this.getX() >= e.getX()
                                    && this.getX() <= e.getX() + 60
                                    && this.getY() + 60 >= e.getY()
                                    && this.getY() + 60 <= e.getY() + 40){
                                return true;
                            }
                            if(this.getX() + 40 >= e.getX()
                                    && this.getX() + 40 <= e.getX() + 60
                                    && this.getY() + 60 >= e.getY()
                                    && this.getY() + 60 <= e.getY() + 40){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < enemyTanks.size(); i++){
                    EnemyTank e = enemyTanks.get(i);
                    if (e != this) {
                        if (e.getDirect() == 0 || e.getDirect() == 1){
                            if (this.getX() >= e.getX()
                                    && this.getX() <= e.getX() + 40
                                    && this.getY() >= e.getY()
                                    && this.getY() <= e.getY() + 60){
                                return true;
                            }
                            if(this.getX() >= e.getX()
                                    && this.getX() <= e.getX() + 40
                                    && this.getY() + 40 >= e.getY()
                                    && this.getY() + 40 <= e.getY() + 60){
                                return true;
                            }
                        }
                        if (e.getDirect() == 2 || e.getDirect() == 3){
                            if(this.getX() >= e.getX()
                                    && this.getX() <= e.getX() + 60
                                    && this.getY() >= e.getY()
                                    && this.getY() <= e.getY() + 40){
                                return true;
                            }
                            if(this.getX() >= e.getX()
                                    && this.getX() <= e.getX() + 60
                                    && this.getY() + 40 >= e.getY()
                                    && this.getY() + 40 <= e.getY() + 40){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < enemyTanks.size(); i++){
                    EnemyTank e = enemyTanks.get(i);
                    if (e != this) {
                        if (e.getDirect() == 0 || e.getDirect() == 1){
                            if (this.getX() + 60 >= e.getX()
                                    && this.getX()  + 60 <= e.getX() + 40
                                    && this.getY() >= e.getY()
                                    && this.getY() <= e.getY() + 60){
                                return true;
                            }
                            if(this.getX() + 60 >= e.getX()
                                    && this.getX() + 60 <= e.getX() + 40
                                    && this.getY() + 40 >= e.getY()
                                    && this.getY() + 40 <= e.getY() + 60){
                                return true;
                            }
                        }
                        if (e.getDirect() == 2 || e.getDirect() == 3){
                            if(this.getX() + 60 >= e.getX()
                                    && this.getX() + 60 <= e.getX() + 60
                                    && this.getY() >= e.getY()
                                    && this.getY() <= e.getY() + 40){
                                return true;
                            }
                            if(this.getX() + 60 >= e.getX()
                                    && this.getX() + 60 <= e.getX() + 60
                                    && this.getY() + 40 >= e.getY()
                                    && this.getY() + 40 <= e.getY() + 40){
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public void run() {
        while (true) {

            if(isLive && shots.size() == 0) {
                Shot shot = setEnemyShot();
                shots.add(shot);
            }

            if (! isLive) {
                break;
            }

            int i = (int) (Math.random() * 5);
            switch (i) {
                case 1:
                    setDirect(i - 1);
                    //让他跑一会
                    for (int j = 0; j < 30; j++) {
                        if (!isTouchEnemyTank()){
                            moveUp();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    continue;
                case 2:
                    setDirect(i - 1);
                    for (int j = 0; j < 30; j++) {
                        if (!isTouchEnemyTank()){
                            moveDown();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    continue;
                case 3:
                    setDirect(i - 1);
                    for (int j = 0; j < 30; j++) {
                        if (!isTouchEnemyTank()){
                            moveleft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    continue;
                case 4:
                    setDirect(i - 1);
                    for (int j = 0; j < 30; j++) {
                        if (!isTouchEnemyTank()){
                            moveRight();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    continue;
            }
        }
    }
}
