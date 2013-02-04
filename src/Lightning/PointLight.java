package Lightning;

import main.RayTracer;
import main.World;
import object.Hit;
import ray.Ray;
import vectorlib.Point3;
import vectorlib.Vector3;
import color.Color;

/**
 * PointLight for the {@link RayTracer}
 * 
 * @author Johann Hofmann
 * @author Gregor Rosenbaum
 * @author Anton Krebs
 */
public class PointLight extends Light {

	public final Point3 position;

	/**
	 * 
	 * @param color
	 *            = {@link Color} of the hitted pixel.
	 * @param castsShadows
	 *            = True if the {@link Light} casts any shadow.
	 * @param position
	 *            = Position of the light.
	 */
	public PointLight(final Color color, final boolean castsShadows, final Point3 position) {
		super(color, castsShadows);
		this.position = position;
	}

	@Override
	public boolean illuminates(final Point3 point,final World world) {
		// Check if the light is supposed to cast any shadows, if not we skip and return true,
		// this doesnt necessarily mean it illuminates the point, just that nothing is in the way.
		// And that is sufficient for shadows.
		// the actual illumination is calculated by the material with the distance of directionFrom
		if (castsShadows) {
			// create a new ray that points from the position of the light to the hitted point
			Ray ray = new Ray(position, point.sub(position).normalized());
			// try to hit all objects with this ray
			Hit hit = world.hit(ray);
			// remember: this check is only for shadows
			if (hit == null || (double) Math.round(hit.t * 100000) / 100000 >= (double) Math.round(ray.tOf(point) * 100000) / 100000) {
				// if something is hit, we check if its t (the distance on the ray) is closer to our
				// light source than the t of the point

				// the ugly rounding is necessary because we deal with very precise floating point
				// numbers:
				// obviously t = 1.028393 and t = 1.028394 refer to the same object, but they are
				// still not accepted if we do not round

				// if t is bigger (= farther away) or equal we return true, meaning our object is
				// the first to be hit by the light ray and nothing shadows it
				return true;
			}
			// otherwise we return false, something is in the way, the light doesnt reach our point
			return false;
		}
		return true;
	}

	@Override
	public Vector3 directionFrom(final Point3 point) {
		return position.sub(point).normalized();
	}
}
