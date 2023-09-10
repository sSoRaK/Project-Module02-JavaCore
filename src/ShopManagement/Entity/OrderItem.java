package ShopManagement.Entity;

import ShopManagement.Attribute.AttributeText;

import java.util.List;
import java.util.Scanner;

public class OrderItem {
    private List<ShopManagement.Entity.Product> productList;
    private int quantity;

    public OrderItem() {
    }

    public OrderItem(List<Product> productList, int quantity) {
        this.productList = productList;
        this.quantity = quantity;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public int getCapacity() {
        return quantity;
    }

    public void setCapacity(int quantity) {
        this.quantity = quantity;
    }

    public void inputData(Scanner scanner, List<Product> productList) {
        boolean isCheck = false;
        do {
            System.out.print("Chọn mã sản phẩm: ");
            String input = scanner.nextLine().trim();
            for (Product product : productList) {
                if (product.getProductId().equals(input)) {
                    this.quantity = validateQuantity(scanner, productList);
                }
            }
        } while (!isCheck);
    }

    public void displayOrder() {

    }

    public int validateQuantity(Scanner scanner, List<Product> productList) {
        do {
            System.out.print("Nhập số lượng sản phẩm: ");
            String input = scanner.nextLine().trim();
            try {
                int capacity = Integer.parseInt(input);
                if (capacity < 0) {
                    System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "*Số lượng sản phẩm đang có giá trị âm!" + AttributeText.RESET);
                } else {
                    return capacity;
                }
            } catch (NumberFormatException e) {
                System.out.println(AttributeText.RED_BRIGHT + "Lỗi: Bạn vừa nhập vào [ " + input + " ]" + AttributeText.RESET);
            }
        } while (true);
    }
}
