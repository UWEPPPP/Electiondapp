package Tool;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 合约代码的java包装器
 */
@SuppressWarnings("rawtypes")
public class Election extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50600080546001600160a01b031916331790554260015560408051808201909152600581526453616e647960d81b602082015261004c9061009a565b604080518082019091526005815264426c61636b60d81b60208201526100719061009a565b60408051808201909152600481526352656e6f60e01b60208201526100959061009a565b6102b3565b6000546001600160a01b031633146100c55760405163793c003560e01b815260040160405180910390fd5b600480549060006100d58361012e565b909155505060408051606081018252600454808252602080830185815260008486018190529283526002909152929020815181559151909190600182019061011d90826101f4565b506040820151816002015590505050565b60006001820161014e57634e487b7160e01b600052601160045260246000fd5b5060010190565b634e487b7160e01b600052604160045260246000fd5b600181811c9082168061017f57607f821691505b60208210810361019f57634e487b7160e01b600052602260045260246000fd5b50919050565b601f8211156101ef57600081815260208120601f850160051c810160208610156101cc5750805b601f850160051c820191505b818110156101eb578281556001016101d8565b5050505b505050565b81516001600160401b0381111561020d5761020d610155565b6102218161021b845461016b565b846101a5565b602080601f831160018114610256576000841561023e5750858301515b600019600386901b1c1916600185901b1785556101eb565b600085815260208120601f198616915b8281101561028557888601518255948401946001909101908401610266565b50858210156102a35787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b61068b806102c26000396000f3fe608060405234801561001057600080fd5b50600436106100625760003560e01c80630121b93f1461006757806341c0e1b51461007c578063462e91ec146100845780636d4ce63c14610097578063a3ec138d1461009f578063a9a981a3146100d7575b600080fd5b61007a610075366004610320565b6100ee565b005b61007a6101e3565b61007a61009236600461034f565b61021c565b61007a6102b0565b6100c26100ad366004610400565b60036020526000908152604090205460ff1681565b60405190151581526020015b60405180910390f35b6100e060045481565b6040519081526020016100ce565b62015180600154426101009190610446565b1061011e57604051637426baf760e01b815260040160405180910390fd5b3360009081526003602052604090205460ff161561014f57604051637d8e8c1560e11b815260040160405180910390fd5b80158061015d575060045481115b1561017b576040516321af0a2560e21b815260040160405180910390fd5b336000908152600360209081526040808320805460ff191660011790558383526002918290528220018054916101b08361045f565b909155505060405181907ffff3c900d938d21d0990d786e819f29b8d05c1ef587b462b939609625b684b1690600090a250565b6000546001600160a01b0316331461020e5760405163793c003560e01b815260040160405180910390fd5b6000546001600160a01b0316ff5b6000546001600160a01b031633146102475760405163793c003560e01b815260040160405180910390fd5b600480549060006102578361045f565b909155505060408051606081018252600454808252602080830185815260008486018190529283526002909152929020815181559151909190600182019061029f9082610501565b506040820151816002015590505050565b60015b600454811161031d576000818152600260208190526040918290209081015491517fb097a8e77b663f746fdf364074d167eedc4fc6b1c565da5e5acf7dfdfc34b0c09261030392600101916105c1565b60405180910390a1806103158161045f565b9150506102b3565b50565b60006020828403121561033257600080fd5b5035919050565b634e487b7160e01b600052604160045260246000fd5b60006020828403121561036157600080fd5b813567ffffffffffffffff8082111561037957600080fd5b818401915084601f83011261038d57600080fd5b81358181111561039f5761039f610339565b604051601f8201601f19908116603f011681019083821181831017156103c7576103c7610339565b816040528281528760208487010111156103e057600080fd5b826020860160208301376000928101602001929092525095945050505050565b60006020828403121561041257600080fd5b81356001600160a01b038116811461042957600080fd5b9392505050565b634e487b7160e01b600052601160045260246000fd5b8181038181111561045957610459610430565b92915050565b60006001820161047157610471610430565b5060010190565b600181811c9082168061048c57607f821691505b6020821081036104ac57634e487b7160e01b600052602260045260246000fd5b50919050565b601f8211156104fc57600081815260208120601f850160051c810160208610156104d95750805b601f850160051c820191505b818110156104f8578281556001016104e5565b5050505b505050565b815167ffffffffffffffff81111561051b5761051b610339565b61052f816105298454610478565b846104b2565b602080601f831160018114610564576000841561054c5750858301515b600019600386901b1c1916600185901b1785556104f8565b600085815260208120601f198616915b8281101561059357888601518255948401946001909101908401610574565b50858210156105b15787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b6040815260008084546105d381610478565b80604086015260606001808416600081146105f5576001811461060f57610640565b60ff1985168884015283151560051b880183019550610640565b8960005260208060002060005b868110156106375781548b820187015290840190820161061c565b8a018501975050505b5050505050602092909201929092529291505056fea2646970667358221220b2e6304c1a6e4038f2737964023c6669d207ac82182954ed56f614585aeccc0364736f6c63430008110033";

    public static final String FUNC_ADDCANDIDATE = "addCandidate";

    public static final String FUNC_CANDIDATECOUNT = "candidateCount";

    public static final String FUNC_GET = "get";

    public static final String FUNC_KILL = "kill";

    public static final String FUNC_VOTE = "vote";

    public static final String FUNC_VOTERS = "voters";

    public static final Event CANDICATEINFORMATION_EVENT = new Event("candicateInformation", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event VOTEDEVENT_EVENT = new Event("votedEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}));
    ;

    @Deprecated
    protected Election(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Election(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Election(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Election(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<CandicateInformationEventResponse> getCandicateInformationEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CANDICATEINFORMATION_EVENT, transactionReceipt);
        ArrayList<CandicateInformationEventResponse> responses = new ArrayList<CandicateInformationEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            CandicateInformationEventResponse typedResponse = new CandicateInformationEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.name = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.voteCount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<CandicateInformationEventResponse> candicateInformationEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, CandicateInformationEventResponse>() {
            @Override
            public CandicateInformationEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CANDICATEINFORMATION_EVENT, log);
                CandicateInformationEventResponse typedResponse = new CandicateInformationEventResponse();
                typedResponse.log = log;
                typedResponse.name = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.voteCount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<CandicateInformationEventResponse> candicateInformationEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CANDICATEINFORMATION_EVENT));
        return candicateInformationEventFlowable(filter);
    }

    public List<VotedEventEventResponse> getVotedEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(VOTEDEVENT_EVENT, transactionReceipt);
        ArrayList<VotedEventEventResponse> responses = new ArrayList<VotedEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            VotedEventEventResponse typedResponse = new VotedEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._candidateId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<VotedEventEventResponse> votedEventEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, VotedEventEventResponse>() {
            @Override
            public VotedEventEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(VOTEDEVENT_EVENT, log);
                VotedEventEventResponse typedResponse = new VotedEventEventResponse();
                typedResponse.log = log;
                typedResponse._candidateId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<VotedEventEventResponse> votedEventEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(VOTEDEVENT_EVENT));
        return votedEventEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> addCandidate(String _name) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDCANDIDATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> candidateCount() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CANDIDATECOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> get() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_GET, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> kill() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_KILL, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> vote(BigInteger _candidateID) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_VOTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_candidateID)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> voters(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_VOTERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    @Deprecated
    public static Election load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Election(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Election load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Election(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Election load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Election(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Election load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Election(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Election> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Election.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Election> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Election.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Election> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Election.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Election> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Election.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class CandicateInformationEventResponse extends BaseEventResponse {
        public String name;

        public BigInteger voteCount;
    }

    public static class VotedEventEventResponse extends BaseEventResponse {
        public BigInteger _candidateId;
    }
}
