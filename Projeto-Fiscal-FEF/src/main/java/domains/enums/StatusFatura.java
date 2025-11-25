package domains.enums;

public enum StatusFatura {
    ABERTA,
    FECHADA,
    PAGA;

    public String getValue() {
        return name();
    }

    public static StatusFatura fromValue(String value) {
        if (value == null) return null;
        try {
            return StatusFatura.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("StatusFatura inválido: " + value);
        }
    }

    @Override
    public String toString() {
        return getValue();
    }
}
