package View;

import java.util.List;

/**
 * 选举观点
 *
 * @author 刘家辉
 * @date 2023/02/08
 */
public class ElectionView {
    public static void resultView(List<C> list){
        int i=0;
        for (Election.CandicateInformationEventResponse response : list) {
            System.out.println((++i)+". 选举人 "+response.name+" 投票数 "+response.voteCount);
        }
    }
}
