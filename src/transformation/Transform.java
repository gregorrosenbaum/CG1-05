package transformation;

public class Transform {

	public final Mat4x4 m;
	public final Mat4x4 i;

	private Transform(Mat4x4 m, Mat4x4 i) {
		this.m = m;
		this.i = i;
	}

	public static Transform Translation(double x, double y, double z) {
		return new Transform(new Mat4x4(1, 0, 0, x, 0, 1, 0, y, 0, 0, 1, z, 0, 0, 0, 1),
				new Mat4x4(1, 0, 0, -x, 0, 1, 0, -y, 0, 0, 1, -z, 0, 0, 0, 1));
	}

	public static Transform Scaling(double sx, double sy, double sz) {
		return new Transform(new Mat4x4(sx, 0, 0, 0, 0, sy, 0, 0, 0, 0, sz, 0, 0, 0, 0, 1), new Mat4x4(1 / sx, 0, 0, 0, 0, 1 / sy, 0, 0, 0, 0,
				1 / sz, 0, 0, 0, 0, 1));
	}

	public static Transform XRotation(double angle) {
		return new Transform(new Mat4x4(1, 0, 0, 0, 0, Math.cos(angle), -Math.sin(angle), 0, 0, Math.sin(angle), Math.cos(angle), 0, 0, 0, 0, 1),
				new Mat4x4(1, 0, 0, 0, 0, Math.cos(angle), Math.sin(angle), 0, 0, -Math.sin(angle), Math.cos(angle), 0, 0, 0, 0, 1));
	}

	public static Transform YRotation(double angle) {
		return new Transform(new Mat4x4(Math.cos(angle), 0, Math.sin(angle), 0, 0, 1, 0, 0, -Math.sin(angle), 0, Math.cos(angle), 0, 0, 0, 0, 1),
				new Mat4x4(Math.cos(angle), 0, -Math.sin(angle), 0, 0, 1, 0, 0, Math.sin(angle), 0, Math.cos(angle), 0, 0, 0, 0, 1));
	}

	public static Transform ZRotation(double angle) {
		return new Transform(new Mat4x4(Math.cos(angle), -Math.sin(angle), 0, 0, Math.sin(angle), Math.cos(angle), 0, 0, 0, 0, 1, 0, 0, 0, 0, 1),
				new Mat4x4(Math.cos(angle), Math.sin(angle), 0, 0, -Math.sin(angle), Math.cos(angle), 0, 0, 0, 0, 1, 0, 0, 0, 0, 1));
	}

	public Transform translate(double x, double y, double z) {
		return new Transform(m.x(new Mat4x4(1, 0, 0, x, 0, 1, 0, y, 0, 0, 1, z, 0, 0, 0, 1)), i.x(new Mat4x4(1, 0, 0, -x, 0, 1, 0, -y, 0, 0, 1, -z,
				0, 0, 0, 1)));
	}

	public Transform scale(double sx, double sy, double sz) {
		return new Transform(m.x(new Mat4x4(sx, 0, 0, 0, 0, sy, 0, 0, 0, 0, sz, 0, 0, 0, 0, 1)), i.x(new Mat4x4(1 / sx, 0, 0, 0, 0, 1 / sy, 0, 0, 0,
				0, 1 / sz, 0, 0, 0, 0, 1)));
	}

	public Transform xRotate(double angle) {
		return new Transform(
				m.x(new Mat4x4(1, 0, 0, 0, 0, Math.cos(angle), -Math.sin(angle), 0, 0, Math.sin(angle), Math.cos(angle), 0, 0, 0, 0, 1)),
				i.x(new Mat4x4(1, 0, 0, 0, 0, Math.cos(angle), Math.sin(angle), 0, 0, -Math.sin(angle), Math.cos(angle), 0, 0, 0, 0, 1)));
	}

	public Transform yRotate(double angle) {
		return new Transform(
				m.x(new Mat4x4(Math.cos(angle), 0, Math.sin(angle), 0, 0, 1, 0, 0, -Math.sin(angle), 0, Math.cos(angle), 0, 0, 0, 0, 1)),
				i.x(new Mat4x4(Math.cos(angle), 0, -Math.sin(angle), 0, 0, 1, 0, 0, Math.sin(angle), 0, Math.cos(angle), 0, 0, 0, 0, 1)));
	}

	public Transform zRotate(double angle) {
		return new Transform(
				m.x(new Mat4x4(Math.cos(angle), -Math.sin(angle), 0, 0, Math.sin(angle), Math.cos(angle), 0, 0, 0, 0, 1, 0, 0, 0, 0, 1)),
				i.x(new Mat4x4(Math.cos(angle), Math.sin(angle), 0, 0, -Math.sin(angle), Math.cos(angle), 0, 0, 0, 0, 1, 0, 0, 0, 0, 1)));
	}

}
