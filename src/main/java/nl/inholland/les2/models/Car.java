package nl.inholland.les2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car extends BaseEntity {

    private String brand;

    @NotBlank(message = "License plate is mandatory")
    private String licensePlate;

    @ManyToOne
    private Person owner;

}
