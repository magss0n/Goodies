package cm.sji.goodies.Model.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@DiscriminatorValue("ADMIN")
@Setter
@Getter
//@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Admin extends Person{
}
