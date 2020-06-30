
class RobotControl {
	private Robot r;
	private int d = 1;
	private int h = 1;
	private int w = 1;
	private int j = 0;
	private int k = 3;
	private int l = 3;
	private int length = 0;
	private int height = 1;
	private int destCol = 1;
	private int srceCol = 10;
	private int initialSumOfBlockHeights = 0;
	private int maxOfBarHeights = 0;
	private int maxOfBlockHeights = 0;
	private int sumOfBlockHeights = 0;
	private int sumOfTarget = 0;
	private int sumOfTemp = 0;
	private int temp[] = new int[4];
	private int arr[] = new int[8];

	public RobotControl(Robot r) {
		this.r = r;
	}

	public void control(int barHeights[], int blockHeights[], int required[], boolean ordered) {
		// defined separate methods for separate parts, uncomment the method call to run the desired part
		// all four parts are working fine for any combination of values passed as arguments to the specific part

		// controlPartA(barHeights, blockHeights);
		// controlPartB(barHeights, blockHeights);
		// controlPartC(barHeights, blockHeights);
		// controlPartD(barHeights, blockHeights, required, ordered);
	}

	public void controlPartA(int barHeights[], int blockHeights[]) {
		// The first past can be solved easily with out any arrays as the height of bars
		// and blocks are fixed.
		// Use the method r.up(), r.down(), r.extend(), r.contract(), r.raise(),
		// r.lower(), r.pick(), r.drop()
		// The code below will cause first arm to be moved up, the second arm to the
		// right and the third to be lowered.

		maxOfBarHeights = 4; // fixed
		maxOfBlockHeights = 2; // fixed
		sumOfBlockHeights = 8; // fixed
		length = 4;

		while (sumOfBlockHeights > h) { // h will increase till sumOfBlockHeights
			r.up();
			h++;
		}

		// calling the method which will perform the task of moving blocks from source
		// to target
		moveBlocks(maxOfBarHeights, maxOfBlockHeights, sumOfBlockHeights, blockHeights, h, length);
	}

	public void controlPartB(int barHeights[], int blockHeights[]) {
		// Part B requires you to access the array barHeights passed as argument as the
		// robot arm must move
		// over the bars

		sumOfBlockHeights = 8; // fixed
		maxOfBlockHeights = 2; // fixed
		length = 4; // fixed

		maxOfBarHeights = MyMath.max(MyMath.max(barHeights[0], barHeights[1], barHeights[2], barHeights[3]),
				barHeights[4], barHeights[5]);

		// as bar height can vary, height 'h' needs to be fixed according to
		// maxOfBarHeights and maxOfBlockHeights
		if (sumOfBlockHeights - maxOfBarHeights < maxOfBlockHeights) {
			height = maxOfBarHeights + maxOfBlockHeights;
		} else {
			height = sumOfBlockHeights;
		}

		while (height > h) {
			r.up();
			h++;
		}

		// calling the method which will perform the task of moving blocks from source
		// to target
		moveBlocks(maxOfBarHeights, maxOfBlockHeights, sumOfBlockHeights, blockHeights, h, length);
	}

	public void controlPartC(int barHeights[], int blockHeights[]) {
		// The third part requires you to access the arrays barHeights and blockHeights
		// as the heights of bars and blocks are allowed to vary through command line
		// arguments

		maxOfBarHeights = MyMath.max(MyMath.max(barHeights[0], barHeights[1], barHeights[2], barHeights[3]),
				barHeights[4], barHeights[5]);

		maxOfBlockHeights = blockHeights[0]; 
		
		for (int i = 0; i < blockHeights.length; i++) {
			sumOfBlockHeights += blockHeights[i];
			if (blockHeights[i] > maxOfBlockHeights) // finding maximum of block heights
				maxOfBlockHeights = blockHeights[i];
			length++; // getting length of blockHeights array 
		}
		
		System.out.println("maxOfBlockHeights is:"+maxOfBlockHeights);

		// as bar heights and block heights can vary, height 'h' needs to be fixed
		// according to maxOfBarHeights and maxOfBlockHeights
		if (sumOfBlockHeights - maxOfBarHeights < maxOfBlockHeights) {
			height = maxOfBarHeights + maxOfBlockHeights;
		} else {
			height = sumOfBlockHeights;
		}

		while (height > h) {
			r.up();
			h++;
		}

		// calling the method which will perform the task of moving blocks from source
		// to target
		moveBlocks(maxOfBarHeights, maxOfBlockHeights, sumOfBlockHeights, blockHeights, h, length);
	}

