package algorithm;

/*
 * Given array of -ve and +ve nos, group -ves together and +ves together, 
 * maintaining original order of respective numbers w/o using auxiliary space
 */
public class ArrayGrouping {
	
	//O(n^2) Time and O(1) Aux space solution
	private static void modifiedInsertionSort(int arr[]){
		for(int i=0; i<arr.length-1; i++ ){
			if(arr[i]>0){ //if arr[i] +ve, then bring the next -ve no in its place
				for(int j=i+1; j< arr.length; j++){
					if(arr[j]<0){
						int tmp = arr[j];
						int k = j;
						while(k>i){
							arr[k] = arr[--k];
						}
						arr[i] = tmp;
						break; //Once no has been put to correct i pos, break this loop
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int[] arr = {-4,3,5,-7,2,-1};
		modifiedInsertionSort(arr);
		printArray(arr);
	}
	
	private static void printArray(int arr[]){
		for(int i=0; i< arr.length; i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
}
