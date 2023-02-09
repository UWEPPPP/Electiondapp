package BO;

/**
 * 登录博
 *
 * @author 刘家辉
 * @date 2023/02/09
 */
public class LoginInBO {
    private String key;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "LoginInBO{" +
                "key='" + key + '\'' +
                '}';
    }
}
