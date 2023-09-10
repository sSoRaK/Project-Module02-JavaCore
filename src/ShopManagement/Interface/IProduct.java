package ShopManagement.Interface;

import ShopManagement.Entity.Category;
import ShopManagement.Entity.Product;

import java.util.List;
import java.util.Scanner;

public interface IProduct {
    float MIN_INTEREST_RATE = 0.2F;

    void inputData(Scanner scanner, List<Product> productList, List<Category> categoryList);

    void displayData();

    void calProfit();
}
