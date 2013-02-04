package tests;

import ray.Ray;
import vectorlib.Point3;
import vectorlib.Vector3;
import Cameras.OrthographicCamera;
import Cameras.PerspectiveCamera;

public class CamUnit {

	public static void main(String[] args) {
		PerspectiveCamera testCam = new PerspectiveCamera(new Point3(0, 0, 0), new Vector3(0, 0,-1), new Vector3(0,1,0), Math.PI / 4);
		OrthographicCamera testCam2 = new OrthographicCamera(new Point3(0, 0, 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), 3);
		Ray testRay1 = testCam.rayFor(1024, 768, 300, 400);
		Ray testRay2 = testCam2.rayFor(1024, 768, 300, 400);
		return;
	}

}
