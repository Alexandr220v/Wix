package entities;

public class Cart {

    private String subtotalPrice;
    private String shipping;
    private String totalPrice;

    private Cart(Builder builder) {
        this.subtotalPrice = builder.subtotalPrice;
        this.shipping = builder.shipping;
        this.totalPrice = builder.totalPrice;

    }

    public String getSubtotalPrice() {
        return subtotalPrice;
    }

    public void setSubtotalPrice(String subtotalPrice) {
        this.subtotalPrice = subtotalPrice;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cart cart = (Cart) o;

        if (subtotalPrice != null ? !subtotalPrice.equals(cart.subtotalPrice) : cart.subtotalPrice != null)
            return false;
        if (shipping != null ? !shipping.equals(cart.shipping) : cart.shipping != null) return false;
        return totalPrice != null ? totalPrice.equals(cart.totalPrice) : cart.totalPrice == null;
    }

    @Override
    public int hashCode() {
        int result = subtotalPrice != null ? subtotalPrice.hashCode() : 0;
        result = 31 * result + (shipping != null ? shipping.hashCode() : 0);
        result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "subtotalPrice='" + subtotalPrice + '\'' +
                ", shipping='" + shipping + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                '}';
    }

    public static class Builder {

        private String subtotalPrice;
        private String shipping;
        private String totalPrice;

        public Builder() {
        }

        public Builder(Cart origin) {
            this.subtotalPrice = origin.subtotalPrice;
            this.shipping = origin.shipping;
            this.totalPrice = origin.totalPrice;
        }

        public Builder subtotalPrice(String subtotalPrice) {
            this.subtotalPrice = subtotalPrice;
            return this;
        }

        public Builder shipping(String shipping) {
            this.shipping = shipping;
            return this;
        }

        public Builder totalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Cart create() {
            return new Cart(this);
        }
    }

}
