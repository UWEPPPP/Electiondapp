import Controller.ElectionController;

import java.util.Objects;
import java.util.Scanner;

/**
 * @author 刘家辉
 */
public class Start {


    /**
     * 简易模拟了一个启动器来启动
     *
     */
    public static void main(String[] args) throws Exception {
        Scanner in =new Scanner(System.in);
        //初始化
        ElectionController.init();
        int userChoice;
        String userChoiceAgain;
        do {
            //登录
            System.out.println("请输入密钥以验证你的身份");
            do {
                userChoice=ElectionController.login(in.next());
            }while (userChoice==3);
            //根据返回判断身份
            do{
                if(userChoice==1){
                System.out.println("用户你好 请选择1.投票 2.查看选票人");
                ElectionController.enterCommon(in.nextInt());
            }else{
                System.out.println("管理员你好 请选择1.添加候选人 2.合约自毁");
                ElectionController.enterRoot(in.nextInt());
            }
            System.out.println("是否继续使用？(y or n)");
            userChoiceAgain= in.next();
            }while(!Objects.equals(userChoiceAgain, "n"));
            System.out.println("要切换账号吗(y or n) --便于测试 密钥合集就在根目录下");
            if(Objects.equals(in.next(),"n")) {
                return;
            }
        }while (true);
    }
}