package implementation.object;

import java.util.LinkedList;
import java.util.List;

import core.object.Entity;

public abstract class MovingEntity implements Entity {
	
	private double d_x, d_y;
	private double a_x, a_y;
	
	private double x, y, a;
	private double old_x, old_y, old_a;
	private List<EntityMoveListener> listeners;
	
	private double radius;

	public MovingEntity() {
		this.d_x = this.d_y = 0;
		this.a_x = this.a_y = 0;
		this.x = 0;
		this.y = 0;
		this.a = 0;
		this.old_x = 0;
		this.old_y = 0;
		this.old_a = 0;
		this.radius = 0;
		listeners = new LinkedList<EntityMoveListener>();
	}
	
	public double getX() { return x; }
	public double getY() { return y; }
	public double getAngle() { return a; }
	public double getRadius() { return radius; }
	
	public void setX(double x) { this.x = x; }
	public void setY(double y) { this.y = y; }
	public void setAngle(double a) { this.a = a; }
	public void setRadius(double r) { this.radius = r; }
	
	public void addXAcceleration(double x) { this.a_x += x; };
	public void addYAcceleration(double y) { this.a_y += y; };
	
	public void applicAcceleration() {
		d_x += a_x;
		d_y += a_y;
		x += d_x;
		y += d_y;
		a_x = 0;
		a_y = 0;
	}
	
	public void notifyMove() {
		for(EntityMoveListener e : listeners)
			e.onMove(this, old_x, old_y, old_a);
		
		old_x = x;
		old_y = y;
		old_a = a;
	}
	
	public void addMoveListener(EntityMoveListener listener) {
		listeners.add(listener);
	}
	
	public void removeMoveListener(EntityMoveListener listener) {
		listeners.remove(listener);
	}
}