package Lightning;

import main.RayTracer;
import main.World;
import object.Hit;
import ray.Ray;
import vectorlib.Point3;
import vectorlib.Vector3;
import color.Color;

/**
 * Spotlight for the {@link RayTracer}.
 * 
 * @author Johann Hofmann
 * @author Gregor Rosenbaum
 * @author Anton Krebs
 */
public class Spotlight extends Light {

	public final Point3 position;
	public final Vector3 direction;
	public final double halfAngle;
	
	/**
	 * 
	 * @param color = {@link Color} of the hitted pixel.
	 * @param position = Position of the {@link Light}.
	 * @param castsShadows = True if the {@link Light} casts any shadow.
	 * @param direction = Direction of the {@link Light}.
	 * @param halfAngle = Half the aperture of the Spotlight.
	 */

	public Spotlight(final Color color, final Point3 position, final boolean castsShadows, final Vector3 direction, double halfAngle) {
		super(color, castsShadows);
		this.halfAngle = halfAngle;
		this.direction = direction;
		this.position = position;
	}

	@Override
	public boolean illuminates(final Point3 point, final World world) {
		// please look at PointLight first, in order to understand this
		// the spotlight is limited by its angle, therefore we dont just calculate shadows but also
		// if a point is within the allowed angle
		// for everything else see PointLight
		Ray ray = new Ray(position, point.sub(position).normalized());
		Hit hit = world.hit(ray);

		// the angle between the vector to the center of the light and the vector to the point:
		// cos alpha = (dc * dp) / (|dc| * |dp|)
		double alpha = Math.acos(direction.dot(point.sub(position)) / (direction.magnitude * point.sub(position).magnitude));

		// if alpha is smaller than half the angle of our light cone, our point is illuminated
		// we then check if any object is in the way, as usual
		// the castsShadows check is built in here, because the angle calculation is independent
		if (alpha <= halfAngle
				&& (castsShadows == false || hit == null || (double) Math.round(hit.t * 100000) / 100000 >= (double) Math
						.round(ray.tOf(point) * 100000) / 100000)) {
			return true;
		}
		return false;
	}

	@Override
	public Vector3 directionFrom(final Point3 point) {
		return position.sub(point).normalized();
	}
}
