package lseg.example.prueba.util.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lseg.example.prueba.util.enums.Status;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {
    public String convertToDatabaseColumn(Status status) {
        return status != null ? status.getValue() : null;
    }

    public Status convertToEntityAttribute(String status) {
        return status != null ? Status.fromValue(status) : null;
    }
}
