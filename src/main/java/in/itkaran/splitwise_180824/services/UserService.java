package in.itkaran.splitwise_180824.services;

import in.itkaran.splitwise_180824.models.User;
import in.itkaran.splitwise_180824.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(String name, String phoneNumber, String password) {
        User user = new User();
        user.setName(name);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        return userRepository.save(user);
    }
}
