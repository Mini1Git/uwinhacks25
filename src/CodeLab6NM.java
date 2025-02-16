/* Copyright material by xyuan@uwindsor.ca,
 * for students working on assignments and projects */

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jogamp.java3d.Alpha;
import org.jogamp.java3d.BranchGroup;
import org.jogamp.java3d.Canvas3D;
import org.jogamp.java3d.Transform3D;
import org.jogamp.java3d.TransformGroup;
import org.jogamp.java3d.utils.universe.SimpleUniverse;
import org.jogamp.vecmath.Point3d;

import org.jogamp.java3d.*;

import org.jogamp.java3d.Appearance;
import org.jogamp.java3d.ImageComponent2D;
import org.jogamp.java3d.Link;
import org.jogamp.java3d.PolygonAttributes;
import org.jogamp.java3d.Shape3D;
import org.jogamp.java3d.TexCoordGeneration;
import org.jogamp.java3d.Texture2D;
import org.jogamp.java3d.TextureAttributes;
import org.jogamp.java3d.TriangleStripArray;
import org.jogamp.java3d.utils.image.TextureLoader;
import org.jogamp.vecmath.Point3f;
import org.jogamp.vecmath.Vector3d;
import org.jogamp.vecmath.Vector3f;

import org.jogamp.java3d.Node;

import org.jogamp.java3d.GeometryArray;
import org.jogamp.java3d.LineArray;
import org.jogamp.vecmath.Color3f;

public class CodeLab6NM extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	
	private static String frame_name = "NM's Lab #6";
	private static boolean r_tag = false;
	private static final String OBJECT_NAME = "Textured Disk";
	
	/* a function to build and return the content branch */
	private static BranchGroup create_Scene() {
		BranchGroup sceneBG = new BranchGroup();

		TransformGroup sceneTG = new TransformGroup();     // introduce a TransformGroup for rotation 
		sceneBG.addChild(CommonsNM.rotate_Behavior(7500, sceneTG));

		String[] side_name = {"Top", "Side", "JWST"};              // create disk sides
		sceneTG.addChild(L5TextureSurfaceNM.ring_Shape(side_name[1], 4));

		TransformGroup topTG = new TransformGroup();      // need 'topTG' to move the (top) surface
		topTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		topTG.addChild(L5TextureSurfaceNM.ring_Shape(side_name[0], 4));

		Transform3D axisPosition = new Transform3D();
		axisPosition.rotY(-Math.PI / 2.0);                 // need to move along X-axis
		sceneTG.addChild(topTG);
		
		TransformGroup botTG = new TransformGroup();      // need 'topTG' to move the (top) surface
		botTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		botTG.addChild(L5TextureSurfaceNM.ring_Shape(side_name[2], 4));

		Transform3D axPos = new Transform3D();
		axPos.rotY(Math.PI / 2.0);                 // need to move along X-axis
		sceneTG.addChild(botTG);
		
		CommonsNM.control_Rotation(r_tag);                 // make 'sceneBG' rotating by default
		sceneBG.addChild(sceneTG);
		
		return sceneBG;  
	}

	/* a constructor to set up for the application */
	public CodeLab6NM(BranchGroup scene) {
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		Canvas3D canvas3D = new Canvas3D(config);
		canvas3D.setSize(800, 800);                        // set size of canvas
		SimpleUniverse su = new SimpleUniverse(canvas3D);  // create a SimpleUniverse
												   // set the viewer's location
		CommonsNM.define_Viewer(su, new Point3d(0, 0, 10.0));
		
		scene.compile();			                           // optimize the BranchGroup
		su.addBranchGraph(scene);                          // attach 'scene' to 'su'

		Menu m = new Menu("Menu");                         // set menu's label
		m.addActionListener(this);
		MenuBar menuBar = CodeLab2NM.build_MenuBar(m, OBJECT_NAME);
		frame.setMenuBar(menuBar);                         // build and set the menu bar

		setLayout(new BorderLayout());
		add("Center", canvas3D);
		frame.setSize(810, 800);                           // set the size of the frame
		frame.setVisible(true);
	}

	public static void main(String[] args) {               // NOTE: copyright material 
		frame = new JFrame(frame_name + ": moving textured disks");
		frame.getContentPane().add(new CodeLab6NM(create_Scene()));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	} 	

	@Override
	public void actionPerformed(ActionEvent e) {        
		switch(e.getActionCommand()) {                     // handle different menu items
		case "Exit": 
			System.exit(0);                                // quit the application
		case "Pause/Rotate":
			r_tag = !r_tag;
			CommonsNM.control_Rotation(r_tag);
			return;
		default:
			return;
		}
	}    
}

