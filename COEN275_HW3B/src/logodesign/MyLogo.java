/**
 * 
 */
package logodesign;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 * This MyLogo class will create the window and call the LogoCreator to display the logo.
 * 
 * @author keshavgovindaraju
 *
 */
public class MyLogo extends JFrame {

	/**
	 * Base Constructor
	 */
	public MyLogo() {
		LogoCreator lc = new LogoCreator();
		Container container = getContentPane();
		container.add(lc);
		lc.draw();
		setSize(500, 500);
		setVisible(true);
	}
	
	/**
	 * Overloaded Constructor with objectTitle parameter for the super-class
	 * @param objectTitle
	 */
	public MyLogo(String objectTitle) {
		super(objectTitle);
		LogoCreator lc = new LogoCreator();
		Container container = getContentPane();
		container.add(lc);
		lc.draw();
		setSize(500, 500);
		setVisible(true);
	}

	/**
	 * Main method to be executed to display the Logo.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyLogo("ShivaGovindaraju_COEN275_Winter2020_Assignment3B");
		return;
	}

}

/**
 * This LogoCreator class will actually generate the Logo to be drawn.
 * 
 * @author keshavgovindaraju
 *
 */
class LogoCreator extends JPanel {
	
	//a few private variables for the Logo itself
	private Shape mTopT, mBottomT, mOuterQ, mInnerQ, mTick, mInnerBorder, mOuterBorder;
	
	/**
	 * Base Constructor for the Logo
	 * Will be initializing all the Shape objects here, and calculating all the necessary details.
	 */
	public LogoCreator() {
		//Create a T
		double topTX = 80;
		double topTY = 80;
		double topTWidth = 200;
		double topTHeight = 40;
		
		double bottomTX = topTX + (topTWidth - topTHeight) / 2;
		double bottomTY = topTY + topTHeight;
		double bottomTWidth = topTHeight;
		double bottomTHeight = topTWidth;
		
		mTopT = new Rectangle2D.Double(topTX, topTY, topTWidth, topTHeight);
		mBottomT = new Rectangle2D.Double(bottomTX, bottomTY, bottomTWidth, bottomTHeight);
		
		//Create a Q
		double outerQX = topTX - topTHeight / 2;
		double outerQY = topTY - topTHeight / 2;
		double outerQWidth = 250;
		double outerQHeight = outerQWidth;
		
		double innerQX = outerQX + 0.2 * outerQWidth;
		double innerQY = outerQY + 0.2 * outerQHeight;
		double innerQWidth = outerQWidth * 0.6;
		double innerQHeight = innerQWidth;
		
		mOuterQ = new Ellipse2D.Double(outerQX, outerQY, outerQWidth, outerQHeight);
		mInnerQ = new Ellipse2D.Double(innerQX, innerQY, innerQWidth, innerQHeight);
		
		//this will be the little tick-mark on the edge of the Q.
		double tickX = innerQX + innerQWidth / 2 + 2 * topTX / 3;
		double tickY = innerQY + innerQHeight / 2;
		double tickWidth = topTWidth / 2;
		double tickHeight = topTHeight / 2;
		
		mTick = new Rectangle2D.Double(tickX, tickY, tickWidth, tickHeight);
		
		//create a border.
		double innerBorderX = topTX / 2;
		double innerBorderY = topTY / 2;
		double innerBorderWidth = innerBorderX * 2 + topTWidth;
		double innerBorderHeight = innerBorderY * 2 + topTHeight + bottomTHeight;
		
		double outerBorderX = innerBorderX / 2;
		double outerBorderY = innerBorderX / 2;
		double outerBorderWidth = outerBorderX * 2 + innerBorderWidth;
		double outerBorderHeight = outerBorderY * 2 + innerBorderHeight;
		
		mInnerBorder = new Rectangle2D.Double(innerBorderX, innerBorderY, innerBorderWidth, innerBorderHeight);
		mOuterBorder = new Rectangle2D.Double(outerBorderX, outerBorderY, outerBorderWidth, outerBorderHeight);
		
		//actually create the background
		setBackground(Color.cyan);
	}
	
	/**
	 * The draw() method necessary for drawing the logo
	 */
	public void draw() {
		this.add(new JLabel("ShivaGovindaraju_COEN275_Winter2020_Assignment3B"));
		repaint();
	}
	
	/**
	 * The method needed to to actually render the various Shapes into the logo properly
	 * Will draw and color the various shapes/areas as desired for the logo.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graph = (Graphics2D)g;
		graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//Fill in the T
		Area areaTopT = new Area(mTopT);
		Area areaBottomT = new Area(mBottomT);
		
		areaTopT.add(areaBottomT);
		
		graph.setPaint(Color.blue);
		graph.fill(areaTopT);
		
		//Fill in the Q
		Area areaOuterQ = new Area(mOuterQ);
		Area areaInnerQ = new Area(mInnerQ);
		Area areaTick = new Area(mTick);
		
		areaOuterQ.subtract(areaInnerQ);
		areaOuterQ.subtract(areaTopT);
		
		
		graph.setPaint(Color.yellow);
		graph.fill(areaOuterQ);
		
		graph.setPaint(Color.black);
		graph.draw(areaTopT);
		graph.draw(areaOuterQ);
		
		//Add the red tick-mark to the Q
		AffineTransform old = graph.getTransform();
		//note, we need to rotate the graph around the center of the Q in order to draw the tick-mark properly
		graph.rotate(Math.toRadians(45), areaOuterQ.getBounds().getX() + areaOuterQ.getBounds().getWidth() / 2, 
				areaOuterQ.getBounds().getY() + areaOuterQ.getBounds().getHeight() / 2);
		graph.setPaint(Color.black);
		graph.draw(areaTick);
		graph.setPaint(Color.red);
		graph.fill(areaTick);
		//resetting the graph's orientation
		graph.setTransform(old);
		
		//Fill in the logo's border
		Area areaInnerBorder = new Area(mInnerBorder);
		Area areaOuterBorder = new Area(mOuterBorder);
		
		areaOuterBorder.subtract(areaInnerBorder);
		graph.setPaint(Color.red);
		graph.fill(areaOuterBorder);
		graph.setPaint(Color.black);
		graph.draw(areaInnerBorder);
		graph.draw(areaOuterBorder);
		
		//Logo drawing is complete
	}
}