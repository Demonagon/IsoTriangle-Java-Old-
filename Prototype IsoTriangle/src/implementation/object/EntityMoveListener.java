package implementation.object;

import core.object.Entity;

public interface EntityMoveListener {
	public void onMove(Entity entity, double old_x, double old_y, double old_a);
}
