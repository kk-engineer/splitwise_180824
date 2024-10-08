package in.itkaran.splitwise_180824.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddUserExpenseRequestDto {
    private Long paidByUserId;
    private List<Long> owedByUserIds;
    private Long groupId;
    private int amount;
    private String description;
}
