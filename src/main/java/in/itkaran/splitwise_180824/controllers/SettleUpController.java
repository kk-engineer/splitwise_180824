package in.itkaran.splitwise_180824.controllers;

import in.itkaran.splitwise_180824.dtos.SettleUpGroupRequestDto;
import in.itkaran.splitwise_180824.dtos.SettleUpGroupResponseDto;
import in.itkaran.splitwise_180824.dtos.SettleUpUserRequestDto;
import in.itkaran.splitwise_180824.dtos.SettleUpUserResponseDto;
import in.itkaran.splitwise_180824.models.Expense;
import in.itkaran.splitwise_180824.services.SettleUpService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SettleUpController {
    private SettleUpService settleUpService;

    public SettleUpController(SettleUpService settleUpService) {
        this.settleUpService = settleUpService;
    }

    public SettleUpUserResponseDto settleUpUser(SettleUpUserRequestDto requestDto) {
        List<Expense> expenses = settleUpService.settleUpUser(requestDto.getUserId());
        SettleUpUserResponseDto responseDto = new SettleUpUserResponseDto();
        responseDto.setExpenses(expenses);
        return responseDto;
    }

    public SettleUpGroupResponseDto settleUpGroup(SettleUpGroupRequestDto requestDto) {
        return null;
    }
}
