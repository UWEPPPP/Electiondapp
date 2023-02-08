import Controller.ElectionController;

import java.util.Objects;

/**
 * @author 刘家辉
 */
public class Start {
    public static void main(String[] args) {
        ElectionController.init();
        do {
            System.out.println("欢迎登录投票系统");
            System.out.println("请输入你的密钥来验证身份");
            if(ElectionController.login()){
                System.out.println("登录成功 用户");
            }else {
                System.out.println("登录成功 管理员");
            }
            System.out.println("要切换账号吗(y or n)");
            String choice=in.next();
            if(Objects.equals(choice, "n")) {
                return;
            }
        }while (true);
    }
}
