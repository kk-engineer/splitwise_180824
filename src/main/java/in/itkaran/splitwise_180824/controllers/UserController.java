package in.itkaran.splitwise_180824.controllers;

import in.itkaran.splitwise_180824.dtos.UserRequestDto;
import in.itkaran.splitwise_180824.dtos.UserResponseDto;
import in.itkaran.splitwise_180824.models.User;
import in.itkaran.splitwise_180824.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public UserResponseDto registerUser(UserRequestDto requestDto) {
        UserResponseDto userResponseDto = new UserResponseDto();
        User user = userService.register(requestDto.getName(),
                                            requestDto.getPhoneNumber(),
                                            requestDto.getPassword());
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        return userResponseDto;
    }
}
