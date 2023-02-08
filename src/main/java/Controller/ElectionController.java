package Controller;

import BO.ElectionBO;
import Tool.Election;
import View.ElectionView;

import java.util.List;

/**
 * 选举控制器
 *
 * @author 刘家辉
 * @date 2023/02/08
 */
public class ElectionController {
    public static void init() throws Exception {
        ElectionBO.deploy();
    }
    public static Boolean login() throws Exception {
       return ElectionBO.loginIn();
    }
    public static void enterCommon() throws Exception {
         ElectionBO.common();
    }
    public static void enterRoot() throws Exception {
        ElectionBO.root();
    }
    public static void showAll(List<Election.CandicateInformationEventResponse> list){
        ElectionView.resultView(list);
    }
    public static void showMan(List<Election.CandicateInformationEventResponse> list){
        ElectionView.manView(list);
    }
}
