package object;

import ray.Ray;
import Materials.Material;
import color.Color;

/**
 * Abstract Superclass for all the objects we are going to create for the RayTracer. For example a
 * {@link Sphere}, a {@link Plane} or a {@link Triangle}.
 * 
 * @author Johann Hofmann
 * @author Gregor Rosenbaum
 * @author Anton Krebs
 * 
 */
public abstract class Geometry {

	public final Material material;

	/**
	 * Abstract Superclass for all the objects we are going to create for the RayTracer. For example
	 * a {@link Sphere}, a {@link Plane} or a {@link Triangle}.
	 * 
	 * @param color
	 *            = {@link Color} of the Geometry.
	 */
	public Geometry(final Material material) {
		this.material = material;
	}

	/**
	 * Checks if the given {@link Geometry} is hit by the {@link Ray}.
	 * 
	 * @param r
	 *            = {@link Ray} to hit the {@link Geometry}.
	 * @return
	 */
	public abstract Hit hit(final Ray r);

}
