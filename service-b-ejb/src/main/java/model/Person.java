package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "PERSON")
public class Person extends IdableClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @NotNull(message = "name - is null!")
    private String name; //Поле не может быть null

    private java.util.Date birthday; //Поле может быть null

    @NotNull(message = "weight - is null!")
    @DecimalMin(value = "0", inclusive = false, message = "weight must be > 0")
    private Long weight; //Поле не может быть null, Значение поля должно быть больше 0

    @Column(unique = true)
    @NotNull(message = "passport - is null!")
    private String passportId; //Значение этого поля должно быть уникальным, Поле не может быть null


    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location; //Поле может быть null
}