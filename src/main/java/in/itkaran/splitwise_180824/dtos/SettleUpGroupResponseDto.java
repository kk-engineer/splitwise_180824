package in.itkaran.splitwise_180824.dtos;

import in.itkaran.splitwise_180824.models.Expense;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SettleUpGroupResponseDto {
    private List<Expense> expenses;
}
