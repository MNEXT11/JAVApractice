package prepare;

/**
 * @Description
 * @Author rdm
 * @data 2022/4/15 - 14:23
 */
public class CpuNum {
    public static void main(String[] args) {

        //电脑cpu核心数量
        Runtime runtime = Runtime.getRuntime();
        int cpuNum = runtime.availableProcessors();
        System.out.println(cpuNum);
    }
}
