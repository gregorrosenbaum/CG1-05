package transformation;

public class Transform {

	public final Mat4x4 m;
	public final Mat4x4 i;

	private Transform(Mat4x4 m, Mat4x4 i) {
		this.m = m;
		this.i = i;
	}

	public static Transform Translation(double x, double y, double z) {
		return null;
	}

	public static Transform Scaling(double sx, double sy, double sz) {
		return null;
	}

	public static Transform XRotation(double angle) {
		return null;
	}

	public static Transform YRotation(double angle) {
		return null;
	}

	public static Transform ZRotation(double angle) {
		return null;
	}

	public Transform translate(double x, double y, double z) {
		return null;
	}

	public Transform scale(double sx, double sy, double sz) {
		return null;
	}

	public Transform xRotate(double angle) {
		return null;
	}

	public Transform yRotate(double angle) {
		return null;
	}

	public Transform zRotate(double angle) {
		return null;
	}

}
