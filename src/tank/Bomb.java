package tank;

/**
 * @Description
 * @Author rdm
 * @data 2022/4/16 - 16:38
 */
public class Bomb {
    int x,y;
    int life = 18; //生命周期
    boolean isLive = true;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //减少生命
    public void lifeDown() {
        if(life > 0) {
            life --;
        } else {
            isLive = false;
        }
    }
}
