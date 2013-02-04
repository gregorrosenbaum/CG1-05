package object;

import ray.Ray;
import transformation.Transform;
import Materials.Material;

public class Node extends Geometry {

	public final Transform transformation;
	public final Geometry[] geometries;

	public Node(Transform transformation, Geometry[] geometries, Material material) {
		super(material);
		this.transformation = transformation;
		this.geometries = geometries;
	}

	@Override
	public Hit hit(final Ray r) {
		Ray ray = new Ray(transformation.i.x(r.o), transformation.i.x(r.d));
		Hit temp = null;
		for (Geometry element : geometries) {
			Hit h = element.hit(ray);
			if (temp == null) {
				temp = h;
			}
			if (temp != null && h != null) {
				if (temp.t > h.t)
					temp = h;
			}
		}
		if (temp != null) {
			return new Hit(temp.t, r, temp.geo, transformation.i.transpond().x(temp.normal));
		}
		return null;
	}
}
