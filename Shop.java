
public class Shop {
    //Shop class handles interactions between the player's inventory and 
    //the player's money. All methods will involve modifying the player's
    //money variable and/or the player's inventory array values.

    //The shop's stock indicates the amount of quantity the shop has left.
    //This may be initialized differently in the future to account for
    //different types of items/consumables.
    private int[] stock ;

    public Shop(int n) {
	stock = new int[] {n,n,n,n,n,n,n,n,n,n};
    }

    //used for purchasing items
    //takes a player, and modifies their inventory int[] array
    //returns the resulting level of the value that it modified
    //if a player does not own an item or upgrade, the level is 0.
    //behaviours of different items will be handled individually.
    public int setItemLevel (Player p, int index, int val) {
	p.setInventorySlot(index, val);
	return val;
    }

    //sets an item's level, and deducts the cost. 
    //cost may be negative for selling items.
    public int purchase (Player p, int cost, int index, int val) {
	if (p.getMoney() < cost) {
	    System.out.println("Not enough money");
	    return -1;
	}
	p.setMoney(p.getMoney() - cost);
	return setItemLevel(p, index, val);
    }


}
