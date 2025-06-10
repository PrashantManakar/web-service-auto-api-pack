package java.com.example.api.model;

public class MobilePhoneItem {
    private String id;
    private String name;
    private String cpu;
    private Double price;

    private MobilePhoneItem(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.cpu = builder.cpu;
        this.price = builder.price;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getCpu() { return cpu; }
    public Double getPrice() { return price; }

    public static class Builder {
        private String id;
        private String name;
        private String cpu;
        private Double price;

        public Builder() {}

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder cpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder price(Double price) {
            this.price = price;
            return this;
        }

        public MobilePhoneItem build() {
            return new MobilePhoneItem(this);
        }
    }

}
