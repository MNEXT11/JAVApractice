package tank;

/**
 * @Description
 * @Author rdm
 * @data 2022/4/14 - 23:21
 */
public class Tank {
    boolean isLive = true;
    private int x;
    private int y;
    private int direct;
    private int speed = 5;//坦克方向 0：上  1：下  2：左  3：右

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }



    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    //上下左右移动
    public void moveUp(){
        if (this.y > 0) {
            y -= speed;
        } else {
            setDirect(1);
        }
    }
    public void moveDown(){
        if (this.y < 690) {
            y += speed;
        } else {
            setDirect(0);
        }
    }
    public void moveleft(){
        if (this.x > 0) {
            x -= speed;
        } else {
            setDirect(3);
        }
    }
    public void moveRight(){
        if (this.x < 940) {
            x += speed;
        } else {
            setDirect(2);
        }
    }
}
