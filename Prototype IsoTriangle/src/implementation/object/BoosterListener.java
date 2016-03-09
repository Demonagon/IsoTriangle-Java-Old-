package implementation.object;

public interface BoosterListener {
	public void onRotation(Booster booster, double old_angle, double new_angle);
	public void onActivation(boolean active);
}
