package project2;

import java.util.HashMap;

public class Functions {
	public static double[][] ReadComplex(String file) {
		// "C:\Neki"
		return null; //seznam tock (x, y)
	}
	public static double[][][] DefineEdges(double[][] tocke) {
		// {tocka1, ...}
		return null; // {{tocka1, tocka2}, ...}
	}
	
	public static double[][][] Normalize(double[][][] edges) {
		//druga tocka vedno visja
		//najdi max in min x in y
		double[] min;
		double[] max;
		for (double[][] edge : edges) {
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
		return rotate(edges, angle, new double[] { 50, 50 });
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
	
	public static double[][][] GenerateBarcode(Filter filter) {
		return null;
	}
	public static char CompareBarcode(double[][][] bars, double[][][][][] idealneCrke) {
		return 'a';
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
}
