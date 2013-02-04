package object;

import ray.Ray;
import Materials.Material;

public class Node extends Geometry {

	public Node(Material material) {
		super(material);
	}

	@Override
	public Hit hit(Ray r) {
		return null;
	}

}
