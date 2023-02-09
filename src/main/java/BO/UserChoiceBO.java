package BO;

/**
 * 用户选择博
 *
 * @author 刘家辉
 * @date 2023/02/09
 */
public class UserChoiceBO {
    private int choice;

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    @Override
    public String toString() {
        return "UserChoiceBO{" +
                "choice=" + choice +
                '}';
    }
}
