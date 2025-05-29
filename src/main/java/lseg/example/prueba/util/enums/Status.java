package lseg.example.prueba.util.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    PENDING("pending"),
    IN_PROGRESS("in-progress"),
    COMPLETED("completed");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    @JsonValue  // Used during serialization (Java → JSON)
    public String getValue() {
        return value;
    }

    @JsonCreator  // Used during deserialization (JSON → Java)
    public static Status fromValue(String value) {
        for (Status status : Status.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + value);
    }
}
