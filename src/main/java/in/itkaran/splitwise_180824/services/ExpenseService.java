package in.itkaran.splitwise_180824.services;

import in.itkaran.splitwise_180824.exceptions.InvalidUserException;
import in.itkaran.splitwise_180824.models.*;
import in.itkaran.splitwise_180824.repositories.ExpenseRepository;
import in.itkaran.splitwise_180824.repositories.UserExpenseRepository;
import in.itkaran.splitwise_180824.repositories.GroupRepository;
import in.itkaran.splitwise_180824.repositories.UserRepository;
import in.itkaran.splitwise_180824.services.strategies.settleup.GreedySettleUpStrategy;
import in.itkaran.splitwise_180824.services.strategies.settleup.SettleUpStrategy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExpenseService {
    private final UserRepository userRepository;
    private final UserExpenseRepository userExpenseRepository;
    private SettleUpStrategy settleUpStrategy;
    private final GroupRepository groupRepository;
    private final ExpenseRepository expenseRepository;

    public ExpenseService(UserRepository userRepository,
                          UserExpenseRepository userExpenseRepository,
                          GroupRepository groupRepository,
                          ExpenseRepository expenseRepository) {
        this.userRepository = userRepository;
        this.userExpenseRepository = userExpenseRepository;
        this.groupRepository = groupRepository;
        this.expenseRepository = expenseRepository;
    }

    public Expense addExpense(Long paidByUserId,
                              List<Long> owedByUserIds,
                              int amount,
                              Long groupId,
                              String description) {
        Expense expense = new Expense();
        Optional<User> optionalPaidByUser = userRepository.findById(paidByUserId);
        if (optionalPaidByUser.isEmpty()) {
            throw new InvalidUserException("Invalid userId - " + paidByUserId);
        }
        expense.setCreatedBy(optionalPaidByUser.get());
        expense.setAmount(amount);
        expense.setDescription(description);
        expense.setExpenseType(ExpenseType.ACTUAL);
        expense.setGroup(groupId == null ? null : groupRepository.findById(groupId).orElse(null));
        // save the expense
        expenseRepository.save(expense);

        // Divide the expense equally among all the users.
        // create UserExpense objects for each user.
        int participants = owedByUserIds.size() + 1;
        int amountPerUser = amount / participants;

        // add paidByUser expense
        UserExpense paidByUserExpense = new UserExpense();
        paidByUserExpense.setUser(optionalPaidByUser.get());
        paidByUserExpense.setExpense(expense);
        paidByUserExpense.setAmount(amountPerUser * (participants - 1));
        paidByUserExpense.setUserExpenseType(UserExpenseType.PAID);
        userExpenseRepository.save(paidByUserExpense);

        // add owedByUser expenses
        for (Long userId : owedByUserIds) {
            Optional<User> optionalOwedByUser = userRepository.findById(userId);
            if (optionalOwedByUser.isEmpty()) {
                throw new InvalidUserException("Invalid userId - " + userId);
            }
            User user = optionalOwedByUser.get();
            UserExpense userExpense = new UserExpense();
            userExpense.setUser(user);
            userExpense.setExpense(expense);
            userExpense.setAmount((-1)*amountPerUser);
            userExpense.setUserExpenseType(UserExpenseType.OWED);
            userExpenseRepository.save(userExpense);
        }

        return expense;
    }

    public List<Transaction> settleUpUser(Long userId) {
        settleUpStrategy = new GreedySettleUpStrategy();
        /*
        1. Get the user with the given userId.
        2. Get all the expenses for this user.
        3. Iterate through all the expenses, and find out total extra/lesser amount
        paid by every user involved in the expenses.
        4. Implement Min/Max Heap algorithm to settle up users.
         */

        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new InvalidUserException("Invalid userId - " + userId);
        }

        List<UserExpense> userExpenses = userExpenseRepository.findAllByUserId(userId);
        List<Long> expenseIds = new ArrayList<>();
        for (UserExpense userExpense : userExpenses) {
            expenseIds.add(userExpense.getExpense().getId());
        }
        List<UserExpense> allInvolvedUserExpenses = new ArrayList<>();
        for (Long expenseId : expenseIds) {
            allInvolvedUserExpenses.addAll(userExpenseRepository.findAllByExpenseId(expenseId));
        }

        List<Transaction>  allTransactions = settleUpStrategy.settleUp(allInvolvedUserExpenses);
        List<Transaction> userTransactions = new ArrayList<>();
        for (Transaction transaction : allTransactions) {
            if(transaction.getFrom().equals(userId) || transaction.getTo().equals(userId)) {
                userTransactions.add(transaction);
            }
        }
        return userTransactions;
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
        // TODO: Implement this method.
        return null;
    }
}
