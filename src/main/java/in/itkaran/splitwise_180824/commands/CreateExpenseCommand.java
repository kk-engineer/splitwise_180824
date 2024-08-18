package in.itkaran.splitwise_180824.commands;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class CreateExpenseCommand implements Command {
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
        List<Long> owedUsers = new ArrayList<>();
        int i = 2;
        while (!words.get(i).equals("iPay")) {
            owedUsers.add(Long.valueOf(words.get(i)));
            i++;
        }
        i++;    // Skip "iPay"
        int amount = Integer.parseInt(words.get(i));
        i++;    // Skip "Desc"
        StringBuilder description = new StringBuilder();
        while (i < words.size()) {
            description.append(words.get(i)).append(" ");
            i++;
        }
    }
}
