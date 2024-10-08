package in.itkaran.splitwise_180824.commands;

import java.util.List;

public class CreateGroupCommand implements Command {
    @Override
    public boolean matches(String input) {
        List<String> words = List.of(input.split(" "));
        return words.size() == 3 && words.get(1).equals(CommandKeywords.CREATE_GROUP_COMMAND);
    }

    @Override
    public void execute(String input) {
        // TODO: Implement this method
    }
}
