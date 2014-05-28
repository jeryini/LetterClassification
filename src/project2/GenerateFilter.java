/**
 * 
 */
package project2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jernej Jerin
 * 
 */
public class GenerateFilter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Discretize the edges on the number of given stages. The points in edges
	 * are normalized (i.e. between 0 and 1).
	 * 
	 * @param edges
	 * @param numberOfStages
	 * @return
	 */
	public static Filter filter(double edges[][][], int numberOfStages) {
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
