package project2;

import java.util.ArrayList;

/**
 * @author Jernej Jerin
 * @author Tadej Vodopivec
 * @author Marija Ðurðeviæ
 * 
 */
public class LetterClassification {
	public static void main(String[] args) {
		for (char test: Constants.crke) {
			double[][][] nEdges = Functions.normalize(Functions.defineEdges(Functions.readComplex(Functions.readPicture("./letters/" + test + ".png")))); // normalize point coordinates of edges - center, account for future rotation, keep aspect ratio
			ArrayList<double[][][]> bars = new ArrayList<double[][][]>();
			for (double angle = 0; angle < 2 * Math.PI; angle += Math.PI / 4) {
				// Functions.plotEdges(Functions.rotate(nEdges, angle), angle); // plot the edges for the given angle
				bars.add(Functions.filterAndSortBarcodes(Functions.generateBarcodes(Functions.generateFilter(Functions.rotate(nEdges, angle), Constants.numberOfStages)), 4)); // Very useful comment! :P
			}
			System.out.println(test + ": " + "'" + Functions.CompareBarcode(bars.toArray(new double[bars.size()][][][]), Constants.idealneCrke) + "'");
		}
	}
}
