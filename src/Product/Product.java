package Product;

public class Product {

    private String name;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof Product && name.equals(((Product)(obj)).name));
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