class CommonsNM extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	
	private static Alpha rotationAlpha;                    // use for rotation behavior
	
	public final static Color3f White = new Color3f(1.0f, 1.0f, 1.0f);
	public final static Color3f Red = new Color3f(1.0f, 0.0f, 0.0f);
	public final static Color3f Orange = new Color3f(1.0f, 0.6f, 0.0f);
	public final static Color3f Yellow = new Color3f(1.0f, 1.0f, 0.0f);
	public final static Color3f Green = new Color3f(0.0f, 1.0f, 0.0f);
	public final static Color3f Blue = new Color3f(0.0f, 0.0f, 1.0f);
	public final static Color3f Cyan = new Color3f(0.0f, 1.0f, 1.0f);
	public final static Color3f Magenta = new Color3f(1.0f, 0.0f, 1.0f);
	public final static Color3f Purple = new Color3f(0.5f, 0.0f, 0.5f);
	public final static Color3f Grey = new Color3f(0.35f, 0.35f, 0.35f);
	public final static Color3f Black = new Color3f(0.0f, 0.0f, 0.0f);
	public final static Color3f Lime = new Color3f(0.0f, 1.0f, 0.5f);
	public static Color3f[] list_clrs = {White, Red, Orange, Yellow, Green, Blue, Purple, Black};

	public final static BoundingSphere hundred_BS = new BoundingSphere(new Point3d(), 100.0);
	public final static BoundingSphere twenty_BS = new BoundingSphere(new Point3d(), 20.0);

	/* a function to create and return material definition */
	public static Material set_Material(Color3f m_clr) {
		Material mtl = new Material();
		mtl.setShininess(32);                              // try 10 or 128
		mtl.setAmbientColor(White);
		mtl.setDiffuseColor(m_clr);
		mtl.setSpecularColor(Grey);
		mtl.setEmissiveColor(Black);                       // non-emissive
		mtl.setLightingEnable(true);
		return mtl;
	}
	
    /* a function to set appearance with provided color ('clr') and predefined material */
	public static Appearance set_Appearance(Color3f clr) {		
		Appearance app = new Appearance();
		app.setMaterial(set_Material(clr));                // set appearance's material
		return app;
	}	

	/* a function to create a rotation behavior and refer it to 'rot_TG' */
	public static RotationInterpolator rotate_Behavior(int r_num, TransformGroup rot_TG) {

		rot_TG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D yAxis = new Transform3D();
		rotationAlpha = new Alpha(1, r_num);        // rotate 180
		RotationInterpolator rot_beh = new RotationInterpolator(
				rotationAlpha, rot_TG, yAxis, 0.0f, (float) Math.PI);
		rot_beh.setSchedulingBounds(hundred_BS);           // start rotation at 0- and end at 360-degrees
		return rot_beh;
	}
	
    /* a function to pause ('tag'==false) or resume ('tag'==true) the rotation behavior */
	public static void control_Rotation(boolean tag) {
		if (tag == true)
			rotationAlpha.resume();
		else
			rotationAlpha.pause();			
	}

	/* a function to position viewer at 'eye' location */
	public static void define_Viewer(SimpleUniverse simple_U, Point3d eye) {
	    TransformGroup viewTransform = simple_U.getViewingPlatform().getViewPlatformTransform();
		Point3d center = new Point3d(0, 0, 0);             // define the point where the eye looks at
		Vector3d up = new Vector3d(0, 1, 0);               // define camera's up direction
		Transform3D view_TM = new Transform3D();
		view_TM.lookAt(eye, center, up);                   // look at 'center' from 'eye'
		view_TM.invert();
	    viewTransform.setTransform(view_TM);               // set the TransformGroup of ViewingPlatform
	}

	/* a constructor to set up for the application */
	public CommonsNM(BranchGroup sceneBG) {
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		Canvas3D canvas = new Canvas3D(config);		
		SimpleUniverse su = new SimpleUniverse(canvas);    // create a SimpleUniverse

		define_Viewer(su, new Point3d(1, 1, 3));           // set the viewer's location		
		
		sceneBG.compile();		                           // optimize the BranchGroup
		su.addBranchGraph(sceneBG);                        // attach the scene to SimpleUniverse

		setLayout(new BorderLayout());
		add("Center", canvas);
		frame.setSize(800, 800);                           // set the size of the JFrame
		frame.setVisible(true);
	}
}

