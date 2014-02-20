package main;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import object.AxisAlignedBox;
import object.Geometry;
import object.Node;
import object.Plane;
import object.Sphere;
import transformation.Transform;
import vectorlib.Normal3;
import vectorlib.Point3;
import vectorlib.Vector3;
import Cameras.PerspectiveCamera;
import Lightning.DirectionalLight;
import Lightning.Light;
import Lightning.PointLight;
import Lightning.Spotlight;
import Materials.LambertMaterial;
import Materials.ReflectiveMaterial;
import Materials.TransparentMaterial;
import color.Color;

/**
 * A small application that uses {@link ImageCanvas} and {@link ImageIO#write()}
 * to create an image and save it wherever the user decides.
 * 
 * @author Johann Hofmann
 * @author Gregor Rosenbaum
 * @author Anton Krebs
 */

public class ImageSaver {

	// uses ImageIO so save the BufferedImage to the path
	// the suffix is added without any checks if it is already specified in path
	// you could do some regex but that seemed like a bit overkill for a simple
	// application
	public static void saveImageToPng(final BufferedImage img, final String path)
			throws IOException {
		ImageIO.write(img, "png", new File(path + ".png"));
	}

	// same as above, with a file instead of a path
	public static void saveImageToPng(final BufferedImage img, final File file)
			throws IOException {
		saveImageToPng(img, file.getPath());
	}

