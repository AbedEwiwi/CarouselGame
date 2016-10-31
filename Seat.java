import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.Timer;
/**
 * This class represent the seats that we have in the game ,and its can be
 * the seat that move around the table ,or the seat that the player use
 * to move from one place to other, and it also contain the timer object 
 * that represent the seat speed 
 * @author mac
 *
 */
public   class Seat extends JComponent implements ActionListener {

	private static final long serialVersionUID = 1L;

	private int xPosition , yPosition , ballHight = 70 ,
			ballWidth = 70 , id ,speed;
	private double angle = 0;
	private final int HALF_RADIOS = (Table.RADIOS -35);
	private final int NEW_CENTER_X =Table.TABLE_CENTER_X -35
			,NEW_CENTER_Y =Table.TABLE_CENTER_Y -35;
	private   Timer timer ;
	private Color seatColor ;
	private final int INITIAL_SPEED = 700 , MAX_SPEED = 50 ,COMPONENT_WIDTH = myFrame.frameWidth 
			, COMPONENT_HIGH = myFrame.frameHigh;
	private boolean fillable = false ;
	private static final Color FILLABLE_SEAT_COLOR =Color.GREEN;
	private static final Color NONFILLABLE_SEAT_COLOR =Color.RED;
	/**
	 * constructor to constructor new seat 
	 * @param id the id that the seat have
	 * @param angle	the angle that we use to place the seat in the place that it should has
	 * @param color the seat color which can be red or green 
	 * @param fillable this represent if the player can use this seat to move through،
	 * NOTE:the red color mean that this place is busy so you cant move through while
	 * the green one mean that the player can use this seat to move through
	 * 
	 * NOTE:there is relation between the color and the fillable value
	 * and its like that , if the color is red mean the fillable should be false
	 * and if the color is green mean that the fillable should be true
	 *  
	 */
	public Seat( int id ,double angle,Color color,boolean fillable){
		super();
		this.angle = angle;
		xPosition = (int) (NEW_CENTER_X  +(HALF_RADIOS * Math.cos(angle)));
		yPosition = (int) (NEW_CENTER_Y +(HALF_RADIOS * Math.sin(angle)));
		this.setSize(COMPONENT_WIDTH, COMPONENT_HIGH);
		this.setVisible(true);
		this.id = id;
		timer = new Timer(INITIAL_SPEED,this);
		this.seatColor = color;
		this.fillable = fillable;
		this.speed = INITIAL_SPEED;
	}
	/**
	 * the same as the default constructo thus we use this constructor to 
	 * create the player seat with color cyan 
	 * @param xPosition the xPosition we want this seat to place at
	 * @param yPosition the yPosition that we want this seat to place at
	 * @param color the seat color 
	 * @param id the seat id
	 */
	public Seat(int xPosition,int yPosition,Color color,int id){
		this.setSize(COMPONENT_WIDTH, COMPONENT_HIGH);
		this.setVisible(true);
		this.xPosition =xPosition;
		this.yPosition=yPosition;
		this.seatColor =color;
		this.id=id;
	}
	/**
	 * return the angle that the seat currently has ,to place this seat
	 * in another place depend on the angle that it already has.  
	 * @return the angle that the seat has
	 */
	public double getAngle(){
		return angle;
	}
	/**
	 * 
	 * @return the id that the seat has
	 */
	
	public int getId(){
		return id;
	}
	/**
	 * return if the seat fillable variable true or false  
	 * @return fillable value
	 */
	public boolean fillable(){
		return fillable;
	}
	/**
	 * set the seat speed to be the newSpeed ,so it can update
	 * it speed , actually what we do is to set the timer value to 
	 * new value , 
	 * @param newSpeed the speed that we want the seat to has
	 */
	public void setSpeed(int newSpeed)
	{
		if( speed == MAX_SPEED && newSpeed < MAX_SPEED)
			return;
		if(speed >= MAX_SPEED && speed <=INITIAL_SPEED)
		{
			this.speed = newSpeed;
			timer.setDelay(newSpeed);
		}	
	}
	/**
	 * return the seat currently speed
	 * @return the seat speed
	 */
	public int getSpeed(){
		return speed;
	}
	/**
	 * paint the seat component so you can see it at the frame in
	 * the place that it should have
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(seatColor);
		g.fillOval(xPosition , yPosition  , ballWidth, ballHight);
	}
	/**
	 * start the timer and it mean start moving the seat around the
	 * table ,  
	 */
	public  void  start(){
		timer.start();
	}
	/**
	 * stop the timer mean stop the seat from moving 
	 */
	public void stop(){
		timer.stop();
	}
	@Override
	/**
	 * This is the most important function and its task to compute a formula 
	 * depend on the seat angle and the table radius,and actually this function 
	 * which move the seat because it change its x and y position.   
 	 */
	public void actionPerformed(ActionEvent e) {
		xPosition = (int)(NEW_CENTER_X  + (HALF_RADIOS * Math.cos(angle)));
		yPosition = (int)( NEW_CENTER_Y  +(HALF_RADIOS * Math.sin(angle)));
		angle = angle + Math.PI/8;
		if(angle >= 2*Math.PI)
			angle = angle - 2*Math.PI;
		repaint();
	}
	/**
	 * set the seat xPosition to be the newPosition
	 * @param newPosition the new xPosition that we want the sat to have
	 */
	public void setXPosition(int newPosition){
		this.xPosition = newPosition;
		repaint();
	}
	/**
	 * set the yPosition to be the newPosition
	 * @param newPosition the new yPosition that we want the seat to have
	 */
	public void setYPosition(int newPosition){
		this.yPosition=newPosition;
		repaint();
	}
	/**
	 * 
	 * @return the seat xPosition
	 */
	public int getXposition(){
		return xPosition;
	}
	/**
	 * 
	 * @return the seat yPosition
	 */
	public int getYposition(){
		return yPosition;
	}
	/**
	 * set the seat color to be the newColor 
	 * @param newColor the new color that we want the seat to have
	 */
	public void setColor(Color newColor){
		this.seatColor=newColor;
	}
	/**
	 * 
	 * @return the seat color
	 */
	public Color getColor(){
		return seatColor;
	}
	/**
	 * return the seat default color which depend on the fillable value
	 * if it true it mean return green ,otherwise return red
	 * @param fillable the value which decide if the color that we should to return
	 * is the red color or green color
	 * @return green or red color depend on fillable value
	 */
	public static Color getDefaultColor(boolean fillable){
		if(fillable)
		{
			return Seat.FILLABLE_SEAT_COLOR;
		}
		return Seat.NONFILLABLE_SEAT_COLOR;
	}
}
