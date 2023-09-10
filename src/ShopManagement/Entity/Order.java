package ShopManagement.Entity;

import java.util.List;

public class Order {
    private String orderID;
    private String orderDate;
    private String customName;
    private List<ShopManagement.Entity.OrderItem> orderItemList;

    public Order() {
    }

    public Order(String orderID, String orderDate, String customName, List<OrderItem> orderItemList) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customName = customName;
        this.orderItemList = orderItemList;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
    
}
