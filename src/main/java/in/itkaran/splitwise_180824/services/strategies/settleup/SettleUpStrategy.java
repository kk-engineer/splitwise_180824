package in.itkaran.splitwise_180824.services.strategies.settleup;

import in.itkaran.splitwise_180824.models.Expense;
import in.itkaran.splitwise_180824.models.Transaction;
import in.itkaran.splitwise_180824.models.UserExpense;

import java.util.List;

public interface SettleUpStrategy {
    List<Transaction> settleUp(List<UserExpense> userExpenses);
}
