package nl.inholland.apidemo.models.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarDTO {

    private long id;
    private String brand;
    private String licensePlate;

    // The variables below will be automapped :)
    private long ownerId;
    private String ownerFirstName;
    private String ownerLastName;

}
