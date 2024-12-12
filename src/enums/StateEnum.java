package enums;

public enum StateEnum {
    HOUSE,
    HOUSE_WITH_CUBIC,
    ROCKETS();

    private static final StateEnum[] values = values();

    public StateEnum nextState() {
        return values[(this.ordinal() + 1) % values.length];
    }
}
