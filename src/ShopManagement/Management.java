package ShopManagement;

import ShopManagement.Entity.Category;
import ShopManagement.Attribute.AttributeText;
import ShopManagement.Entity.Order;
import ShopManagement.Entity.OrderItem;
import ShopManagement.Entity.Product;
import ShopManagement.Attribute.Table;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Management implements Serializable {
    public static List<Category> categoryList = new ArrayList<>();
    public static List<Product> productList = new ArrayList<>();
    public static List<Order> orderList = new ArrayList<>();
    public static List<OrderItem> orderItemList = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // đọc dữ liệu từ file
        readCategoryDataFromFile("categories.txt");
        readProductDataFromFile("products.txt", productList, categoryList);

        do {
            System.out.println(Table.titleMenu("SHOP MANAGEMENT"));
            System.out.println(Table.borderMenuTop());
            System.out.println(Table.infoMenu("1. Quản lý danh mục sản phẩm\t\t\t\t\t\t"));
            System.out.println(Table.infoMenu("2. Quản lý sản phẩm\t\t\t\t\t\t\t\t"));
            System.out.println(Table.infoMenu("3. Quản lý đặt hàng\t\t\t\t\t\t\t\t"));
            System.out.println(Table.infoMenu("4. Thoát\t\t\t\t\t\t\t\t\t\t\t"));
            System.out.println(Table.borderMenuBottom());
            System.out.print("Chọn chức năng (1 - 4): ");
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1:
                        boolean categoryMenu = true;
                        do {
                            System.out.println(Table.titleMenu("CATALOG MANAGEMENT"));
                            System.out.println(Table.borderMenuTop());
                            System.out.println(Table.infoMenu("1. Thêm mới danh mục\t\t\t\t\t\t\t\t"));
                            System.out.println(Table.infoMenu("2. Hiển thị danh sách danh mục\t\t\t\t\t"));
                            System.out.println(Table.infoMenu("3. Cập nhật danh mục\t\t\t\t\t\t\t\t"));
                            System.out.println(Table.infoMenu("4. Xóa danh mục\t\t\t\t\t\t\t\t\t"));
                            System.out.println(Table.infoMenu("5. Tìm kiếm danh mục theo tên danh mục\t\t\t"));
                            System.out.println(Table.infoMenu("6. Thống kê số lượng sản phẩm trong danh mục\t\t"));
                            System.out.println(Table.infoMenu("7. Quay lại\t\t\t\t\t\t\t\t\t\t"));
                            System.out.println(Table.borderMenuBottom());
                            System.out.print("Chọn chức năng (1 - 7): ");
                            try {
                                int choiceCategoryMenu = Integer.parseInt(scanner.nextLine().trim());
                                switch (choiceCategoryMenu) {
                                    case 1:
                                        Management.inputDataCategory();
                                        Management.writeCategoryToData("categories.txt", categoryList);
                                        break;
                                    case 2:
                                        Management.displayDataCategory();
                                        break;
                                    case 3:
                                        Management.updateDataCategory(scanner, categoryList);
                                        Management.writeCategoryToData("categories.txt", categoryList);
                                        Management.displayDataCategory();
                                        break;
                                    case 4:
                                        Management.removeCategoryById();
                                        Management.writeCategoryToData("categories.txt", categoryList);
                                        Management.displayDataCategory();
                                        break;
                                    case 5:
                                        Management.searchCategoryByName();
                                        break;
                                    case 6:
                                        Management.statsByProductStatus(categoryList, productList);
                                        break;
                                    case 7:
                                        System.out.println(AttributeText.RED_BACKGROUND_BRIGHT + "Quay lại Shop Management" + AttributeText.RESET);
                                        Management.writeCategoryToData("categories.txt", categoryList);
                                        categoryMenu = false;
                                        break;
                                    default:
                                        System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "Vui lòng chọn chức năng (1 - 7)!" + AttributeText.RESET);
                                        break;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println(AttributeText.RED_BRIGHT + "Vui lòng nhập chức năng (1 - 7)!" + AttributeText.RESET);
                            }
                        } while (categoryMenu);
                        break;
                    case 2:
                        boolean productMenu = true;
                        do {
                            System.out.println(Table.titleMenu("PRODUCT MANAGEMENT"));
                            System.out.println(Table.borderMenuTop());
                            System.out.println(Table.infoMenu("1. Thêm mới sản phẩm\t\t\t\t\t\t\t\t"));
                            System.out.println(Table.infoMenu("2. Hiển thị danh sách sản phẩm\t\t\t\t\t"));
                            System.out.println(Table.infoMenu("3. Cập nhật sản phẩm\t\t\t\t\t\t\t\t"));
                            System.out.println(Table.infoMenu("4. Xóa sản phẩm\t\t\t\t\t\t\t\t\t"));
                            System.out.println(Table.infoMenu("5. Hiển thị sản phẩm theo tên A-Z\t\t\t\t\t"));
                            System.out.println(Table.infoMenu("6. Hiển thị sản phẩm theo lợi nhuận từ cao - thấp\t"));
                            System.out.println(Table.infoMenu("7. Tìm kiếm sản phẩm\t\t\t\t\t\t\t\t"));
                            System.out.println(Table.infoMenu("8. Quay lại\t\t\t\t\t\t\t\t\t\t"));
                            System.out.println(Table.borderMenuBottom());
                            System.out.print("Chọn chức năng (1 - 7): ");
                            try {
                                int choiceCategoryMenu = Integer.parseInt(scanner.nextLine().trim());
                                switch (choiceCategoryMenu) {
                                    case 1:
                                        Management.displayDataCategory();
                                        Management.inputDataProduct();
                                        Management.writeProductToData("products.txt", productList, categoryList);
                                        Management.writeCategoryToData("categories.txt", categoryList);
                                        break;
                                    case 2:
                                        Management.displayDataProduct();
                                        break;
                                    case 3:
                                        Management.updateDataProduct();
                                        Management.writeProductToData("products.txt", productList, categoryList);
                                        Management.writeCategoryToData("categories.txt", categoryList);
                                        Management.displayDataProduct();
                                        break;
                                    case 4:
                                        Management.removeDataProduct();
                                        Management.writeProductToData("products.txt", productList, categoryList);
                                        Management.displayDataProduct();
                                        break;
                                    case 5:
                                        Management.sortASCProductName();
                                        break;
                                    case 6:
                                        Management.sortASCProfit();
                                        break;
                                    case 7:
                                        Management.searchProduct();
                                        break;
                                    case 8:
                                        System.out.println(AttributeText.RED_BACKGROUND_BRIGHT + "Quay lại Shop Management" + AttributeText.RESET);
                                        Management.writeProductToData("products.txt", productList, categoryList);
                                        productMenu = false;
                                        break;
                                    default:
                                        System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "Vui lòng chọn chức năng (1 - 8)!" + AttributeText.RESET);
                                        break;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println(AttributeText.RED_BRIGHT + "Vui lòng nhập chức năng (1 - 8)!" + AttributeText.RESET);
                            }
                        } while (productMenu);
                        break;
                    case 3:
                        boolean orderMenu = true;
                        do {
                            System.out.println(Table.titleMenu("ORDER MANAGEMENT"));
                            System.out.println(Table.borderMenuTop());
                            System.out.println(Table.infoMenu("1. Đặt hàng\t\t\t\t\t\t\t\t\t\t"));
                            System.out.println(Table.infoMenu("2. Hiển thị đơn hàng\t\t\t\t\t\t\t\t"));
                            System.out.println(Table.infoMenu("3. Cập nhật đơn hàng\t\t\t\t\t\t\t\t"));
                            System.out.println(Table.infoMenu("4. Thoát\t\t\t\t\t\t\t\t\t\t\t"));
                            System.out.println(Table.borderMenuBottom());
                            System.out.print("Chọn chức năng (1 - 4): ");
                            try {
                                int choiceOrderMenu = Integer.parseInt(scanner.nextLine());
                                switch (choiceOrderMenu) {
                                    case 1:
                                        // Hiển thị product list
                                        displayDataProduct();
                                        inputOrder();
                                        break;
                                    case 2:
                                        break;
                                    case 3:
                                        break;
                                    case 4:
                                        System.out.println(AttributeText.RED_BACKGROUND_BRIGHT + "Quay lại Shop Management" + AttributeText.RESET);
                                        orderMenu = false;
                                        break;
                                    default:
                                        System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "Vui lòng chọn chức năng (1 - 4)!" + AttributeText.RESET);
                                        break;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println(AttributeText.RED_BRIGHT + "Vui lòng nhập chức năng (1 - 4)!" + AttributeText.RESET);
                            }
                        } while (orderMenu);
                        break;
                    case 4:
                        System.out.println(AttributeText.RED_BACKGROUND_BRIGHT + "Đã thoát chương trình!" + AttributeText.RESET);
                        System.exit(0);
                        break;
                    default:
                        System.out.println(AttributeText.YELLOW_BRIGHT + "Vui lòng nhập chức năng (1 - 4)!" + AttributeText.RESET);
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println(AttributeText.RED_BRIGHT + "Lỗi: Chức năng nhập vào không đúng!" + AttributeText.RESET); // lỗi nhập sai chức năng
            }
        } while (true);
    }

    /**
     * Save data to file (Category)
     */
    public static void writeCategoryToData(String fileCategory, List<Category> categoryList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileCategory, false))) { // true: để ghi chèn tiếp data, false: để ghi đè
            for (Category category : categoryList) {
                String categoryInfo = "------- Category List -------" + "\n"
                        + "ID: " + category.getCategoryId() + "\n"
                        + "Name: " + category.getCategoryName() + "\n"
                        + "Description: " + category.getDescription() + "\n"
                        + "Status: " + category.getStatus() + "\n"
                        + "-----------------------------" + "\n";
                writer.write(categoryInfo);
            }
        } catch (IOException | NullPointerException e) {
            System.out.println(AttributeText.RED_BRIGHT + "Lỗi: " + e.getMessage() + AttributeText.RESET);
        }
    }

    /**
     * Read file data (Category)
     */
    public static void readCategoryDataFromFile(String fileCategory) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileCategory))) {
            String line;
            int id = 0;
            String name = "";
            String description = "";
            boolean status;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("ID: ")) {
                    try {
                        id = Integer.parseInt(line.substring(4));
                    } catch (NumberFormatException e) {
                        id = 0; // Xử lý lỗi chuyển đổi
                    }
                } else if (line.startsWith("Name: ")) {
                    name = line.substring(6);
                } else if (line.startsWith("Description: ")) {
                    description = line.substring(13);
                } else if (line.startsWith("Status: ")) {
                    try {
                        status = Boolean.parseBoolean(line.substring(8));
                    } catch (Exception e) {
                        status = false; // Xử lý lỗi chuyển đổi
                    }
                    Category category = new Category(id, name, description, status);
                    categoryList.add(category);
                }
            }
        } catch (IOException | NullPointerException e) {
            System.out.println(AttributeText.RED_BRIGHT + "Lỗi: " + e.getMessage() + AttributeText.RESET);
        }
    }

    /**
     * Save data to file (Product)
     */
    public static void writeProductToData(String fileProduct, List<Product> productList, List<Category> categoryList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileProduct, false))) { // true: để ghi chèn tiếp data, false: để ghi đè
            for (Product product : productList) {
                String getCategoryName = ""; // Reset getCategoryName cho mỗi sản phẩm
                for (Category category : categoryList) {
                    if (category.getCategoryId() == product.getCategoryId()) {
                        getCategoryName = category.getCategoryName();
                        break; // Tìm thấy categoryName, thoát vòng lặp
                    }
                }

                String productInfo = "------- Product List -------" + "\n"
                        + "Category ID: " + product.getCategoryId() + "\n"
                        + "Category Name: " + getCategoryName + "\n"
                        + "Product ID: " + product.getProductId() + "\n"
                        + "Product Name: " + product.getProductName() + "\n"
                        + "Quantity: " + product.getQuantity() + "\n"
                        + "Import Price: " + product.getImportPrice() + "\n"
                        + "Export Price: " + product.getExportPrice() + "\n"
                        + "Profits: " + product.getProfit() + "\n"
                        + "Description: " + product.getDescription() + "\n"
                        + "Product Status: " + product.getStatus() + "\n"
                        + "-----------------------------" + "\n";
                writer.write(productInfo);
            }
        } catch (IOException | NullPointerException e) {
            System.out.println(AttributeText.RED_BRIGHT + "Lỗi: " + e.getMessage() + AttributeText.RESET);
        }
    }

    /**
     * Read file data (Product)
     */
    public static void readProductDataFromFile(String fileProduct, List<Product> productList, List<Category> categoryList) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileProduct))) {
            String line;
            int cateId = 0;
            String cateName = "";
            String prId = "";
            String name = "";
            int quantity = 0;
            double importPrice = 0F;
            double exportPrice = 0F;
            double profit = 0F;
            String description = "";
            boolean status = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Category ID: ")) {
                    try {
                        cateId = Integer.parseInt(line.substring(13));
                    } catch (NumberFormatException e) {
                        cateId = 0; // Xử lý lỗi chuyển đổi
                    }
                } else if (line.startsWith("Category Name: ")) {
                    cateName = line.substring(15);
                } else if (line.startsWith("Product ID: ")) {
                    prId = line.substring(12);
                } else if (line.startsWith("Product Name: ")) {
                    name = line.substring(14);
                } else if (line.startsWith("Quantity: ")) {
                    try {
                        quantity = Integer.parseInt(line.substring(10));
                    } catch (NumberFormatException e) {
                        quantity = 0; // Xử lý lỗi chuyển đổi
                    }
                } else if (line.startsWith("Import Price: ")) {
                    try {
                        importPrice = Double.parseDouble(line.substring(14));
                    } catch (NumberFormatException e) {
                        importPrice = 0F; // Xử lý lỗi chuyển đổi
                    }
                } else if (line.startsWith("Export Price: ")) {
                    try {
                        exportPrice = Double.parseDouble(line.substring(14));
                    } catch (NumberFormatException e) {
                        exportPrice = 0F; // Xử lý lỗi chuyển đổi
                    }
                } else if (line.startsWith("Profits: ")) {
                    try {
                        profit = Double.parseDouble(line.substring(9));
                    } catch (NumberFormatException e) {
                        profit = 0F; // Xử lý lỗi chuyển đổi
                    }
                } else if (line.startsWith("Description: ")) {
                    description = line.substring(13);
                } else if (line.startsWith("Product Status: ")) {
                    try {
                        status = Boolean.parseBoolean(line.substring(16));
                    } catch (Exception e) {
                        status = false; // Xử lý lỗi chuyển đổi
                    }

                    // Tạo đối tượng Product và thêm vào danh sách sản phẩm
                    Product product = new Product(prId, name, importPrice, exportPrice, profit, description, status, cateName, categoryList, cateId, quantity);
                    productList.add(product);
                }
            }
        } catch (IOException | NullPointerException e) {
            System.out.println(AttributeText.RED_BRIGHT + "Lỗi: " + e.getMessage() + AttributeText.RESET);
        }
    }

    /**
     * Input data Category
     */
    public static void inputDataCategory() {
        boolean isCheck = false;
        do {
            System.out.print("Nhập số lượng danh mục muốn thêm: ");
            try {
                int num = Integer.parseInt(scanner.nextLine().trim());
                if (num > 0) {
                    for (int i = 0; i < num; i++) {
                        System.out.println(AttributeText.CYAN_BRIGHT + "----- Nhập thông tin danh mục " + (i + 1) + " -----" + AttributeText.RESET);
                        Category category = new Category();
                        category.inputData(scanner, categoryList);
                        categoryList.add(category);
                    }
                    isCheck = true;
                    System.out.println(AttributeText.GREEN_BRIGHT + "Đã thêm danh mục, thành công!" + AttributeText.RESET);
                } else {
                    System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "*Số lượng danh mục yêu cầu là số nguyên dương!" + AttributeText.RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(AttributeText.RED_BRIGHT + "Lỗi: " + e.getMessage() + AttributeText.RESET);
            }
        } while (!isCheck);
    }

    /**
     * Display data for Category
     */
    public static void displayDataCategory() {
        System.out.println(Table.border());
        System.out.println(Table.titleCategoryList());
        System.out.println(Table.border());
        System.out.println(Table.tableCategory());
        System.out.println(Table.border());
        if (categoryList.isEmpty()) {
            System.out.println(AttributeText.YELLOW_BRIGHT + "*Chưa có danh mục nào trong danh sách!" + AttributeText.RESET);
        } else {
            for (Category category : categoryList) {
                category.displayData();
            }
            System.out.println(Table.border());
        }
    }

    /**
     * Update data category
     */
    public static void updateDataCategory(Scanner scanner, List<Category> categoryList) {
        if (categoryList.isEmpty()) {
            System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "*Chưa có danh mục nào để cập nhật!" + AttributeText.RESET);
            return;
        }

        boolean isCheck = false;
        while (!isCheck) {
            // ID and name are unique
            System.out.print("Nhập ID hoặc tên danh mục để cập nhật - (exit) để thoát: "); // thêm option (exit)
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("e")) {
                System.out.println(AttributeText.RED_BACKGROUND + "Đã thoát cập nhật." + AttributeText.RESET);
                return;
            }
            int id;
            try {
                id = Integer.parseInt(input);
                for (Category category : categoryList) {
                    // kiểm tra ID tồn tại trong categoryList ?
                    if (category.getCategoryId() == id) {
                        System.out.println(AttributeText.CYAN_BRIGHT + "---- Cập nhật thông tin danh mục ----" + AttributeText.RESET);
                        category.setCategoryName(category.validateCategoryName(scanner, categoryList));
                        category.setDescription(category.validateDescription(scanner));
                        category.setStatus(category.validateCategoryStatus(scanner));
                        System.out.println(AttributeText.GREEN_BRIGHT + "Cập nhật danh mục thành công!" + AttributeText.RESET);
                        isCheck = true;
                    }
                }
            } catch (NumberFormatException e) {
                for (Category category : categoryList) {
                    // kiểm tra categoryName tồn tại trong categoryList ?
                    if (category.getCategoryName().equalsIgnoreCase(input)) {
                        System.out.println(AttributeText.CYAN_BRIGHT + "---- Cập nhật thông tin danh mục ----" + AttributeText.RESET);
                        category.setCategoryName(category.validateCategoryName(scanner, categoryList));
                        category.setDescription(category.validateDescription(scanner));
                        category.setStatus(category.validateCategoryStatus(scanner));
                        System.out.println(AttributeText.GREEN_BRIGHT + "Cập nhật danh mục thành công!" + AttributeText.RESET);
                        isCheck = true;
                    }
                }
            }
            if (!isCheck) {
                System.out.println(AttributeText.RED_BRIGHT + "*Không tìm thấy " + input + " trong danh sách!" + AttributeText.RESET);
            }
        }
    }

    /**
     * Remove category data by ID
     */
    public static void removeCategoryById() {
        if (categoryList.isEmpty()) {
            System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "*Chưa có danh mục nào để xóa!" + AttributeText.RESET);
            return;
        }
        boolean ischeck = false;
        while (!ischeck) {
            System.out.print("Nhập mã (ID) danh mục muốn xóa - (exit) để thoát: "); // thêm option (exit)
            String input = scanner.nextLine().trim().toLowerCase();
            int id;
            try {
                id = Integer.parseInt(input);
                boolean checkRemove = false;
                boolean isExist = false;
                for (int i = 0; i < categoryList.size(); i++) {
                    if (categoryList.get(i).getCategoryId() == id) {
                        if (!isExistProductInCategory(categoryList.get(i), productList)) { // kiểm tra có product nằm trong category ?
                            categoryList.remove(id - 1);
                            checkRemove = true;
                            ischeck = true;
                        } else {
                            isExist = true;
                        }
                        break;
                    }
                }
                if (checkRemove) {
                    System.out.println(AttributeText.GREEN_BRIGHT + "Đã xóa danh mục, thành công!" + AttributeText.RESET);
                }
                if (isExist) {
                    System.err.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "*Danh mục đang chứa sản phẩm không thể xóa!" + AttributeText.RESET);
                } else {
                    System.err.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "*Không có danh mục [ " + input + " ] trong danh sách." + AttributeText.RESET);
                }
            } catch (NumberFormatException e) {
                if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("e")) {
                    System.out.println(AttributeText.RED_BACKGROUND + "Đã thoát cập nhật." + AttributeText.RESET);
                    return;
                } else {
                    System.err.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "*Không có danh mục [ " + input + " ] trong danh sách." + AttributeText.RESET);
                }
            }
        }
    }

    /**
     * Check the product exists in the category
     */
    public static boolean isExistProductInCategory(Category category, List<Product> productsList) {
        for (Product product : productsList) {
            if (product.getCategoryId() == category.getCategoryId()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Search category by name
     */
    public static void searchCategoryByName() {
        if (!categoryList.isEmpty()) {
            System.out.print("Nhập tên danh mục cần tìm kiếm: ");
            String searchName = scanner.nextLine().trim();
            List<Category> categorySearched = categoryList.stream().filter(category -> category.getCategoryName().toLowerCase().contains(searchName.toLowerCase())).collect(Collectors.toList());
            if (categorySearched.isEmpty()) {
                System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "*Không tìm thấy [ " + searchName + " ] trong danh sách!" + AttributeText.RESET);
                return;
            }
            System.out.println(Table.border());
            System.out.println(Table.titleCategoryList());
            System.out.println(Table.border());
            System.out.println(Table.tableCategory());
            System.out.println(Table.border());
            categorySearched.forEach(Category::displayData);
            System.out.println(Table.border());
        }
    }

    /**
     * Category statistics by status: thống kê danh mục theo trạng thái
     */
    public static void statsByProductStatus(List<Category> categoryList, List<Product> productList) {
        if (productList.isEmpty()) {
            System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "*Chưa có sản phẩm nào trong danh mục!" + AttributeText.RESET);
        }
        // thêm table hiển thị
        String border = "─";
        String borderT = " ";
        String repeated = new String(new char[50]).replace("\0", border);
        String repeatedTitle = new String(new char[15]).replace("\0", borderT);
        System.out.println("┌ " + repeated + " ┐");
        System.out.println("| " + repeatedTitle + " Statistical Tables " + repeatedTitle + " |");
        System.out.println("├ " + repeated + " ┤");
        System.out.printf("| %-25s | %-33s |\n", AttributeText.WHITE_BOLD_BRIGHT + "Category", "Quantity" + AttributeText.RESET);
        System.out.println("├ " + repeated + " ┤");
        for (Category category : categoryList) {
            int count = 0;
            for (Product product : productList) {
                if (product.getCategoryId() == category.getCategoryId()) {
                    count++;
                }
            }
            System.out.printf("| %-25s | %-22d |\n", category.getCategoryName(), count);
        }
        System.out.println("└ " + repeated + " ┘");
    }

    /**
     * Input data product
     */
    public static void inputDataProduct() {
        if (categoryList.isEmpty()) {
            System.out.println(AttributeText.RED_BRIGHT + "Lưu ý: Cần thêm danh mục trước khi thêm sản phẩm !" + AttributeText.RESET);
        } else {
            boolean isCheck = false;
            do {
                System.out.print("Nhập " + AttributeText.YELLOW_BRIGHT + "số lượng " + AttributeText.RESET + "sản phẩm muốn thêm: ");
                String n = scanner.nextLine();
                try {
                    int num = Integer.parseInt(n);
                    if (num > 0) {
                        for (int i = 0; i < num; i++) {
                            System.out.println(AttributeText.CYAN_BRIGHT + "----- Nhập thông tin sản phẩm " + (i + 1) + " -----" + AttributeText.RESET);
                            Product product = new Product();
                            product.setCategoryList(categoryList);
                            product.inputData(scanner, productList, categoryList);
                            productList.add(product);
                            /*
                             * Hỏi người dùng muốn thay đổi trạng thái category ?
                             * */
                            System.out.println(AttributeText.YELLOW_BRIGHT + "Bạn có muốn thay đổi trạng thái danh mục sau khi thêm sản phẩm không ?" + AttributeText.RESET);
                            System.out.print("Nhập 'yes'(Y) hoặc 'no'(N): ");
                            String changeCategoryStatus = scanner.nextLine().trim().toLowerCase();
                            if (changeCategoryStatus.equalsIgnoreCase("yes") || changeCategoryStatus.equalsIgnoreCase("y")) {
                                // Gọi hàm thay đổi trạng thái danh mục
                                ;
                                updateCategoryStatus(categoryList, product.getCategoryId());
                                System.out.println(AttributeText.GREEN_BRIGHT + "Đã cập nhật trạng thái danh mục, thành công!" + AttributeText.RESET);
                            }
                        }
                        isCheck = true;
                        System.out.println(AttributeText.GREEN_BRIGHT + "Thêm mới sản phẩm, thành công!" + AttributeText.RESET);
                    } else {
                        System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "*Số lượng sản phẩm yêu cầu là số nguyên dương!" + AttributeText.RESET);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(AttributeText.RED_BRIGHT + "Lỗi: Bạn vừa nhập vào " + n + AttributeText.RESET);
                }
            } while (!isCheck);
        }
    }

    /**
     * Display data for Product
     */
    public static void displayDataProduct() {
        System.out.println(Table.borderP());
        System.out.println(Table.titleProductList());
        System.out.println(Table.borderP());
        System.out.println(Table.tableProduct());
        System.out.println(Table.borderP());
        if (productList.isEmpty()) {
            System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "*Chưa có sản phẩm nào trong danh sách!" + AttributeText.RESET);
        } else {
            for (Product product : productList) {
                product.displayData();
            }
            System.out.println(Table.borderP());
        }
    }

    /**
     * Update data Product
     */
    public static void updateDataProduct() {
        if (productList.isEmpty()) {
            System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "*Chưa có sản phẩm nào để cập nhật!" + AttributeText.RESET);
            return;
        }

        boolean isCheck = false;
        boolean checkStatusUpdate = false;
        while (!isCheck && !checkStatusUpdate) {
            // ID and name are unique
            System.out.print("Nhập ID hoặc tên sản phẩm để cập nhật - (exit) để thoát: ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("e")) {
                System.out.println(AttributeText.RED_BACKGROUND + "Đã thoát cập nhật." + AttributeText.RESET);
                return;
            }
            for (Product product : productList) {
                // kiểm tra ID hoặc categoryName tồn tại trong productList ?
                if (product.getProductId().equalsIgnoreCase(input) || product.getProductName().equalsIgnoreCase(input)) {
                    product.setProductName(product.validateProductName(scanner, productList));
                    product.setImportPrice(product.validateImportPrice(scanner));
                    product.setExportPrice(product.validateExportPrice(scanner));
                    product.setDescription(product.validateDescription(scanner));
                    product.setStatus(product.validateProductStatus(scanner));
                    System.out.println(AttributeText.GREEN_BRIGHT + "Cập nhật sản phẩm thành công!" + AttributeText.RESET);

                    /*
                     * Hỏi người dùng muốn thay đổi trạng thái category ?
                     * */
                    System.out.println(AttributeText.YELLOW_BRIGHT + "Bạn có muốn thay đổi trạng thái danh mục sau khi cập nhật sản phẩm không ?" + AttributeText.RESET);
                    System.out.print("Nhập 'yes'(Y) hoặc 'no'(N): ");
                    String changeCategoryStatus = scanner.nextLine().trim().toLowerCase();
                    if (changeCategoryStatus.equalsIgnoreCase("yes") || changeCategoryStatus.equalsIgnoreCase("y")) {
                        // Gọi hàm thay đổi trạng thái danh mục
                        updateCategoryStatus(categoryList, product.getCategoryId());
                        System.out.println(AttributeText.GREEN_BRIGHT + "Đã cập nhật trạng thái danh mục, thành công!" + AttributeText.RESET);
                    }
                    isCheck = true; // thoát vòng lặp
                }
            }
            if (!isCheck) {
                System.out.println(AttributeText.RED_BRIGHT + "Không tìm thấy [ " + input + " ] trong danh sách: " + AttributeText.RESET);
            }
        }
    }

    /**
     * Update trạng thái category sau khi thêm product hoặc update product ...
     *
     * @param input:       lấy categoryId để kiểm tra status của category hiện tại
     * @param categoryList : lấy dữ liệu 1 objCategory từ list category
     */
    public static void updateCategoryStatus(List<Category> categoryList, int input) {
        for (Category category : categoryList) {
            if ((category.getCategoryId() == input)) {
                System.out.println("Danh mục: " + category.getCategoryName());
                System.out.println("Trạng thái hiện tại: " + (category.getStatus() ? AttributeText.GREEN_BRIGHT + "Hoạt động" + AttributeText.RESET : AttributeText.YELLOW_BRIGHT + "Không hoạt động" + AttributeText.RESET));
                System.out.print("Nhập trạng thái mới (true/false): ");
                String newStatus = scanner.nextLine().trim();

                if (newStatus.equalsIgnoreCase("true") || newStatus.equalsIgnoreCase("false")) {
                    category.setStatus(Boolean.parseBoolean(newStatus));
                    System.out.println(AttributeText.GREEN_BRIGHT + "Thay đổi trạng thái thành công!" + AttributeText.RESET);
                } else {
                    System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "Vui lòng nhập trạng thái hợp lệ (true/false)." + AttributeText.RESET);
                }
                return;
            }
        }

        System.out.println(AttributeText.RED_BRIGHT + "Không tìm thấy danh mục: [ " + input + " ]" + AttributeText.RESET);
    }

    /**
     * Remove data Product
     */
    public static void removeDataProduct() {
        if (productList.isEmpty()) {
            System.out.println(AttributeText.RED_BRIGHT + "Chưa có sản phẩm để xóa!" + AttributeText.RESET);
            return;
        }
        boolean isCheck = false;
        while (!isCheck) {
            System.out.print("Nhập mã (ID) sản phẩm muốn xóa: ");
            String removeProductID = scanner.nextLine().trim();
            boolean checkRemove = false;
            for (int i = 0; i < productList.size(); i++) {
                if (productList.get(i).getProductId().equals(removeProductID)) {
                    productList.remove(productList.get(i));
                    checkRemove = true;
                    isCheck = true;
                    break;
                }
            }
            if (checkRemove) {
                System.out.println(AttributeText.GREEN_BRIGHT + "Đã xóa sản phẩm, thành công!" + AttributeText.RESET);
            }
            if (!isCheck) {
                System.out.println(AttributeText.RED_BRIGHT + "Không có sản phẩm [ " + removeProductID + " ] trong danh sách." + AttributeText.RESET);
            }
        }
    }

    /**
     * Sort Ascending productName
     */
    public static void sortASCProductName() {
        if (productList.isEmpty()) {
            System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "*Chưa có sản phẩm để hiển thị!" + AttributeText.RESET);
            return;
        }
        System.out.println(Table.borderP());
        System.out.println(Table.titleProductList());
        System.out.println(Table.borderP());
        System.out.println(Table.tableProduct());
        System.out.println(Table.borderP());
        List<Product> sortASCName = productList.stream().sorted(Comparator.comparing(Product::getProductName)).collect(Collectors.toList());
        for (Product product : sortASCName) {
            product.displayData();
        }
        System.out.println(Table.borderP());
    }

    /**
     * Sort Ascending Profit
     */
    public static void sortASCProfit() {
        if (productList.isEmpty()) {
            System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "*Chưa có sản phẩm để hiển thị!" + AttributeText.RESET);
            return;
        }
        System.out.println(Table.borderP());
        System.out.println(Table.titleProductList());
        System.out.println(Table.borderP());
        System.out.println(Table.tableProduct());
        System.out.println(Table.borderP());
        List<Product> sortASCProfit = productList.stream().sorted(Comparator.comparing(Product::getProfit)).collect(Collectors.toList());
        for (Product product : sortASCProfit) {
            product.displayData();
        }
        System.out.println(Table.borderP());
    }

    /**
     * Search Product (Name, ImportPrice, ExportPrice)
     */
    public static void searchProduct() {
        boolean isCheck = false;
        while (!isCheck) {
            System.out.println(AttributeText.CYAN_UNDERLINED + "* tên sản phẩm hoặc giá nhập, xuất của sản phẩm *" + AttributeText.RESET);
            System.out.print("Nhập dữ liệu cần tìm kiếm: ");
            String searchData = scanner.nextLine().toLowerCase().trim();
            List<Product> foundProducts = productList.stream()
                    .filter(product -> product.getProductName().toLowerCase().contains(searchData.toLowerCase())
                            || String.valueOf(product.getImportPrice()).contains(searchData)
                            || String.valueOf(product.getExportPrice()).contains(searchData)
                    ).collect(Collectors.toList());
            isCheck = true;
            if (!foundProducts.isEmpty()) {
                System.out.println(Table.borderP());
                System.out.println(Table.titleProductList());
                System.out.println(Table.borderP());
                System.out.println(Table.tableProduct());
                System.out.println(Table.borderP());
                foundProducts.forEach(Product::displayData);
                System.out.println(Table.borderP());
            } else {
                System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "Không tìm thấy [ " + searchData + " ] trong danh sách" + AttributeText.RESET);
            }
        }
    }

    /**
     * Input order
     */
    public static void inputOrder() {
        if (productList.isEmpty()) {
            System.out.println(AttributeText.RED_BRIGHT + "Lưu ý: Chưa có sản phẩm để đặt hàng !" + AttributeText.RESET);
        } else {
            boolean isCheck = false;
            do {
                System.out.print("Nhập " + AttributeText.YELLOW_BRIGHT + "số lượng " + AttributeText.RESET + "mặt hàng muốn đặt: ");
                String input = scanner.nextLine();
                try {
                    int num = Integer.parseInt(input);
                    if (num > 0) {
                        for (int i = 0; i < num; i++) {
                            System.out.println(AttributeText.CYAN_UNDERLINED + AttributeText.ITALIC + "------- Order List " + (i + 1) + " -------" + AttributeText.RESET);
                            OrderItem orderItem = new OrderItem();
                            orderItem.setProductList(productList);
                            orderItem.inputData(scanner, productList);
                        }
                        isCheck = true;
                        System.out.println(AttributeText.GREEN_BRIGHT + "Đặt hàng sản phẩm, thành công!" + AttributeText.RESET);
                    } else {
                        System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "*Số lượng sản phẩm yêu cầu là số nguyên dương!" + AttributeText.RESET);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(AttributeText.RED_BRIGHT + "Lỗi: Bạn vừa nhập vào [ " + input + " ]" + AttributeText.RESET);
                }
            } while (!isCheck);
        }
    }
}
