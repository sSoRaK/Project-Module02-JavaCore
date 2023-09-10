package ShopManagement.Entity;

import ShopManagement.Attribute.AttributeText;
import ShopManagement.Interface.ICategory;

import java.util.List;
import java.util.Scanner;

import static ShopManagement.Management.categoryList;

public class Category implements ICategory {
    private int categoryId;
    private String categoryName;
    private String description;
    private boolean status;

    public Category() {
    }

    public Category(int categoryId, String categoryName, String description, boolean status) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
        this.status = status;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    @Override
    public void inputData(Scanner scanner, List<Category> categoryList) {
        this.categoryId = validateCategoryId(scanner, categoryList);
        this.categoryName = validateCategoryName(scanner, categoryList);
        this.description = validateDescription(scanner);
        this.status = validateCategoryStatus(scanner);
    }

    @Override
    public void displayData() {
        String displayStatus = this.status ? "Hoạt động" : "Không hoạt động";
        String dependStatus = this.status ? AttributeText.GREEN_BRIGHT : AttributeText.YELLOW_BRIGHT;
        System.out.printf("| %-15d | %-25s | %-25s | " + dependStatus + "%-21s" + AttributeText.RESET + " |\n", categoryId, categoryName, description, displayStatus);
    }

    public static int findIndexById(int id) {
        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).getCategoryId() == id) {
                return i;
            }
        }
        return -1;
    }

    public int validateCategoryId(Scanner scanner, List<Category> categoryList) {
        do {
            System.out.print("Nhập mã danh mục: ");
            String input = scanner.nextLine().trim().toLowerCase();
            try {
                // exception cho trường hợp nhập không phải số
                int categoryId = Integer.parseInt(input);
                if (categoryId <= 0) {
                    System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "*Mã danh mục yêu cầu là số nguyên dương!" + AttributeText.RESET);
                } else {
                    // check unique for categoryId
                    boolean isExist = false;
                    for (Category category : categoryList) {
                        if (category.getCategoryId() == categoryId) {
                            isExist = true;
                            break;
                        }
                    }
                    if (!isExist) {
                        return categoryId;
                    } else {
                        System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "*Mã danh mục đã tồn tại!" + AttributeText.RESET);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println(AttributeText.RED_BRIGHT + "Lỗi: bạn vừa nhập " + input + AttributeText.RESET);
            }
        } while (true);
    }

    public String validateCategoryName(Scanner scanner, List<Category> categoryList) {
        do {
            System.out.print("Nhập tên danh mục: ");
            String categoryName = scanner.nextLine().trim();
            if (categoryName.length() >= 6 && categoryName.length() <= 30) {
                boolean isExist = false;
                for (Category category : categoryList) {
                    if (category.getCategoryName().equals(categoryName)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "*Tên danh mục đã tồn tại!" + AttributeText.RESET);
                } else {
                    return categoryName;
                }
            } else {
                System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "*Tên danh mục bao gồm 6 - 30 kí tự!" + AttributeText.RESET);
            }
        } while (true);
    }

    public String validateDescription(Scanner scanner) {
        do {
            System.out.print("Nhập mô tả danh mục: ");
            String description = scanner.nextLine().trim();
            if (!description.isEmpty()) {
                return description;
            } else {
                System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "*Vui lòng nhập mô tả danh mục!" + AttributeText.RESET);
            }
        } while (true);
    }

    public boolean validateCategoryStatus(Scanner scanner) {
        do {
            System.out.print("Nhập trạng thái danh mục: ");
            String status = scanner.nextLine().trim();
            if (status.equalsIgnoreCase("true") || status.equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(status);
            } else {
                System.out.println(AttributeText.YELLOW_BRIGHT + AttributeText.ITALIC + "*Vui lòng nhập trạng thái danh mục!" + AttributeText.RESET);
            }
        } while (true);
    }
}
