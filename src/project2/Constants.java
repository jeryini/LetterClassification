package project2;

public class Constants {
	public static int edgeSize = 3;

	public static double border = 0.15; // ko rotiras za 45, mora biti stranica najvec 1/sqrt(2), ker cene grejo vogali izven kvadranta.

	public static double round = 100000; // na koliko decimalnih mest zaokrozi tocke

	public static int numberOfStages = 100;

	public static char[] crke = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l' };

	public static double[][][][][] idealneCrke = {
	/*
	 * (A, B, C, D, E, F, G, H, I, J, K, L) (0, 90, 180, 270, 45, 135, 225, 315) (beti0, beti1) (zaporedna stevilka 0, 1, 2, 3, ...) (zacetek, konec)
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
	/* 315 */{ { { 10, Double.POSITIVE_INFINITY }, { 0, 0 }, { 0, 0 } }, {} } } };
}
