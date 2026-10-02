package carrental;

import java.util.Objects;

public final class Money {
    private final long amountInCents;

    Money(long amountInCents) {
        if (amountInCents < 0) {
            throw new IllegalArgumentException("Amount can't be negative");
        }
        this.amountInCents = amountInCents;
    }

    public long getAmountInCents() {
        return amountInCents;
    }

    public Money add(Money other) {
        if (other == null) {
            throw new IllegalArgumentException("Money can't be null");
        }
        return new Money(amountInCents + other.getAmountInCents());
    }

    public Money multiply(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity can't be negative");
        }
        return new Money(amountInCents * quantity);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) return false;
        if (object == this) return true;

        if (!(object instanceof Money money)) return false;

        return money.getAmountInCents() == this.amountInCents;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amountInCents);
    }
}