	public void controlPartD(int barHeights[], int blockHeights[], int required[], boolean ordered) {
		// The fourth part allows the user to specify the order in which bars must
		// be placed in the target column. This will require you to use the use
		// additional column
		// which can hold temporary values

		maxOfBarHeights = MyMath.max(MyMath.max(barHeights[0], barHeights[1], barHeights[2], barHeights[3]),
				barHeights[4], barHeights[5]);

		maxOfBlockHeights = MyMath.max(blockHeights[0], blockHeights[1], blockHeights[2], blockHeights[3]);

		for (int i = 0; i <= 3; i++) {
			sumOfBlockHeights += blockHeights[i];
		}

		initialSumOfBlockHeights = sumOfBlockHeights; // saving initial value of sumOfBlockHeights as it will change
														// in further steps

		// as bar heights and block heights can vary, height 'h' needs to be fixed
		// according to maxOfBarHeights and maxOfBlockHeights
		if (sumOfBlockHeights - maxOfBarHeights < maxOfBlockHeights) {
			height = maxOfBarHeights + maxOfBlockHeights;
		} else {
			height = sumOfBlockHeights;
		}

		while (height > h) {
			r.up();
			h++;
		}

		for (k = 3; k >= 0; k--) {

			while (w < srceCol) {
				r.extend();
				w++;
			}

			while (d < h - sumOfBlockHeights + 1) {

				r.lower();
				d++;
			}
			r.pick();

			sumOfBlockHeights -= blockHeights[k]; // after picking block from source, subtracting current block height
													// from sumOfBlockHeights

			// creating array of variables need to be passed to the method
			arr[0] = j;
			arr[1] = k;
			arr[2] = d;
			arr[3] = sumOfTemp;
			arr[4] = sumOfBlockHeights;
			arr[5] = sumOfTarget;
			arr[6] = maxOfBarHeights;
			arr[7] = l;

			moveBlocksWithCondition(arr, required, blockHeights);

		}

		k++; // as in for loop, after last iteration, value of k went to -1 so doing k++

		l++; // after completing all iterations of method 'moveBlocksWithCondition' value of
				// l went to -1 so doing l++

		// moving w from current position to the desired position
		if (w == 1 && sumOfTarget != initialSumOfBlockHeights) {
			while (w < srceCol - 1) {
				r.extend();
				w++;
			}
			while (d < h - sumOfTemp + 1) {
				r.lower();
				d++;
			}
			w++;
		}
		w--;

		while (sumOfTarget != initialSumOfBlockHeights) {

			if (sumOfBlockHeights == 0) {

				while (sumOfTemp != 0) {

					r.pick();

					sumOfTemp -= temp[l]; // after picking current block, subtracting value of current block from
											// sumOfTemp

					if (required[j] != temp[l]) { // checking whether the picked block is required one

						while (d > h - Math.max(sumOfTemp, sumOfBlockHeights) - temp[l] + 1) { // raising d to the
																								// minimum extent
																								// possible
							r.raise();
							d--;
						}
						while (w < srceCol) {
							r.extend();
							w++;
						}

						while (d < h - sumOfBlockHeights - temp[l] + 1) { // lowering d to the minimum extent possible
							r.lower();
							d++;
						}
						r.drop();

						blockHeights[k] = temp[l];
						sumOfBlockHeights += blockHeights[k];

						k++;

						while (d > h - Math.max(sumOfTemp, sumOfBlockHeights) + 1) {
							r.raise();
							d--;
						}
						r.contract();

						while (d < h - sumOfTemp + 1) {
							r.lower();
							d++;
						}
						l++;
						w--;
					}

					else {

						while (d > (h - temp[l] - MyMath.max(sumOfTarget, sumOfTemp, maxOfBarHeights)) + 1) {
							r.raise();
							d--;
						}

						while (w > 1) {
							r.contract();
							w--;
						}

						while (d < h - sumOfTarget - temp[l] + 1) {
							r.lower();
							d++;
						}
						r.drop();

						sumOfTarget += temp[l]; // after dropping at target, adding current block height to sumOfTarget

						j++;

						while (d > (h - MyMath.max(maxOfBarHeights, sumOfTemp, sumOfBlockHeights)) + 1) {
							r.raise();
							d--;
						}

						if (sumOfTemp != 0) { // when sumOfTemp is not zero, moving d to the desired position
							while (w < 9) {
								r.extend();
								w++;
							}
							while (d < h - sumOfTemp + 1) {
								r.lower();
								d++;
							}
						}
						if (l != 3) {
							l++;
						}

					}

				}

			}

			if (sumOfTemp == 0) {

				while (sumOfBlockHeights != 0) {
					while (w < srceCol) {
						r.extend();
						w++;
					}

					while (d < h - sumOfBlockHeights + 1) {
						r.lower();
						d++;
					}
					r.pick();
					k--;

					sumOfBlockHeights -= blockHeights[k]; // after picking block from source, subtracting current block
															// height from sumOfBlockHeights

					// creating array of variables need to be passed to the method
					arr[0] = j;
					arr[1] = k;
					arr[2] = d;
					arr[3] = sumOfTemp;
					arr[4] = sumOfBlockHeights;
					arr[5] = sumOfTarget;
					arr[6] = maxOfBarHeights;
					arr[7] = l;

					moveBlocksWithCondition(arr, required, blockHeights);

				}
			}

		}

	}

