package PO;

import DAO.ElectionDAO;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author 刘家辉
 * @date 2023/02/08
 */
public class ElectionPO {
    private String key;
    private   Credentials credentials;
    private Web3j web3j;

    public String getKey() {
        return key;
    }

    public Credentials getCredentials() {
        return credentials;
    }
    public Web3j getWeb3j(){
        return web3j;
    }

    public ElectionPO() throws SQLException, IOException {
        ElectionDAO electionDAO=new ElectionDAO();
        this.credentials=Credentials.create(electionDAO.getKey());
        this.web3j=Web3j.build(new HttpService(electionDAO.getHttp()));
        this.key=electionDAO.getKey();
    }

}
