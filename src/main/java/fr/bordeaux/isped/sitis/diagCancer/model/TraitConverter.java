package fr.bordeaux.isped.sitis.diagCancer.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class TraitConverter implements AttributeConverter<Trait, String> {

    @Override
    public String convertToDatabaseColumn(Trait trait) {
        if (trait == null) {
            return null;
        }
        return trait.getTrait();
    }

    @Override
    public Trait convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(Trait.values())
                .filter(c -> c.getTrait().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
