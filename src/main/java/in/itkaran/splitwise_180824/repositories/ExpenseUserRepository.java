package in.itkaran.splitwise_180824.repositories;

import in.itkaran.splitwise_180824.models.ExpenseUser;
import in.itkaran.splitwise_180824.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseUserRepository extends JpaRepository<ExpenseUser, Long> {
    List<ExpenseUser> findAllByUser(User user);
}
