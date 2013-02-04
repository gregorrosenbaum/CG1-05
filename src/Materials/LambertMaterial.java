package Materials;

import main.Tracer;
import main.World;
import object.Hit;
import Lightning.Light;
import color.Color;

/**
 * LambertMaterial shows a {@link Material} for a perfect diffuse reflected body
 * 
 * @author Johann Hofmann
 * @author Gregor Rosenbaum
 * @author Anton Krebs
 */
public class LambertMaterial extends Material {

	public final Color color;

	/**
	 * Constructor for a LambertMaterial.
	 * 
	 * @param color
	 *            = {@link Color} of the {@link Material}.
	 */
	public LambertMaterial(final Color color) {
		this.color = color;
	}

	@Override
	public Color colorFor(final Hit hit, final World world, final Tracer tracer) {
		// we declare a temporary color to which we will add all light colors
		// it initializes with the ambient color of the world
		Color returnColor = color.mul(world.ambient);

		for (Light l : world.lights) {
			// here we check if the light illuminates the object and add the corresponding color
			// formula: see assignment sheet -> LambertMaterial
			if (l.illuminates(hit.ray.at(hit.t), world)) {
				returnColor = returnColor.add(color.mul(l.color.mul(Math.max(l.directionFrom(hit.ray.at(hit.t)).dot(hit.normal), 0))));
			}
		}
		return returnColor;
	}
}
