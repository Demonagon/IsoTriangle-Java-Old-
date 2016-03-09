package implementation.object;

import core.artist.GraphicalObject;
import core.artist.PaintableEntity;

public class Floor implements PaintableEntity {

	private GraphicalObject graphical_object;
	private double x_center, y_center, width, height, angle;
	
	public Floor(double x_center, double y_center, double width, double height, double angle) {
		this.x_center = x_center; this.y_center = y_center;
		this.width = width;
		this.height = height;
		this.angle = angle;
	}
	public Floor(double x_center, double y_center, double width, double height) {
		this.x_center = x_center; this.y_center = y_center;
		this.width = width;
		this.height = height;
		this.angle = 0;
	}
	
	public Floor() {
		this.x_center = 0; this.y_center = 0;
		this.width = 0;
		this.height = 0;
		this.angle = 0;
	}
	
	@Override
	public void setGraphicalRepresentation(GraphicalObject representation) {
		this.graphical_object = representation;
	}

	@Override
	public GraphicalObject getGraphicalRepresentation() {
		return graphical_object;
	}

	public boolean isIn(double x, double y) {
		double centered_x = x - x_center;
		double centered_y = y - y_center;
		double radian_angle = Math.toRadians(angle);
		double rotated_x = centered_x * Math.cos(radian_angle) - centered_y * Math.sin(radian_angle);
		double rotated_y = centered_x * Math.sin(radian_angle) + centered_y * Math.cos(radian_angle);
		
		return  rotated_x >= -width/2.0  &&
				rotated_x <= +width/2.0  &&
				rotated_y >= -height/2.0 &&
				rotated_y <= +height/2.0;
	}
	
	public double getXCenter() { return x_center; }
	public double getYCenter() { return y_center; }
	public double getWidth() { return width; }
	public double getHeight() { return height; }
	public double getAngle() { return angle; }
}
