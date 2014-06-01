package project2;

import java.awt.Color;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

import edu.stanford.math.plex.*;
import edu.stanford.math.plex.PersistenceInterval.Float;

/**
 * @author Jernej Jerin
 * @author Tadej Vodopivec
 * @author Marija �ur�evi�
 * 
 */
public class Functions {

	/**
	 * Reads picture and returns array of 1's and 0's, where value 0 represents white pixel and value 1 any other color.
	 * 
	 * @param file
	 * @return
	 */
	public static int[][] readPicture(String file) {
		try {
			BufferedImage image = ImageIO.read(new File(file));
			final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
			final int width = image.getWidth();
			final int height = image.getHeight();
			final boolean hasAlphaChannel = image.getAlphaRaster() != null;

			int[][] result = new int[height][width];
			for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += hasAlphaChannel ? 4 : 3) {
				// the image has alpha channel before rgb that is why we need a
				// step of size 4!
				int r = (pixels[pixel + (hasAlphaChannel ? 1 : 0)] >> 16) & 0xFF;
				int g = (pixels[pixel + (hasAlphaChannel ? 2 : 1)] >> 8) & 0xFF;
				int b = (pixels[pixel + (hasAlphaChannel ? 3 : 2)] & 0xFF);

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
					points.add(new int[] { j, height - i });
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
		return defineEdges(points, Constants.edgeSize);
	}

	/**
	 * Define edges (build a simplical complex) of dimension 1 with nerve of U. The distance r should be 1.
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

		double[] originalSize = new double[] { (max[0] - min[0]), (max[1] - min[1]) };

		double size;
		double[] offset = new double[] { 0, 0 };
		double stretch[] = new double[] { 1, 1 };
		// check if length of x value is greater than the length of y value
		if (originalSize[0] > originalSize[1]) {
			size = originalSize[0];
			stretch[1] = originalSize[0] / originalSize[1];
		} else {
			size = originalSize[1];
			offset[0] = (1 - originalSize[0] / originalSize[1]) / 2;
		}

		// now we need double values
		double[][][] nEdges = new double[edges.length][2][2];
		for (int edge = 0; edge < edges.length; edge++) {
			if (edges[edge][0][1] > edges[edge][1][1]) { // ce je prva tocka visja od druge, zamenjaj vrstni red
				int[] point = edges[edge][0];
				edges[edge][0] = edges[edge][1];
				edges[edge][1] = point;
			}
			for (int tocka = 0; tocka < 2; tocka++) {
				for (int koordinata = 0; koordinata < 2; koordinata++) {
					nEdges[edge][tocka][koordinata] = Constants.border + (1 - 2 * Constants.border)
							* (offset[koordinata] + (edges[edge][tocka][koordinata] - min[koordinata]) / size * stretch[koordinata]);
				}
			}
		}
		return nEdges;
	}

	/**
	 * Default origin point (0.5, 0.5). Positive angle means counter clockwise rotation and negative angle means counter clockwise rotation.
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
	public static double[][][] rotate(double[][][] edges, double angle, double[] originPoint) {
		// we need to return new array
		double[][][] edgesR = new double[edges.length][2][2];

		// iterate over all edges
		for (int i = 0; i < edges.length; i++) {
			// rotate first point in edge
			double[] newPoint1 = rotatePoint(edges[i][0], originPoint, angle);
			double[] newPoint2 = rotatePoint(edges[i][1], originPoint, angle);

			edgesR[i][0] = newPoint1;
			edgesR[i][1] = newPoint2;
		}
		return edgesR;
	}

	private static double[] rotatePoint(double[] point, double[] originPoint, double angle) {
		double[] newPoint = new double[2];

		// three steps
		// 1. A translation that brings point 1 to the origin.
		// 2. Rotation around the origin by the required angle.
		// 3. A translation that brings point 1 back to its original position.
		newPoint[0] = Math.round((originPoint[0] + (point[0] - originPoint[0]) * Math.cos(angle) - (point[1] - originPoint[1]) * Math.sin(angle)) * Constants.round) / Constants.round;
		newPoint[1] = Math.round((originPoint[1] + (point[0] - originPoint[0]) * Math.sin(angle) + (point[1] - originPoint[1]) * Math.cos(angle)) * Constants.round) / Constants.round;
		return newPoint;
	}

	/**
	 * Discretize the edges on the number of given stages. The points in edges are normalized (i.e. between 0 and 1) and ordered so the firs point in edge is smaller in y value than the second point
	 * in edge.
	 * 
	 * @param edges
	 * @param numberOfStages
	 * @return
	 */
	public static Filter generateFilter(double edges[][][], int numberOfStages) {
		HashMap<Integer, Integer> dict = new HashMap<Integer, Integer>();
		Filter filter = new Filter(numberOfStages);

		int counter = 1;
		for (double[][] edge : edges) {
			for (double[] point : edge) {
				// value for our key (point) is abstract point (integer)
				if (!dict.containsKey(Arrays.hashCode(point)))
					dict.put(Arrays.hashCode(point), counter++);

				// add abstract value of point to appropriate stage regarding
				// the y value of point. The bigger the y value, higher the
				// stage
				filter.stages[(int) (numberOfStages * point[1])].points.add(dict.get(Arrays.hashCode(point)));
			}

			// add abstract edge (consisting of two abstract points) to stage
			// according to the largest of y value of two points
			filter.stages[(int) (numberOfStages * Math.max(edge[0][1], edge[1][1]))].edges.add(new Integer[] { dict.get(Arrays.hashCode(edge[0])), dict.get(Arrays.hashCode(edge[1])) });
		}

		return filter;
	}

	/**
	 * Generate barcode.
	 * 
	 * @param filter
	 * @return
	 */
	public static double[][][] generateBarcodes(Filter filter) {
		// complex stream for barcode
		ExplicitStream complex = new ExplicitStream();
		int numberOfStages = filter.numberOfStages;

		complex.add(new double[][] { { Integer.MAX_VALUE - 2 }, { Integer.MAX_VALUE - 1 }, { Integer.MAX_VALUE }, { Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 1 },
				{ Integer.MAX_VALUE - 2, Integer.MAX_VALUE }, { Integer.MAX_VALUE - 1, Integer.MAX_VALUE }, { Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 1, Integer.MAX_VALUE } }, new double[] {
				numberOfStages + 1, numberOfStages + 1, numberOfStages + 1, numberOfStages + 1, numberOfStages + 1, numberOfStages + 1, numberOfStages + 1 });

		// iterate over all stages of filter
		for (int i = 0; i < numberOfStages; i++) {
			Stage stage = filter.stages[i];

			for (Integer point : stage.points)
				// add abstract point and stage number as parameter
				complex.add(new double[] { point }, i);

			for (Integer[] edge : stage.edges)
				// add abstract edge to complex
				complex.add(new double[][] { { edge[0].doubleValue(), edge[1].doubleValue() } }, new double[] { i });
		}

		complex.close();
		Float[] intervals = Plex.Persistence().computeIntervals(complex);
		// Plex.plot(intervals, "Barcode plot", numberOfStages);

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
			arrayOfbarcodes[i] = new double[arrayOfPersistenceInterval[i].length - 1][];
			for (j = 0; j < arrayOfPersistenceInterval[i].length - 1; j++) {
				double[] bar = arrayOfPersistenceInterval[i][j].toDouble();
				if (bar[0] != filter.numberOfStages + 1) {
					arrayOfbarcodes[i][j] = arrayOfPersistenceInterval[i][j].toDouble();
				}
			}
		}
		return arrayOfbarcodes;
	}

