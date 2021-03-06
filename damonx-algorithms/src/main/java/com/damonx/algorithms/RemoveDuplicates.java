/*
 * Copyright (c) Fisher and Paykel Appliances.
 *
 * This document is copyright. Except for the purpose of fair reviewing, no part
 * of this publication may be reproduced or transmitted in any form or by any
 * means, electronic or mechanical, including photocopying, recording, or any
 * information storage and retrieval system, without permission in writing from
 * the publisher. Infringers of copyright render themselves liable for
 * prosecution.
 */
package com.damonx.algorithms;

import org.apache.commons.lang3.ArrayUtils;

public class RemoveDuplicates {

	public static void main(final String[] args) {
		final int[] integerArray = new int[] { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4, 4, 4, 4, 4, 4, 6, 6, 7, 7, 8, 9 };
		final int[] removedDuplicateArray = removeDuplicates(integerArray);
		for (final int element : removedDuplicateArray) {
			System.out.print(element + " ");
		}

	}

	static int[] removeDuplicates(final int[] nums) {
		final int n = nums.length;
		if (n == 0) {
			return ArrayUtils.EMPTY_INT_ARRAY;
		}
		int slow = 0, fast = 1;
		while (fast < n) {
			if (nums[fast] != nums[slow]) {
				slow++;
				nums[slow] = nums[fast];
			}
			fast++;
		}
		return ArrayUtils.subarray(nums, 0, slow + 1);
	}

}
