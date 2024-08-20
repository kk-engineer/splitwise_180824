package in.itkaran.splitwise_180824.controllers;

import in.itkaran.splitwise_180824.dtos.*;
import in.itkaran.splitwise_180824.models.Expense;
import in.itkaran.splitwise_180824.models.Transaction;
import in.itkaran.splitwise_180824.services.ExpenseService;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ExpenseController {
    private ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public AddUserExpenseResponseDto addExpense(AddUserExpenseRequestDto requestDto) {
        Expense expense = expenseService.addExpense(
                requestDto.getPaidByUserId(),
                requestDto.getOwedByUserIds(),
                requestDto.getAmount(),
                requestDto.getGroupId(),
                requestDto.getDescription()
        );
        return expense.toAddUserExpenseResponseDto();
    }

    public List<Transaction> settleUpUser(SettleUpUserRequestDto requestDto) {
        return expenseService.settleUpUser(requestDto.getUserId());
//        List<Transaction> transactions =  expenseService.settleUpUser(requestDto.getUserId());
//        List<SettleUpUserResponseDto> responseDtos = new ArrayList<>();
//        for (Transaction transaction : transactions) {
//            responseDtos.add(transaction.toSettleUpUserResponseDto());
//        }
//        return responseDtos;
    }

    public SettleUpGroupResponseDto settleUpGroup(SettleUpGroupRequestDto requestDto) {
        return null;
    }
}
