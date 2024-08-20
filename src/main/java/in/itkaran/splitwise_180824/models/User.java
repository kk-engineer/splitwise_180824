package in.itkaran.splitwise_180824.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "users")
public class User extends BaseModel {
    private String name;
    private String phoneNumber;
    private String password;

    public String toString() {
        return  name;
    }
}