package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "MOVIE")
public class Movie extends IdableClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    @NotEmpty
    @NotNull(message = "name - is null!")
    private String name;//Поле не может быть null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coordinate_id")
    private Coordinates coordinates;//Поле не может быть null

    @Column(name = "date")
    @CreationTimestamp
    private ZonedDateTime creationDate;//Поле не может быть null;Значение этого поля должно генерироваться автоматически

    @Column(name = "oscarsCount")
    @DecimalMin(value = "0", message = "oscars count must be >= 0")
    private Long oscarsCount; //Значение поля должно быть больше 0, Поле может быть null

    @Enumerated(EnumType.STRING)
    private MovieGenre genre; //Поле может быть null

    @Enumerated(EnumType.STRING)
    @Column(name = "mpaaRating")
    @NotNull(message = "rating - is null!")
    private MpaaRating mpaaRating;//Поле не может быть null

    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person director;//Поле не может быть null
}
