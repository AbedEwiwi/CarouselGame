import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
/**
 * This class represent the table, which is the big cycle in the center 
 * of the frame and it also contains the two points start,finish.
 * its a simple class which its task to draw the big cycle and both the
 * start,finish.    
 * @author mac
 *
 */
public  class Table extends JComponent{
		private static final long serialVersionUID = 1L;
		private final Color tableColor = new Color(166,133,35) , 
				START_FINISH_SEATS_COLOR = Color.BLACK;
		
		
		public static final int TABLE_POSITION_X = 150 ,TABLE_POSITION_Y = 20;
		public static final int TABLE_WIDTH = 700 ,TABLE_HIGHT = 700 ;
		public static final int RADIOS = TABLE_WIDTH / 2 ;
		public static final  int TABLE_CENTER_X = TABLE_POSITION_X + RADIOS ,
				TABLE_CENTER_Y = TABLE_POSITION_Y + RADIOS ;
		private static final int SPACES_BETWEEN_TABLE = 100  ;
		private static final int START_SEAT_WIDTH = 70 , START_SEAT_HEIGHT = 70;
		public static final int start_Seat_Position_X = TABLE_POSITION_X - SPACES_BETWEEN_TABLE -START_SEAT_WIDTH/2,
				start_Seat_Position_Y =TABLE_POSITION_Y + RADIOS - START_SEAT_WIDTH/2;
		public static final int finish_Seat_Position_X = start_Seat_Position_X + TABLE_WIDTH + SPACES_BETWEEN_TABLE +START_SEAT_WIDTH +30,
				finish_Seat_Position_Y = start_Seat_Position_Y  ; 
		/**
		 * constructor to construct the table object with its components
		 * 
		 */
		public Table(){
			super();
			this.setSize(myFrame.frameWidth, myFrame.frameHigh);
			this.setVisible(true);
		}
		/**
		 * This method is actually the method that draw the table itself.
		 * so it first of all draw the big cycle then it draw two small cycles which are 
		 * the start,finish points ,and then it write
		 * the two strings ("start","finish") for the start , finish points
		 */
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.setColor(tableColor);
			g.fillOval(TABLE_POSITION_X, TABLE_POSITION_Y, TABLE_WIDTH, TABLE_HIGHT);
			g.setColor(START_FINISH_SEATS_COLOR);
			g.fillOval(start_Seat_Position_X, start_Seat_Position_Y, START_SEAT_WIDTH, START_SEAT_HEIGHT);
			g.drawString("Start", start_Seat_Position_X + 20 , start_Seat_Position_Y + 90);
			g.fillOval(finish_Seat_Position_X, finish_Seat_Position_Y, START_SEAT_WIDTH, START_SEAT_HEIGHT);
			g.drawString("Finish",finish_Seat_Position_X +20, finish_Seat_Position_Y+90);
		}
	}