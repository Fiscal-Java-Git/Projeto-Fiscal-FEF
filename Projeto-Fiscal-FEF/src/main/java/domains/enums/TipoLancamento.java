package domains.enums;

public enum TipoLancamento {
    PAGAR,
    RECEBER;

    public String getValue() {
        return name();
    }

    public static TipoLancamento fromValue(String value) {
        if (value == null) return null;
        try {
            return TipoLancamento.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("TipoLancamento inválido: " + value);
        }
    }

    @Override
    public String toString() {
        return getValue();
    }
}
