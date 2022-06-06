package tank;

/**
 * @Description
 * @Author rdm
 * @data 2022/4/15 - 20:54
 */
//射击子弹
public class Shot implements Runnable{
    int x; //子弹坐标
    int y;
    int direct;
    int speed = 15;
    boolean isLive = true;

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    //射击行为
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //根据方向改坐标
            switch (direct) {
                case 0:
                    y -= speed;
                    break;
                case 1:
                    y += speed;
                    break;
                case 2:
                    x -= speed;
                    break;
                case 3:
                    x += speed;
                    break;
            }

            //子弹移动到边界或者碰到敌人坦克时就要销毁
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750 && isLive)) {
                isLive = false;
                break;
            }
        }
    }
}
