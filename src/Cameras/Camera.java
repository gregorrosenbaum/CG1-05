package Cameras;

import ray.Ray;
import vectorlib.Point3;
import vectorlib.Vector3;

/**
 * Abstract Superclass for all the cameras we are going to create. For example
 * {@link OrthographicCamera} or {@link PerspectiveCamera}.
 * 
 * @author Johann Hofmann
 * @author Gregor Rosenbaum
 * @author Anton Krebs
 */

public abstract class Camera {
	/**
	 * eye position.
	 */
	public final Point3 e;
	/**
	 * gaze direction.
	 */
	public final Vector3 g;
	/**
	 * up vector.
	 */
	public final Vector3 t;
	/**
	 * u-axis of the new coordinate system.
	 */
	public final Vector3 u;
	/**
	 * v-axis of the new coordinate system.
	 */
	public final Vector3 v;
	/**
	 * w-axis of the new coordinate system.
	 */
	public final Vector3 w;

	/**
	 * Creates a Camera in 3-dimensional space.
	 * 
	 * @param e
	 *            eye position.
	 * @param g
	 *            gaze direction.
	 * @param t
	 *            up vector.
	 */
	public Camera(final Point3 e, final Vector3 g, final Vector3 t) {
		this.e = e;
		this.g = g;
		this.t = t;
		this.w = g.mul(1 / g.magnitude).mul(-1);
		this.u = t.x(w).mul(1.0 / t.x(w).magnitude);
		this.v = w.x(u);
	}

	/**
	 * 
	 * Casts a ray for the selected size and pixel.
	 * 
	 * @param w
	 *            width of the image.
	 * @param h
	 *            height of the image.
	 * @param x
	 *            x-coordinate of the pixel.
	 * @param y
	 *            y-coordinate of the pixel.
	 * @return a Ray object containing the calculated ray.
	 */
	public abstract Ray rayFor(final int w, final int h, final int x,
			final int y);

	@Override
	public String toString() {
		return "Camera [e=" + e + ", g=" + g + ", t=" + t + ", u=" + u + ", v="
				+ v + ", w=" + w + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((e == null) ? 0 : e.hashCode());
		result = prime * result + ((g == null) ? 0 : g.hashCode());
		result = prime * result + ((t == null) ? 0 : t.hashCode());
		result = prime * result + ((u == null) ? 0 : u.hashCode());
		result = prime * result + ((v == null) ? 0 : v.hashCode());
		result = prime * result + ((w == null) ? 0 : w.hashCode());
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
		Camera other = (Camera) obj;
		if (e == null) {
			if (other.e != null)
				return false;
		} else if (!e.equals(other.e))
			return false;
		if (g == null) {
			if (other.g != null)
				return false;
		} else if (!g.equals(other.g))
			return false;
		if (t == null) {
			if (other.t != null)
				return false;
		} else if (!t.equals(other.t))
			return false;
		if (u == null) {
			if (other.u != null)
				return false;
		} else if (!u.equals(other.u))
			return false;
		if (v == null) {
			if (other.v != null)
				return false;
		} else if (!v.equals(other.v))
			return false;
		if (w == null) {
			if (other.w != null)
				return false;
		} else if (!w.equals(other.w))
			return false;
		return true;
	}

}