	public static double[][][] filterAndSortBarcodes(double[][][] barCodes, int minLength) {
		ArrayList<double[][]> bars2 = new ArrayList<double[][]>();
		for (int betti = 0; betti < 2; betti++) {
			ArrayList<double[]> bars = new ArrayList<double[]>();
			for (double[] a : barCodes[0]) {
				if (a[1] - a[0] >= minLength)
					bars.add(a);
			}
			double[][] out = new double[bars.size()][];
			for (int i = 0; i < bars.size(); i++)
				out[i] = bars.get(i);
			bars2.add(out);
		}
		double[][][] bars2out = new double[2][][];
		bars2out[0] = bars2.get(0);
		bars2out[1] = bars2.get(1);

		double[][][] out = new double[2][3][2];

		for (int betti = 0; betti < 2; betti++) {
			int max1 = 0;

			for (int bar = 0; bar < bars2out[betti].length; bar++) {
				if (length(bars2out[betti][bar]) > length(bars2out[betti][max1]))
					max1 = bar;
			}

			out[betti][0] = bars2out[betti][max1];

			if (bars2out[betti].length > 1) {
				int max2 = -1;
				for (int bar = 0; bar < bars2out[betti].length; bar++) {
					if (bar != max1) {
						if (max2 == -1)
							max2 = bar;
						if (length(bars2out[betti][bar]) > length(bars2out[betti][max2]) && length(bars2out[betti][bar]) <= length(bars2out[betti][max1]))
							max2 = bar;
					}
				}
				out[betti][1] = bars2out[betti][max2];

				if (bars2out[betti].length > 2) {
					int max3 = -1;
					for (int bar = 0; bar < bars2out[betti].length; bar++) {
						if (bar != max1 && bar != max2) {
							if (max3 == -1)
								max3 = bar;
							if (length(bars2out[betti][bar]) > length(bars2out[betti][max3]) && length(bars2out[betti][bar]) <= length(bars2out[betti][max2]))
								max3 = bar;
						}
					}
					out[betti][2] = bars2out[betti][max3];
				} else {
					out[betti][2] = new double[] { 0, 0 };
				}
			} else {
				out[betti][1] = new double[] { 0, 0 };
				out[betti][2] = new double[] { 0, 0 };
			}
		}

		return out;
	}

