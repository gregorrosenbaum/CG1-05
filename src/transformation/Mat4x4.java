package transformation;

import vectorlib.Normal3;
import vectorlib.Point3;
import vectorlib.Vector3;

public class Mat4x4 {

	public final double m11;
	public final double m12;
	public final double m13;
	public final double m14;
	public final double m21;
	public final double m22;
	public final double m23;
	public final double m24;
	public final double m31;
	public final double m32;
	public final double m33;
	public final double m34;
	public final double m41;
	public final double m42;
	public final double m43;
	public final double m44;

	public Mat4x4(final double m11, final double m12, final double m13, final double m14, final double m21, final double m22, final double m23,
			final double m24, final double m31, final double m32, final double m33, final double m34, final double m41, final double m42,
			final double m43, final double m44) {

		this.m11 = m11;
		this.m12 = m12;
		this.m13 = m13;
		this.m14 = m14;
		this.m21 = m21;
		this.m22 = m22;
		this.m23 = m23;
		this.m24 = m24;
		this.m31 = m31;
		this.m32 = m32;
		this.m33 = m33;
		this.m34 = m34;
		this.m41 = m41;
		this.m42 = m42;
		this.m43 = m43;
		this.m44 = m44;

	}

	public Mat4x4 transpond() {
		return new Mat4x4(m11, m21, m31, m41, m12, m22, m32, m42, m13, m23, m33, m43, m14, m24, m34, m44);
	}

	@Override
	public String toString() {
		return m11 + " " + m12 + " " + m13 + " " + m14 + " " + m21 + " " + m22 + " " + m23 + " " + m24 + " " + m31 + " " + m32 + " " + m33 + " "
				+ m34 + " " + m41 + " " + m42 + " " + m43 + " " + m44 + "]";
	}

	public Normal3 x(Normal3 n) {
		return new Normal3(m11 * n.x + m12 * n.y + m13 * n.z, m21 * n.x + m22 * n.y + m23 * n.z, m31 * n.x + m32 * n.y + m33 * n.z);
	}

	public Vector3 x(Vector3 v) {
		return new Vector3(m11 * v.x + m12 * v.y + m13 * v.z, m21 * v.x + m22 * v.y + m23 * v.z, m31 * v.x + m32 * v.y + m33 * v.z);
	}

	public Point3 x(Point3 p) {
		return new Point3(m11 * p.x + m12 * p.y + m13 * p.z + m14 * 1, m21 * p.x + m22 * p.y + m23 * p.z + m24 * 1, m31 * p.x + m32 * p.y + m33 * p.z
				+ m34 * 1);
	}

	public Mat4x4 x(Mat4x4 m) {
		return new Mat4x4(m11 * m.m11 + m12 * m.m21 + m13 * m.m31 + m14 * m.m41, m11 * m.m12 + m12 * m.m22 + m13 * m.m32 + m14 * m.m42, m11 * m.m13
				+ m12 * m.m23 + m13 * m.m33 + m14 * m.m43, m11 * m.m14 + m12 * m.m24 + m13 * m.m34 + m14 * m.m44, m21 * m.m11 + m22 * m.m21 + m23
				* m.m31 + m24 * m.m41, m21 * m.m12 + m22 * m.m22 + m23 * m.m32 + m24 * m.m42, m21 * m.m13 + m22 * m.m23 + m23 * m.m33 + m24 * m.m43,
				m21 * m.m14 + m22 * m.m24 + m23 * m.m34 + m24 * m.m44, m31 * m.m11 + m32 * m.m21 + m33 * m.m31 + m34 * m.m41, m31 * m.m12 + m32
						* m.m22 + m33 * m.m32 + m34 * m.m42, m31 * m.m13 + m32 * m.m23 + m33 * m.m33 + m34 * m.m43, m31 * m.m14 + m32 * m.m24 + m33
						* m.m34 + m34 * m.m44, m41 * m.m11 + m42 * m.m21 + m43 * m.m31 + m44 * m.m41, m41 * m.m12 + m42 * m.m22 + m43 * m.m32 + m44
						* m.m42, m41 * m.m13 + m42 * m.m23 + m43 * m.m33 + m44 * m.m43, m41 * m.m14 + m42 * m.m24 + m43 * m.m34 + m44 * m.m44);
	}
}
