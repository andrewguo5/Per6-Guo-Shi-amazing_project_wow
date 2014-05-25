
public class Player 
{
    private long score;
    private int mana;
    private int money;
    private int health;
    private boolean alive;
    private int[] inventory;

    public Player() {
	score = 0;
	mana = 100;
	money = 0;
	health = 100;
	alive = true;
	inventory = new int[] {0,0,0,0,0,0,0,0,0,0};
    }

    public long getScore() {return score;}
    public int getMana() {return mana;}
    public int getMoney() {return money;}
    public int getHealth() {return health;}
    public boolean isAlive() {return alive;}

    public int getInventorySlot(int val) {
	try {
	    return inventory[val];
	}
	catch (ArrayIndexOutOfBoundsException e) {
	    return -1;
	}
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
    public int setInventorySlot(int index, int val) {
	try {
	    int oldVal = inventory[index];
	    inventory[index] = val;
	    return oldVal;
	}
	catch (ArrayIndexOutOfBoundsException e) {
	    return -1;
	}
    }

    
    

}
