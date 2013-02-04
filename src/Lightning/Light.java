package Lightning;

import main.RayTracer;
import main.World;
import vectorlib.Point3;
import vectorlib.Vector3;
import color.Color;

/**
 * Abstract superclass for all light of the {@link RayTracer}.
 * 
 * @author Johann Hofmann
 * @author Gregor Rosenbaum
 * @author Anton Krebs
 */
public abstract class Light {

	public final Color color;
	public final boolean castsShadows;

	/**
	 * @param color
	 *            = {@link Color}
	 * @param castsShadows
	 */
	public Light(final Color color, final boolean castsShadows) {
		this.color = color;
		this.castsShadows = castsShadows;
	}
	
	/**
	 * Checks a {@link Point3} if it's illuminated by a specific light.
	 * 
	 * @param point = {@link Point3} to check.
	 * @param world = Given {@link World} for the Raytracer.
	 * @return true if the point is illuminated
	 */
	public abstract boolean illuminates(final Point3 point, final World world);
	
	/**
	 * Returns the direction a {@link Point3} is illuminated from.
	 * 
	 * @param point = {@link Point3} 
	 * @return {@link Vector3} that describes the direction the light is
	 * coming from. 
	 */

	public abstract Vector3 directionFrom(final Point3 point);
}
