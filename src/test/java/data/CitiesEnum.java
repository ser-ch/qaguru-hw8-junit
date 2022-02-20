package data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CitiesEnum {
    KEMEROVO("Кемерово"), BISHKEK("Бешкек"), NOVOSIBIRSK("Новосибирск");

    private String city;
}
