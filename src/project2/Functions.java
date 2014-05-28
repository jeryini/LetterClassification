package project2;

import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import edu.stanford.math.plex.*;

public class Functions {
	public static int[][] ReadPicture(String file) {
		try {
			BufferedImage image = ImageIO.read(new File(file));
			final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
			final int width = image.getWidth();
			final int height = image.getHeight();
			
			int[][] result = new int[height][width];
			for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += 3) {
				int r = (pixels[pixel] >> 16) & 0xFF;
		        int g = (pixels[pixel + 1] >> 8) & 0xFF;
		        int b = (pixels[pixel + 2] & 0xFF);

				result[row][col] = 1 - (r + g + b) / (3*128);
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
	
	public static int[][] ReadComplex(int[][] slika) {
		final int width = slika.length;
		final int height = slika[0].length;
		
		ArrayList<int[]> tocke = new ArrayList<int[]>();
		
		for (int i=0;i<width;i++) {
			for (int j=0;j<height;j++) {
				if (slika[i][j] == 1) {
					tocke.add(new int[] {i,j});
				}
			}
		}
		tocke.size();
		
		int[][] result = new int[tocke.size()][2];
		for (int i=0; i<tocke.size();i++) {
			result[i] = tocke.get(i);
		}
		
		return result;
	}
	
	public static double[][][] DefineEdges(int[][] tocke) { //TODO Jernej
		// {tocka1, ...}
		return new double[][][] {{{0,0}, {10,0}}, {{10,0}, {10,10}}, {{10,10}, {0,10}}, {{0,10}, {0,0}}}; // {{tocka1, tocka2}, ...}
	}
	
	public static double[][][] Normalize(double[][][] edges) {
		//druga tocka v edge vedno visja
		double border = 0.15; // ko rotiras za 45, mora biti stranica najvec 1/sqrt(2), ker cene grejo vogali izven kvadranta.
		
		double[] min = edges[0][0].clone(), max = edges[0][0].clone();
		for (double[][] edge: edges) {
			for (double[] tocka: edge) {
				if (tocka[0] < min[0]) {
					min[0] = tocka[0];
				}
				if (tocka[1] < min[1]) {
					min[1] = tocka[1];
				}
				if (tocka[0] > max[0]) {
					max[0] = tocka[0];
				}
				if (tocka[1] > max[1]) {
					max[1] = tocka[1];
				}
			}
		}
		
		double[] originalSize = new double[] {(max[0]-min[0]), (max[1]-min[1])};

		double size;
		double[] offset;
		
		if (originalSize[0] > originalSize[1]) {
			size = originalSize[0];
			offset = new double[] {0, (size-originalSize[1])/2};
		}
		else {
			size = originalSize[1];
			offset = new double[] {(size-originalSize[0])/2, 0};
		}

		for (int edge=0; edge<edges.length; edge++) {
			if (edges[edge][0][1] > edges[edge][1][1]) { //ce je prva tocka visja od druge, zamenjaj vrstni red
				double[] point = edges[edge][0];
				edges[edge][0] = edges[edge][1];
				edges[edge][1] = point;
			}
			for (int tocka=0;tocka<2;tocka++) {
				for (int koordinata=0;koordinata<2;koordinata++) {
					edges[edge][tocka][koordinata] = border + offset[koordinata] + (1-2*border)*edges[edge][tocka][koordinata]/size;
				}
			}
		}
		return edges;
	}
	
	/**
	 * Default origin point (50, 50).
	 * 
	 * @param edges
	 * @param angle
	 * @return
	 */
	public static double[][][] Rotate(double[][][] edges, double angle) {
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
		// three steps
		// 1. A translation that brings point 1 to the origin.
		// 2. Rotation around the origin by the required angle.
		// 3. A translation that brings point 1 back to its original position.
		newPoint[0] = originPoint[0] + (point[0] - originPoint[0])
				* Math.cos(angle) - (point[1] - originPoint[1])
				* Math.sin(angle);
		newPoint[1] = originPoint[1] + (point[0] - originPoint[0])
				* Math.sin(angle) + (point[1] - originPoint[1])
				* Math.cos(angle);
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
	public static Filter GenerateFilter(double edges[][][], int numberOfStages) {
		// TODO: include points lying below or on the high border and above low
		// border (not on low border)
		// TODO: include only edges that have both points on or below high
		// border and above
		// TODO: Map -> key values, key -> tocka, value -> enolicna vrednost
		HashMap<double[], Integer> dict = new HashMap<double[], Integer>();
		Filter filter = new Filter(numberOfStages);

		int counter = 0;
		for (double[][] edge: edges) {
			for (double[] tocka: edge) {
				if (!dict.containsKey(tocka)) dict.put(tocka, counter++);
				filter.stages[(int) (numberOfStages*tocka[1])].points.add(dict.get(tocka));
			}
			
			filter.stages[(int) (numberOfStages*edge[1][1])].edges.add(new Integer[] {dict.get(edge[0]), dict.get(edge[1])});
		}
		return filter;
	}
	
	 public static double[][][] GenerateBarcode(Filter filter) {
	        ExplicitStream complex = new ExplicitStream();
	        for (int i = 0; i < filter.stages.length; i++) {
	            Stage stage = filter.stages[i];
	            Integer[] points = stage.points.toArray(new Integer[stage.points.size()]);
	            Integer[][] edges = stage.edges.toArray(new Integer[stage.points.size()][2]);
	            int[] pts = new int[points.length];
	            for (int j = 0; j < points.length; j++) {
	                pts[j] = points[j].intValue();
	            }
	            int[][] eds = new int[edges.length][];
	            for (int j = 0; j < edges.length; j++) {
	                eds[j][0] = edges[j][0].intValue();
	                eds[j][1] = edges[j][1].intValue();
	            }
	            complex.add(pts, i);
	            complex.add(eds, new double[]{i, i});
	        }
	        complex.close();
	        Persistence persistence = Plex.Persistence();
	        PersistenceInterval.Float[] intervals = persistence.computeIntervals(complex);
	        double[][][] arrayOfDouble = printable_intervals(intervals);
	        return arrayOfDouble;
	    }
	    
		public static char CompareBarcode(double[][][] bars, double[][][][][] idealneCrke) {
			return 'a';
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
	                arrayOfDouble[i][j] = arrayOfPersistenceInterval[i][j]
	                        .toDouble();
	            }
	        }
	        return arrayOfDouble;
	    }
	
	public static char CompareBarcode(double[][][][] bars, double[][][][][] idealneCrke) {//TODO Marija
		//preverjas samo prve stiri rotacije
		return 'i';
	}
	
	public static char CompareBarcode2(double[][][][] bars, double[][][][][] idealneCrke) {//TODO Marija
		// to mora biti dosti enako, samo da
		// preverjas samo zadnje stiri rotacije
		// in preverjas samo tiste crke, ki so v konfliktu (ce je 'i' primerjas samo med 'i' in 'j'
		return 'a';
	}
}
