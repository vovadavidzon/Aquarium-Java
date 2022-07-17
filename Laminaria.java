package aquarium3;

import java.awt.Color;
import java.awt.Graphics;
/**
 * This is the Laminaria class which extends the abstract Immobile class.
 * 
 * @version 1.0  07/06/2022
 * @authors Michael ilkanayev -318216678 and Vladimir davidzon -317648632.
 */
public class Laminaria extends Immobile {
	/**
	 * A constructor that activates the Immobile constructor.
	 * 
	 * @param panel - AquaPanel onject.
	 * @param size - the Laminaria size.
	 * @param x - the x of the Laminaria.
	 * @param y - the y of the Laminaria.
	 */
	public Laminaria (AquaPanel panel,int size,int x,int y) {
		super(panel,"Laminaria",size,x,y);
	}

	/**
	 * A function that draws the Laminaria
	 */
	@Override
	public void drawCreature(Graphics g) {
		g.setColor(colorr);
		g.fillArc(x-size/20, y-size, size/10, size*4/5, 0, 360);
		g.fillArc(x-size*3/20, y-size*13/15, size/10, size*2/3, 0, 360);
		g.fillArc(x+size/20, y-size*13/15, size/10, size*2/3, 0, 360);
		g.drawLine(x, y, x, y-size/5);
		g.drawLine(x, y, x-size/10, y-size/5);
		g.drawLine(x, y, x+size/10, y-size/5);
	}
}
