package color;

/*
 * 
 * Represents a RGB Color with double values ranging from 0 to 1.
 * 
 * @author Johann Hofmann
 * @author Gregor Rosenbaum
 * @author Anton Krebs

 */

public class Color {

	/**
	 * red value.
	 */
	public final double r;
	/**
	 * green value.
	 */
	public final double g;
	/**
	 * blue value.
	 */
	public final double b;

	/**
	 * 
	 * Creates a new RGB-Color.
	 * 
	 * @param r
	 *            red
	 * @param g
	 *            green
	 * @param b
	 *            blue
	 */
	public Color(final double r, final double g, final double b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	/**
	 * adds two colors
	 * 
	 * @param c
	 *            color to add with
	 * @return a new color.
	 */
	public Color add(final Color c) {
		return new Color(r + c.r, g + c.g, b + c.b);
	}

	/**
	 * subtracts two colors
	 * 
	 * @param c
	 *            color to subtract with
	 * @return a new color.
	 */
	public Color sub(final Color c) {
		return new Color(r - c.r, g - c.g, b - c.b);
	}

	/**
	 * multiplies two colors
	 * 
	 * @param c
	 *            color to multiply with
	 * @return a new color.
	 */

	public Color mul(final Color c) {
		return new Color(r * c.r, g * c.g, b * c.b);
	}

	/**
	 * multiplies a color with a double.
	 * 
	 * @param d
	 *            the value to multiply with
	 * @return a new color.
	 */
	public Color mul(final double d) {
		return new Color(r * d, g * d, b * d);
	}

	/**
	 * Gives an int representing the hex-value of the color
	 * 
	 * @return an int representing the hex-value of the color
	 */
	public int toInt() {
		return ((int) (Math.min(r, 1) * 255) << 16) + ((int) (Math.min(g, 1) * 255) << 8) + (int) (Math.min(b, 1) * 255);
	}

	@Override
	public String toString() {
		return "Color [r=" + r + ", g=" + g + ", b=" + b + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(b);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(g);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(r);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Color other = (Color) obj;
		if (Double.doubleToLongBits(b) != Double.doubleToLongBits(other.b))
			return false;
		if (Double.doubleToLongBits(g) != Double.doubleToLongBits(other.g))
			return false;
		if (Double.doubleToLongBits(r) != Double.doubleToLongBits(other.r))
			return false;
		return true;
	}

}
