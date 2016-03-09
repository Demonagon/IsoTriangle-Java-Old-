package implementation.object;

import java.util.LinkedList;
import java.util.List;

import util.math.MyMath;
import core.artist.GraphicalObject;
import core.artist.PaintableEntity;

public class Booster implements PaintableEntity {
	
	public static final double basic_size = 60;
	public static final double effect_on_squares = 0.12;
	public static final int basic_angle_positions = 32;
	
	private GraphicalObject representation;

	private double x, y;
	private double arrow_angle;
	private double square_size;
	private double square_max_radius;
	private int angle_positions;
	private boolean active;
	
	private List<BoosterListener> listeners;
	
	public Booster() {
		this.arrow_angle = 0;
		this.x = 0;
		this.y = 0;
		this.active = false;
		setSquareSize(basic_size);
		this.representation = null;
		
		setAnglePositions(basic_angle_positions);
		
		listeners = new LinkedList<BoosterListener>();
	}
	
	@Override
	public void setGraphicalRepresentation(GraphicalObject representation) {
		this.representation = representation;
	}

	@Override
	public GraphicalObject getGraphicalRepresentation() {
		return representation;
	}
	
	public double getSquareSize() {
		return square_size;
	}
	
	public void setAnglePositions(int angle_positions) {
		this.angle_positions = angle_positions;
	}
	
	public int getAnglePositions() {
		return angle_positions;
	}
	
	public void setSquareSize(double size) {
		this.square_size = size;
		this.square_max_radius = Math.sqrt( 2 * size * size );
	}
	
	public double getArrowAngle() {
		return arrow_angle;
	}
	
	public void setArrowAngle(double arrow_angle) {
		double old_angle = this.arrow_angle;
		this.arrow_angle = limitAngle(arrow_angle);
		
		notifyAngleChange(old_angle);
	}
	
	public double limitAngle(double angle) {
		double positive_angle = angle + 180;
		double angle_portion = 360.0 / angle_positions;
		double position_num = Math.toIntExact( Math.round( positive_angle / angle_portion ) );
		double positive_result = position_num * angle_portion;
		return positive_result - 180;
	}
	
	public double getX() { return x; }
	public double getY() { return y; }
	public void setX(double x) { this.x = x; }
	public void setY(double y) { this.y = y; }
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		boolean old_active = this.active;
		this.active = active;
		if( old_active != active )
			notifyActivation();
	}
	
	public void addListener(BoosterListener l) {
		listeners.add(l);
	}
	
	public void notifyAngleChange(double old_angle) {
		for(BoosterListener l : listeners)
			l.onRotation(this, old_angle, arrow_angle);
	}
	
	public void notifyActivation() {
		for(BoosterListener l : listeners)
			l.onActivation(active);
	}
	
	public boolean isUnderEntity(MovingEntity entity) {
		if( ! active ) return false;
		
		//Version simple : le booster n'a pas d'angle
		//C'est donc un carré dans le sens orthogonal, comme dans l'ancienne version
		//Dans le futur on voudrait que les boosters puissent adopter un angle personnalisÃ©,
		//voire que cet angle change au cours du temps
		
		if( entity.getX() + entity.getRadius() < this.getX() - square_size/2 ) return false;
		if( entity.getX() - entity.getRadius() > this.getX() + square_size/2 ) return false;
		if( entity.getY() + entity.getRadius() < this.getY() - square_size/2 ) return false;
		if( entity.getY() - entity.getRadius() > this.getY() + square_size/2 ) return false;
		
		double distance = MyMath.distance(x, y, entity.getX(), entity.getY());
		
		if( distance > this.square_max_radius ) return false;
		
		return true;
	}
	
}
