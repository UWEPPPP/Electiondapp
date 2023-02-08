package BO;

import Controller.ElectionController;
import PO.ElectionPO;
import Tool.Web3jUtil;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * 选举博
 *
 * @author 刘家辉
 * @date 2023/02/08
 */
public class ElectionBO {
    static String contractAddress;
    static Web3jUtil user;
    static Web3jUtil owner;
    static Scanner in =new Scanner(System.in);
    static org.web3j.protocol.Web3j web3j;
    static String ownerKey;
    /**
     * 部署
     *
     */
    public static void deploy() throws Exception {
        ElectionPO election =new ElectionPO();
        ownerKey=election.getKey();
        web3j=election.getWeb3j();
        Credentials credentials= election.getCredentials();
        owner= Web3jUtil.deploy(web3j,credentials,new DefaultGasProvider()).send();
        contractAddress=owner.getContractAddress();
    }


    public static Boolean loginIn()  {
        int spilt;
        do{
            spilt=0;
        String key=in.next();
        Credentials credentials=Credentials.create(key);
            if(!Objects.equals(key, ownerKey)){
               try{
                  user= Web3jUtil.load(contractAddress,web3j,credentials,new DefaultGasProvider());
                  }catch (RuntimeException rte){
                   spilt=1;
                   System.out.println("输入密钥错误，请重新输入");
                   }
                   if(spilt==0) {
                         return true;
                   }
             }else{
            System.out.println("管理员你好");
            return false;
                  }
        }while (true);
        }

    /**
     * 根
     *
     */
    public static void root() throws Exception {
            int choice= in.nextInt();
            switch (choice){
                case 1:addCandidate();break;
                case 2:kill();break;
                default:System.out.println("输入数据无效,请重新输入");root();
    }
    }
    public static void common() throws Exception {
        int choice = in.nextInt();
        switch (choice) {
            case 1: vote();break;
            case 2: getAll();break;
            default: System.out.println("输入数据无效,请重新输入");common();
        }
    }


    public static List<Web3jUtil.CandicateInformationEventResponse> getList() throws Exception {
        return user.getCandicateInformationEvents(user.get().send());
    }


    public static void getAll() throws Exception {
        ElectionController.showAll(getList());
    }


    public static void vote() throws Exception {
        ElectionController.showMan(getList());
        System.out.println("请根据序号选择要投票的人");
        int choice = in.nextInt();
        try {
            user.vote(BigInteger.valueOf(choice)).send();
        }catch (TransactionException e){
            System.out.println("输入非法");
            vote();
        }
    }


    public static void kill() throws Exception {
        owner.kill().send();
    }


    public static void addCandidate() throws Exception {
        System.out.println("输入要添加的候选者");
        String name=in.next();
        owner.addCandidate(name).send();
        System.out.println("输入完毕");
    }
}