class L5TextureSurfaceNM extends GroupObjects {

	/* a function to scale and position the linked item(s) at a particular location */
	public static TransformGroup link_OneDisk(Vector3f pos, Link link, Vector3d scl) {
		Transform3D trfm = new Transform3D();
		trfm.rotX(Math.PI);
		trfm.setTranslation(pos);                          	// specify the translation
		trfm.setScale(scl);                                	// specify the scaling
		TransformGroup posTG = new TransformGroup(trfm);   	// define the transformation
		posTG.addChild(link);                              	// position the linked item(s)
		return posTG;                                      	// return the TransformGroup
	}
	
	/* a function to a surface of the disk with 'n' sides and with texture mapping */
	private static TriangleStripArray ring_Side(String shape_key, int n) {
		float r = 2.0f;
		int k;
		
		int v_num = (n + 1) * 2;                           	// use 'n+1' points on the circle
		int vn_count[] = {v_num};                          	// set point counters for this surface
		Point3f[] v_cdnts = new Point3f[v_num];            	// allocate 3D coordinates for points of this surface
		Vector3f[] c_nmls = new Vector3f[v_num];           	// declare normals at each point of this surface
		Vector3f nml;
		double nt;                                         	// declare variables for the calculation of normal		
		float x0, y0;
		                                                   	// prepare points on the circle
		Point3f c_pts[] = L2StarNM.rectangle_Points(r, n);                
		Point3f ctr_pt = new Point3f(0f, 0f, 0.1f);
		Point3f ctrpt = new Point3f(0f, 0f, -0.1f);
		Point3f p1, p2;

		for (int i = 0; i <= n; i++) {
			k = (i < n) ? i : 0;                           	// NOTE: set the last two points as the first two points
		
			if (shape_key == "Top") {                      	// set for top (flat, circular) surface
				p1 = new Point3f(c_pts[k].x, c_pts[k].y, 0.1f);
				p2 = ctr_pt;
				nml = new Vector3f(0f, 0f,  1f);
			}
			else if (shape_key == "JWST") {
				p1 = new Point3f(c_pts[k].x, c_pts[k].y, -0.1f);
				p2 = ctrpt;
				nml = new Vector3f(0f, 0f,  -1f);
			}
			else {                                         	// set for outside (vertical, curve) surface
				p1 = new Point3f(c_pts[k].x, c_pts[k].y, -0.1f);
				p2 = new Point3f(c_pts[k].x, c_pts[k].y, 0.1f);
				x0 = c_pts[k].x;
				y0 = c_pts[k].y;
				nt = Math.sqrt(x0 * x0 + y0 * y0);         	// normalize the normals of side (vertical) surface points
				nml = new Vector3f((float) (x0 / nt), (float) (y0 / nt), 0f);
			}
			v_cdnts[i * 2 + 1] = p1;                       	// set the coordinate for the point on a surface
			v_cdnts[i * 2] = p2;
			c_nmls[i * 2] = c_nmls[i * 2 + 1] = nml;       	//     ... normal ... 
		}
		
		TriangleStripArray object_geometry = new TriangleStripArray(v_num, 
				TriangleStripArray.COORDINATES | TriangleStripArray.TEXTURE_COORDINATE_3 |
				TriangleStripArray.NORMALS, vn_count);
		object_geometry.setStripVertexCounts(vn_count);    	// create the object as a TriangleStripArray
		object_geometry.setCoordinates(0, v_cdnts, 0, v_num); 
		object_geometry.setNormals(0, c_nmls, 0, v_num);   	// set the geometry's normals 
		
		return object_geometry;
	}
	
