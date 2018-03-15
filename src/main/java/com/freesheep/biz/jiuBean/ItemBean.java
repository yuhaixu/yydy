package com.freesheep.biz.jiuBean;

public class ItemBean extends BaseBean {

    // 商品编码
    private String itemCode;
    // 商品仓储系统编码
    private String itemId;
    // 包裹内该商品的数量
    private String quantity;
    // 商品名称
    private String itemName;
    // 条形码
    private String barCode;
    // 商品类型
    private String itemType;
    // 仓库编码
    private String warehouseCode;
    // 库存类型
    private String inventoryType;
    // 冻结库存数量
    private String lockQuantity;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(String inventoryType) {
        this.inventoryType = inventoryType;
    }

    public String getLockQuantity() {
        return lockQuantity;
    }

    public void setLockQuantity(String lockQuantity) {
        this.lockQuantity = lockQuantity;
    }

    @Override
    public String toString() {
        return "ItemBean{" +
                "itemCode='" + itemCode + '\'' +
                ", itemId='" + itemId + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
