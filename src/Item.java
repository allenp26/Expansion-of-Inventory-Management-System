
/**
 * This is the class that defines the Inventory Item of a company 
 * 
 * @ Allen Pan
 * 
 */
public class Item
{
    // the instance variables (fields)
    private String itemNo;
    private String itemName;
    private int onHand;
    private int committed;
    private int onOrder;
    private double unitPrice;
    private int reorderPoint;
    private int econOrderQty;
    
    /**
     * A default (no-arg) constructor that sets 
     * all numerical values to 0 and strings to null
     * 
     */
    Item()
    {
        itemNo = null;
        itemName = null;
        onHand = 0;
        committed = 0;
        onOrder = 0;
        unitPrice = 0;
        reorderPoint = 0;
        econOrderQty = 0;
        
    }
    
    /**
     * A constructor that accepts as parameters, values for all fields
     */
    Item (String itemNum, String itemName, int onHand, int committed, 
        int onOrder, double unitPrice, int reorderPoint, int econOrderQty)
    {
        this.itemNo = itemNum;
        this.itemName = itemName;
        this.onHand = onHand;
        this.committed = committed;
        this.onOrder = onOrder;
        this.unitPrice = unitPrice;
        this.reorderPoint = reorderPoint;
        this.econOrderQty = econOrderQty;
    }
    
    /**
     * A constructor that accepts as parameters,
     * values for the fields except for committed and onOrder 
     * (those should default to 0)
     * 
     */
    Item (String itemNum, String itemName, int onHand, 
        double unitPrice, int reorderPoint, int econOrderQty)
    {
        this.itemNo = itemNum;
        this.itemName = itemName;
        this.onHand = onHand;
        this.unitPrice = unitPrice;
        this.reorderPoint = reorderPoint;
        this.econOrderQty = econOrderQty;
    }
    
    /**
     * A copy constructor that will take an Item object and copy all 
     * of its values
     */
    Item (Item otherItems)
    {
        this.itemNo = otherItems.getItemNo();
        this.itemName = otherItems.getItemName();
        this.onHand = otherItems.getOnHand();
        this.committed = otherItems.getCommitted();
        this.onOrder = otherItems.getOnOrder();
        this.unitPrice = otherItems.getUnitPrice();
        this.reorderPoint = otherItems.getReorderPoint();
        this.econOrderQty = otherItems.getEconOrderQty();
    }
    
    //  the accessors
    public String getItemNo()
    {
        return itemNo;
    }
    
    public String getItemName()
    {
        return itemName;
    }
    
    public int getOnHand()
    {
        return onHand;
    }
    
    public int getCommitted()
    {
        return committed;
    }
    
    public int getOnOrder()
    {
        return onOrder;
    }
    
    public double getUnitPrice()
    {
        return unitPrice;
    }
    
    public int getReorderPoint()
    {
        return reorderPoint;
    }
    
    public int getEconOrderQty()
    {
        return econOrderQty;
    }
    
    // the mutator
    public void setOnHand(int onHand)
    {
        this.onHand = onHand;
    }
    
    public void setCommitted(int committed)
    {
        this.committed = committed;
    }
    
    public void setOnOrder(int onOrder)
    {
        this.onOrder = onOrder;
    }
    
    public void setUnitPrice(double unitPrice)
    {
        this.unitPrice = unitPrice;
    }

    /**
     * gather item details 
     * 
     * @return item details to the method that has called
     */
    public String itemDetails()
    {
        return String.format("Item Number: %s%nItem Name: %s%nItem On Hand: %d%nItem Committed: %d%n" +
            "Item Price: %.2f%n", itemNo, itemName, onHand, committed, unitPrice); 
    }
    
    /**
     * helper method to calculate the item value 
     * 
     * @return item value to the method that has called
     */
    private double itemValueCalc()
    {
        return (unitPrice * (onHand + committed));
    }
    
    /**
     * Converts the item details in a string
     * 
     * @return item details in a string
     */
    public String toString()
    {
        return String.format("%-12s%-12s%-12d%-12d%-12d$%-12.2f$%-12.2f", itemNo, itemName, onHand, committed, onOrder, unitPrice, itemValueCalc());
        
    }

    /**
     * convert item details in a string for the file writer
     *
     * @return // item details
     */
    public String itemToString() {
        return String.format("%s %s %d %d %d %.2f %d %d ", itemNo, itemName, onHand, committed, onOrder, unitPrice, reorderPoint, econOrderQty);
    }
}
