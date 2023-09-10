package ShopManagement.Attribute;

public class Table {
    public static String titleCategoryList() {
        String border = " ";
        String repeated = new String(new char[41]).replace("\0", border);
        return String.format("| " + repeated + AttributeText.PURPLE_BOLD_BRIGHT + "Category List" + AttributeText.RESET + repeated + " |");
    }

    public static String titleProductList() {
        String border = " ";
        String repeated = new String(new char[94]).replace("\0", border);
        return String.format("| " + repeated + AttributeText.PURPLE_BOLD_BRIGHT + "Product List" + AttributeText.RESET + repeated + " |");
    }

    public static String tableCategory() {
        return String.format("| %-22s | %-25s | %-25s | %-25s |"
                , AttributeText.WHITE_BOLD_BRIGHT + "ID Category", "Category Name", "Description", "Status" + AttributeText.RESET);
    }

    public static String tableProduct() {
        return String.format("| %-24s | %-30s | %-10s | %-25s | %-10s | %-14s | %-14s | %-10s | %-23s | %-24s |"
                , AttributeText.WHITE_BOLD_BRIGHT + "Category ID", "Category Name", "ID Product", "Product Name", "Quantity", "Import Price", "Export Price", "Profit", "Description", "Status" + AttributeText.RESET);
    }

    public static String border() {
        String border = "═";
        String repeated = new String(new char[95]).replace("\0", border);
        return String.format("+ " + repeated + " +");
    }

    public static String borderP() {
        String border = "═";
        String repeated = new String(new char[200]).replace("\0", border);
        return String.format("+ " + repeated + " +");
    }

    public static String titleMenu(String titleMenu) {
        String border = "━";
        String repeated;
        if (titleMenu.equals("SHOP MANAGEMENT")) {
            repeated = new String(new char[29]).replace("\0", border);
        } else {
            repeated = new String(new char[27]).replace("\0", border);
        }
        return String.format("* >" + repeated + AttributeText.CYAN_BRIGHT + titleMenu + AttributeText.RESET + repeated + "< *");
    }

    public static String infoMenu(String info) {
        String border = " ";
        String repeated = new String(new char[13]).replace("\0", border);
        return String.format("║" + repeated + info + repeated + "║");
    }

    public static String borderMenuTop() {
        String border = "═";
        String repeated = new String(new char[74]).replace("\0", border);
        return String.format(" ╔" + repeated + "╗");
    }

    public static String borderMenuBottom() {
        String border = "═";
        String repeated = new String(new char[74]).replace("\0", border);
        return String.format(" ╚" + repeated + "╝");
    }
}
