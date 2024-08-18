package in.itkaran.splitwise_180824.services;

import in.itkaran.splitwise_180824.models.Expense;
import in.itkaran.splitwise_180824.repositories.ExpenseRepository;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {
    private ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }
}
