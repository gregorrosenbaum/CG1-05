package object;

import ray.Ray;
import vectorlib.Normal3;
import Cameras.Camera;

/**
 * Tests if the {@link Ray} of the {@link Camera} hits the {@link Geometry}
 * 
 * @author Johann Hofmann
 * @author Gregor Rosenbaum
 * @author Anton Krebs
 * 
 * @param t
 *            = Distance between the {@link Geometry}-Object and the {@link Camera}.
 * @param ray
 *            = Ray from the {@link Camera} to the {@link Geometry}.
 * @param geo
 *            = {@link Geometry}-Object ({@link Plane}, {@link Sphere}, {@link Triangle}
 */
public class Hit {

	public final double t;
	public final Ray ray;
	public final Geometry geo;
	public final Normal3 normal;

	/**
	 * 
	 * @param t
	 *            = Distance between the {@link Geometry}-Object and the {@link Camera}.
	 * @param ray
	 *            = Ray from the {@link Camera} to the {@link Geometry}.
	 * @param geo
	 *            = {@link Geometry}-Object ({@link Plane}, {@link Sphere}, {@link Triangle}
	 */

	public Hit(final double t, final Ray ray, final Geometry geo, final Normal3 normal) {
		this.t = t;
		this.ray = ray;
		this.geo = geo;
		this.normal = normal;
	}

	@Override
	public String toString() {
		return t + " " + ray + " " + geo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((geo == null) ? 0 : geo.hashCode());
		result = prime * result + ((ray == null) ? 0 : ray.hashCode());
		long temp;
		temp = Double.doubleToLongBits(t);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hit other = (Hit) obj;
		if (geo == null) {
			if (other.geo != null)
				return false;
		} else if (!geo.equals(other.geo))
			return false;
		if (ray == null) {
			if (other.ray != null)
				return false;
		} else if (!ray.equals(other.ray))
			return false;
		if (Double.doubleToLongBits(t) != Double.doubleToLongBits(other.t))
			return false;
		return true;
	}

}
