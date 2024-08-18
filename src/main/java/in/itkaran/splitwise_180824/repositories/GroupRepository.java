package in.itkaran.splitwise_180824.repositories;

import in.itkaran.splitwise_180824.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Optional<Group> findById(Long groupId);
}
