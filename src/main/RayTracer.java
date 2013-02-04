package main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Cameras.Camera;

/**
 * 
 * A RayTracer
 * 
 * @author Johann Hofmann
 * @author Gregor Rosenbaum
 * @author Anton Krebs
 */

public class RayTracer extends Canvas {

	private static final long serialVersionUID = 5555522598959749695L;

	/**
	 * The image that is created.
	 */
	protected BufferedImage img;
	/**
	 * Width of the image.
	 */
	protected int width;
	/**
	 * Heigth of the image.
	 */
	protected int height;
	/**
	 * The world that stores the objects we intersect.
	 */
	protected World world;
	/**
	 * The camera to cast our rays.
	 */
	protected Camera cam;

	protected Tracer tracer;

	/**
	 * 
	 * Creates a new Raytracer.
	 * 
	 * @param width
	 *            Width of the image.
	 * @param height
	 *            Heigth of the image.
	 * @param world
	 *            The world that stores the objects we intersect.
	 * @param cam
	 *            The camera to cast our rays.
	 */
	public RayTracer(final int width, final int height, final World world, final Camera cam) {
		this.world = world;
		this.width = width;
		this.height = height;
		this.cam = cam;
		this.tracer = new Tracer(world);
	}

	@Override
	public void paint(final Graphics g) {
		// erzeugt ein neues BufferedImage mit dem Farbtyp INT RGB
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				// setzt einen Punkt des Rasters mit den Koordinaten x,x auf die
				// berechnete Farbe
				img.getRaster().setDataElements(x, height - y - 1,
						img.getColorModel().getDataElements((tracer.colorFor((cam.rayFor(width, height, x, y))).toInt()), null));
			}
		}

		g.drawImage(img, 0, 0, null);
	}

	@Override
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the width of the image. Redraws the {@link RayTracer} for the changes to show.
	 * 
	 * @param width
	 *            the width of the image.
	 */
	public void setWidth(final int width) {
		this.width = width;
		repaint();
	}

	@Override
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the height of the image. Redraws the {@link RayTracer} for the changes to show.
	 * 
	 * @param height
	 *            the height of the image.
	 */
	public void setHeight(final int height) {
		this.height = height;
		repaint();
	}

	/**
	 * Gets the BufferedImage in the {@link RayTracer}.
	 * 
	 * @return the {@link BufferedImage} in the {@link RayTracer}.
	 */
	public BufferedImage getImage() {
		return img;
	}

	@Override
	public String toString() {
		return "RayTracer [img=" + img + ", width=" + width + ", height=" + height + ", world=" + world + ", cam=" + cam + "]";
	}

}