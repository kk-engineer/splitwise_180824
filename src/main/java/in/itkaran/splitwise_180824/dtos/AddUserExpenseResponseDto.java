package in.itkaran.splitwise_180824.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddUserExpenseResponseDto {
    private String paidByUserName;
    private int amount;
    private String description;
    private String groupName;

    public String toString() {
        return " Paid by: " + paidByUserName + "\n" +
                " Amount: " + amount + "\n" +
                " Description: " + description + "\n" +
                " Group: " + groupName;
    }
}
