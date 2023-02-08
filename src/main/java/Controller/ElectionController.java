package Controller;

/**
 * 选举控制器
 *
 * @author 刘家辉
 * @date 2023/02/08
 */
public class ElectionController {
    public static void init() throws Exception {
        Logic.deploy();
    }
    public static Boolean login() throws Exception {
       return Logic.loginIn();
    }
    public static void run() throws Exception {
        Logic.goRun();
    }
}