	public static Shape3D ring_Shape(String shape_key, int n) {
		Appearance app = set_Appearance(shape_key);        	// set appearance with texture mapping		
		return new Shape3D(ring_Side(shape_key, n), app);

	}
	
	/* a function to define the appearance with texture mapping */
	public static Appearance set_Appearance(String s) {
		Appearance app = CommonsNM.set_Appearance(CommonsNM.White);
		PolygonAttributes pa = new PolygonAttributes();
		pa.setCullFace(PolygonAttributes.CULL_NONE);       	// show both sides
		app.setPolygonAttributes(pa);

		TexCoordGeneration tcg = new TexCoordGeneration(TexCoordGeneration.OBJECT_LINEAR,
				TexCoordGeneration.TEXTURE_COORDINATE_2);
		app.setTexCoordGeneration(tcg);
		app.setTexture(L5TextureSurfaceNM.texture_Appearance("Image" + s));
		
		TextureAttributes textureAttrib= new TextureAttributes();
		textureAttrib.setTextureMode(TextureAttributes.REPLACE);
		app.setTextureAttributes(textureAttrib);
		
		if (s == "JWST") {
			float scl = 0.24f;
			Vector3d scale = new Vector3d(scl, scl, scl);
			Transform3D scaleTransform= new Transform3D();
			Transform3D rotateTansform= new Transform3D();
			Transform3D moveRightTransform= new Transform3D();
			scaleTransform.setScale(scale);
			rotateTansform.rotX(Math.PI);
			moveRightTransform.setTranslation(new Vector3d(-0.25, 0.0, 0.0));
			scaleTransform.mul(rotateTansform);
			scaleTransform.mul(moveRightTransform);
			textureAttrib.setTextureTransform(scaleTransform);
		}
		else {
			float scl = 0.250f;								// need to rearrange the four quarters
			Vector3d scale = new Vector3d(scl, scl, scl);
			Transform3D transMap = new Transform3D();
			transMap.setScale(scale);
			textureAttrib.setTextureTransform(transMap);
		}
		
		return app;
	}

	/* a function to define the texture with a specific image */	
	private static Texture2D texture_Appearance(String f_name) {
		String file_name = f_name + ".jpg";   			 	// indicate the location of the image
		TextureLoader loader = new TextureLoader(file_name, null);
		ImageComponent2D image = loader.getImage();        	// get the image
		if (image == null)
			System.out.println("Cannot load file: " + file_name);

		Texture2D texture = new Texture2D(Texture2D.BASE_LEVEL,
				Texture2D.RGBA, image.getWidth(), image.getHeight());
		texture.setImage(0, image);                        	// define the texture with the image

		return texture;
	}
	
	public L5TextureSurfaceNM(String s) {
		super(ring_Shape(s, 60));
	}
}

class CodeLab2NM extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	
	private static String frame_name = "NM's Lab #2";
	private static boolean r_tag = true;
	private static final String OBJECT_NAME = "Circle";

	/* a constructor to set up for the application */
	public CodeLab2NM(BranchGroup scene) {
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		Canvas3D canvas3D = new Canvas3D(config);
		canvas3D.setSize(800, 800);                        // set size of canvas
		SimpleUniverse su = new SimpleUniverse(canvas3D);  // create a SimpleUniverse
		                                                   // set the viewer's location
		CommonsNM.define_Viewer(su, new Point3d(1.35, -0.35, 1.5));
		
		scene.compile();		                           // optimize the BranchGroup
		su.addBranchGraph(scene);                          // attach 'scene' to 'su'

		Menu m = new Menu("Menu");                         // set menu's label
		m.addActionListener(this);
		MenuBar menuBar = build_MenuBar(m, OBJECT_NAME);   // build and set the menu bar
		frame.setMenuBar(menuBar);

		setLayout(new BorderLayout());
		add("Center", canvas3D);
		frame.setSize(810, 800);                           // set the size of the frame
		frame.setVisible(true);
	}	

	/* a function to build the menu bar of the demo */
	public static MenuBar build_MenuBar(Menu m, String s) {
		MenuBar menuBar = new MenuBar();

		m.add("Exit");		                               // specify menu items
		m.add("Pause/Rotate");
		m.addSeparator();                                  // group of geometry arrays
		m.add(s);
		menuBar.add(m);                                    // add items to the menu

		return menuBar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String chosen_item = e.getActionCommand();		

		frame.setTitle(frame_name + ": " + chosen_item);
		switch(chosen_item) {                              // handle different menu items
		case "Exit": 
			System.exit(0);                                // quit the application
		case "Pause/Rotate":
			r_tag = (r_tag == true)? false : true;
			CommonsNM.control_Rotation(r_tag);
			return;
        default:
            return;
		}
	}	
}

