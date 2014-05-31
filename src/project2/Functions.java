package project2;

import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

import edu.stanford.math.plex.*;

public class Functions {

	/**
	 * Reads picture and returns array of 1's and 0's, where value 0 represents
	 * white pixel and value 1 any other color.
	 * 
	 * @param file
	 * @return
	 */
	public static int[][] readPicture(String file) {
		try {
			BufferedImage image = ImageIO.read(new File(file));
			final byte[] pixels = ((DataBufferByte) image.getRaster()
					.getDataBuffer()).getData();
			final int width = image.getWidth();
			final int height = image.getHeight();

			int[][] result = new int[height][width];
			for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += 4) {
				// the image has alpha channel before rgb that is why we need a
				// step of size 4!
				int r = (pixels[pixel + 1] >> 16) & 0xFF;
				int g = (pixels[pixel + 2] >> 8) & 0xFF;
				int b = (pixels[pixel + 3] & 0xFF);

				// if the pixel is white then 0, otherwise 1
				result[row][col] = 1 - (r + g + b) / (3 * 128);
				col++;
				if (col == width) {
					col = 0;
					row++;
				}
			}

			return result;
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * Define point positions of those pixels, whose value is set to 1.
	 * 
	 * @param image
	 * @return
	 */
	public static int[][] readComplex(int[][] image) {
		final int width = image.length;
		final int height = image[0].length;

		ArrayList<int[]> points = new ArrayList<int[]>();

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (image[i][j] == 1) {
					// add point x and y position to array
					points.add(new int[] { i, j });
				}
			}
		}

		int[][] result = new int[points.size()][2];
		for (int i = 0; i < points.size(); i++) {
			result[i] = points.get(i);
		}

