package entity;

public class Currency extends Tradeable {
    private final String name;

    private Currency(String name) {
        this.name = name;
    }

    public void trade() {
        // TODO: Complete trade method.
    }

    @Override
    public String getCurrencyName() { return name; }
}
