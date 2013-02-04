package Materials;

import main.Tracer;
import main.World;
import object.Hit;
import ray.Ray;
import vectorlib.Normal3;
import vectorlib.Vector3;
import color.Color;

/**
 * TransparentMaterials shows a transparent {@link Material}
 * 
 * @author Johann Hofmann
 * @author Gregor Rosenbaum
 * @author Anton Krebs
 */
public class TransparentMaterial extends Material {

	public final double indexOfRefraction;
	// the transparent material needs an own recursion counter because it
	// creates tracer requests
	// exponentially, which cant be handled by the tracer recursion guard
	protected int recursionCounter;
	public static final int maxDepth = 4;

	/**
	 * Constructor for a TransparentMaterial.
	 * 
	 * @param indexOfRefraction
	 *            = a dimensionless number that describes how light, or any
	 *            other radiation, propagates through that medium
	 * 
	 */
	public TransparentMaterial(double indexOfRefraction) {
		this.indexOfRefraction = indexOfRefraction;
		this.recursionCounter = maxDepth;
	}

	@Override
	public Color colorFor(final Hit hit, final World world, final Tracer tracer) {
		// if the maximum depth is reached, we just return the backgroundcolor
		// this doesnt make much of a difference, it will be for internal
		// reflection by now anyway
		recursionCounter--;
		if (recursionCounter <= 0) {
			return world.backgroundColor;
		}

		// we declare a separate normal, because we might need to change it
		// later
		Normal3 normal = hit.normal;

		// the cosinus of the entry angle of the Incoming ray.
		double cosI = hit.ray.d.mul(-1).dot(normal);

		// refraction numbers of our materials. might be switched later
		double n1 = world.indexOfRefraction;
		double n2 = indexOfRefraction;

		// if the entry angle is greater than 90 degrees, the ray is coming from
		// inside the object
		// this obviously means we need to change a few things
		if (Math.toDegrees(Math.acos(cosI)) > 90) {
			// we reverse the normal, as it should point to the inside now
			normal = hit.normal.mul(-1);
			// we recalculate the entry angle
			cosI = hit.ray.d.mul(-1).dot(normal);
			// we switch the refraction numbers
			n1 = indexOfRefraction;
			n2 = world.indexOfRefraction;
		}

		// the cosinus of the angle of the Transmitted ray t, formula see
		// lecture notes
		// (ATTENTION: lecture notes contain errors regarding this. this is the
		// correct calculation)
		double cosT = Math
				.sqrt(1 - ((n1 / n2) * (n1 / n2)) * (1 - cosI * cosI));

		// TIR = Total Internal Reflection
		boolean tir = false;

		if (n1 > n2) {
			double sing2 = (n1 / n2) * (n1 / n2) * (1 - cosI * cosI);
			if (sing2 > 1) {
				tir = true;
			}
		}

		// necessary for schlicks approximation, formula see lecture notes
		double R0 = Math.pow(((n1 - n2) / (n1 + n2)), 2);

		// the share of reflection in the resulting color
		// goes from 0 to 1, with transmission being the rest
		// the higher R is, the more is reflected and the less is transmitted
		// if there is Total Internal Reflection, R is 1 (see below)
		double R = 1;

		// calculation of r, see lecture notes
		// (ATTENTION: lecture notes contain errors regarding this. this is the
		// correct calculation)
		// if n1 is greater than n2, we need to use the transmission angle
		if (n1 <= n2) {
			R = R0 + (1 - R0) * Math.pow(1 - cosI, 5);
		} else if (n1 > n2 && tir == false) {
			R = R0 + (1 - R0) * Math.pow(1 - cosT, 5);
		} else if (n1 > n2 && tir == true) {
			// if total internal reflection, r = 1
			R = 1;
		}

		// if there is total reflection, we can avoid to calculate the
		// transmission
		if (R != 1) {
			// transmitted vector t, formula see lecture notes
			Vector3 t = hit.ray.d.mul(n1 / n2).add(
					normal.mul(cosI * (n1 / n2) - cosT));

			// calculate reflection and transmission colors, formula see
			// assignment sheet
			// see also ReflectiveMaterial
			Color color = (tracer.colorFor(new Ray(hit.ray.at(hit.t), hit.ray.d
					.add(normal.mul(cosI * 2)))).mul(R)).add(tracer.colorFor(
					new Ray(hit.ray.at(hit.t), t)).mul(1 - R));
			recursionCounter = maxDepth;
			return color;
		} else {
			// only calculate the reflection
			Color color = tracer.colorFor(new Ray(hit.ray.at(hit.t), hit.ray.d
					.add(normal.mul(cosI * 2))));
			recursionCounter = maxDepth;
			return color;
		}
	}
}