	private static double length(double[] bar) {
		return bar[1] - bar[0];
	}

	public static char CompareBarcode(double[][][][] bars, double[][][][][] idealneCrke) {
		// bars [kot][beti][bar][zacetek, konec]
		// idealneCrke [crka][kot][beti][bar][zacetek, konec]
		double maxPodobnost = Double.POSITIVE_INFINITY;
		char crka = ' ';
		for (int i = 0; i < idealneCrke.length; i++) {
			double[][][][] idealnaCrka = idealneCrke[i];
			double podobnost = CompareLetter(bars, idealnaCrka);
			if (podobnost < maxPodobnost) {
				maxPodobnost = podobnost;
				crka = Constants.crke[i];
			}
		}
		return crka;
	}

	private static double CompareLetter(double[][][][] bars, double[][][][] idealnaCrka) {
		// [kot][beti][bar][zacetek, konec]
		double distance = 0;

		for (int kot = 0; kot < idealnaCrka.length; kot++) {
			for (int bar = 0; bar < idealnaCrka[kot][0].length; bar++) {
				distance += Math.abs(idealnaCrka[kot][0][bar][0] - bars[kot][0][bar][0]);
				if (idealnaCrka[kot][0][bar][1] != Double.POSITIVE_INFINITY && bars[kot][0][bar][1] != Double.POSITIVE_INFINITY)
					distance += Math.abs(idealnaCrka[kot][0][bar][1] - bars[kot][0][bar][1]);
				else if (idealnaCrka[kot][0][bar][1] == Double.POSITIVE_INFINITY)
					distance += 100;
				else
					distance += 100 - idealnaCrka[kot][0][bar][1];
			}
		}

		return distance;
	}

	/**
	 * Plot edges in panel.
	 * 
	 * @param nEdges
	 * @param angle
	 */
	public static void plotEdges(double[][][] nEdges, double angle) {
		// create your PlotPanel (you can use it as a JPanel)
		Plot2DPanel plot = new Plot2DPanel();
		plot.addLinePlot("Letter with angle " + angle, new Color(0, 0, 0), new double[][] { { 0, 0 }, { 0, 1 } });
		plot.addLinePlot("Letter with angle " + angle, new Color(0, 0, 0), new double[][] { { 0, 0 }, { 1, 0 } });
		for (double[][] nEdge : nEdges) {
			plot.addLinePlot("Letter with angle " + angle, new Color(0, 0, 0), nEdge);
		}

		// put the PlotPanel in a JFrame, as a JPanel
		JFrame frame = new JFrame("a plot panel");
		frame.setContentPane(plot);
		frame.setVisible(true);
		frame.setSize(400, 400);
	}
}
