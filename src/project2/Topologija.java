package project2;

import java.util.Arrays;
import edu.stanford.math.plex.*;
import edu.stanford.math.plex.PersistenceInterval.Float;

public class Topologija {
	private static int numberOfStages = 100;
	
	public static void main(String[] args) {

		ExplicitStream complex = new ExplicitStream();
		complex.add(new double[][] { {Integer.MAX_VALUE-2}, {Integer.MAX_VALUE-1}, {Integer.MAX_VALUE}, { Integer.MAX_VALUE-2, Integer.MAX_VALUE-1 }, { Integer.MAX_VALUE-2, Integer.MAX_VALUE }, { Integer.MAX_VALUE-1, Integer.MAX_VALUE }, { Integer.MAX_VALUE-2, Integer.MAX_VALUE-1, Integer.MAX_VALUE }},	new double[] { numberOfStages+1, numberOfStages+1, numberOfStages+1, numberOfStages+1, numberOfStages+1, numberOfStages+1, numberOfStages+1 });
		
		complex.add(new double[] { 1 }, 15);
		complex.add(new double[] { 2 }, 15);
		complex.add(new double[] { 3 }, 30);
		complex.add(new double[] { 4 }, 30);
		complex.add(new double[] { 5 }, 50);
		complex.add(new double[] { 6 }, 50);
		complex.add(new double[] { 7 }, 70);
		complex.add(new double[] { 8 }, 70);
		complex.add(new double[] { 9 }, 85);

		complex.add(new double[][] { { 1, 3 }, { 2, 4 }}, new double[] { 30, 30 });
		complex.add(new double[][] {  { 3, 5 }, { 4, 6 }, { 5, 6 } }, new double[] { 50, 50, 50 });
		complex.add(new double[][] { { 5, 7 }, { 6, 8 } }, new double[] { 70, 70 });
		complex.add(new double[][] { { 7, 9 }, { 8, 9 } }, new double[] { 85, 85 });
		
		complex.close();
		
		Float[] intervals = Plex.Persistence().computeIntervals(complex);
		
		Plex.plot(intervals, "Barcode plot", numberOfStages);
		
		double[][][] arrayOfDouble = printable_intervals(intervals);
		for (int i = 0; i < arrayOfDouble.length; i++) {
			for (int j = 0; j < arrayOfDouble[i].length; j++) {
				for (int k = 0; k < arrayOfDouble[i][j].length; k++) {
					System.out.println("betti-" + i + ": bar:" + j + " " + arrayOfDouble[i][j][k]);
				}
			}
		}
		// System.out.println(complex);
	}

	protected static double[][][] printable_intervals(PersistenceInterval[] intervals) {
		int[] localObject = new int[0];
		int j = -1;
		for (int k = 0; k < intervals.length; k++) {
			if (intervals[k].dimension >= localObject.length) {
				int[] arrayOfInt = new int[Math.max(intervals[k].dimension + 1, 2 * localObject.length)];
				for (int m = 0; m < localObject.length; m++) {
					arrayOfInt[m] = localObject[m];
				}
				localObject = arrayOfInt;
			}
			j = Math.max(j, intervals[k].dimension);
			localObject[intervals[k].dimension] += 1;
		}
		PersistenceInterval[][] arrayOfPersistenceInterval = new PersistenceInterval[j + 1][];
		double[][][] arrayOfDouble = new double[j + 1][][];
		for (int k = 0; k < arrayOfPersistenceInterval.length; k++) {
			arrayOfPersistenceInterval[k] = new PersistenceInterval[localObject[k]];
		}
		localObject = new int[arrayOfPersistenceInterval.length];
		for (j = 0; j < intervals.length; j++) {
			int k = intervals[j].dimension;
			int tmp184_182 = k;
			int[] tmp184_181 = localObject;
			int tmp186_185 = tmp184_181[tmp184_182];
			tmp184_181[tmp184_182] = (tmp186_185 + 1);
			arrayOfPersistenceInterval[k][tmp186_185] = intervals[j];
		}
		for (j = 0; j < arrayOfPersistenceInterval.length; j++) {
			Arrays.sort(arrayOfPersistenceInterval[j]);
		}
		for (int i = 0; i < arrayOfPersistenceInterval.length; i++) {
			arrayOfDouble[i] = new double[arrayOfPersistenceInterval[i].length][];
			for (j = 0; j < arrayOfPersistenceInterval[i].length; j++) {
				arrayOfDouble[i][j] = arrayOfPersistenceInterval[i][j].toDouble();
			}
		}
		return arrayOfDouble;
	}

}
