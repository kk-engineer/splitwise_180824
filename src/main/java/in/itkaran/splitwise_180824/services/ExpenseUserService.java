package in.itkaran.splitwise_180824.services;

import in.itkaran.splitwise_180824.models.ExpenseUser;
import in.itkaran.splitwise_180824.repositories.ExpenseUserRepository;
import org.springframework.stereotype.Service;

@Service
public class ExpenseUserService {
    private ExpenseUserRepository expenseUserRepository;

    public ExpenseUserService(ExpenseUserRepository expenseUserRepository) {
        this.expenseUserRepository = expenseUserRepository;
    }

    public ExpenseUser addExpenseUser(ExpenseUser expenseUser) {
        return expenseUserRepository.save(expenseUser);
    }
}
