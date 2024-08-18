package in.itkaran.splitwise_180824.services;

import in.itkaran.splitwise_180824.models.Expense;
import in.itkaran.splitwise_180824.models.ExpenseUser;
import in.itkaran.splitwise_180824.models.Group;
import in.itkaran.splitwise_180824.models.User;
import in.itkaran.splitwise_180824.repositories.ExpenseRepository;
import in.itkaran.splitwise_180824.repositories.ExpenseUserRepository;
import in.itkaran.splitwise_180824.repositories.GroupRepository;
import in.itkaran.splitwise_180824.repositories.UserRepository;
import in.itkaran.splitwise_180824.services.strategies.settleup.HeapSettleUpStrategy;
import in.itkaran.splitwise_180824.services.strategies.settleup.SettleUpStrategy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SettleUpService {
    private final UserRepository userRepository;
    private final ExpenseUserRepository expenseUserRepository;
    private SettleUpStrategy settleUpStrategy;
    private final GroupRepository groupRepository;
    private final ExpenseRepository expenseRepository;

    public SettleUpService(UserRepository userRepository,
                           ExpenseUserRepository expenseUserRepository,
                           GroupRepository groupRepository,
                           ExpenseRepository expenseRepository) {
        this.userRepository = userRepository;
        this.expenseUserRepository = expenseUserRepository;
        this.groupRepository = groupRepository;
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> settleUpUser(Long userId) {
        settleUpStrategy = new HeapSettleUpStrategy();
        /*
        1. Get the user with the given userId.
        2. Get all the expenses for this user.
        3. Iterate through all the expenses, and find out total extra/lesser amount
        paid by every user involved in the expenses.
        4. Implement Min/Max Heap algorithm to settle up users.
         */

        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Invalid userId - " + userId);
        }

        User user = optionalUser.get();

        List<ExpenseUser> expenseUsers = expenseUserRepository.findAllByUser(user);

        Set<Expense> expenses = new HashSet<>();

        for (ExpenseUser expenseUser : expenseUsers) {
            expenses.add(expenseUser.getExpense());
        }

        //Settle Up the expenses.
        List<Expense> settleUpExpenses = settleUpStrategy.settleUp(expenses.stream().toList());
        /*
        A -> B : 200
        A -> C : 700
        D -> C : 500
         */

        List<Expense> expensesToReturn = new ArrayList<>();

        for (Expense expense : settleUpExpenses) {
            for (ExpenseUser expenseUser : expense.getExpenseUsers()) {
                if (expenseUser.getUser().equals(user)) {
                    expensesToReturn.add(expense);
                    break;
                }
            }
        }

        return expensesToReturn;
    }

    public List<Expense> settleUpGroup(Long groupId) {

        Optional<Group> optionalGroup = groupRepository.findById(groupId);

        if (optionalGroup.isEmpty()) {
            throw new RuntimeException("Invalid group id : " + groupId);
        }

        //Find all the expenses for this group.
        //select * from expenses where group_id = 123.
        List<Expense> expenses = expenseRepository.findAllByGroup(optionalGroup.get());

        //Settle Up Algorithm
        return settleUpStrategy.settleUp(expenses);
    }
}