		return result;
	}

	/**
	 * Define edges for default distance r = 1.
	 * 
	 * @param points
	 * @return
	 */
	public static int[][][] defineEdges(int[][] points) {
		return defineEdges(points, 1);
	}

	/**
	 * Define edges (build a simplical complex) of dimension 1 with nerve of U.
	 * The distance r should be 1.
	 * 
	 * @param points
	 * @param r
	 * @return
	 */
	public static int[][][] defineEdges(int[][] points, int r) {
		ArrayList<int[][]> edges = new ArrayList<int[][]>();

		// check all possible two pair combinations
		int maxX, maxY, minX, minY;
		for (int i = 0; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				// check for max, min of x value of two points
				if (points[i][0] > points[j][0]) {
					maxX = points[i][0];
					minX = points[j][0];
				} else {
					maxX = points[j][0];
					minX = points[i][0];
				}

				// check for max, min of y value of two points
				if (points[i][1] > points[j][1]) {
					maxY = points[i][1];
					minY = points[j][1];
				} else {
					maxY = points[j][1];
					minY = points[i][1];
				}

				// now we check according to this formula:
				// U = { [x - r; x + r] x [y - r; y + r] | (x, y) e V }
				// we want exclusive that is why <
				if (maxX - r < minX + r && maxY - r < minY + r)
					edges.add(new int[][] { points[i], points[j] });
			}
		}

		int[][][] result = new int[edges.size()][2][2];
		for (int i = 0; i < edges.size(); i++) {
			result[i] = edges.get(i);
		}

		return result;
	}

	/**
	 * Normalize the values of point position.
	 * 
	 * @param edges
	 * @return
	 */
	public static double[][][] normalize(int[][][] edges) {
		// druga tocka v edge vedno visja
		double border = 0.15; // ko rotiras za 45, mora biti stranica najvec
								// 1/sqrt(2), ker cene grejo vogali izven
								// kvadranta.

		// find minimum point and maximum point
		int[] min = edges[0][0].clone(), max = edges[0][0].clone();
		for (int[][] edge : edges) {
			for (int[] point : edge) {
				if (point[0] < min[0]) {
					min[0] = point[0];
				}
				if (point[1] < min[1]) {
					min[1] = point[1];
				}
				if (point[0] > max[0]) {
					max[0] = point[0];
				}
				if (point[1] > max[1]) {
					max[1] = point[1];
				}
			}
		}

		double[] originalSize = new double[] { (max[0] - min[0]),
				(max[1] - min[1]) };

		double size;
		double[] offset;

		// check if length of x value is greater than the length of y value
		if (originalSize[0] > originalSize[1]) {
			size = originalSize[0];
			offset = new double[] { 0, (size - originalSize[1]) / 2 / size };
		} else {
			size = originalSize[1];
			offset = new double[] { (size - originalSize[0]) / 2 / size, 0 };
		}

		// now we need double values
		double[][][] nEdges = new double[edges.length][2][2];
		for (int edge = 0; edge < edges.length; edge++) {
			if (edges[edge][0][1] > edges[edge][1][1]) { // ce je prva tocka
															// visja od druge,
															// zamenjaj vrstni
															// red
				int[] point = edges[edge][0];
				edges[edge][0] = edges[edge][1];
				edges[edge][1] = point;
			}
			for (int tocka = 0; tocka < 2; tocka++) {
				for (int koordinata = 0; koordinata < 2; koordinata++) {
					nEdges[edge][tocka][koordinata] = border
							+ offset[koordinata] + (1 - 2 * border)
							* edges[edge][tocka][koordinata] / size;
				}
			}
		}
		return nEdges;
	}

	/**
	 * Default origin point (50, 50).
	 * 
	 * @param edges
	 * @param angle
	 * @return
	 */
	public static double[][][] rotate(double[][][] edges, double angle) {
		return rotate(edges, angle, new double[] { 0.5, 0.5 });
	}

	/**
	 * Rotate complex by given angle.
	 * 
	 * @param edges
	 * @param angle
	 * @return
	 */
	public static double[][][] rotate(double[][][] edges, double angle,
			double[] originPoint) {
		// iterate over all edges
		for (int i = 0; i < edges[0].length; i++) {
			// rotate first point in edge
			double[] newPoint1 = rotatePoint(edges[i][0], originPoint, angle);
			double[] newPoint2 = rotatePoint(edges[i][1], originPoint, angle);

			edges[i][0] = newPoint1;
			edges[i][1] = newPoint2;
		}
		return edges;
	}

	private static double[] rotatePoint(double[] point, double[] originPoint,
			double angle) {
		double[] newPoint = new double[2];
		double round = 100000;
		// three steps
		// 1. A translation that brings point 1 to the origin.
		// 2. Rotation around the origin by the required angle.
		// 3. A translation that brings point 1 back to its original position.
		newPoint[0] = Math.round((originPoint[0] + (point[0] - originPoint[0])
				* Math.cos(angle) - (point[1] - originPoint[1])
				* Math.sin(angle))
				* round)
				/ round;
		newPoint[1] = Math.round((originPoint[1] + (point[0] - originPoint[0])
				* Math.sin(angle) + (point[1] - originPoint[1])
				* Math.cos(angle))
				* round)
				/ round;
		return newPoint;
	}

	/**
	 * Discretize the edges on the number of given stages. The points in edges
	 * are normalized (i.e. between 0 and 1).
	 * 
	 * @param edges
	 * @param numberOfStages
	 * @return
	 */
	public static Filter generateFilter(double edges[][][], int numberOfStages) {
		// TODO: include points lying below or on the high border and above low
		// border (not on low border)
		// TODO: include only edges that have both points on or below high
		// border and above
		// TODO: Map -> key values, key -> tocka, value -> enolicna vrednost
		HashMap<double[], Integer> dict = new HashMap<double[], Integer>();
		Filter filter = new Filter(numberOfStages);

		int counter = 1;
		for (double[][] edge : edges) {
			for (double[] tocka : edge) {
				if (!dict.containsKey(tocka)) {
					dict.put(tocka, counter++);
				}
				filter.stages[(int) (numberOfStages * tocka[1])].points
						.add(dict.get(tocka));
			}
			filter.stages[(int) (numberOfStages * Math.max(edge[0][1],
					edge[1][1]))].edges.add(new Integer[] { dict.get(edge[0]),
					dict.get(edge[1]) });
		}
		return filter;
	}

	public static double[][][] GenerateBarcode(Filter filter) {
		ExplicitStream complex = new ExplicitStream();
		int numberOfStages = filter.stages.length;
		for (int i = 0; i < filter.stages.length; i++) {
			Stage stage = filter.stages[i];

			complex.add(new double[][] {
					{ Integer.MAX_VALUE - 2 },
					{ Integer.MAX_VALUE - 1 },
					{ Integer.MAX_VALUE },
					{ Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 1 },
					{ Integer.MAX_VALUE - 2, Integer.MAX_VALUE },
					{ Integer.MAX_VALUE - 1, Integer.MAX_VALUE },
					{ Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 1,
							Integer.MAX_VALUE } }, new double[] {
					numberOfStages + 1, numberOfStages + 1, numberOfStages + 1,
					numberOfStages + 1, numberOfStages + 1, numberOfStages + 1,
					numberOfStages + 1 });

			for (Iterator<Integer> it = stage.points.iterator(); it.hasNext();) {
				int f = it.next();
				complex.add(new double[] { f }, i);
			}
			/*
			 * Integer[] points = stage.points.toArray(new
			 * Integer[stage.points.size()]); if (points.length>0) { int[] pts =
			 * new int[points.length]; for (int j = 0; j < points.length; j++) {
			 * pts[j] = points[j]; } complex.add(pts, i); }
			 */
			for (Iterator<Integer[]> it = stage.edges.iterator(); it.hasNext();) {
				Integer[] f = it.next();
				complex.add(
						new double[][] { { f[0].doubleValue(),
								f[1].doubleValue() } }, new double[] { i });
			}
			/*
			 * Integer[][] edges = stage.edges.toArray(new
			 * Integer[stage.points.size()][2]); if (edges.length > 0) { int[][]
			 * eds = new int[edges.length][2]; for (int j = 0; j < edges.length;
			 * j++) { eds[j][0] = edges[j][0]; eds[j][1] = edges[j][1]; }
			 * complex.add(eds, new double[]{i, i}); }
			 */
		}

		complex.close();
		Persistence persistence = Plex.Persistence();
		PersistenceInterval.Float[] intervals = persistence
				.computeIntervals(complex);

		/*
		 * for (int i = 0; i < arrayOfBarcodes.length; i++) { for (int j = 0; j
		 * < arrayOfBarcodes[i].length; j++) { for (int k = 0; k <
		 * arrayOfBarcodes[i][j].length; k++) { System.out.println("betti-" + i
		 * + ": bar:" + j + " " + arrayOfBarcodes[i][j][k]); } } }
		 */
		return printable_intervals(intervals);
	}

	protected static double[][][] printable_intervals(
			PersistenceInterval[] intervals) {
		int[] localObject = new int[0];
		int j = -1;
		for (int k = 0; k < intervals.length; k++) {
			if (intervals[k].dimension >= localObject.length) {
				int[] arrayOfInt = new int[Math.max(intervals[k].dimension + 1,
						2 * localObject.length)];
				for (int m = 0; m < localObject.length; m++) {
					arrayOfInt[m] = localObject[m];
				}
				localObject = arrayOfInt;
			}
			j = Math.max(j, intervals[k].dimension);
			localObject[intervals[k].dimension] += 1;
		}
		PersistenceInterval[][] arrayOfPersistenceInterval = new PersistenceInterval[j + 1][];
		double[][][] arrayOfbarcodes = new double[j + 1][][];
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
			arrayOfbarcodes[i] = new double[arrayOfPersistenceInterval[i].length][];
			for (j = 0; j < arrayOfPersistenceInterval[i].length; j++) {
				arrayOfbarcodes[i][j] = arrayOfPersistenceInterval[i][j]
						.toDouble();
			}
		}
		return arrayOfbarcodes;
	}

	private static boolean CompareTwoBarcodes(double[][][] bars1,
			double[][][] idealBars1) {
		double[][][] bars = removBars(bars1);
		double[][][] idealBars = removBars(idealBars1);
		for (int i = 0; i < Math.min(idealBars.length, bars.length); i++) {
			for (int j = 0; j < idealBars[i].length; j++) {
				double zacetak = idealBars[i][j][0];
				double konec = idealBars[i][j][1];
				double start = bars[i][j][0];
				double end = bars[i][j][1];
				if (((0.9 * start < zacetak) && (zacetak < 1.1 * start))
						&& ((0.9 * end < konec) && (konec < 1.1 * end))) {
					// System.out.println("true");
					return true;
				}
			}
		}
		// System.out.println("false");
		return false;
	}

	public static char CompareBarcode(ArrayList<double[][][]> bars,
			double[][][][][] idealneCrke) {// TODO Marija
		char detektovanaCrka = ' ';
		for (int i = 0; i < idealneCrke.length; i++) {
			double[][][][] crka = idealneCrke[i];
			if ((CompareTwoBarcodes(bars.get(0), crka[0]))
					&& (CompareTwoBarcodes(bars.get(1), crka[1]))
					&& (CompareTwoBarcodes(bars.get(2), crka[2]))
					&& (CompareTwoBarcodes(bars.get(3), crka[3]))) {
				int index = i;
				if (index == 0) {
					detektovanaCrka = 'A';
				} else if (index == 1) {
					detektovanaCrka = 'B';
				} else if (index == 2) {
					detektovanaCrka = 'C';
				} else if (index == 3) {
					detektovanaCrka = 'D';
				} else if (index == 4) {
					detektovanaCrka = 'E';
				} else if (index == 5) {
					detektovanaCrka = 'F';
				} else if (index == 6) {
					detektovanaCrka = 'G';
				} else if (index == 7) {
					detektovanaCrka = 'H';
				} else if (index == 8) {
					detektovanaCrka = 'I';
				} else if (index == 9) {
					detektovanaCrka = 'J';
				} else if (index == 10) {
					detektovanaCrka = 'K';
				} else if (index == 11) {
					detektovanaCrka = 'L';
				}
			}
		}
		// System.out.println("Detektovana crka je: "+detektovanaCrka);
		return detektovanaCrka;
	}

	public static char CompareBarcode2(double[][][][] bars,
			double[][][][][] idealneCrke) {// TODO Marija
		char detektovanaCrka = ' ';
		for (int i = 0; i < idealneCrke.length; i++) {
			double[][][][] crka = idealneCrke[i];
			if ((CompareTwoBarcodes(bars[0], crka[4]))
					&& (CompareTwoBarcodes(bars[1], crka[5]))
					&& (CompareTwoBarcodes(bars[2], crka[6]))
					&& (CompareTwoBarcodes(bars[3], crka[7]))) {
				int index = i;
				if (index == 8) {
					detektovanaCrka = 'I';
				} else if (index == 9) {
					detektovanaCrka = 'J';
				} else if (index == 11) {
					detektovanaCrka = 'L';
				}
			}
		}
		// System.out.println("Detektovana crka je: "+detektovanaCrka);
		return detektovanaCrka;
	}

	private static double[][][] removBars(double[][][] bars) {
		int l = 0;
		for (int i = 0; i < bars.length; i++) {
			if (bars[i].length > l)
				l = bars[i].length;
		}

		double[][][] newBars = new double[bars.length][l][2];
		for (int i = 0; i < bars.length; i++) {
			for (int j = 0; j < bars[i].length; j++) {
				double zacetak = bars[i][j][0];
				double konec = bars[i][j][1];
				if (zacetak < 100) {
					if (konec - zacetak > 10) {
						newBars[i][j] = bars[i][j];
					}
				}
			}
		}
		return newBars;
	}
}