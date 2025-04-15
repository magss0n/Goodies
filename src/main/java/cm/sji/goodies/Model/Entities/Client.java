package cm.sji.goodies.Model.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("USER")
public class Client extends Person {

    @OneToMany(mappedBy = "client")
    private Set<Order> orders;
}
