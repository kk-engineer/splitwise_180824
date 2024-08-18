package in.itkaran.splitwise_180824.commands;

import java.util.List;

public class AddMemberCommand implements Command {
    @Override
    public boolean matches(String input) {
        List<String> words = List.of(input.split(" "));
        return words.size() == 4 && words.get(1).equals(CommandKeywords.ADD_MEMBER_COMMAND);
    }

    @Override
    public void execute(String input) {
        List<String> words = List.of(input.split(" "));
        // TODO: Implement this method
    }
}
