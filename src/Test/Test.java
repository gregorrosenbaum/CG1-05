package Test;

import transformation.Mat4x4;

public class Test {

	public static void main(String[] args) {
		Mat4x4 test = new Mat4x4(0, 4, 3, 1, 1, 3, 2, 4, 3, 1, 2, 1, 2, 2, 3, -2);
		Mat4x4 test2 = new Mat4x4(0, 4, 3, 1, 1, 3, 2, 4, 3, 1, 2, 1, 2, 2, 3, -2);
		System.out.println(test.x(test2));
	}
}
 