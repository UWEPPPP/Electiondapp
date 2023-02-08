package Controller;

import BO.ElectionBO;
import Tool.Web3jUtil;
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
    public static void showAll(List<Web3jUtil.CandicateInformationEventResponse> list){
        ElectionView.resultView(list);
    }
    public static void showMan(List<Web3jUtil.CandicateInformationEventResponse> list){
        ElectionView.manView(list);
    }
}