class GroupObjects {
	
	public static BranchGroup scene_Group(BranchGroup a_BG, BranchGroup s_BG) {
		BranchGroup sceneBG = new BranchGroup();
		
		a_BG.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
		a_BG.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
		s_BG.setCapability(BranchGroup.ALLOW_DETACH);      // allow 'shapeBG' detached
		a_BG.addChild(s_BG);                               // add 'shapeBG' to 'alterableBG'
		
		TransformGroup sceneTG = new TransformGroup();     // introduce a TransformGroup for rotation
		sceneTG.addChild(a_BG);                            // attach 'alterableBG' to 'sceneTG'
		sceneBG.addChild(CommonsNM.rotate_Behavior(7500, sceneTG));
		CommonsNM.control_Rotation(true);                  // make 'alterableBG' rotating by default
		sceneBG.addChild(sceneTG);
		
		return sceneBG;                                    // return the structured 'sceneBG'
	}
	
	protected BranchGroup shapeBG = new BranchGroup();
	
	/* a function to return 'shapeBG' as a BranchGroup */
	public BranchGroup get_ShapeBG() {
		return shapeBG;
	}
	
	public GroupObjects(Node p) {
		shapeBG.addChild(p);
	}
}

class L2StarNM extends GroupObjects {
	/* a function to create and return a points on a rectangle */
	public static Point3f[] rectangle_Points(float width, float height) {
	    Point3f[] rect_pts = new Point3f[4]; // Four corners of the rectangle

	    float x = height / 2.0f;
	    float y = width / 2.0f;

	    rect_pts[0] = new Point3f(-x, -y, 0.0f); // Bottom-left
	    rect_pts[1] = new Point3f(x, -y, 0.0f);  // Bottom-right
	    rect_pts[2] = new Point3f(x, y, 0.0f);   // Top-right
	    rect_pts[3] = new Point3f(-x, y, 0.0f);  // Top-left

	    return rect_pts;
	}
	
	/* a function to create and return the geometry of a circle shape */
	private static LineArray rectangle(float width, float height, Color3f color) {
	    LineArray lineArr = new LineArray(8, GeometryArray.COLOR_3 | GeometryArray.COORDINATES);

	    Point3f[] rect_pts = rectangle_Points(width, height); // Get rectangle corners

	    // Define edges of the rectangle (connecting points)
	    lineArr.setCoordinate(0, rect_pts[0]); // Bottom-left -> Bottom-right
	    lineArr.setCoordinate(1, rect_pts[1]);

	    lineArr.setCoordinate(2, rect_pts[1]); // Bottom-right -> Top-right
	    lineArr.setCoordinate(3, rect_pts[2]);

	    lineArr.setCoordinate(4, rect_pts[2]); // Top-right -> Top-left
	    lineArr.setCoordinate(5, rect_pts[3]);

	    lineArr.setCoordinate(6, rect_pts[3]); // Top-left -> Bottom-left
	    lineArr.setCoordinate(7, rect_pts[0]);

	    // Set color for each point
	    for (int i = 0; i < 8; i++) {
	        lineArr.setColor(i, color);
	    }
	    return lineArr;
	}
			
	/* a function to create and return a Shape3D with one LineArray */
	public static Shape3D line_Shape(float width, float height) {
	    LineArray rectGeometry = rectangle(width, height, CommonsNM.White);
	    return new Shape3D(rectGeometry);
	}
	
	public L2StarNM(float width, float height) {
	    super(line_Shape(width, height));
	}
}