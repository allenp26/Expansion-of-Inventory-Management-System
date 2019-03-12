import java.util.Scanner;
import java.io.*;

/**
 * This is the interface that the user will use to process Inventory for Phase 2
 * 
 * @author  Allen Pan
 * @version 
 */
/**
 * Instructor: 
 * Assumptions: 
 * Known errors: None (or, a SPECIFIC explanation of what you know doesn't work)
 *                e.g. not "sometime bombs when reading file" bit "bombs when reading if > 20 lines")
 *
 * DELETE extraneous info from this comment
*/
public class NewUI 
{
    private static Scanner kb = new Scanner(System.in);
    private static Warehouse wHouse = new Warehouse();
    private static final char CHOICE_ONE = '1';
    private static final char CHOICE_TWO = '2';
    private static final char CHOICE_THREE = '3';
    private static final char CHOICE_FOUR = '4';
    private static final char CHOICE_FIVE = '5';
    
    /**
     *  Based on the user's choice, transactions are processed
     */
    public static void main(String args[]) throws IOException
    {
        char usrIn;
        String fName = "../data/inventory.txt";
        wHouse.loadData(fName);

        do
        {
            mainMenu();
            usrIn = kb.next().charAt(0);
            handleUsrSelection(usrIn);
        }while (!usrChooseToCont(usrIn));
        
        
        System.out.println ("Thank you for using the Inventory Processing System");
        
    }
    
    
    /**
     * call different methods to handle tasks based on what the 
     * user has entered
     */
    private static void handleUsrSelection(int usrChoice) throws IOException
    {
        char usrIn;
        if (usrChoice == CHOICE_ONE) {
            handleItemInquiry();

        }
        else if (usrChoice == CHOICE_TWO) {
            do{
                invMenu();
                usrIn = kb.next().charAt(0);
                handleInvChoice(usrIn);
            }while(!usrChooseToBack(usrIn));
        }
        else if (usrChoice == CHOICE_THREE) {
            processTrans();
        }
        else if (usrChoice == CHOICE_FOUR) {
            handleEndDayProcessing();
        }
        else if (usrChoice == CHOICE_FIVE) {
            System.out.println ("Thank you for using the Inventory Processing System");
            System.exit(0);
        }
        else {
            System.out.println("Invalid choice. Please enter a valid option");
        }
    }

    /**
     * handles the inventory maintenance choices
     *
     * @param usrChoice
     */
    private static void handleInvChoice(int usrChoice) 
    {
        if (usrChoice == CHOICE_ONE)
        {
            addItem();
        }
        else if (usrChoice == CHOICE_TWO)
        {
            rmvItem();
        }
        else if(usrChoice == CHOICE_THREE)
        {
            priceChanger();
        }
        else if(usrChoice == CHOICE_FOUR)
        {
        }
        else
        {
            System.out.println("Invalid choice. Please enter a valid option");
        }
    }
    
    /**
     * helper method to check if the user still wants to continue
     */
    private static boolean usrChooseToCont(int usrInput) {
        if ( usrInput == CHOICE_FIVE)
        {
            return true;
        }

        return false;
    }

    /**
     * helper method to check if the user wants to be
     * at the maintenance menu
     *
     * @param usrInput // usr input
     * @return // true if usr inputs 4, else false
     */
    private static boolean usrChooseToBack(int usrInput) {
        if ( usrInput == CHOICE_FOUR)
        {
            return true;
        }
        
        return false;
    }

    /**
     * helper method to retrieve item number from user
     *
     * @return usrItemNo // item number that the user has entered
     */
    private static String getItemNum()
    {
        kb.nextLine();
        System.out.println("Please enter the item number: ");
        String usrItemNo = kb.nextLine();

        return usrItemNo;
    }

    /**
     * helper method to handle item inquiries, which verifies the
     * item number and then prints the item details
     */
    private static void handleItemInquiry() {
        String itemNumFUser = getItemNum();
        boolean validator = itemNumValidator(itemNumFUser);

        if (validator)
        {
            String itemDetail = wHouse.itemInquiry(itemNumFUser);
            System.out.println(itemDetail);
        }
        else
        {
            errorMsg();
        }

    }

