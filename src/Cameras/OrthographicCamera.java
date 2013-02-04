package Cameras;

import ray.Ray;
import vectorlib.Point3;
import vectorlib.Vector3;

/**
 * 
 * An Orthographic Camera.
 * 
 * @author Johann Hofmann
 * @author Gregor Rosenbaum
 * @author Anton Krebs
 * 
 */
public class OrthographicCamera extends Camera {

	/**
	 * the scale factor.
	 */
	public final double s;

	/**
	 * 
	 * @param e
	 *            eye position.
	 * @param g
	 *            gaze direction
	 * @param t
	 *            up vector.
	 * @param s
	 *            the scale factor.
	 */
	public OrthographicCamera(final Point3 e, final Vector3 g, final Vector3 t,
			final double s) {
		super(e, g, t);
		this.s = s;
	}

	@Override
	public Ray rayFor(final int w, final int h, final int x, final int y) {
		Vector3 d = this.w.mul(-1);
		double a = (double) w / (double) h;
		Point3 o = this.e.add(
				this.u.mul(a * s * ((x - ((w - 1) / 2.0)) / (w - 1)))).add(
				this.v.mul(s * ((y - ((h - 1) / 2.0)) / (h - 1))));
		return new Ray(o, d);
	}

	@Override
	public String toString() {
		return "OrthographicCamera [s=" + s + ", e=" + e + ", g=" + g + ", t="
				+ t + ", u=" + u + ", v=" + v + ", w=" + w + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(s);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrthographicCamera other = (OrthographicCamera) obj;
		if (Double.doubleToLongBits(s) != Double.doubleToLongBits(other.s))
			return false;
		return true;
	}
}