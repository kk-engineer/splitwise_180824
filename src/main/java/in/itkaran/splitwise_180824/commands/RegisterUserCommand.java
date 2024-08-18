package in.itkaran.splitwise_180824.commands;

import in.itkaran.splitwise_180824.controllers.UserController;
import in.itkaran.splitwise_180824.dtos.UserRequestDto;
import in.itkaran.splitwise_180824.dtos.UserResponseDto;

import java.util.List;

public class RegisterUserCommand implements Command {
    private UserController userController;

    public RegisterUserCommand(UserController userController) {
        this.userController = userController;
    }

    @Override
    public boolean matches(String input) {
        System.out.println("Checking if RegisterUserCommand matches the input: " + input);
        List<String> words = List.of(input.split(" "));

        return words.size() == 4 && words.get(0).equals(CommandKeywords.REGISTER_USER_COMMAND);
    }

    @Override
    public void execute(String input) {
        List<String> words = List.of(input.split(" "));
        String name = words.get(1);
        String phoneNumber = words.get(2);
        String password = words.get(3);

        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName(name);
        userRequestDto.setPhoneNumber(phoneNumber);
        userRequestDto.setPassword(password);

        // Call the UserService to register the user
        UserResponseDto userResponseDto = userController.registerUser(userRequestDto);
        System.out.println(userResponseDto);
    }
}
