package object;

import ray.Ray;
import util.Eps;
import vectorlib.Mat3x3;
import vectorlib.Normal3;
import vectorlib.Point3;
import vectorlib.Vector3;
import Materials.Material;
import color.Color;

/**
 * Constructs a {@link Geometry} Triangle with three {@link Point3}.
 * 
 * @param a
 *            = First {@link Point3} to construct the Triangle.
 * @param b
 *            = Second {@link Point3} to construct the Triangle.
 * @param c
 *            = Third {@link Point3} to construct the Triangle.
 * 
 * @author Johann Hofmann
 * @author Gregor Rosenbaum
 * @author Anton Krebs
 * 
 */

public class Triangle extends Geometry {

	public final Point3 a;
	public final Point3 b;
	public final Point3 c;

	/**
	 * Constructs a {@link Geometry} Triangle with three {@link Point3}.
	 * 
	 * @param material
	 *            = {@link Color} of the Triangle.
	 * @param a
	 *            = First {@link Point3} to construct the Triangle.
	 * @param b
	 *            = Second {@link Point3} to construct the Triangle.
	 * @param c
	 *            = Third {@link Point3} to construct the Triangle.
	 */
	public Triangle(final Material material, final Point3 a, final Point3 b, final Point3 c) {
		super(material);
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	public Hit hit(final Ray r) {
		Mat3x3 A = new Mat3x3(a.x - b.x, a.x - c.x, r.d.x, a.y - b.y, a.y - c.y, r.d.y, a.z - b.z, a.z - c.z, r.d.z);
		Vector3 x = new Vector3(a.x - r.o.x, a.y - r.o.y, a.z - r.o.z);
		Mat3x3 A1 = A.changeCol1(x);
		Mat3x3 A2 = A.changeCol2(x);
		Mat3x3 A3 = A.changeCol3(x);
		double beta = A1.determinant / A.determinant;
		double gamma = A2.determinant / A.determinant;
		double t = A3.determinant / A.determinant;
// 		Choose one point and from this point creates 2 vectors
//		dot product of this 2 vector and set the result as normal
		Vector3 v1 = new Vector3 (b.x - a.x, b.y - a.y, b.z - a.z);
		Vector3 v2 = new Vector3 (c.x - a.x, c.y - a.y, c.z - a.z);
		Vector3 direction = (v2.x(v1));
		Normal3 norm = direction.asNormal();
		if (t > Eps.ylon && Eps.ylon <= beta && Eps.ylon <= gamma && beta + gamma <= 1) {
			return new Hit(t, r, this, norm);
		}
		return null;
	}

	@Override
	public String toString() {
		return "Triangle [a=" + a + ", b=" + b + ", c=" + c + ", material=" + material + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((b == null) ? 0 : b.hashCode());
		result = prime * result + ((c == null) ? 0 : c.hashCode());
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
		Triangle other = (Triangle) obj;
		if (a == null) {
			if (other.a != null)
				return false;
		} else if (!a.equals(other.a))
			return false;
		if (b == null) {
			if (other.b != null)
				return false;
		} else if (!b.equals(other.b))
			return false;
		if (c == null) {
			if (other.c != null)
				return false;
		} else if (!c.equals(other.c))
			return false;
		return true;
	}
}
