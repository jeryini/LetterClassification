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
			/* A */ {/*gor*/{{{10, 100}, {10, 50}}, {{90, 100}}}, /*desno*/ {{{10, 100}}, {{70, 100}}}, /*dol*/ {{{10, 100}}, {{50, 100}}}, /*levo*/ {{{10, 100}}, {{70, 100}}}, /*ostali*/ {}, {}, {}, {}},
			/* B */ {/*gor*/{{{10, 100}}, {{50, 100}, {90, 100}}}, /*desno*/ {{{10, 100}}, {{90, 100}, {90, 100}}}, /*dol*/ {{{10, 100}}, {{50, 100}, {90, 100}}}, /*levo*/ {{{10, 100}, {10, 30}}, {{90, 100}, {90, 100}}}, /*ostali*/ {}, {}, {}, {}},
			/* C */ {/*gor*/{{{10, 100}}, {}}, /*desno*/ {{{10, 100}}, {}}, /*dol*/ {{{10, 100}}, {}}, /*levo*/ {{{10, 100}, {10, 90}}, {}}, /*ostali*/ {}, {}, {}, {}},
			/* D */ {/*gor*/{{{10, 100}, {10, 50}}, {{90, 100}}}, /*desno*/ {{{10, 100}}, {{70, 100}}}, /*dol*/ {{{10, 100}}, {{50, 100}}}, /*levo*/ {{{10, 100}}, {{70, 100}}}, /*ostali*/ {}, {}, {}, {}},
			/* E */ {/*gor*/{{{10, 100}},{}}, /*desno*/ {{{10, 100}}, {}}, /*dol*/ {{{10, 100}}, {}}, /*levo*/ {{{10, 100}, {10, 90}, {10, 90}}, {}}, /*ostali*/ {}, {}, {}, {}},
			/* F */ {/*gor*/{{{10, 100}},{}}, /*desno*/ {{{10, 100}}, {}}, /*dol*/ {{{10, 100}}, {}}, /*levo*/ {{{10, 100}, {10, 90}}, {}}, /*ostali*/ {}, {}, {}, {}},
			/* G */ {/*gor*/{{{10, 100}},{}}, /*desno*/ {{{10, 100}, {50, 90}}, {}}, /*dol*/ {{{10, 100}, {50, 90}}, {}}, /*levo*/ {{{10, 100}, {10, 90}}, {}}, /*ostali*/ {}, {}, {}, {}},
			/* H */ {/*gor*/{{{10, 100}, {10, 50}},{}}, /*desno*/ {{{10, 100}}, {}}, /*dol*/ {{{10, 100}, {10, 50}}, {}}, /*levo*/ {{{10, 100}}, {}}, /*ostali*/ {}, {}, {}, {}},
			/* I */ {/*gor*/{{{10, 100}},{}}, /*desno*/ {{{50, 100}}, {}}, /*dol*/ {{{10, 100}}, {}}, /*levo*/ {{{50, 100}}, {}}, /*ostali*/ {}, {}, {}, {}},
			/* J */ {/*gor*/{{{10, 100}},{}}, /*desno*/ {{{10, 100}}, {}}, /*dol*/ {{{10, 100}, {80, 90}}, {}}, /*levo*/ {{{10, 100}}, {}}, /*ostali*/ {}, {}, {}, {}},
			/* K */ {/*gor*/{{{10, 100}, {10, 50}},{}}, /*desno*/ {{{10, 100}}, {}}, /*dol*/ {{{10, 100}, {10, 50}}, {}}, /*levo*/ {{{10, 100}, {10, 50}}, {}}, /*ostali*/ {}, {}, {}, {}},
			/* L */ {/*gor*/{{{10, 100}},{}}, /*desno*/ {{{10, 100}}, {}}, /*dol*/ {{{10, 100}}, {}}, /*levo*/ {{{10, 100}}, {}}, /*ostali*/ {}, {}, {}, {}}
	};
	
	public static void main(String[] args) {
		/*
		double[][] tocke = Functions.ReadComplex("C:\neki");
		double[][][] edges = Functions.DefineEdges(tocke);
		edges = Functions.Normalize(edges);
		
		for (int kot=0; kot<360; kot+=45) {
			edges = Functions.Rotate(edges, kot);
			Filter filter = Functions.GenerateFilter(edges, numberOfStages);
			double[][][] bars = Functions.GenerateBarcode(filter);
			Functions.CompareBarcode(bars, idealneCrke);
		}
		
		*/
		

		
		
		
		
		
		
		
		
		double[][][] edges2 = { { { 5, 5 }, { 0, 10 } }, { { 5, 0 }, { 4, 6 } }};
		edges2 = Functions.Rotate(edges2, Math.PI / 4);

		for (int i = 0; i < edges2[0].length; i++) {
			System.out.println("(" + edges2[i][0][0] + ", " + edges2[i][0][1] + ")"
					+ ", " + "(" + edges2[i][1][0] + ", " + edges2[i][1][1] + ")");
		}
	}
}
