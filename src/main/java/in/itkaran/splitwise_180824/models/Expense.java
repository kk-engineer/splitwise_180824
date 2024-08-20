package in.itkaran.splitwise_180824.models;

import in.itkaran.splitwise_180824.dtos.AddUserExpenseResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Expense extends BaseModel {
    private String description;
    private int amount;
    @ManyToOne
    private User createdBy;
    @Enumerated(EnumType.ORDINAL)
    private ExpenseType expenseType;
    @OneToMany(mappedBy = "expense")
    private List<UserExpense> userExpenses;
    @ManyToOne
    private Group group;

    public AddUserExpenseResponseDto toAddUserExpenseResponseDto(){
        AddUserExpenseResponseDto addUserExpenseResponseDto = new AddUserExpenseResponseDto();
        addUserExpenseResponseDto.setPaidByUserName(createdBy.getName());
        addUserExpenseResponseDto.setAmount(amount);
        if (group!=null) addUserExpenseResponseDto.setGroupName(group.getName());
        addUserExpenseResponseDto.setDescription(description);
        return addUserExpenseResponseDto;
    }
}
