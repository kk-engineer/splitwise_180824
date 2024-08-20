package in.itkaran.splitwise_180824.commands;

import in.itkaran.splitwise_180824.controllers.ExpenseController;
import in.itkaran.splitwise_180824.dtos.SettleUpUserRequestDto;
import in.itkaran.splitwise_180824.dtos.SettleUpUserResponseDto;
import in.itkaran.splitwise_180824.models.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SettleUpUserCommand implements Command {
    private ExpenseController expenseController;

    public SettleUpUserCommand(ExpenseController expenseController) {
        this.expenseController = expenseController;
    }

    @Override
    public boolean matches(String input) {
        System.out.println("Checking if SettleUpUserCommand matches the input: " + input);
        //input - 1234 SettleUp
        // words -> [1234, SettleUp]
        List<String> words = List.of(input.split(" "));
        return words.size() == 2 && words.get(1).equals(CommandKeywords.SETTLE_UP_COMMAND);
    }

    @Override
    public void execute(String input) {
        List<String> words = List.of(input.split(" "));
        Long userId = Long.valueOf(words.get(0));
        SettleUpUserRequestDto requestDto = new SettleUpUserRequestDto();
        requestDto.setUserId(userId);
        List<Transaction> transactions = expenseController.settleUpUser(requestDto);
        for (Transaction transaction : transactions) {
            System.out.println("=====================================");
            System.out.println("From: " + transaction.getFrom());
            System.out.println("To: " + transaction.getTo());
            System.out.println("Amount: " + transaction.getAmount());
            System.out.println("=====================================");
        }
    }
}