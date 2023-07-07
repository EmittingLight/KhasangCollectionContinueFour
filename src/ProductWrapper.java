import java.util.Arrays;
import java.util.Objects;

public class ProductWrapper {
    private final Product product;

    public ProductWrapper(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ProductWrapper other = (ProductWrapper) obj;
        return Objects.equals(product.getName(), other.product.getName()) &&
                Objects.equals(product.getCategory(), other.product.getCategory()) &&
                Arrays.equals(product.getInternalCode(), other.product.getInternalCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(product.getName(), product.getCategory(), Arrays.hashCode(product.getInternalCode()));
    }
}




































