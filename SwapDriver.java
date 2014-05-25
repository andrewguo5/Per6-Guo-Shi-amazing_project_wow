public class SwapDriver {

    public static void main (String[] args) {
	
	//Test1: Swaps three at a time
	System.out.println("Swaps the first three gems on the lefthand edge");
	Grid swagGrid = new Grid();
	System.out.println(swagGrid);
	Gem[] swapList = new Gem[3];
	System.out.println("nice giraffe");
	swapList[0] = swagGrid.getGem(0, 0);
	swapList[1] = swagGrid.getGem(0, 1);
	swapList[2] = swagGrid.getGem(0, 2);
	swagGrid.switchGems(3, swapList);
	System.out.println(swagGrid);

	System.out.println("\n\n\n");
	System.out.println("Swaps two pairs of gems near the lefthand top corner");
	System.out.println(swagGrid);
	Gem[] swapList2 = new Gem[4];
	swapList2[0] = swagGrid.getGem(1, 1);
	swapList2[1] = swagGrid.getGem(2, 1);
	swapList2[2] = swagGrid.getGem(1, 2);
	swapList2[3] = swagGrid.getGem(2, 2);
	swagGrid.switchGems(2, swapList2);
	System.out.println("giraffe here for your convenience how nice");
	System.out.println(swagGrid);

	
    }
    
}
