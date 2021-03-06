import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

/**
 * Warehouse contains the different items in stock
 * 
 * @author Allen Pan
 *
 */
public class Warehouse
{
    // the constructor
    ArrayList<Item> itemList = new ArrayList <> ();
    /**
     * This is the hardcoded data to be loaded into the instance variables.  
     */
    public void loadData(String fileName) throws IOException
    {
        Scanner inFile = new Scanner( new File(fileName));
        
        while (inFile.hasNext())
        {
            itemList.add( new Item(inFile.next(), inFile.next(), inFile.nextInt(),
                inFile.nextInt(), inFile.nextInt(), inFile.nextDouble(),
                inFile.nextInt(), inFile.nextInt()));
        }

    }

    /**
     * validates the item number that the user has entered
     * 
     * @param itemNum //item number that the user entered
     * @return boolean true/false // if the item number is found in the array, return true, vice versa
     */
    public boolean itemNoValidator(String itemNum)
    {
        for (int i = 0; i < itemList.size(); i++)
        {
            if ( itemNum.equals(itemList.get(i).getItemNo()))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * handles the item inquiry, which calls another method to find item details 
     * and return the item details
     * 
     * @param itemNumber // item number that the user entered
     * @return String itemDetails //item details in a string
     */
    public String itemInquiry(String itemNumber)
    {
        String itemDetails = "";
        for (int i = 0; i < itemList.size(); i++)
        {
            if ( itemNumber.equals(itemList.get(i).getItemNo()))
            {
                itemDetails = itemList.get(i).itemDetails();
                return itemDetails;
            }
        }
        return itemDetails;
    }

    /**
     * Handles the task of ordering from the supplier, which calls the setter method and set the amount
     * to the amount that the user has entered
     * 
     * 
     */
    public void orderFromSupplier(int amt, String itemNum)
    {
        if (amt > 0)
        {
            for (int i = 0; i < itemList.size(); i++)
            {
                if (itemNum.equals(itemList.get(i).getItemNo()))
                {
                    itemList.get(i).setOnOrder(itemList.get(i).getOnOrder() + amt);
                }
            }
        }
        else 
        {
            System.out.println("The amount you have entered is not valid");
        }
    }

    /**
     * Handles the task of receving shipments from supplier, which compares the amount received with the 
     * on order amount and set the amount on hand and on order based on the amount received
     * 
     * @param amt // the amount that the user wants to order
     * @param itemNum // the item number that the user has entered
     */
    public void receiveShipment(int amt, String itemNum)
    {
        if (amt > 0)
        {
            for (int i = 0; i < itemList.size(); i++)
            {
                if (itemNum.equals(itemList.get(i).getItemNo()))
                {
                    itemList.get(i).setOnHand(itemList.get(i).getOnHand() + amt);
                    if ( amt <= itemList.get(i).getOnOrder())
                    {
                        itemList.get(i).setOnOrder(itemList.get(i).getOnOrder() - amt);
                    }
                    else
                    {
                        itemList.get(i).setOnOrder(0);
                    }
                }
            }
        }
        else 
        {
            System.out.println("The amount you have entered is not valid");
        }
    }

    /**
     * Handles the task of returning items to the supplier, which compares the amount to return with 
     * the amount on hand and call the setter method to set the amount on hand
     * 
     * @param amt // the amount that the user wants to order
     * @param itemNum // the item number that the user has entered
     */
    public void returnToSupplier(int amt, String itemNum)
    {
        if (amt > 0)
        {
            for (int i = 0; i < itemList.size(); i++)
            {
                if (itemNum.equals(itemList.get(i).getItemNo()))
                {
                    if ( amt <= itemList.get(i).getOnHand())
                    {
                        itemList.get(i).setOnHand(itemList.get(i).getOnOrder() - amt);
                    }
                    else
                    {
                        itemList.get(i).setOnHand(0);
                    }
                }

            }
        }
        else 
        {
            System.out.println("The amount you have entered is not valid");
        }
    }

    /**
     * Handles the shipment to customer, which compares the amount entered by user with the committed amount
     * and the on hand amount and set the committed amount and on hand amount
     * 
     * @param amt // the amount that the user wants to order
     * @param itemNum // the item number that the user has entered
     */
    public void shipToCus(int amt, String itemNum)
    {
        if (amt > 0)
        {
            for ( int i = 0; i < itemList.size(); i++)
            {
                if (itemNum.equals(itemList.get(i).getItemNo()))
                {
                    if ( amt < itemList.get(i).getCommitted())
                    {
                        itemList.get(i).setCommitted(itemList.get(i).getCommitted() - amt);
                    }

                    else if (amt > itemList.get(i).getCommitted() && amt <=
                    (itemList.get(i).getCommitted() + itemList.get(i).getOnHand()))
                    {
                        itemList.get(i).setOnHand(amt - itemList.get(i).getCommitted());
                        itemList.get(i).setCommitted(0);

                    }

                    else if (amt > (itemList.get(i).getCommitted() + itemList.get(i).getOnHand()))
                    {
                        System.out.println("The requested amount is larger than the amount on hand and committed");
                    }
                }
            }
        }
        else 
        {
            System.out.println("The amount you have entered is not valid");
        }
    }

    /**
     * Handles customer orders, which compares the amount entered with the amount on hand and call 
     * different setters to set the values for on hand and committed
     * 
     * @param amt // the amount that the user wants to order
     * @param itemNum // the item number that the user has entered
     */
    public void cusOrder(int amt, String itemNum)
    {
        if (amt > 0)
        {
            for ( int i = 0; i < itemList.size(); i++)
            {
                if (itemNum.equals(itemList.get(i).getItemNo()))
                {
                    if ( amt <= itemList.get(i).getOnHand())
                    {
                        itemList.get(i).setCommitted( itemList.get(i).getCommitted() + amt);
                        itemList.get(i).setOnHand(itemList.get(i).getOnHand() - amt);
                    }
                    else 
                    {
                        itemList.get(i).setCommitted(itemList.get(i).getCommitted() +
                            itemList.get(i).getOnHand());
                        itemList.get(i).setOnHand(0);
                    }
                }

            }
        }
        else 
        {
            System.out.println("The amount you have entered is not valid");
        }
    }

    /**
     * Handles customer returns, which adds the amount on hand with the amount that the user has entered
     * 
     * @param amt // the amount that the user wants to order
     * @param itemNum // the item number that the user has entered
     */
    public void cusReturn (int amt, String itemNum)
    {
        if (amt > 0)
        {
            for ( int i = 0; i < itemList.size(); i++)
            {
                if (itemNum.equals(itemList.get(i).getItemNo()))
                {
                    itemList.get(i).setOnHand( itemList.get(i).getOnHand() + amt);
                }
            }
        }
        else 
        {
            System.out.println("The amount you have entered is not valid");
        }
    }

    /**
     * handles the end of day processing, which prints out all item details, and calls autoOrder method 
     * to order items 
     * 
     */
    public void endOfDayProcessing()
    {
        System.out.printf("%-12s%-12s%-12s%-12s%-12s%-12s%-12s%n", "Item Number", "Item Name", "On Hand", "Committed", "On Order", "Unit Price", "Item Value");
        for (int i =0; i < itemList.size(); i++)
        {
            System.out.println(itemList.get(i).toString());
        }
        autoOrder();
    }

    /**
     * helper method for endOfDayProcess, which place orders if the item on hand is less than the 
     * reorder point amount 
     * 
     */
    private void autoOrder()
    {
        for(int i = 0; i < itemList.size(); i++)
        {
            if (itemList.get(i).getOnHand() <= itemList.get(i).getReorderPoint())
            {
                orderFromSupplier(itemList.get(i).getEconOrderQty(), itemList.get(i).getItemNo());
            }
        }
    }

    /**
     * process transaction file and call different methods
     * based on the transaction type
     *
     * @throws IOException
     */
    public void prsTransaction() throws IOException {
        Scanner fileR = new Scanner(new File("../data/transaction.txt"));
        while (fileR.hasNext())
        {
            String itemNo = fileR.next();
            int transType = fileR.nextInt();
            int amt = fileR.nextInt();

            handleTransTypes(transType, amt, itemNo);
        }

        writeNewFile();
    }

    /**
     * handle different types of transactions
     *
     * @param typeNum // transaction type number
     * @param amount // amount in the transaction file
     * @param itemNum // item number in the transaction file
     */
    private void handleTransTypes(int typeNum, int amount, String itemNum) {
        final int CHOICE_TWO = 2;
        final int CHOICE_THREE = 3;
        final int CHOICE_FOUR = 4;
        final int CHOICE_FIVE = 5;
        final int CHOICE_SIX = 6;
        final int CHOICE_SEVEN = 7;

        if (typeNum == CHOICE_TWO)
        {
            orderFromSupplier(amount, itemNum);
        }
        else if (typeNum == CHOICE_THREE)
        {
            receiveShipment(amount, itemNum);
        }
        else if (typeNum == CHOICE_FOUR)
        {
            returnToSupplier(amount, itemNum);
        }
        else if (typeNum == CHOICE_FIVE)
        {
            shipToCus(amount, itemNum);
        }
        else if (typeNum == CHOICE_SIX)
        {
            cusOrder(amount, itemNum);
        }
        else if (typeNum == CHOICE_SEVEN)
        {
            cusReturn(amount, itemNum);
        }
        else
        {
            printError(itemNum, typeNum);
        }
    }

    /**
     * Prints an error message to notify the user that the transaction
     * wasn't complete
     *
     * @param itemNo // item number that didn't get processed
     * @param tNum // transaction type that's not valid
     */
    private void printError(String itemNo, int tNum) {

        System.out.println("The transaction for item number, " +itemNo+
            ", is not complete. Transaction type: " +tNum+ " is not valid");
    }

    /**
     * writes a new text file which include all the item details
     *
     * @throws IOException
     */
    private void writeNewFile() throws IOException{
        FileWriter writer = new FileWriter("../data/newInv.txt");
        BufferedWriter otWriter = new BufferedWriter(writer);
        for (int i = 0; i < itemList.size(); i++)
        {
            otWriter.write(itemList.get(i).itemToString());
            otWriter.newLine();
        }
        otWriter.close();
    }

    /**
     * add new items to the inventory
     *
     * @param itemNo // item number from the user
     * @param name // name of the product from the user
     * @param onHand // on hand amount from the user
     * @param price // unit price from the user
     * @param reorderP // reorder point amount from the user
     * @param econOrderQty // econ order quantity from the user
     */
    public void addItemToInv (String itemNo, String name, int onHand,
                              double price, int reorderP, int econOrderQty)
    {
        itemList.add(new Item(itemNo, name, onHand, price, reorderP, econOrderQty));

    }

    /**
     * remove item from the inventory with the matching
     * item number from the user
     *
     * @param itemNo // item number from the user
     */
    public void rmvItemFromInv(String itemNo)
    {
        for (int i = 0; i < itemList.size(); i++)
        {
            if (itemNo.equals(itemList.get(i).getItemNo()))
            {
                itemList.remove(i);
            }
        }
    }

    /**
     * change an item's unit price based on user inputs
     *
     * @param itemNum // item number from the user
     * @param amt // the new desired unit price
     */
    public void itemPChanger (String itemNum, double amt) {
        for (int i = 0; i < itemList.size(); i++)
        {
            if (itemNum.equals(itemList.get(i).getItemNo()))
            {
                itemList.get(i).setUnitPrice(amt);
            }
        }
    }

}
