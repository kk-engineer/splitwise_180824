package in.itkaran.splitwise_180824.commands;

public interface Command {
    boolean matches(String input);
    void execute(String input);
}
