package test.com.sort;

public class InsertSort {

	/**
	 * （1）基本思想：在要排序的一组数中，假设前面(n-1)[n>=2] 个数已经是排 好顺序的，现在要把第n个数插到前面的有序数中，使得这n个数
	 * 也是排好顺序的。如此反复循环，直到全部排好顺序。
	 * 
	 * 时间复杂度:平均情况 o(n)^2 最好:o(n) 最坏:o(n)^2 空间复杂度:o(1) 稳定 简单
	 * 
	 * 分析:稳定性，就是有两个相同的元素，排序先后的相对位置是否变化，主要用在排序时有多个排序规则的情况下。在插入排序中，K1是已排序部分中的元素，
	 * 当K2和K1比较时，直接插到K1的后面(没有必要插到K1的前面，这样做还需要移动！！)，因此，插入排序是稳定的
	 */
	public static void insertSort() {
		int a[] = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35,
				25, 53, 51 };
		
		int tmp;
		for(int i=1 ; i<a.length; i++){
			 int j = i - 1;
			 tmp = a[i];
			 for(;j>=0 && a[j] > tmp; ){
				 a[j+1] = a[j];
				 j--;
			 }
			 a[j+1] = tmp;
		}
		
		
		for (int i = 0; i < a.length; i++)
			System.out.println(a[i]);
	}

	/**
	 * 希尔排序（最小增量排序）
	 * 
	 * 希尔排序(Shell
	 * Sort)是插入排序的一种。也称缩小增量排序，是直接插入排序算法的一种更高效的改进版本。希尔排序是非稳定排序算法。该方法因DL．
	 * Shell于1959年提出而得名。 该方法实质上是一种分组插入方法
	 * 
	 * （1）基本思想：算法先将要排序的一组数按某个增量d（n/2,n为要排序数的个数）分成若干组，每组中记录的下标相差d.
	 * 对每组中全部元素进行直接插入排序，然后再用一个较小的增量（d/2）对它进行分组，在每组中再进行直接插入排序。当增量减到1时，进行直接插入排序后，
	 * 排序完成。
	 * 
	 * 分析: 希尔排序也是一种插入排序方法,实际上是一种分组插入方法。先取定一个小于n的整数d1作为第一个增量,把表的全部记录分成d1个组,
	 * 所有距离为d1的倍数的记录放在同一个组中,
	 * 在各组内进行直接插入排序；然后,取第二个增量d2(＜d1),重复上述的分组和排序,直至所取的增量dt=1(dt<dt-1<…<d2<d1),
	 * 即所有记录放在同一组中进行直接插入排序为止
	 * 
	 * 最好情况：由于希尔排序的好坏和步长d的选择有很多关系，因此，目前还没有得出最好的步长如何选择(现在有些比较好的选择了，但不确定是否是最好的)。所以
	 * ，不知道最好的情况下的算法时间复杂度。 最坏情况下：O(N*logN)，最坏的情况下和平均情况下差不多。
	 * 
	 * 由于多次插入排序，我们知道一次插入排序是稳定的，不会改变相同元素的相对顺序，但在不同的插入排序过程中，相同的元素可能在各自的插入排序中移动，
	 * 最后其稳定性就会被打乱，所以shell排序是不稳定的。(有个猜测，方便记忆：一般来说，若存在不相邻元素间交换，则很可能是不稳定的排序。)
	 * 
	 */
	public static void shellSort() {
		int a[] = { 1, 54, 6, 3, 78, 34, 12, 45, 56, 100, 79 };
		double d1 = a.length;
		int temp;
		while (true) {
			d1 = Math.ceil(d1 / 2);// Returns the smallest (closest to negative
									// infinity) double value that is greater
									// than or equal to the argument and is
									// equal to a mathematical integer
			int d = (int) d1;

			for (int x = 0; x < d; x++) {
				for (int i = x + d; i < a.length; i += d) {
					int j = i - d;
					temp = a[i];
					for (; j >= 0 && temp < a[j]; j -= d) {
						a[j + d] = a[j];
					}
					a[j + d] = temp;
				}
			}
			if (d == 1)
				break;
		}
		for (int i = 0; i < a.length; i++)
			System.out.println(a[i]);
	}

	/**
	 * 基本思想：在要排序的一组数中，选出最小的一个数与第一个位置的数交换；
	 * 然后在剩下的数当中再找最小的与第二个位置的数交换，如此循环到倒数第二个数和最后一个数比较为止。 
	 */
	public static void selectSort() {
		int a[] = { 1, 54, 6, 3, 78, 34, 12, 45 };
		int position = 0;//指针表示最小的位置
		for (int i = 0; i < a.length; i++) {//i:表示有序区指针
			int j = i + 1;//j:无序区的指针
			position = i;
			int temp = a[i];
			for (; j < a.length; j++) {
				if (a[j] < temp) {
					temp = a[j];
					position = j;
				}
			}
			a[position] = a[i];
			a[i] = temp;
		}
		for (int i = 0; i < a.length; i++)
			System.out.println(a[i]);
	}
	
	/**
	 * 求出最大值
	 */
	private static void getMax() {
		// TODO Auto-generated method stub
		int a[] = {2,5,7,3,4,1,0,-11,275687};
		int temp = a[0];
		for(int i = 0; i< a.length;i++){
			if(temp < a[i]){
				temp = a[i];
			}
			
		}
		System.out.println(temp);
		
	}

	public static void main(String[] args) {
		//insertSort();
		 //shellSort();
		//selectSort();
		 getMax();
	}

	

}