	public void moveBlocks(int maxOfBarHeights, int maxOfBlockHeights, int sumOfBlockHeights, int blockHeights[],
			int h, int length) {
		initialSumOfBlockHeights = sumOfBlockHeights;
		for (int k = length-1; k >= 0; k--) {

			while (w < srceCol) {
				r.extend();
				w++;
			}

			while (d < h - sumOfBlockHeights + 1) {
				r.lower();
				d++;
			}
			r.pick();

			sumOfBlockHeights -= blockHeights[k]; // after picking block, subtracting current block height from
													// sumOfBlockHeights

			while (d > (h - blockHeights[k] - Math.max(sumOfTarget, maxOfBarHeights)) + 1) {
				r.raise();
				d--;
			}
			while (w > destCol) {
				r.contract();
				w--;
			}

			while (d < h - sumOfTarget - blockHeights[k] + 1) {
				r.lower();
				d++;
			}

			r.drop();
			sumOfTarget += blockHeights[k]; // after dropping block to target, adding current block Height to
											// sumOfTarget
			if (sumOfTarget != initialSumOfBlockHeights) { // if all the blocks are at target then do not raise 'd' 
				while (d > (h - Math.max(maxOfBarHeights, sumOfBlockHeights)) + 1) {
					r.raise();
					d--;
				}
			} 
			
		}

	}

	public void moveBlocksWithCondition(int arr[], int required[], int blockHeights[]) {
		// re-assigning values from array 'arr' to the respective variables
		j = arr[0];
		k = arr[1];
		d = arr[2];
		sumOfTemp = arr[3];
		sumOfBlockHeights = arr[4];
		sumOfTarget = arr[5];
		maxOfBarHeights = arr[6];
		l = arr[7];

		if (required[j] != blockHeights[k]) {
			while (d > 1 + (h - blockHeights[k] - Math.max(sumOfTemp, sumOfBlockHeights))) {
				r.raise();
				d--;
			}
			r.contract();

			while (d < h - sumOfTemp - blockHeights[k] + 1) {
				r.lower();
				d++;
			}
			r.drop();

			temp[l] = blockHeights[k]; // after dropping block at temp, adding it to the array of temp

			l--; // decrementing l after dropping block at temp

			sumOfTemp += blockHeights[k]; // after dropping block at temp, adding it to the sumOfTemp

			while (d > h - Math.max(sumOfTemp, sumOfBlockHeights) + 1) {
				r.raise();
				d--;
			}
			if (sumOfBlockHeights != 0) {
				r.extend();
			}
		}

		else {

			while (d > 1 + (h - blockHeights[k] - MyMath.max(sumOfTarget, sumOfTemp, maxOfBarHeights))) {
				r.raise();
				d--;
			}

			while (w > destCol) {
				r.contract();
				w--;
			}

			while (d < (h - sumOfTarget - blockHeights[k]) + 1) {
				r.lower();
				d++;
			}
			r.drop();

			sumOfTarget += blockHeights[k]; // after dropping block at target, adding current block height to
											// sumOfTarget
			j++;

			while (d > (h - MyMath.max(maxOfBarHeights, sumOfTemp, sumOfBlockHeights)) + 1) {
				r.raise();
				d--;
			}
		}
	}
}
