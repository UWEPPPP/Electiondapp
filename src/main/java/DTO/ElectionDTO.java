package DTO;

import Tool.Web3jUtil;
import service.ElectionService;

import java.util.List;

/**
 * 选举dto
 *
 * @author 刘家辉
 * @date 2023/02/09
 */
public class ElectionDTO {
    public static List<Web3jUtil.CandicateInformationEventResponse> getAllCandidates() throws Exception {
        return ElectionService.getList();
    }
}