	public static void main(String[] args) {

		// our application window's height and width
		final int WIDTH = 640;
		final int HEIGHT = 480;

		// our application window
		final JFrame myFrame = new JFrame("Image Saver");
		myFrame.setSize(WIDTH, HEIGHT);

		// ///////////////////////////// Testing Environment Start
		// //////////////////////////////

		// This is a simple test setup that is fast to render and easy to
		// comprehend
		// Use it to test work in progress

		Light[] lights = new Light[] {
		// new Spotlight(new Color(0.5, 0.5, 0.5), new Point3(-1, 4, -5), true,
		// new Vector3(0, -1, 0), Math.PI / 4),
		new PointLight(new Color(0.8, 0.8, 0.8), false, new Point3(-1, -1, -1)) };
		// new DirectionalLight(new Color(0.3, 0.3, 0.3), true, new Vector3(1,
		// 1, 1)) };

		Geometry[] objects = new Geometry[] { new Node(
				Transform.Scaling(1, 5, 1.5).translate(-1, -1, -1)
						.xRotate(Math.toRadians(0))
						.zRotate(Math.toRadians(90))
						.yRotate(Math.toRadians(0)),
				new Geometry[] { new Sphere(new ReflectiveMaterial(new Color(1,
						0, 0), new Color(1, 1, 1), 1, new Color(1, 0.5, 0.5))) },
				null) };
		PerspectiveCamera testCam = new PerspectiveCamera(new Point3(8, 8, 8),
				new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI / 4);

		// Light[] lights = new Light[] { new Spotlight(new Color(0.5, 0.5,
		// 0.5), new Point3(-1, 4, -5), true, new Vector3(0, -1, 0), Math.PI /
		// 4),
		// new PointLight(new Color(0.5, 0.5, 0.5), true, new Point3(0, 5, 0)),
		// new DirectionalLight(new Color(0.3, 0.3, 0.3), true, new Vector3(1,
		// 1, 1)) };
		//
		// Geometry[] objects = new Geometry[] {
		// new Node(Transform.Scaling(1, 2, 1).translate(3, 1, 1), new
		// Geometry[] { new Sphere(new TransparentMaterial(1)) }, null),
		// new Node(Transform.Scaling(1, 2, 1).translate(-3, 2,
		// 1).xRotate(Math.toRadians(90)), new Geometry[] { new Sphere(new
		// LambertMaterial(
		// new Color(0, 0, 1))) }, null),
		// new Plane(new LambertMaterial(new Color(0, 0, 1)), new Point3(0, -1,
		// 0), new Normal3(0, 1, 0)),
		// new Node(Transform.Scaling(1, 1, 1).translate(1, 1,
		// -5).yRotate(Math.toRadians(90)), new Geometry[] { new AxisAlignedBox(
		// new LambertMaterial(new Color(1, 0, 0))) }, null) };
		// PerspectiveCamera testCam = new PerspectiveCamera(new Point3(8, 8,
		// 8), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI / 4);

		// ///////////////////////////// Testing Environment End
		// //////////////////////////////

		// ///////////////////////////// AK Environment Start
		// //////////////////////////////

		// Geometry[] objects = new Geometry[] {
		// new Plane(new ReflectiveMaterial(new Color(1, 1, 1), new Color(1, 1,
		// 1), 10, new Color(1,
		// 1, 1)), new Point3(0, 0, 0), new Normal3(0,
		// 1, 0)),
		// new Sphere(new ReflectiveMaterial(new Color(1, 0, 0), new Color(1, 1,
		// 1), 10, new
		// Color(1, 0.5, 0.5)), new Point3(0, 0.5, 0), 0.5),
		// new Sphere(new ReflectiveMaterial(new Color(0, 1, 0), new Color(1, 1,
		// 1), 10, new
		// Color(1, 0.5, 0.5)), new Point3(-1.5, 0.5, 0), 0.5),
		// new Sphere(new ReflectiveMaterial(new Color(0, 0, 1), new Color(1, 1,
		// 1), 10, new
		// Color(1, 0.5, 0.5)), new Point3(1.5, 0.5, 0), 0.5),
		// new Sphere(new ReflectiveMaterial(new Color(0, 1, 1), new Color(1, 1,
		// 1), 10, new
		// Color(1, 0.5, 0.5)), new Point3(0, 0.5, -1.5), 0.5),
		// new Sphere(new ReflectiveMaterial(new Color(1, 0, 1), new Color(1, 1,
		// 1), 10, new
		// Color(1, 0.5, 0.5)), new Point3(-1.5, 0.5, -1.5),
		// 0.5),
		// new Sphere(new ReflectiveMaterial(new Color(1, 1, 0), new Color(1, 1,
		// 1), 10, new
		// Color(1, 0.5, 0.5)), new Point3(1.5, 0.5, -1.5),
		// 0.5),
		// new Sphere(new TransparentMaterial(1.33), new Point3(0, 2, 1.5),
		// 0.5),
		// new Sphere(new TransparentMaterial(1.33), new Point3(-1.5, 2, 1.5),
		// 0.5),
		// new Sphere(new TransparentMaterial(1.33), new Point3(1.5, 2, 1.5),
		// 0.5),
		// new AxisAlignedBox(new TransparentMaterial(1.33), new Point3(-0.5, 0,
		// 3), new Point3(0.5,
		// 1, 4)),
		// new Triangle(new PhongMaterial(new Color(0, 1, 0), new Color(0, 1,
		// 0), 20), new
		// Point3(0.7, 0.5, 3), new Point3(1.3, 0.5, 3),
		// new Point3(0.7, 0.5, 4)) };
		//
		// Light[] lights = new Light[] { new Spotlight(new Color(0.3, 0.3,
		// 0.3), new Point3(0, 5,
		// -10), true, new Vector3(0, -1, 0), Math.PI / 8),
		// new DirectionalLight(new Color(0.3, 0.3, 0.3), true, new Vector3(1,
		// -1, 0)),
		// new PointLight(new Color(0.3, 0.3, 0.3), true, new Point3(5, 5, -10))
		// };
		//
		// PerspectiveCamera testCam = new PerspectiveCamera(new Point3(8, 8,
		// 8), new Vector3(-1,
		// -1, -1), new Vector3(0, 1, 0), Math.PI / 4);

		// ///////////////////////////// AK Environment End
		// //////////////////////////////

		World testWorld = new World(new Color(0.1, 0.1, 0.3), objects, lights,
				new Color(0.1, 0.1, 0.1), 1);
		final RayTracer canvas = new RayTracer(WIDTH, HEIGHT, testWorld,
				testCam);
		myFrame.add(canvas);

		// we add a Listener to adjust the image when the user resizes the
		// window
		myFrame.addComponentListener(new ComponentListener() {

			@Override
			public void componentHidden(ComponentEvent e) {
				// not used but necessary for the Listener
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// not used but necessary for the Listener
			}

			@Override
			public void componentResized(ComponentEvent e) {
				// sets width and height of our image(canvas) to the dimensions
				// of the frame
				canvas.setWidth(myFrame.getWidth());
				canvas.setHeight(myFrame.getHeight());
			}

			@Override
			public void componentShown(ComponentEvent e) {
				// not used but necessary for the Listener
			}
		});

		// adds our Menu Datei->Speichern to save the file

		final JMenuBar menuBar = new JMenuBar();
		myFrame.setJMenuBar(menuBar);

		final JMenu file = new JMenu("Datei");
		menuBar.add(file);

		final JMenuItem saveFile = new JMenuItem("Speichern");
		file.add(saveFile);

		// the dialog to select the save destination
		final JFileChooser fileDialog = new JFileChooser();

		// listens if the menu item was clicked
		saveFile.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// not used but necessary for the Listener
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// not used but necessary for the Listener
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// not used but necessary for the Listener
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// not used but necessary for the Listener
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// if the filedialog returns that the user clicked "ok"
				if (fileDialog.showSaveDialog(myFrame) == JFileChooser.APPROVE_OPTION) {
					try {
						saveImageToPng(canvas.getImage(),
								fileDialog.getSelectedFile());
					} catch (IOException exception) {
						// messagebox alerts the user in case of an error
						JOptionPane
								.showMessageDialog(myFrame,
										"Fehler bei Dateiauswahl. Datei nicht gespeichert.");
					}
				}

			}
		});

		// shows the application
		myFrame.setVisible(true);

	}
}
