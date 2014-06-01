package project2;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

/**
 * @author Jernej Jerin
 * @author Tadej Vodopivec
 * @author Marija Ðurðeviæ
 * 
 */
public class LetterClassification {
	private static int numberOfStages = 100;

	static double[][][][][] idealneCrke = {
	/*
	 * (A, B, C, D, E, F, G, H, I, J, K, L) (0, 90, 180, 270, 45, 135, 225, 315)
	 * (beti0, beti1) (zaporedna stevilka 0, 1, 2, 3, ...) (zacetek, konec)
	 */

	/* A */{
	/* 000 */{ { { 15, Double.POSITIVE_INFINITY }, { 15, 50 }, { 0, 0 } }, {} },
	/* 045 */{ { { 0, Double.POSITIVE_INFINITY }, { 50, 60 }, { 0, 0 } }, {} },
	/* 090 */{ { { 15, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 135 */{ { { 25, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 180 */{ { { 15, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 225 */{ { { 25, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 270 */{ { { 15, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 315 */{ { { 0, Double.POSITIVE_INFINITY }, { 50, 60 }, { 0, 0 } }, {} } },

	/* B */{
	/* 000 */{ { { 15, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 045 */{ { { 15, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 090 */{ { { 25, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 135 */{ { { 15, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 180 */{ { { 15, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 225 */{ { { 25, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 270 */{ { { 25, Double.POSITIVE_INFINITY }, { 35, 45 }, { 0, 0 } }, {} },
	/* 315 */{ { { 25, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} } },

	/* C */{
	/* 000 */{ { { 15, Double.POSITIVE_INFINITY }, { 75, 85 }, { 0, 0 } }, {} },
	/* 045 */{ { { 15, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 090 */{ { { 15, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 135 */{ { { 15, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 180 */{ { { 15, Double.POSITIVE_INFINITY }, { 75, 85 }, { 0, 0 } }, {} },
	/* 225 */{ { { 15, Double.POSITIVE_INFINITY }, { 50, 85 }, { 0, 0 } }, {} },
	/* 270 */{ { { 20, Double.POSITIVE_INFINITY }, { 20, 85 }, { 0, 0 } }, {} },
	/* 315 */{ { { 15, Double.POSITIVE_INFINITY }, { 50, 85 }, { 0, 0 } }, {} } },

	/* D */{
	/* 000 */{ { { 15, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 045 */{ { { 10, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 090 */{ { { 30, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 135 */{ { { 10, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 180 */{ { { 15, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 225 */{ { { 30, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 270 */{ { { 30, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 315 */{ { { 30, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} } },

	/* E */{
	/* 000 */{ { { 15, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 045 */{ { { 10, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 090 */{ { { 25, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 135 */{ { { 10, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 180 */{ { { 15, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 225 */{ { { 10, Double.POSITIVE_INFINITY }, { 55, 85 }, { 35, 65 } }, {} },
	/* 270 */{ { { 25, Double.POSITIVE_INFINITY }, { 25, 75 }, { 25, 75 } }, {} },
	/* 315 */{ { { 10, Double.POSITIVE_INFINITY }, { 55, 85 }, { 35, 65 } }, {} } },

	/* F */{
	/* 000 */{ { { 15, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 045 */{ { { 10, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 090 */{ { { 25, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 135 */{ { { 10, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 180 */{ { { 15, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 225 */{ { { 10, Double.POSITIVE_INFINITY }, { 35, 65 }, { 0, 0 } }, {} },
	/* 270 */{ { { 25, Double.POSITIVE_INFINITY }, { 25, 75 }, { 0, 0 } }, {} },
	/* 315 */{ { { 35, Double.POSITIVE_INFINITY }, { 55, 85 }, { 35, 65 } }, {} } },

	/* G */{
	/* 000 */{ { { 15, Double.POSITIVE_INFINITY }, { 75, 85 }, { 0, 0 } }, {} },
	/* 045 */{ { { 15, Double.POSITIVE_INFINITY }, { 50, 60 }, { 0, 0 } }, {} },
	/* 090 */{ { { 25, Double.POSITIVE_INFINITY }, { 50, 75 }, { 0, 0 } }, {} },
	/* 135 */{ { { 15, Double.POSITIVE_INFINITY }, { 50, 85 }, { 0, 0 } }, {} },
	/* 180 */{ { { 15, Double.POSITIVE_INFINITY }, { 50, 85 }, { 0, 0 } }, {} },
	/* 225 */{ { { 15, Double.POSITIVE_INFINITY }, { 35, 85 }, { 0, 0 } }, {} },
	/* 270 */{ { { 25, Double.POSITIVE_INFINITY }, { 25, 75 }, { 0, 0 } }, {} },
	/* 315 */{ { { 15, Double.POSITIVE_INFINITY }, { 50, 85 }, { 0, 0 } }, {} } },

	/* H */{
	/* 000 */{ { { 15, Double.POSITIVE_INFINITY }, { 15, 50 }, { 0, 0 } }, {} },
	/* 045 */{ { { 10, Double.POSITIVE_INFINITY }, { 35, 70 }, { 0, 0 } }, {} },
	/* 090 */{ { { 25, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 135 */{ { { 10, Double.POSITIVE_INFINITY }, { 35, 70 }, { 0, 0 } }, {} },
	/* 180 */{ { { 15, Double.POSITIVE_INFINITY }, { 15, 50 }, { 0, 0 } }, {} },
	/* 225 */{ { { 10, Double.POSITIVE_INFINITY }, { 35, 70 }, { 0, 0 } }, {} },
	/* 270 */{ { { 25, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 315 */{ { { 10, Double.POSITIVE_INFINITY }, { 35, 70 }, { 0, 0 } }, {} } },

	/* I */{
	/* 000 */{ { { 15, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 045 */{ { { 25, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 090 */{ { { 50, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 135 */{ { { 25, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 180 */{ { { 15, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 225 */{ { { 25, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 270 */{ { { 50, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 315 */{ { { 25, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} } },

	/* J */{
	/* 000 */{ { { 15, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 045 */{ { { 35, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 090 */{ { { 35, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 135 */{ { { 35, Double.POSITIVE_INFINITY }, { 50, 75 }, { 0, 0 } }, {} },
	/* 180 */{ { { 15, Double.POSITIVE_INFINITY }, { 70, 85 }, { 0, 0 } }, {} },
	/* 225 */{ { { 15, Double.POSITIVE_INFINITY }, { 75, 80 }, { 0, 0 } }, {} },
	/* 270 */{ { { 35, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 315 */{ { { 25, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} } },

	/* K */{
	/* 000 */{ { { 15, Double.POSITIVE_INFINITY }, { 15, 50 }, { 0, 0 } }, {} },
	/* 045 */{ { { 10, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 090 */{ { { 25, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 135 */{ { { 10, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 180 */{ { { 15, Double.POSITIVE_INFINITY }, { 15, 50 }, { 0, 0 } }, {} },
	/* 225 */{ { { 10, Double.POSITIVE_INFINITY }, { 35, 60 }, { 0, 0 } }, {} },
	/* 270 */{ { { 25, Double.POSITIVE_INFINITY }, { 25, 70 }, { 0, 0 } }, {} },
	/* 315 */{ { { 10, Double.POSITIVE_INFINITY }, { 35, 60 }, { 0, 0 } }, {} } },

	/* L */{
	/* 000 */{ { { 15, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 045 */{ { { 10, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 090 */{ { { 25, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 135 */{ { { 10, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 180 */{ { { 15, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 225 */{ { { 35, Double.POSITIVE_INFINITY }, { 60, 85 }, { 0, 0 } }, {} },
	/* 270 */{ { { 25, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} },
	/* 315 */{ { { 10, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} } }
	};

	public static void main(String[] args) {
		// read picture from file
		char[] crke = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l' };
		char[] crke2 = new char[] {'a'};
		for (char test: crke) {
			int[][] image = Functions.readPicture("./letters/" + test + ".png");

			if (image != null) {
				// get point positions
				// test case for character A (points):
				// int[][] points = new int[][] {
				// {2, 2},
				// {3, 3},
				// {4, 4},
				// {5, 5},
				// {6, 6},
				// {7, 7},
				// {8, 8},
				// {9, 7},
				// {10, 6},
				// {11, 5},
				// {12, 4},
				// {13, 3},
				// {14, 2},
				// {6, 5},
				// {7, 5},
				// {8, 5},
				// {9, 5},
				// {10, 5}
				// };
				int[][] points = Functions.readComplex(image);

				// get edges from points
				// test case for character A (edges):
				// int[][][] edges = new int[][][] { { { 2, 2 }, { 3, 3 } },
				// { { 3, 3 }, { 4, 4 } }, { { 4, 4 }, { 5, 5 } },
				// { { 5, 5 }, { 6, 6 } }, { { 6, 6 }, { 7, 7 } },
				// { { 7, 7 }, { 8, 8 } }, { { 8, 8 }, { 9, 7 } },
				// { { 9, 7 }, { 10, 6 } }, { { 10, 6 }, { 11, 5 } },
				// { { 11, 5 }, { 12, 4 } }, { { 12, 4 }, { 13, 3 } },
				// { { 13, 3 }, { 14, 2 } }, { { 5, 5 }, { 6, 5 } },
				// { { 6, 5 }, { 7, 5 } }, { { 7, 5 }, { 8, 5 } },
				// { { 8, 5 }, { 9, 5 } }, { { 9, 5 }, { 10, 5 } },
				// { { 10, 5 }, { 11, 5 } } };

				int[][][] edges = Functions.defineEdges(points);

				// normalize point coordinates of edges ([0, 1])
				double[][][] nEdges = Functions.normalize(edges);

				// sink simplical complex under water under different angles
				ArrayList<double[][][]> bars = new ArrayList<double[][][]>();

				// rotate every 90 degree
				for (double angle = 0; angle < 2 * Math.PI; angle += Math.PI / 4) {
					double[][][] nEdgesR = Functions.rotate(nEdges, angle);

					// plot the edges for the given angle
					//plotEdges(nEdgesR, angle); // TODO
					Filter filter = Functions.generateFilter(nEdgesR, numberOfStages);

					bars.add(Functions.filterAndSortBarcodes(Functions.generateBarcodes(filter), 4));
				}
				double[][][][] barsArray = new double[bars.size()][][][];
				for (int i = 0; i < bars.size(); i++) {
					barsArray[i] = bars.get(i);
				}

				char crka = Functions.CompareBarcode(barsArray, idealneCrke);

				/*
				 * if (crka == 'i' || crka == 'j' || crka == 'l') { for (double kot
				 * = Math.PI / 4; kot < 2 * Math.PI; kot += Math.PI / 2) { nEdges =
				 * Functions.rotate(nEdges, kot); Filter filter =
				 * Functions.generateFilter(nEdges, numberOfStages);
				 * bars.add(Functions.generateBarcodes(filter)); } crka =
				 * Functions.CompareBarcode2((double[][][][]) bars.toArray(),
				 * idealneCrke); }
				 */
				System.out.println(test + ": " + "'" + crka + "'");
			}
		}
	}

	/**
	 * Plot edges in panel.
	 * 
	 * @param nEdges
	 * @param angle
	 */
	private static void plotEdges(double[][][] nEdges, double angle) {
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
