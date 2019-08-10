package javadub1.jdbc.auta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private Long id;
    private String Nazwa;
    private String Marka;
    private double Przebieg;
    private LocalDate date;
}
