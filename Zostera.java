package aquarium3;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * This is the Zostera class which extends the abstract Immobile class.
 * 
 * @version 1.0  07/06/2022
 * @authors Michael ilkanayev -318216678 and Vladimir davidzon -317648632.
 */
public class Zostera extends Immobile{
	/**
	 * A constructor that activates the Immobile constructor.
	 * 
	 * @param panel - AquaPanel onject.
	 * @param size - the Zostera size.
	 * @param x - the x of the Zostera.
	 * @param y - the y of the Zostera.
	 */
	public Zostera (AquaPanel panel, int size,int x,int y) {
		super(panel,"Zostera",size,x,y);
	}

	/**
	 * A function that draws the Laminaria
	 */
	@Override
	public void drawCreature(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g2.setColor(colorr);
		g.drawLine(x, y, x, y-size);
		g.drawLine(x-2, y, x-10, y-size*9/10);
		g.drawLine(x+2, y, x+10, y-size*9/10);
		g.drawLine(x-4, y, x-20, y-size*4/5);
		g.drawLine(x+4, y, x+20, y-size*4/5);
		g.drawLine(x-6, y, x-30, y-size*7/10);
		g.drawLine(x+6, y, x+30, y-size*7/10);
		g.drawLine(x-8, y, x-40, y-size*4/7);
		g.drawLine(x+8, y, x+40, y-size*4/7);
		g2.setStroke(new BasicStroke(1));
	}

}
