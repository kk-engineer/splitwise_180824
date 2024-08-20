package in.itkaran.splitwise_180824.services;

import in.itkaran.splitwise_180824.models.UserExpense;
import in.itkaran.splitwise_180824.repositories.UserExpenseRepository;
import org.springframework.stereotype.Service;

@Service
public class ExpenseUserService {
    private UserExpenseRepository userExpenseRepository;

    public ExpenseUserService(UserExpenseRepository userExpenseRepository) {
        this.userExpenseRepository = userExpenseRepository;
    }

    public UserExpense addExpenseUser(UserExpense userExpense) {
        return userExpenseRepository.save(userExpense);
    }
}
