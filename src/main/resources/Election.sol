// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.8.0;

contract Election{

    address owner;
    uint deadLine;
    //偶选人
    struct Candidate{
        uint id;
        string name;
        uint voteCount;//投票数
    }

    //投票事件
    event votedEvent(
        uint indexed _candidateId//索引用于往后的过滤
    );

    //获取信息
    event candicateInformation(
        string name,
        uint voteCount
    );

    //存储候选人
    mapping (uint=>Candidate) candidates;
    //存储投票者是否投票
    mapping (address=>bool)public voters;

    //总候选人
    uint public candidateCount;

    constructor(){
        owner=msg.sender;
        deadLine=block.timestamp;
        addCandidate("Sandy");
        addCandidate("Black");
        addCandidate("Reno");

    }


    modifier OnlyOwner{
        if(msg.sender!=owner )
            revert NoRight();
        _;
    }


    modifier VoteRule{
        if((block.timestamp-deadLine)>= 1 days )
            revert DeadlinePast();
        if(voters[msg.sender])
            revert RepeatVoting();
        _;

    }

    //添加候选人
    function addCandidate(string memory _name) public OnlyOwner{
        candidateCount ++;
        candidates[candidateCount]=Candidate(candidateCount,_name,0);
    }
    function get() public  {
        for(uint i=1;i<=candidateCount;i++)
            emit candicateInformation(candidates[i].name,candidates[i].voteCount );
    }
    //投票
    function vote(uint _candidateID) public VoteRule{
        if(_candidateID<=0||_candidateID>candidateCount)
            revert WrongID();
        //过滤掉不合法的情况
        voters[msg.sender]=true;
        candidates[_candidateID].voteCount++;
        emit votedEvent(_candidateID);

    }

    //自毁
    function kill() public OnlyOwner{
        selfdestruct(payable(owner));
    }

    error WrongID();
    error RepeatVoting();
    error NoRight();
    error DeadlinePast();
}