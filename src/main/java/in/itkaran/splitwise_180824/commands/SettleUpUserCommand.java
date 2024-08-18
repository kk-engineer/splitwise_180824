package in.itkaran.splitwise_180824.commands;

import in.itkaran.splitwise_180824.controllers.SettleUpController;
import in.itkaran.splitwise_180824.dtos.SettleUpUserRequestDto;
import in.itkaran.splitwise_180824.dtos.SettleUpUserResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SettleUpUserCommand implements Command {
    private SettleUpController settleUpController;

    public SettleUpUserCommand(SettleUpController settleUpController) {
        this.settleUpController = settleUpController;
    }

    @Override
    public boolean matches(String input) {
        System.out.println("Checking if SettleUpUserCommand matches the input: " + input);
        //input - 1234 SettleUp
        // words -> [1234, SettleUp]
        List<String> words = List.of(input.split(" "));
        System.out.println("Input: " + input + " Words: " + words);
        return words.size() == 2 && words.get(1).equals(CommandKeywords.SETTLE_UP_COMMAND);
    }

    @Override
    public void execute(String input) {
        List<String> words = List.of(input.split(" "));

        Long userId = Long.valueOf(words.get(0));

        SettleUpUserRequestDto requestDto = new SettleUpUserRequestDto();
        requestDto.setUserId(userId);

        SettleUpUserResponseDto responseDto = settleUpController.settleUpUser(requestDto);
    }
}