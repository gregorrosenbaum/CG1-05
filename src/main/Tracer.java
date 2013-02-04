package main;

import Materials.Material;
import object.Geometry;
import object.Hit;
import ray.Ray;
import color.Color;

/**
 * A Tracer
 * 
 * @author Johann Hofmann
 * @author Gregor Rosenbaum
 * @author Anton Krebs
 */

public class Tracer {
	
	/**
	 * The world that stores the objects we intersect.
	 */
	public final World world;
	
	protected Hit hit;
	// the protection mechanism that prevents recursion from happening more than maxDepth times
	protected int recursionCounter;
	public static final int maxDepth = 6;
	
	/**
	 * Constructor for a Tracer
	 * 
	 * @param world =
	 * 				The world that stores the objects we intersect.
	 * 
	 */

	public Tracer(World world) {
		this.world = world;
		this.recursionCounter = maxDepth;
		
	}
	
	
	/**
	 * This method returns a color for {@link Ray}
	 * @param r =
	 * 				
	 * 			{@link Ray}
	 * @returns color
	 */

	public Color colorFor(Ray r) {
		recursionCounter--;
		if (recursionCounter > 0) {
			hit = world.hit(r);
			if (hit != null) {
				// it is important that the recursionCounter is reset between the variable
				// allocation and the return
				// this makes sure we reset the counter AFTER the recursion
				Color color = hit.geo.material.colorFor(hit, world, this);
				recursionCounter = maxDepth;
				return color;
			}
		}
		recursionCounter = maxDepth;
		return world.backgroundColor;
	}
}
