package in.itkaran.splitwise_180824.services.strategies.settleup;

import in.itkaran.splitwise_180824.models.Expense;

import java.util.List;

public interface SettleUpStrategy {
    List<Expense> settleUp(List<Expense> expenses);
}
