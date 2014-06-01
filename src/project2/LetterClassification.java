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
	private static int numberOfStages = 10;

	static double[][][][][] idealneCrke = {/*
											 * (A, B, C, D, E, F, G, H, I, J, K,
											 * L), (0, 90, 180, 270, 45, 135,
											 * 225, 315), (beti0, beti1),
											 * (zaporedna stevilka 0, 1, 2, 3,
											 * ...), (zacetek, konec)
											 */
	/* A */{/* gor */{ { { 15, 100 }, { 15, 50 } }, { { 85, 100 } } }, /* desno */
	{ { { 15, 100 } }, { { 70, 100 } } }, /* dol */
	{ { { 15, 100 } }, { { 50, 100 } } }, /* levo */
	{ { { 15, 100 } }, { { 70, 100 } } }, /* ostali */{}, {}, {}, {} },
	/* B */{/* gor */
	{ { { 15, 100 } }, { { 50, 100 }, { 85, 100 } } }, /* desno */
	{ { { 15, 100 } }, { { 85, 100 }, { 85, 100 } } }, /* dol */
	{ { { 15, 100 } }, { { 50, 100 }, { 85, 100 } } }, /* levo */
	{ { { 15, 100 }, { 15, 30 } }, { { 85, 100 }, { 85, 100 } } }, /* ostali */
	{}, {}, {}, {} },
	/* C */{/* gor */{ { { 15, 100 } }, {} }, /* desno */
	{ { { 15, 100 } }, {} }, /* dol */{ { { 15, 100 } }, {} }, /* levo */
	{ { { 15, 100 }, { 15, 90 } }, {} }, /* ostali */{}, {}, {}, {} },
	/* D */{/* gor */{ { { 15, 100 }, { 15, 50 } }, { { 85, 100 } } }, /* desno */
	{ { { 15, 100 } }, { { 70, 100 } } }, /* dol */
	{ { { 15, 100 } }, { { 50, 100 } } }, /* levo */
	{ { { 15, 100 } }, { { 70, 100 } } }, /* ostali */{}, {}, {}, {} },
	/* E */{/* gor */{ { { 15, 100 } }, {} }, /* desno */
	{ { { 15, 100 } }, {} }, /* dol */{ { { 15, 100 } }, {} }, /* levo */
	{ { { 15, 100 }, { 15, 90 }, { 15, 90 } }, {} }, /* ostali */
	{}, {}, {}, {} },
	/* F */{/* gor */{ { { 15, 100 } }, {} }, /* desno */
	{ { { 15, 100 } }, {} }, /* dol */{ { { 15, 100 } }, {} }, /* levo */
	{ { { 15, 100 }, { 15, 90 } }, {} }, /* ostali */{}, {}, {}, {} },
	/* G */{/* gor */{ { { 15, 100 } }, {} }, /* desno */
	{ { { 15, 100 }, { 50, 90 } }, {} }, /* dol */
	{ { { 15, 100 }, { 50, 90 } }, {} }, /* levo */
	{ { { 15, 100 }, { 15, 90 } }, {} }, /* ostali */{}, {}, {}, {} },
	/* H */{/* gor */{ { { 15, 100 }, { 15, 50 } }, {} }, /* desno */
	{ { { 15, 100 } }, {} }, /* dol */
	{ { { 15, 100 }, { 15, 50 } }, {} }, /* levo */
	{ { { 15, 100 } }, {} }, /* ostali */{}, {}, {}, {} },
	/* I */{/* gor */{ { { 15, 100 } }, {} }, /* desno */
	{ { { 50, 100 } }, {} }, /* dol */{ { { 15, 100 } }, {} }, /* levo */
	{ { { 50, 100 } }, {} }, /* ostali */{}, {}, {}, {} },
	/* J */{/* gor */{ { { 15, 100 } }, {} }, /* desno */
	{ { { 15, 100 } }, {} }, /* dol */
	{ { { 15, 100 }, { 80, 90 } }, {} }, /* levo */
	{ { { 15, 100 } }, {} }, /* ostali */{}, {}, {}, {} },
	/* K */{/* gor */{ { { 15, 100 }, { 15, 50 } }, {} }, /* desno */
	{ { { 15, 100 } }, {} }, /* dol */
	{ { { 15, 100 }, { 15, 50 } }, {} }, /* levo */
	{ { { 15, 100 }, { 15, 50 } }, {} }, /* ostali */{}, {}, {}, {} },
	/* L */{/* gor */{ { { 15, 100 } }, {} }, /* desno */
	{ { { 15, 100 } }, {} }, /* dol */{ { { 15, 100 } }, {} }, /* levo */
	{ { { 15, 100 } }, {} }, /* ostali */{}, {}, {}, {} } };

	public static void main(String[] args) {
		// read picture from file
		int[][] image = Functions.readPicture("./letters/a_ideal.png");

		if (image != null) {
			// get point positions
			// test case for character A (points):
//			 int[][] points = new int[][] {
//			 {2, 2},
//			 {3, 3},
//			 {4, 4},
//			 {5, 5},
//			 {6, 6},
//			 {7, 7},
//			 {8, 8},
//			 {9, 7},
//			 {10, 6},
//			 {11, 5},
//			 {12, 4},
//			 {13, 3},
//			 {14, 2},
//			 {6, 5},
//			 {7, 5},
//			 {8, 5},
//			 {9, 5},
//			 {10, 5}
//			 };
			int[][] points = Functions.readComplex(image);

			// get edges from points
			// test case for character A (edges):
//			int[][][] edges = new int[][][] { { { 2, 2 }, { 3, 3 } },
//					{ { 3, 3 }, { 4, 4 } }, { { 4, 4 }, { 5, 5 } },
//					{ { 5, 5 }, { 6, 6 } }, { { 6, 6 }, { 7, 7 } },
//					{ { 7, 7 }, { 8, 8 } }, { { 8, 8 }, { 9, 7 } },
//					{ { 9, 7 }, { 10, 6 } }, { { 10, 6 }, { 11, 5 } },
//					{ { 11, 5 }, { 12, 4 } }, { { 12, 4 }, { 13, 3 } },
//					{ { 13, 3 }, { 14, 2 } }, { { 5, 5 }, { 6, 5 } },
//					{ { 6, 5 }, { 7, 5 } }, { { 7, 5 }, { 8, 5 } },
//					{ { 8, 5 }, { 9, 5 } }, { { 9, 5 }, { 10, 5 } },
//					{ { 10, 5 }, { 11, 5 } } };

			int[][][] edges = Functions.defineEdges(points);

			// normalize point coordinates of edges ([0, 1])
			double[][][] nEdges = Functions.normalize(edges);

			// sink simplical complex under water under different angles
			ArrayList<double[][][]> bars = new ArrayList<double[][][]>();

			// rotate every 90 degree
			for (double angle = 0; angle < 2 * Math.PI; angle += Math.PI / 2) {
				nEdges = Functions.rotate(nEdges, angle);

				// plot the edges for the given angle
				plotEdges(nEdges, angle);
				Filter filter = Functions
						.generateFilter(nEdges, numberOfStages);
				double[][][] barCode = Functions.generateBarcode(filter);

				bars.add(barCode);
			}

			char crka = Functions.CompareBarcode(bars, idealneCrke); // TODO
			if (crka == 'i' || crka == 'j' || crka == 'l') { // TODO katere
																// crke?
				for (double kot = Math.PI / 4; kot < 2 * Math.PI; kot += Math.PI / 2) {
					nEdges = Functions.rotate(nEdges, kot);
					Filter filter = Functions.generateFilter(nEdges,
							numberOfStages);
					bars.add(Functions.generateBarcode(filter));
				}
				crka = Functions.CompareBarcode2(
						(double[][][][]) bars.toArray(), idealneCrke); // TODO
			}
			System.out.println("'" + crka + "'");
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

		for (double[][] nEdge : nEdges) {
			plot.addLinePlot("Letter with angle " + angle, new Color(0, 0, 0),
					nEdge);
		}

		// put the PlotPanel in a JFrame, as a JPanel
		JFrame frame = new JFrame("a plot panel");
		frame.setContentPane(plot);
		frame.setVisible(true);
		frame.setSize(400, 400);
	}
}
