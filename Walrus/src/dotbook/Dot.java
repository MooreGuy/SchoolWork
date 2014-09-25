package dotbook;

/*Created by Guy Moore
 * 
 * The dot is a position on the field that can be specified by steps or
 * in inches(22.5 per step). The dot also contains the relation from yard lines,
 * hashes, or numbers.
 */
public class Dot
{
	private int stepsFrontToBack;
	private int stepsSideToSide;
	private int yardLine; //from 0-50, the yard line it is closest to.
	private String side;
	private boolean toTheLeftOfYardLine;
	private boolean toTheRightOfYardLine;
	private boolean splittingTheYardLines;
	private boolean onTheYardLine;
	
	public Dot(int sFTB, int sSTS)
	{
		stepsFrontToBack = sFTB;
		stepsSideToSide = sSTS;
	}
	
	
}
