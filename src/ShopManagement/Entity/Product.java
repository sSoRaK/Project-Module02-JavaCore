package ShopManagement.Entity;

import ShopManagement.Attribute.AttributeText;
import ShopManagement.Interface.IProduct;

import static ShopManagement.Entity.Category.findIndexById;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Product implements IProduct {
    private String productId;
    private String productName;
    private double importPrice;
    private double exportPrice;
    private double profit;
    private String description;
    private boolean status;
    private int categoryId;
    private List<ShopManagement.Entity.Category> categoryList;
    private String categoryName;
    private int quantity;

    public Product() {
    }

    public Product(String productId,
                   String productName,
                   double importPrice,
                   double exportPrice,
                   double profit,
                   String description,
                   boolean status,
                   int categoryId,
                   List<ShopManagement.Entity.Category> categoryList,
                   int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.profit = profit;
        this.description = description;
        this.status = status;
        this.categoryId = categoryId;
        this.categoryList = categoryList;
        this.quantity = quantity;
    }

    public Product(String prId,
                   String name,
                   double importPrice,
                   double exportPrice,
                   double profit,
                   String description,
                   boolean status,
                   String cateName,
                   List<Category> categoryList,
                   int cateId,
                   int quantity) {
        this.productId = prId;
        this.productName = name;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.profit = profit;
        this.description = description;
        this.status = status;
        this.categoryName = cateName;
        this.categoryList = categoryList;
        this.categoryId = cateId;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public List<ShopManagement.Entity.Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<ShopManagement.Entity.Category> categoryList) {
        this.categoryList = categoryList;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public void inputData(Scanner scanner, List<Product> productList, List<ShopManagement.Entity.Category> categoryList) {
        boolean checkCategoryId = false;
        do {
            System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "*Lưu ý: Đây là phần (ID Category) mã danh mục!" + AttributeText.RESET);
            System.out.print("Chọn mã danh mục: ");
            try {
                // exception cho trường hợp nhập không phải số
                this.categoryId = Integer.parseInt(scanner.nextLine().trim());
                checkCategoryId = isExistCategoryIdInList(categoryList, categoryId);
                // sau khi chọn categoryId cho phép nhập thông tin của product, nếu để trống hoặc categoryId chưa được tạo sẵn có thể return "Yêu cầu nhập categoryId"
                if (checkCategoryId) { // true
                    this.productId = validateProductId(scanner, productList);
                    this.productName = validateProductName(scanner, productList);
                    this.quantity = validateQuantity(scanner, productList);
                    this.importPrice = validateImportPrice(scanner);
                    this.exportPrice = validateExportPrice(scanner);
                    this.description = validateDescription(scanner);
                    this.status = validateProductStatus(scanner);
                    this.categoryList = categoryList;
                    calProfit();
                } else {
                    // trường hợp nhập (số) mã danh mục không chứa trong List
                    System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "*Vui lòng chọn mã danh mục đã được đăng ký!" + AttributeText.RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(AttributeText.RED_BRIGHT + "Lỗi: " + e.getMessage() + AttributeText.RESET);
            }
        } while (!checkCategoryId);
    }

    @Override
    public void displayData() {
        String displayStatus = this.status ? "Còn hàng" : "Không còn hàng";
        String dependStatus = this.status ? AttributeText.GREEN_BRIGHT : AttributeText.YELLOW_BRIGHT;
        String categoryName = "";
        if (categoryList != null) {
            categoryName = categoryList.get(findIndexById(this.getCategoryId())).getCategoryName();
        }
        System.out.printf("| %-17d | %-30s | %-10s | %-25s | %-10d | %-14.1f | %-14.1f | %-10.1f | %-23s |" + dependStatus + " %-20s " + AttributeText.RESET + "|\n", categoryId, categoryName, productId, productName, quantity, importPrice, exportPrice, profit, description, displayStatus);
    }

    @Override
    public void calProfit() {
        this.profit = exportPrice - importPrice;
    }

    public boolean isExistCategoryIdInList(List<ShopManagement.Entity.Category> categoryList, int categoryId) {
        for (ShopManagement.Entity.Category category : categoryList) {
            if (category.getCategoryId() == categoryId) {
                return true;
            }
        }
        return false;
    }

    public String validateProductId(Scanner scanner, List<Product> productList) {
        while (true) {
            System.out.print("Nhập mã sản phẩm: ");
            String productId = scanner.nextLine().trim();

            if (regexProductId(productId)) {
                boolean isExist = false;
                for (Product product : productList) {
                    if (product.getProductId().equals(productId)) {
                        isExist = true;
                        break; // productId trùng cho thoát vòng lặp
                    }
                }
                if (isExist) {
                    System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "*Mã sản phẩm đã tồn tại!" + AttributeText.RESET);
                } else {
                    return productId;
                }
            } else {
                System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "*Mã sản phẩm bắt đầu ký tự 'P' và bao gồm 4 ký tự." + AttributeText.RESET);
            }
        }
    }

    public boolean regexProductId(String productId) {
        String productIdRegex = "^P\\d{3}$"; //^P[0-9]{3}$
        return Pattern.matches(productIdRegex, productId);
    }

    public String validateProductName(Scanner scanner, List<Product> productList) {
        do {
            System.out.print("Nhập tên sản phẩm: ");
            String productName = scanner.nextLine().trim();
            if (productName.length() >= 6 && productName.length() <= 30) {
                boolean isExist = false;
                for (Product product : productList) {
                    if (product.getProductName().equals(productName)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "*Tên sản phẩm đã tồn tại!" + AttributeText.RESET);
                } else {
                    return productName;
                }
            } else {
                System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "Tên sản phẩm bao gồm 6 - 30 kí tự!" + AttributeText.RESET);
            }
        } while (true);
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

    public Double validateImportPrice(Scanner scanner) {
        do {
            System.out.print("Giá sản phẩm nhập vào: ");
            String input = scanner.nextLine().trim();
            try {
                double importPrice = Double.parseDouble(input);
                if (importPrice < 0) {
                    System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "*Có gì đó không đúng, giá sách đang có giá trị âm!" + AttributeText.RESET);
                } else {
                    return importPrice;
                }
            } catch (NumberFormatException e) { // exception không nhập số
                System.out.println(AttributeText.RED_BRIGHT + "Lỗi: Bạn vừa nhập vào [ " + input + " ]" + AttributeText.RESET);
            }
        } while (true);
    }

    public Double validateExportPrice(Scanner scanner) {
        do {
            System.out.print("Giá sản phẩm bán ra: ");
            String input = scanner.nextLine().trim();
            try {
                double exportPrice = Double.parseDouble(input);
                if (exportPrice < 0) {
                    System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "*Có gì đó không đúng, giá sách đang có giá trị âm!" + AttributeText.RESET);
                } else if (exportPrice <= this.importPrice * (1 + MIN_INTEREST_RATE)) { // lợi nhuận 20%
                    System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "*Sách đang có giá bán ra, thấp hơn giá sách được nhập vào 20%!" + AttributeText.RESET);
                } else {
                    return exportPrice;
                }
            } catch (NumberFormatException e) { // exception không nhập số
                System.out.println(AttributeText.RED_BRIGHT + "Lỗi: Bạn vừa nhập vào [ " + input + " ]" + AttributeText.RESET);
            }
        } while (true);
    }

    public String validateDescription(Scanner scanner) {
        do {
            System.out.print("Nhập mô tả sản phẩm: ");
            String description = scanner.nextLine().trim();
            if (!description.isEmpty()) {
                return description;
            } else {
                System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "*Vui lòng nhập mô tả sản phẩm!" + AttributeText.RESET);
            }
        } while (true);
    }

    public boolean validateProductStatus(Scanner scanner) {
        do {
            System.out.print("Nhập trạng thái sản phẩm: ");
            String status = scanner.nextLine().trim();
            if (status.equalsIgnoreCase("true") || status.equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(status);
            } else {
                System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "*Vui lòng nhập trạng thái sản phẩm!" + AttributeText.RESET);
            }
        } while (true);
    }
}
