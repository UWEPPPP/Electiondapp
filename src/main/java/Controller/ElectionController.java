package Controller;

import BO.AddCandidateBO;
import BO.LoginInBO;
import BO.UserChoiceBO;
import DTO.ElectionDTO;
import service.ElectionService;
import Tool.Web3jUtil;
import View.ElectionView;

import java.util.List;
import java.util.Scanner;

/**
 * 选举控制器
 *
 * @author 刘家辉
 * @date 2023/02/08
 */
public class ElectionController {
    static Scanner in =new Scanner(System.in);
    public static void init() throws Exception {
        ElectionService.deploy();
    }

    public static int login(String key) throws Exception {
        LoginInBO login=new LoginInBO();
        login.setKey(key);
       return ElectionService.loginIn(login.getKey());
    }

    public static void enterCommon(int choice) throws Exception {
         switch (choice) {
             case 1:
                 showMan();
                 System.out.println("请选择候选人");
                 UserChoiceBO user=new UserChoiceBO();
                 user.setChoice(in.nextInt());
                 ElectionService.vote(user.getChoice());
                 break;
             case 2:showAll();break;
             case 3:return;
             default:System.out.println("输入错误,请重新输入");enterCommon(in.nextInt());
         }
    }
    public static void enterRoot(int choice) throws Exception {
         switch (choice){
             case 1:
                 System.out.println("请输入要添加候选人的名字");
                 AddCandidateBO addCandidateBO=new AddCandidateBO();
                 addCandidateBO.setName(in.next());
                 ElectionService.addCandidate(addCandidateBO.getName());
                 System.out.println("输入完毕");break;
             case 2:ElectionService.kill();break;
             case 3:return;
             default:System.out.println("输入错误，请重新输入");enterRoot(in.nextInt());
         }
    }
    public static void showAll() throws Exception {
        List<Web3jUtil.CandicateInformationEventResponse> list= ElectionDTO.getAllCandidates();
        ElectionView.resultView(list);
    }
    public static void showMan() throws Exception {
        List<Web3jUtil.CandicateInformationEventResponse> list=ElectionDTO.getAllCandidates();
        ElectionView.manView(list);
    }
}
