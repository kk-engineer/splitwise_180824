package in.itkaran.splitwise_180824.dtos;

import in.itkaran.splitwise_180824.models.Expense;
import in.itkaran.splitwise_180824.models.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SettleUpUserResponseDto {
    private String from;
    private String to;
    private int amount;
}
