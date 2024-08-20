package in.itkaran.splitwise_180824.models;

import in.itkaran.splitwise_180824.dtos.SettleUpUserResponseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
    private Long from;
    private Long to;
    private int amount;

//    public SettleUpUserResponseDto toSettleUpUserResponseDto() {
//        SettleUpUserResponseDto settleUpUserResponseDto = new SettleUpUserResponseDto();
//        settleUpUserResponseDto.setFrom(from.getName());
//        settleUpUserResponseDto.setTo(to.getName());
//        settleUpUserResponseDto.setAmount(amount);
//        return settleUpUserResponseDto;
//    }
}
