import java.util.List;

public interface ProductLogic  {
    void addProduct(Product p);
    List<Product> getAllProducts();
    void deleteProduct(int id);
}
