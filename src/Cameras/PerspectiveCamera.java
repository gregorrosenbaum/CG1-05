package Cameras;

import ray.Ray;
import vectorlib.Point3;
import vectorlib.Vector3;

/**
 * 
 * A perspective camera.
 * 
 * @author Johann Hofmann
 * @author Gregor Rosenbaum
 * @author Anton Krebs
 * 
 */
public class PerspectiveCamera extends Camera {

	/**
	 * opening angle.
	 */
	public final double angle;

	/**
	 * Creates a new perspective camera.
	 * 
	 * @param e
	 *            eye position.
	 * @param g
	 *            gaze direction
	 * @param t
	 *            up vector.
	 * @param angle
	 *            opening angle.
	 */
	public PerspectiveCamera(final Point3 e, final Vector3 g, final Vector3 t, final double angle) {
		super(e, g, t);
		this.angle = angle;
	}

	@Override
	public Ray rayFor(final int w, final int h, final int x, final int y) {
		Point3 o = this.e;
		Vector3 r = this.w.mul(-1).mul((h / 2.0) / Math.tan(angle / 2.0)).add(u.mul(x - ((w - 1) / 2.0))).add(v.mul(y - ((h - 1) / 2.0)));
		Vector3 d = r.mul(1.0 / r.magnitude);
		return new Ray(o, d);
	}

	@Override
	public String toString() {
		return "PerspectiveCamera [angle=" + angle + ", e=" + e + ", g=" + g + ", t=" + t + ", u=" + u + ", v=" + v + ", w=" + w + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(angle);
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
		PerspectiveCamera other = (PerspectiveCamera) obj;
		if (Double.doubleToLongBits(angle) != Double.doubleToLongBits(other.angle))
			return false;
		return true;
	}
}
