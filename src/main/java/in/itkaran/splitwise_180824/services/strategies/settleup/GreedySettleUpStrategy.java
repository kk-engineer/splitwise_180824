package in.itkaran.splitwise_180824.services.strategies.settleup;

import in.itkaran.splitwise_180824.models.Transaction;
import in.itkaran.splitwise_180824.models.User;
import in.itkaran.splitwise_180824.models.UserExpense;

import java.util.*;

public class GreedySettleUpStrategy implements SettleUpStrategy {
    private class UserBalance {
        private Long userId;
        private Integer balance;

        private UserBalance(Long userId, Integer balance) {
            this.userId = userId;
            this.balance = balance;
        }

        public String toString() {
            return "UserId: " + userId + ", Balance: " + balance;
        }
    }

    private  Map<Long, Integer> userBalance = new HashMap<>();
    private PriorityQueue<UserBalance> maxHeap = new PriorityQueue<>((a, b) -> b.balance - a.balance);
    private PriorityQueue<UserBalance> minHeap = new PriorityQueue<>((a, b) -> a.balance - b.balance);

    public List<Transaction> settleUp(List<UserExpense> userExpenses) {
        // DSA problem
        System.out.println("Settling up using Greedy SettleUp Strategy");
        for (UserExpense userExpense : userExpenses) {
            Long userId = userExpense.getUser().getId();
            Integer amount = userExpense.getAmount();
            userBalance.put(userId, userBalance.getOrDefault(userId, 0) + amount);
        }
        System.out.println("Map User Balance: " + userBalance);

        for (Map.Entry<Long, Integer> entry : userBalance.entrySet()) {
            Long userId = entry.getKey();
            Integer balance = entry.getValue();
            if (balance > 0) {
                maxHeap.add(new UserBalance(userId, balance));
            } else if (balance < 0) {
                minHeap.add(new UserBalance(userId, balance));
            }
        }

        System.out.println("MaxHeap: " + maxHeap);
        System.out.println("MinHeap: " + minHeap);

        // Greedy settle up algorithm
        List<Transaction> transactions = new ArrayList<>();
        while (!maxHeap.isEmpty() && !minHeap.isEmpty()) {
            UserBalance maxUser = maxHeap.poll();
            UserBalance minUser = minHeap.poll();
            int settleUpAmount = Math.min(maxUser.balance, -minUser.balance);
            maxUser.balance -= settleUpAmount;
            minUser.balance += settleUpAmount;
            System.out.println("Settling up " + settleUpAmount + " from " + minUser.userId + " to " + maxUser.userId);
            Transaction transaction = new Transaction();
            transaction.setFrom(minUser.userId);
            transaction.setTo(maxUser.userId);
            transaction.setAmount(settleUpAmount);
            transactions.add(transaction);
            if (maxUser.balance > 0) {
                maxHeap.add(maxUser);
            }
            if (minUser.balance < 0) {
                minHeap.add(minUser);
            }
        }
        return transactions;
    }
}
