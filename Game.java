import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * This class represent the game itself and the controls ,
 * so this class base on other classes such that the Table ,Player,myFrame,Seat
 * so in this class we make objects of this classes and invoke them together
 * as we can play the game 
 * @author mac
 *
 */
public class Game {
	private Seat seats[] ;
	private Timer time ;
	private final myFrame mainFrame ;
	private final int SPEED_FACTOR = 50;
	private JPanel panel; 
	private Player player;
	private boolean jumpFromStart = true;
	private final int MOVE_FACTOR = 135 ,NUMER_OF_SEATS = 8;
	private Seat targetSeat =null;
	/**
	 * construct new game so we can play it
	 */
	public Game(){
		seats = new Seat[NUMER_OF_SEATS];
		mainFrame = new myFrame();
		panel = mainFrame.getPanel();
		player = new Player(Player.INITIAL_SCORE,Player.INITIAL_LIVES,"hi");
		install();
	}
	/**
	 * helper function which add actions to the button 
	 * that the frame has add which is jumpButton ,and actually this button do 
	 * all the work since after we click on this button all the work should start
	 * check if it the right decision or no then add point or punish  
	 */
	private void install(){
		JButton button =mainFrame.getJumpButton();

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Seat ball = player.getBall();
				if(jumpFromStart)
				{
					ball.setXPosition(player.getBall().getXposition()+MOVE_FACTOR);
					Seat test =getClosestSeat();
					targetSeat = test;
					if(targetSeat == null)
					{
						punish();
						control();
					}
					else
					{
						
						mainFrame.updateScoreLabel(player.getScore()+10);
						player.setScore(player.getScore() + 10);
						targetSeat.setColor(ball.getColor());
						jumpFromStart = false;
						ball.hide();
					}
				}
				else
				{
					ball.setXPosition(targetSeat.getXposition() + MOVE_FACTOR);
					ball.setYPosition(targetSeat.getYposition());
					targetSeat.setColor(Seat.getDefaultColor(true));
					ball.show();
					checkFinalState();
					control();
					jumpFromStart = true;
					targetSeat = null;
				}
			}
		});

		Table t = new Table();
		panel.add(player.getBall());
		fillSeats(panel);

		panel.add(t);
		mainFrame.addPanel(panel);
	}
	/**
	 * fill the seats to the array by their id ,and this function work as the following
	 * first it get random place then it check that this number has been taken 
	 * and give it the red color and it make it fillable ,so the next seat should be
	 *  not fillable and give it the green color 
	 * @param panel the panel to add this seat finally to the frame
	 */
	private void fillSeats(JPanel panel){
		boolean fillable =false;
		for(int i=0 ;i<seats.length;i++)
		{
			int random = getRandomSeat();
			if(fillable){
				seats[random] =new Seat(random, random * Math.PI/4, Color.GREEN, true);
				fillable =false;
			}
			else
			{
				seats[random] =new Seat(random, random*Math.PI/4, Color.RED, false);
				fillable= true;
			}
			panel.add(seats[random]);
		}
	}
	/**
	 * play the game it means that the seats start to move around the table 
	 * and the player can move it seat to reach the finish point.
	 */
	public void play(){
		for (int i = 0; i < seats.length; i++) {
			seats[i].start();
		}
	}
	/**
	 * stop the game and it means that the seat should stop moving 
	 * so the game stop
	 */
	public void stop(){
		for (int i = 0; i < seats.length; i++) {
			seats[i].stop();
		}

	}
	/**
	 * update the seats speed ,so the seats will move rapidly 
	 * and actually we update the seat speed 
	 * by constant value which is SPEED_FACTOR
	 */
	public void speedUp(){
		for (int i = 0; i < seats.length; i++) {
			seats[i].setSpeed(seats[i].getSpeed() - SPEED_FACTOR);
		}
	}
	/**
	 * its the opposite of speedUp ,know we let the seats to move 
	 * slowly ,and we update the seats speed by SPEED_FACTOR
	 */
	public void slowDown(){
		for (int i = 0; i < seats.length; i++) {
			seats[i].setSpeed(seats[i].getSpeed() + SPEED_FACTOR);
		}
	}
	/**
	 * helper function to help us to get the proper random place 
	 * for the seats
	 * @return the seat place around the table
	 */
	private int getRandomSeat(){
		int i =(int)((Math.random()*10)%8);
		while(seats[i]!= null)
			i=(int)((Math.random()*10)%8);
		return i;

	}
	/**
	 * this function return the closest seat to the start point 
	 * so then we can know if the player have made the right decision when he
	 * jump or the wrong one ,and its actually depend on the result of this
	 * function if this function return a seat and this seat has value true for
	 * fillable then we pass the player seat through this seat ,else we punish the
	 * player for this decision by reduce the lives value by 1 
	 * @return the closest seat to the start point
	 */
	public Seat getClosestSeat(){
		for(int i=0;i<seats.length;i++){
			Seat temp =seats[i];
			Seat playerBall =player.getBall();
			int xRelative =Math.abs(temp.getXposition() -playerBall.getXposition());
			int yRelative =Math.abs(temp.getYposition() - playerBall.getYposition());
			if((xRelative>=0 && xRelative<=1) && (yRelative>=0 && yRelative<=1) &&
					temp.fillable())
				return temp;
		}
		
		return null;
	}
	/**
	 * this function help to present the move of the seats since we 
	 * have to wait for sometime after the player have jumped so he can see
	 * that he  jump , 
	 */
	private void control(){
		time = new Timer();
		time.schedule(new TimerTask() {
			
			@Override
			public void run() {
				restoreDefaultState();
				time.cancel();
			}
		}, 1000);
	}
	/**
	 * punish the player for his decision to jump by reduce the 
	 * player lives value , we invoke this function whenever the player
	 * jump to wrong seat ,or to wrong place 
	 */
	private void punish()
	{
		player.setLives(player.getLives()-1);
		mainFrame.updateLivesLabel(player.getLives());
		if(player.getLives() == 0)
		{
			System.out.println("Game Over");
			new Game().play();
		}
	}
	/**
	 * restore the default place after that the player success to reach 
	 * the finish point ,or after punished him for his wrong decision so 
	 * he can start again from the start point 
	 */
	private void restoreDefaultState(){
		Seat ball =player.getBall();
		ball.setXPosition(Table.start_Seat_Position_X);
		ball.setYPosition(Table.start_Seat_Position_Y);
	}
	/**
	 * check final state ,actually we invoke this method when the 
	 * player jump to the finish point so we check if he arrive to 
	 * the finish point .  
	 * 
	 */
	private void checkFinalState(){
		Seat ball = player.getBall();
		if(ball.getXposition() == Table.finish_Seat_Position_X && 
				ball.getYposition() == Table.finish_Seat_Position_Y)
		{
			player.setScore(player.getScore() + Player.SCORE_SUCCESS_FACTOR);
			mainFrame.updateScoreLabel(player.getScore());
			speedUp();
		}
		else
		{
			punish();
		}
	}
}
