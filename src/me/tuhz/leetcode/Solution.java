package me.tuhz.leetcode;

public class Solution {
	
	/* Time complexity: O(log(m+n)). Runtime: 6ms */
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		
		if (nums1.length == 0) 
			return (nums2[(nums2.length-1)/2] + nums2[nums2.length/2])/2.0;
		if (nums2.length == 0)
			return (nums1[(nums1.length-1)/2] + nums1[nums1.length/2])/2.0;

		// assuming K elements has been merge-sorted into a new array
		int k = (nums1.length + nums2.length - 1) / 2; 
		// suppose i of the K elements are from nums1, 
		// then (K-i) elements are from nums2
		int i = 0;
		boolean continueSearch = true;
		// binary search
		int begin = 0, end = k;
		while (continueSearch) {
			i = (begin + end) / 2;
			if (i > nums1.length) {
				end = i - 1;
			}
			else if (k - i > nums2.length) {
				begin = i + 1;
			}
			else {
				// Rule #1: the last picked number should be
				// greater than the first number left in nums1
				if (i == nums1.length || 
					Math.max(i - 1 >= 0 ? 
							 nums1[i - 1] : Integer.MIN_VALUE, 
							 k - i - 1 >= 0 ? 
							 nums2[k - i - 1] : Integer.MIN_VALUE)
							 <= nums1[i]) 
				{
					// Rule #2: the last picked number should be
					// greater than the first number left in nums2
					if (k-i == nums2.length ||
						Math.max(i - 1 >= 0 ? 
								 nums1[i - 1] : Integer.MIN_VALUE, 
								 k - i - 1 >= 0 ? 
								 nums2[k - i - 1] : Integer.MIN_VALUE)
								 <= nums2[k-i])
					{
						// i conforms to both Rule #1 and #2. Answer found. 
						continueSearch = false;
					}
					else 
					{
						// i conforms to Rule #1 but not #2
						end = i - 1;
					}
				}
				else 
				{	
					// i does not conform to Rule #1
					begin = i + 1;
				}
			}
		}
		
		int[] nextFew = new int[4];
		int n = 0;
		for (int j = 0 ; i + j < nums1.length && j < 2; ++j) {
			nextFew[n] = nums1[i+j];
			++n;
		}
		for (int j = 0 ; k - i + j < nums2.length && j < 2; ++j) {
			nextFew[n] = nums2[k-i+j];
			++n;
		}
		int temp;
		for (int j = 1; j < n; j++) {
			if (nextFew[j] < nextFew[0]) {
				temp = nextFew[j];
				nextFew[1] = nextFew[0];
				nextFew[0] = temp;
			}
			else if (nextFew[j] < nextFew[1]) {
				nextFew[1] = nextFew[j];
			}
		}
		
		return ((nextFew[0] + 
				nextFew[1 - ((nums1.length + nums2.length) % 2)]) / 2.0);
	}


	public static void main(String[] args) {
		int[] a = {1, 2};
		int[] b = {1, 2};
		System.out.println(findMedianSortedArrays(a, b));
	}

}
