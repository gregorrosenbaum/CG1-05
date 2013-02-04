package Lightning;

import object.Hit;
import main.RayTracer;
import main.World;
import ray.Ray;
import vectorlib.Point3;
import vectorlib.Vector3;
import color.Color;

/**
 * Directional Light for the {@link RayTracer}.
 * 
 * @author Johann Hofmann
 * @author Gregor Rosenbaum
 * @author Anton Krebs
 */
public class DirectionalLight extends Light {

	public final Vector3 direction;

	/**
	 * 
	 * @param color
	 *            = {@link Color} of the hitted pixel.
	 * @param castsShadows
	 *            = True if the {@link Light} casts any shadow.
	 * @param direction
	 *            = Direction of the light.
	 */
	public DirectionalLight(final Color color, final boolean castsShadows, final Vector3 direction) {
		super(color, castsShadows);
		this.direction = direction;
	}

	@Override
	public boolean illuminates(final Point3 point, final World world) {
		if (castsShadows == true) {
			// The directional light has no specific position. So we create one
			// by setting a position, given by the direction. Then we check for 
			// shadowcasting and hits.
			Ray ray = new Ray(point.sub(direction.normalized().mul(50)), direction.normalized());
			Hit hit = world.hit(ray);
			if (hit == null
					|| (double) Math.round(hit.t * 100000) / 100000 >= (double) Math
							.round(ray.tOf(point) * 100000) / 100000) {
				return true;
			}
			return false;
		}
		return true;
	}

	@Override
	public Vector3 directionFrom(final Point3 point) {
		return direction.mul(-0.8);

	}

}
