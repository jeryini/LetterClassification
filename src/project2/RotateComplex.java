/**
 * 
 */
package project2;

/**
 * @author Jernej Jerin
 * 
 */
public class RotateComplex {

	public static void main(String[] args) {
		double[][][] edges = { { { 5, 5 }, { 0, 10 } }, { { 5, 0 }, { 4, 6 } }};
		edges = rotate(edges, Math.PI / 4);

		for (int i = 0; i < edges[0].length; i++) {
			System.out.println("(" + edges[i][0][0] + ", " + edges[i][0][1] + ")"
					+ ", " + "(" + edges[i][1][0] + ", " + edges[i][1][1] + ")");
		}
	}
	
	/**
	 * Default origin point (0, 0).
	 * 
	 * @param edges
	 * @param angle
	 * @return
	 */
	public static double[][][] rotate(double[][][] edges, double angle) {
		return rotate(edges, angle, new double[] { 0, 0 });
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

	public static double[] rotatePoint(double[] point, double[] originPoint,
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
}
