package in.itkaran.splitwise_180824.commands;

import in.itkaran.splitwise_180824.controllers.ExpenseController;
import in.itkaran.splitwise_180824.dtos.AddUserExpenseRequestDto;
import in.itkaran.splitwise_180824.dtos.AddUserExpenseResponseDto;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class CreateExpenseCommand implements Command {
    private ExpenseController expenseController;

    public CreateExpenseCommand(ExpenseController expenseController) {
        this.expenseController = expenseController;
    }

    @Override
    public boolean matches(String input) {
        System.out.println("Checking if CreateExpenseCommand matches the input: " + input);
        List<String> words = List.of(input.split(" "));
        return words.get(1).equals(CommandKeywords.CREATE_EXPENSE_COMMAND);
    }

    @Override
    public void execute(String input) {
        // sample input: u1 Expense u2 u3 u4 iPay 1000 Desc Last night dinner
        List<String> words = List.of(input.split(" "));
        Long paidUserId = Long.valueOf(words.get(0));
        System.out.println("Paid User ID: " + paidUserId);
        List<Long> owedUsers = new ArrayList<>();
        int i = 2;
        while (!words.get(i).equals("iPay")) {
            owedUsers.add(Long.valueOf(words.get(i)));
            i++;
        }
        System.out.println("Owed Users: " + owedUsers);
        i++;    // Skip "iPay"
        int amount = Integer.parseInt(words.get(i));
        System.out.println("Amount: " + amount);
        while (!words.get(i).equals("Desc")) i++;
        // Skip "Desc"
        i++;
        StringBuilder description = new StringBuilder();
        while (i < words.size()) {
            description.append(words.get(i)).append(" ");
            i++;
        }
        System.out.println("Description: " + description);

        // call the Expense Controller to create the expense
        AddUserExpenseRequestDto requestDto = new AddUserExpenseRequestDto();
        requestDto.setPaidByUserId(paidUserId);
        requestDto.setOwedByUserIds(owedUsers);
        requestDto.setAmount(amount);
        requestDto.setDescription(description.toString());
        AddUserExpenseResponseDto responseDto = expenseController.addExpense(requestDto);
        System.out.println(responseDto);
    }
}
