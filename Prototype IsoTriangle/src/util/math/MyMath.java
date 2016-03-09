package util.math;

public class MyMath {
	
	public static final double PI = 3.14159265359;
	
	/*double x1 = 0; // Point du haut
	double y1 = -1;
	double x2 = (1 * Math.cos( (5.0 / 6)*Math.PI )); // Point de gauche
	double y2 = (1 * Math.sin( (5.0 / 6)*Math.PI ));
	double x3 = (1 * Math.cos( (1.0 / 6)*Math.PI )); // Point de droite
	double y3 = (1 * Math.sin( (1.0 / 6)*Math.PI ));*/

	public static double getTriangleAx() {
		return 0;
	}
	public static double getTriangleAy() {
		return -1;
	}
	public static double getTriangleBx() {
		return (1 * Math.cos( (5.0 / 6)*Math.PI ));
	}
	public static double getTriangleBy() {
		return (1 * Math.cos( (1.0 / 6)*Math.PI ));
	}
	public static double getTriangleCx() {
		return (1 * Math.cos( (1.0 / 6)*Math.PI ));
	}
	public static double getTriangleCy() {
		return (1 * Math.sin( (1.0 / 6)*Math.PI ));
	}
	
	/*public static double getLazyTriangleAx() {
		return 0;
	}
	public static double getLazyTriangleAy() {
		return 0;
	}
	public static double getLazyTriangleBx() {
		return 0;
	}
	public static double getLazyTriangleBy() {
		return 1;
	}
	public static double getLazyTriangleCx() {
		return 0.5;
	}
	public static double getLazyTriangleCy() {
		return 0.866025404;
	}
	
	public static double getLazyTriangleCenterX() {
		return (getLazyTriangleAx() + getLazyTriangleBx() + getLazyTriangleCx() )/3.0;
	}
	
	public static double getLazyTriangleCenterY() {
		return (getLazyTriangleAy() + getLazyTriangleBy() + getLazyTriangleCy() )/3.0;
	}
	
	public static double getTriangleAx() {
		return getLazyTriangleAx() - getLazyTriangleCenterX();
	}
	public static double getTriangleAy() {
		return getLazyTriangleAy() - getLazyTriangleCenterY();
	}
	public static double getTriangleBx() {
		return getLazyTriangleBx() - getLazyTriangleCenterX();
	}
	public static double getTriangleBy() {
		return getLazyTriangleBy() - getLazyTriangleCenterY();
	}
	public static double getTriangleCx() {
		return getLazyTriangleCx() - getLazyTriangleCenterX();
	}
	public static double getTriangleCy() {
		return getLazyTriangleCy() - getLazyTriangleCenterY();
	}*/
	
	public static double distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt( distance2(x1, y1, x2, y2) );
	}
	
	public static double distance2(double x1, double y1, double x2, double y2) {
		return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
	}
	
}
