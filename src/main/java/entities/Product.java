package entities;

public class Product {

    private String id;
    private String image;
    private String name;
    private String price;
    private String status;
    private String color;
    private int quantity;


    private Product(Builder builder) {
        this.id = builder.id;
        this.image = builder.image;
        this.name = builder.name;
        this.price = builder.price;
        this.status = builder.status;

    }

    public String getId() { return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() { return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product item = (Product) o;

        if (image != null ? !image.equals(item.image) : item.image != null) return false;
        if (name != null ? !name.equals(item.name) : item.name != null) return false;
        if (price != null ? !price.equals(item.price) : item.price != null) return false;
        return status != null ? status.equals(item.status) : item.status == null;
    }

    @Override
    public int hashCode() {
        int result = image != null ? image.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "withImage='" + image + '\'' +
                ", withName='" + name + '\'' +
                ", withPrice='" + price + '\'' +
                ", withStatus='" + status + '\'' +
                '}';
    }


    public static class Builder {

        private String id;
        private String image;
        private String name;
        private String price;
        private String status;
        private String color;
        private int quantity;

        public Builder() {
        }

        public Builder(Product origin) {

            this.id = origin.id;
            this.image = origin.image;
            this.name = origin.name;
            this.price = origin.price;
            this.status = origin.status;
            this.color = origin.color;
            this.quantity = origin.quantity;

        }

        public Builder withImage(String image) {
            this.image = image;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withPrice(String price) {
            this.price = price;
            return this;
        }

        public Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public Product create() {
            return new Product(this);
        }
    }
}
