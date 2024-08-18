package in.itkaran.splitwise_180824.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String name;

    public String toString() {
        return "User Id: " + this.getId() + ", Name: " + this.getName() ;
    }
}
