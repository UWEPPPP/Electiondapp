package service;

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
public class ElectionService {
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


    public static int loginIn(String key)  {
        Credentials credentials=Credentials.create(key);
        if(!Objects.equals(key, ownerKey)){
               try{
                  user= Web3jUtil.load(contractAddress,web3j,credentials,new DefaultGasProvider());
                  }catch (RuntimeException rte){
                   System.out.println("输入密钥错误，请重新输入");
                   return 3;
                   }
            return 1;
          }else{
            System.out.println("管理员你好");
            return 2;
          }
    }



    public static List<Web3jUtil.CandicateInformationEventResponse> getList() throws Exception {
        return user.getCandicateInformationEvents(user.get().send());
    }



    public static void vote(int choice) throws Exception {
        try {
            user.vote(BigInteger.valueOf(choice)).send();
        }catch (TransactionException e){
            System.out.println("输入非法,原因可能是你已经投过票了或者不存在该投票人");
        }
    }


    public static void kill() throws Exception {
        owner.kill().send();
    }

    public static void addCandidate(String name) throws Exception {
        owner.addCandidate(name).send();
    }
}

