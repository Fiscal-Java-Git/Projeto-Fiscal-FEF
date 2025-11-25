package domains.enums;

public enum StatusLancamento {
    PENDENTE,
    BAIXADO,
    PARCIAL,
    CANCELADO;

    public String getValue() {
        return name();
    }

    public static StatusLancamento fromValue(String value) {
        if (value == null) return null;
        try {
            return StatusLancamento.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("StatusLancamento inválido: " + value);
        }
    }

    @Override
    public String toString() {
        return getValue();
    }
}
