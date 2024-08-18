package in.itkaran.splitwise_180824.repositories;

import in.itkaran.splitwise_180824.models.Expense;
import in.itkaran.splitwise_180824.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findAllByGroup(Group group);
}
