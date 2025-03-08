public class Product {
    private int Id;
    private String Name,Category;
    private float Price;
    private int Amount;
    public Product(String name, String category, float price, int amount)
    {
        Name=name;
        Category=category;
        Price=price;
        Amount=amount;
    }
    public Product(int id,String name, String category, float price, int amount)
    {
        Id=id;
        Name=name;
        Category=category;
        Price=price;
        Amount=amount;
    }
    public String getName() {return Name;}
    public String getCategory() {return Category;}
    public float getPrice() {return Price;}
    public int getAmount() {return Amount;}
    public int getId(){return Id;}
    public void setId(int id){Id =id;}

}
