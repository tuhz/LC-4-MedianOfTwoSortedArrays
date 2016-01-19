package me.tuhz.leetcode;

public class Solution2 {
	
	/* Time complexity: O(m+n). Runtime: 5ms.
	 * This solution finished sooner than the O(log(m+n)) one probably 
	 * due to relatively small test cases */
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int n = nums1.length;
		int m = nums2.length;
		int in = 0, im = 0;
		int[] mergedArray = new int [m+n]; 
		
		while (in < n && im < m) {
			if (nums1[in] < nums2[im]) {
				mergedArray[in+im] = nums1[in];
				++in;
			} 
			else {
				mergedArray[in+im] = nums2[im];
				++im;
			}
		}
		for ( ; in < n; ++in) {
			mergedArray[im+in] = nums1[in];
		}
		for ( ; im < m; ++im) {
			mergedArray[im+in] = nums2[im];
		}
		
		return (mergedArray[(m+n)/2] + mergedArray[(m+n-1)/2]) / 2.0;
	}


	public static void main(String[] args) {
		int[] a = {1, 2};
		int[] b = {1, 2};
		System.out.println(findMedianSortedArrays(a, b));
	}

}
