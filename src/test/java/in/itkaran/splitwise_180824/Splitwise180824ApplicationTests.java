package in.itkaran.splitwise_180824;

import in.itkaran.splitwise_180824.models.Transaction;
import in.itkaran.splitwise_180824.models.User;
import in.itkaran.splitwise_180824.models.UserExpense;
import in.itkaran.splitwise_180824.repositories.ExpenseRepository;
import in.itkaran.splitwise_180824.repositories.UserExpenseRepository;
import in.itkaran.splitwise_180824.repositories.UserRepository;
import in.itkaran.splitwise_180824.services.ExpenseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class Splitwise180824ApplicationTests {
    @Autowired
    private UserExpenseRepository userExpenseRepository;
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExpenseService expenseService;

    @Test
    void contextLoads() {
    }

    @Test
    void testUserExpense() {
        List<UserExpense> userExpenses = userExpenseRepository.findAllByUserId(1L);
        for (UserExpense userExpense : userExpenses) {
            System.out.println(userExpense.getId());
            System.out.println(userExpense.getExpense().getDescription());
            System.out.println(userExpense.getAmount());
            System.out.println(userExpense.getUser().getName());
            System.out.println(userExpense.getUserExpenseType());

            List<UserExpense> userExpenses1 = userExpenseRepository.findAllByExpenseId(userExpense.getExpense().getId());
            for (UserExpense userExpense1 : userExpenses1) {
                System.out.println("#################");
                System.out.println(userExpense1.getId());
                System.out.println(userExpense1.getUser().getName());
                System.out.println(userExpense1.getAmount());
                System.out.println(userExpense1.getUserExpenseType());
                System.out.println("#################");
            }
        }
    }

    @Test
    void testSettleUp() {
        List<Transaction> transactions = expenseService.settleUpUser(3L);
        if (transactions.isEmpty()) {
            System.out.println("No transactions required" );
            return;
        }
        System.out.println("Transactions:");
        for (Transaction transaction : transactions) {
            System.out.println("From: " + transaction.getFrom());
            System.out.println("To: " + transaction.getTo());
            System.out.println("Amount: " + transaction.getAmount());
        }
    }
}
