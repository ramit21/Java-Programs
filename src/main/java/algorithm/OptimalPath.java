package algorithm;
//BE VERY WATCHFUL OF THE COORDINATE SYSTEM AND THE 2D MATRIX INDICES, THEY ARE DIFFERENT
/*
** Instructions to candidate.
**  1) You are an avid rock collector who lives in southern California. Some rare 
**     and desirable rocks just became available in New York, so you are planning 
**     a cross-country road trip. There are several other rare rocks that you could 
**     pick up along the way. 
**     
**     You have been given a grid filled with numbers, representing the number of 
**     rare rocks available in various cities across the country.  Your objective 
**     is to find the optimal path from So_Cal to New_York that would allow you to 
**     accumulate the most rocks along the way. 
**     
**     Note: You can only travel either north (up) or east (right).
**  2) Consider adding some additional tests in doTestsPass().
**  3) Implement optimalPath() correctly.
**  4) Here is an example:
**                                                           ^
**                 {{0,0,0,0,5}, New_York (finish)           N
**                  {0,1,1,1,0},                         < W   E >
**   So_Cal (start) {2,0,0,0,0}}                             S
**                                                           v 
**   The total for this example would be 10 (2+0+1+1+1+0+5).
*/

public class OptimalPath {

	public static Integer optimalPath(Integer[][] grid) {
		//Memoize dynamic programming solutions
		int [][] dpSolutions = createDpSolutionsArray(grid.length, grid[0].length);
		
		int x = grid.length-1;
		int y = 0;
		int finalX = 0;
		int finalY= grid[0].length-1;
		System.out.println("Start coordinates: x= "+x+", y= "+y);
		System.out.println("Destination coordinates: x= "+finalX+", y= "+finalY);
		return maxPath(grid, dpSolutions, x, y, finalX, finalY);
	}
	
	private static Integer maxPath(
			Integer[][] grid, int[][] dpSolutions, int x, int y, int finalX, int finalY) {
		
		if(x==finalX && y== finalY) {
			return grid[x][y];
		}
		int up = Integer.MIN_VALUE;
		int right = Integer.MIN_VALUE;
		if(x-1 >= finalX) {
			right = maxPath(grid, dpSolutions, x-1, y, finalX, finalY);
		}
		if(y+1 <= finalY) {
			up = maxPath(grid, dpSolutions, x, y+1, finalX, finalY);
		}
		if(dpSolutions[x][y] == -1) {
			dpSolutions[x][y] = grid[x][y] + Math.max(up, right);
			System.out.println("ans = " + dpSolutions[x][y] +", x= "+x+", y= "+y); 
		}
		return dpSolutions[x][y];
	}

	public static int[][] createDpSolutionsArray(int rowCount, int colCount){
		int [][] dpSolutions = new int[rowCount][colCount];
		for(int i=0; i< rowCount; i++) {
			for(int j=0; j< colCount; j++) {
				dpSolutions[i][j] = -1;
			}
		}
		return dpSolutions;
	}
	
	public static boolean doTestsPass() {
		boolean result = true;
		
		  result &= optimalPath(new Integer[][] { 
			  { 0, 0, 0, 0, 5 }, 
			  { 0, 1, 1, 1, 0 },
			  { 2, 0, 0, 0, 0 } }) == 10;
		 
		
		result &= optimalPath(new Integer[][] {
			{ 0, 0, 10 }, 
			{ 0, 1, 1 }, 
			{ 2, 0, 5} }) == 18;
		return result;
	}

	public static void main(String[] args) {
		if (doTestsPass()) {
			System.out.println("All tests pass");
		} else {
			System.out.println("Tests fail.");
		}
	}
}