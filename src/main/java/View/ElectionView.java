package View;

import Tool.Election;

import java.util.List;

/**
 * view层
 *
 * @author 刘家辉
 * @date 2023/02/08
 */
public class ElectionView {
    public static void resultView(List<Election.CandicateInformationEventResponse> list){
        int i=0;
        for (Election.CandicateInformationEventResponse response : list) {
            System.out.println((++i)+". 选举人 "+response.name+" 投票数 "+response.voteCount);
        }
    }
    public static void manView(List<Election.CandicateInformationEventResponse> list){
        int i=0;
        for (Election.CandicateInformationEventResponse response:list) {
            System.out.println((++i)+". 选举人"+response.name);
        }
    }

}
