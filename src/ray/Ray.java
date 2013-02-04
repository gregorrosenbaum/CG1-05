package ray;

import vectorlib.Point3;
import vectorlib.Vector3;
import Cameras.Camera;

/**
 * Constructs a Ray with a {@link Point3} and a {@link Vector3}.
 * 
 * @author Johann Hofmann
 * @author Gregor Rosenbaum
 * @author Anton Krebs
 * 
 */
public class Ray {

	public final Point3 o;
	public final Vector3 d;

	/**
	 * 
	 * @param o
	 *            = {@link Point3} of the Ray.
	 * @param d
	 *            = {@link Vector3} of the Ray.
	 */
	public Ray(final Point3 o, final Vector3 d) {
		this.o = o;
		this.d = d;
	}

	/**
	 * Returns the {@link Point3} with the distance t.
	 * 
	 * @param t
	 *            = Distance from the {@link Camera} to the {@link Point3}.
	 * @return {@link Point3}
	 */
	public Point3 at(double t) {
		return o.add(d.mul(t));
	}

	/**
	 * Returns the {@link Vector3} at the {@link Point3} p.
	 * 
	 * @param p
	 *            =
	 * @return {@link Vector3}
	 */
	public double tOf(Point3 p) {
		return o.sub(p).magnitude;
	}

	@Override
	public String toString() {
		return "o:" + o + ", d: " + d;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((d == null) ? 0 : d.hashCode());
		result = prime * result + ((o == null) ? 0 : o.hashCode());
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
		Ray other = (Ray) obj;
		if (d == null) {
			if (other.d != null)
				return false;
		} else if (!d.equals(other.d))
			return false;
		if (o == null) {
			if (other.o != null)
				return false;
		} else if (!o.equals(other.o))
			return false;
		return true;
	}
}
