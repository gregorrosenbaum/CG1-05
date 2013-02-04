package object;

import ray.Ray;
import util.Eps;
import vectorlib.Normal3;
import vectorlib.Point3;
import Materials.Material;

/**
 * An axis aligned box.
 * 
 * @author Johann Hofmann
 * @author Gregor Rosenbaum
 * @author Anton Krebs
 */

public class AxisAlignedBox extends Geometry {

	/**
	 * Point 1
	 */
	public final Point3 lbf;
	/**
	 * Point 2
	 */
	public final Point3 run;

	/**
	 * Creates a new axis aligned box.
	 * 
	 * @param material
	 *            the color.
	 * @param point3
	 *            Point 1.
	 * @param run
	 *            Point 2.
	 */

	public AxisAlignedBox(final Material material, final Point3 lbf, final Point3 run) {
		super(material);
		this.lbf = lbf;
		this.run = run;
	}

	@Override
	public Hit hit(final Ray r) {

		double tx_min;
		double ty_min;
		double tz_min;
		double tx_max;
		double ty_max;
		double tz_max;

		double a = 1.0 / r.d.x;
		if (a >= Eps.ylon) {
			tx_min = (lbf.x - r.o.x) * a;
			tx_max = (run.x - r.o.x) * a;
		} else {
			tx_max = (lbf.x - r.o.x) * a;
			tx_min = (run.x - r.o.x) * a;
		}

		double b = 1.0 / r.d.y;
		if (b >= Eps.ylon) {
			ty_min = (lbf.y - r.o.y) * b;
			ty_max = (run.y - r.o.y) * b;
		} else {
			ty_max = (lbf.y - r.o.y) * b;
			ty_min = (run.y - r.o.y) * b;
		}

		double c = 1.0 / r.d.z;
		if (c >= Eps.ylon) {
			tz_min = (lbf.z - r.o.z) * c;
			tz_max = (run.z - r.o.z) * c;
		} else {
			tz_max = (lbf.z - r.o.z) * c;
			tz_min = (run.z - r.o.z) * c;
		}

		double t0, t1;

		int face_in, face_out;

		if (tx_min > ty_min) {
			t0 = tx_min;
			face_in = (a >= 0.0) ? 0 : 3;
		} else {
			t0 = ty_min;
			face_in = (b >= 0.0) ? 1 : 4;
		}

		if (tz_min > t0) {
			t0 = tz_min;
			face_in = (c >= 0.0) ? 2 : 5;
		}

		if (tx_max < ty_max) {
			t1 = tx_max;
			face_out = (a >= 0.0) ? 3 : 0;
		} else {
			t1 = ty_max;
			face_out = (b >= 0.0) ? 4 : 1;
		}

		if (tz_max < t1) {
			t1 = tz_max;
			face_out = (c >= 0.0) ? 5 : 2;
		}

		if (t0 < t1 && t1 > Eps.ylon) {
			if (t0 > Eps.ylon) {
				return new Hit(t0, r, this, getNormal(face_in));
			} else {
				return new Hit(t1, r, this, getNormal(face_out));
			}
		}
		return null;
	}

	private Normal3 getNormal(final int face) {
		switch (face) {
			case 0:
				return new Normal3(-1, 0, 0);
			case 1:
				return new Normal3(0, -1, 0);
			case 2:
				return new Normal3(0, 0, -1);
			case 3:
				return new Normal3(1, 0, 0);
			case 4:
				return new Normal3(0, 1, 0);
			case 5:
				return new Normal3(0, 0, 1);
		}
		return null;
	}

	@Override
	public String toString() {
		return "AxisAlignedBox [lbf=" + lbf + ", run=" + run + ", color=" + material + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lbf == null) ? 0 : lbf.hashCode());
		result = prime * result + ((run == null) ? 0 : run.hashCode());
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
		AxisAlignedBox other = (AxisAlignedBox) obj;
		if (lbf == null) {
			if (other.lbf != null)
				return false;
		} else if (!lbf.equals(other.lbf))
			return false;
		if (run == null) {
			if (other.run != null)
				return false;
		} else if (!run.equals(other.run))
			return false;
		return true;
	}

}