package BO;

/**
 * 添加候选人波
 *
 * @author 刘家辉
 * @date 2023/02/09
 */
public class AddCandidateBO {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AddCandidateBO{" +
                "name='" + name + '\'' +
                '}';
    }
}
