package in.itkaran.splitwise_180824;

import in.itkaran.splitwise_180824.commands.CommandExecutor;
import in.itkaran.splitwise_180824.commands.CreateExpenseCommand;
import in.itkaran.splitwise_180824.commands.RegisterUserCommand;
import in.itkaran.splitwise_180824.commands.SettleUpUserCommand;
import in.itkaran.splitwise_180824.controllers.SettleUpController;
import in.itkaran.splitwise_180824.controllers.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Splitwise180824Application implements CommandLineRunner {
    @Autowired
    private CommandExecutor commandExecutor;
    @Autowired
    private SettleUpController settleUpController;
    @Autowired
    private UserController userController;


    public static void main(String[] args) {
        SpringApplication.run(Splitwise180824Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        // Register commands
        commandExecutor.addCommand(new SettleUpUserCommand(settleUpController));
        commandExecutor.addCommand(new RegisterUserCommand(userController));
        commandExecutor.addCommand(new CreateExpenseCommand());

        while (true) {
            System.out.println("Enter command:");
            String input = scanner.nextLine();
            commandExecutor.execute(input);
        }

    }
}
