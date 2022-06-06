package tank;

import java.io.*;

/**
 * @Description
 * 记录玩家信息
 * @Author rdm
 * @data 2022/4/17 - 18:56
 */
public class Recorder {
    private static int killNum = 0;
    //定义IO对象
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String recordFile = "src\\击杀数.txt";

    public static int getKillNum() {
        return killNum;
    }

    public static void setKillNum(int killNum) {
        Recorder.killNum = killNum;
    }

    //我方击毁一辆敌方坦克killnum++
    public static void addKillNum(){
        Recorder.killNum++;
    }

    //退出游戏将击杀数保存到文件中
    public static void saveRecord () {
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(killNum);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bw != null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //进入游戏读取击杀信息
    public static void readRecord() {
        try {
            br = new BufferedReader(new FileReader(recordFile));
            int num = br.read();
            setKillNum(num);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