    /**
     * helper method to handle processing transactions
     *
     * @throws IOException
     */
    private static void processTrans() throws IOException{
        wHouse.prsTransaction();
    }

    /**
     * helper method to handle end of day processing
     */
    private static void handleEndDayProcessing() {
        wHouse.endOfDayProcessing();
    }

    /**
     * helper method to handle price change of an item. Prompt
     * user for item number, validates the item number, if valid
     * request amount and process.
     *
     */
    private static void priceChanger() {
        String itemNum = getItemNum();
        boolean valid = itemNumValidator(itemNum);
        double amount = requestPrice();

        if (valid)
        {
            wHouse.itemPChanger(itemNum, amount);
        }
        else {
            errorMsg();
        }
    }

    /**
     * helper method to add item to the inventory. Prompt user
     * for item number and validates the number. If the item number
     * is not in the inventory, then proceed to ask for other info.
     */
    private static void addItem() {
        String itemNo = getItemNum();
        boolean valid = itemNumValidator(itemNo);

        if (!valid)
        {
            wHouse.addItemToInv(itemNo, requestName(), requestOnHand(),
                    requestPrice(), requestReorder(), requestEconQty());
        }
        else
        {
            System.out.println("Item is already in the inventory");
        }
    }

    /**
     * helper method to remove item from the inventory. Prompt user
     * for item number and validates the number. If the number is
     * in the inventory, then call another function to delete it.
     */
    private static void rmvItem() {
        String itemNum = getItemNum();
        boolean valid = itemNumValidator(itemNum);

        if (valid)
        {
            wHouse.rmvItemFromInv(itemNum);
        }
        else
        {
            System.out.println("Item doesn't exist in the inventory");
        }
    }

    /**
     * helper method to validate the item number that the
     * user has entered
     *
     * @param itemNum // item number from the user
     * @return // return true if found in inventory
     */
    private static boolean itemNumValidator(String itemNum){

        return wHouse.itemNoValidator(itemNum);
    }

    /**
     * ask user for the item name
     *
     * @return itemName // item name from user
     */
    private static String requestName() {
        System.out.println("Please enter the item name: ");
        String itemName = kb.nextLine();

        return itemName;
    }

    /**
     * ask user for the on hand amount of an item
     *
     * @return onHand // on hand amount from user
     */
    private static int requestOnHand() {
        System.out.println("Please enter the on hand amount: ");
        int onHand = kb.nextInt();

        return onHand;
    }

    /**
     * ask user for the desired item price
     *
     * @return itemPrice // item price from user
     */
    private static double requestPrice ()
    {
        System.out.println("Please enter the item price: ");
        double itemPrice = kb.nextDouble();

        return itemPrice;
    }

    /**
     * ask user for reorder point amount
     *
     * @return reorderP // reorder point from user
     */
    private static int requestReorder() {
        System.out.println("Please enter the reorder point amount: ");
        int reorderP = kb.nextInt();

        return reorderP;
    }

    /**
     * ask user for econ reorder quantity
     *
     * @return ecoQty // econ reorder quantity from user
     */
    private static int requestEconQty() {
        System.out.println("Please enter the econ order quantity: ");
        int ecoQty = kb.nextInt();

        return ecoQty;
    }

    /**
     * prints error message
     */
    private static void errorMsg() {
        System.out.println("Invalid item number");
    }
  
    /**
     *  The Main menu
     */
    private static void mainMenu()
    {
        System.out.println("\nMAIN MENU:");
        System.out.println("1) Inventory item inquiry");
        System.out.println("2) Warehouse and Inventory Maintenance");
        System.out.println("3) Process transactions from the file");
        System.out.println("4) End of Day Processing");
        System.out.println();
        System.out.println("5) Exit");
    }

    /**
     *  The Inventory Maintenance menu
     */
    private static void invMenu()
    {
        System.out.println("\nINVENTORY PROCESSING MENU:");
        System.out.println("1) Adding an Item to the Warehoue");
        System.out.println("2) Removing an Item from the Warehouse");
        System.out.println("3) Changing the price of an Item in the Warehouse"); 
        System.out.println();
        System.out.println("4) Back");
    } 
}
