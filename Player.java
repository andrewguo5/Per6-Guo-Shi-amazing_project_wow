
public class Player 
{
    private long score;
    private int mana;
    private int money;
    private int health;
    private boolean alive;
    private Inventory inventory;

    public Player() {
	score = 0;
	mana = 100;
	money = 0;
	health = 100;
	alive = true;
        inventory = new Inventory();
    }

    public long getScore() {return score;}
    public int getMana() {return mana;}
    public int getMoney() {return money;}
    public int getHealth() {return health;}
    public boolean isAlive() {return alive;}
    public Inventory getInventory(){return inventory;}
    public int getInventorySlot(int val) {
	try {
	    return inventory.getInventory()[val];
	}
	catch (ArrayIndexOutOfBoundsException e) {
	    return -1;
	}
}
    public void setInventorySlot(int index, int val) {
	System.out.println("do this later lol");
    }

    public long setScore(long val) {
	long oldScore = score;
	score = val;
	return oldScore;
    }
    public int setMana(int val) {
	int oldMana = mana;
	mana = val;
	return oldMana;
    }
    public int setMoney(int val) {
	int oldMoney = money;
	money = val;
	return oldMoney;
    }
    public boolean smite () {
	alive = !alive;
	return !alive;
    }

    
    

}
