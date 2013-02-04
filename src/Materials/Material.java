package Materials;

import main.RayTracer;
import main.Tracer;
import main.World;
import object.Geometry;
import object.Hit;
import color.Color;

/**
 * Superclass for all materials we create for the {@link Geometry}.
 * 
 * @author Johann Hofmann
 * @author Gregor Rosenbaum
 * @author Anton Krebs
 */
public abstract class Material {

	/**
	 * This method returns a color for {@link Material}.
	 * 
	 * @param hit
	 *            = checks for a {@link Hit}
	 * @param world
	 *            = {@link World} that displays all the {@link Geometry}.
	 * @param tracer
	 *            = {@link RayTracer}.
	 * @returns color
	 */

	public abstract Color colorFor(final Hit hit, final World world,
			final Tracer tracer);
}
