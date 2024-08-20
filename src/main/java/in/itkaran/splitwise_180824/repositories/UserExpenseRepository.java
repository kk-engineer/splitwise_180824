package in.itkaran.splitwise_180824.repositories;

import in.itkaran.splitwise_180824.models.UserExpense;
import in.itkaran.splitwise_180824.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserExpenseRepository extends JpaRepository<UserExpense, Long> {
    List<UserExpense> findAllByUser(User user);
    List<UserExpense> findAllByUserId(Long userId);
    List<UserExpense> findAllByExpenseId(Long expenseId);
}
