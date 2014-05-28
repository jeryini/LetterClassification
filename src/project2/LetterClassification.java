package project2;

/**
 * @author Jernej Jerin
 * @author Tadej Vodopivec
 * @author Marija Ðurðeviæ
 *
 */
public class LetterClassification {
	private static int numberOfStages = 100;
	
	static double[][][][][] idealneCrke = {/*(A, B, C, D, E, F, G, H, I, J, K, L), (0, 90, 180, 270, 45, 135, 225, 315), (beti0, beti1), (zaporedna stevilka 0, 1, 2, 3, ...), (zacetek, konec)*/
			/* A */ {/*gor*/{{{15, 100}, {15, 50}}, {{85, 100}}}, /*desno*/ {{{15, 100}}, {{70, 100}}}, /*dol*/ {{{15, 100}}, {{50, 100}}}, /*levo*/ {{{15, 100}}, {{70, 100}}}, /*ostali*/ {}, {}, {}, {}},
			/* B */ {/*gor*/{{{15, 100}}, {{50, 100}, {85, 100}}}, /*desno*/ {{{15, 100}}, {{85, 100}, {85, 100}}}, /*dol*/ {{{15, 100}}, {{50, 100}, {85, 100}}}, /*levo*/ {{{15, 100}, {15, 30}}, {{85, 100}, {85, 100}}}, /*ostali*/ {}, {}, {}, {}},
			/* C */ {/*gor*/{{{15, 100}}, {}}, /*desno*/ {{{15, 100}}, {}}, /*dol*/ {{{15, 100}}, {}}, /*levo*/ {{{15, 100}, {15, 90}}, {}}, /*ostali*/ {}, {}, {}, {}},
			/* D */ {/*gor*/{{{15, 100}, {15, 50}}, {{85, 100}}}, /*desno*/ {{{15, 100}}, {{70, 100}}}, /*dol*/ {{{15, 100}}, {{50, 100}}}, /*levo*/ {{{15, 100}}, {{70, 100}}}, /*ostali*/ {}, {}, {}, {}},
			/* E */ {/*gor*/{{{15, 100}},{}}, /*desno*/ {{{15, 100}}, {}}, /*dol*/ {{{15, 100}}, {}}, /*levo*/ {{{15, 100}, {15, 90}, {15, 90}}, {}}, /*ostali*/ {}, {}, {}, {}},
			/* F */ {/*gor*/{{{15, 100}},{}}, /*desno*/ {{{15, 100}}, {}}, /*dol*/ {{{15, 100}}, {}}, /*levo*/ {{{15, 100}, {15, 90}}, {}}, /*ostali*/ {}, {}, {}, {}},
			/* G */ {/*gor*/{{{15, 100}},{}}, /*desno*/ {{{15, 100}, {50, 90}}, {}}, /*dol*/ {{{15, 100}, {50, 90}}, {}}, /*levo*/ {{{15, 100}, {15, 90}}, {}}, /*ostali*/ {}, {}, {}, {}},
			/* H */ {/*gor*/{{{15, 100}, {15, 50}},{}}, /*desno*/ {{{15, 100}}, {}}, /*dol*/ {{{15, 100}, {15, 50}}, {}}, /*levo*/ {{{15, 100}}, {}}, /*ostali*/ {}, {}, {}, {}},
			/* I */ {/*gor*/{{{15, 100}},{}}, /*desno*/ {{{50, 100}}, {}}, /*dol*/ {{{15, 100}}, {}}, /*levo*/ {{{50, 100}}, {}}, /*ostali*/ {}, {}, {}, {}},
			/* J */ {/*gor*/{{{15, 100}},{}}, /*desno*/ {{{15, 100}}, {}}, /*dol*/ {{{15, 100}, {80, 90}}, {}}, /*levo*/ {{{15, 100}}, {}}, /*ostali*/ {}, {}, {}, {}},
			/* K */ {/*gor*/{{{15, 100}, {15, 50}},{}}, /*desno*/ {{{15, 100}}, {}}, /*dol*/ {{{15, 100}, {15, 50}}, {}}, /*levo*/ {{{15, 100}, {15, 50}}, {}}, /*ostali*/ {}, {}, {}, {}},
			/* L */ {/*gor*/{{{15, 100}},{}}, /*desno*/ {{{15, 100}}, {}}, /*dol*/ {{{15, 100}}, {}}, /*levo*/ {{{15, 100}}, {}}, /*ostali*/ {}, {}, {}, {}}
	};
	
	public static void main(String[] args) {
		int[][] slika = Functions.ReadPicture("C:\\New Folder\\Untitled.png");
		if (slika != null) {
			int[][] tocke = Functions.ReadComplex(slika);
			double[][][] edges = Functions.DefineEdges(tocke); //TODO
			edges = Functions.Normalize(edges);
			
			for (double kot=0; kot<2*Math.PI; kot+=Math.PI/4) {
				edges = Functions.Rotate(edges, kot);
				Filter filter = Functions.GenerateFilter(edges, numberOfStages);
				double[][][] bars = Functions.GenerateBarcode(filter); //TODO
				Functions.CompareBarcode(bars, idealneCrke); //TODO
			}
		}
		
		
		
		

		
		
		
		
		
		
		
		
		double[][][] edges2 = { { { 5, 5 }, { 0, 10 } }, { { 5, 0 }, { 4, 6 } }};
		edges2 = Functions.Rotate(edges2, Math.PI / 4);

		for (int i = 0; i < edges2[0].length; i++) {
			System.out.println("(" + edges2[i][0][0] + ", " + edges2[i][0][1] + ")"
					+ ", " + "(" + edges2[i][1][0] + ", " + edges2[i][1][1] + ")");
		}
	}
}
