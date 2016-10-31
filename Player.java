import java.awt.Color;
/**
 * This class represent the player and its component 
 * such as ,lives ,score ,the seat that the player use to move 
 *
 */

public class Player {
	private int score , lives;
	private String name;
	//the player seat that use to move from one place to anther
	private Seat ball;
	public static final int SCORE_SUCCESS_FACTOR = 50 ,INITIAL_SCORE = 50 , INITIAL_LIVES = 5;
	private int Player_Initial_XPosition = Table.start_Seat_Position_X ,
			Player_Initial_YPosition = Table.start_Seat_Position_Y ,Player_ID = 10;
	
	private final Color playerColor = Color.CYAN;
	/**
	 * The player constructor to construct a new player 
	 * @param score the initial score that we want the player to have
	 * @param lives the initial lives that we want the player to have
	 * @param name the name of the player
	 */
	public Player(int score,int lives,String name){
		this.score=score;
		this.lives=lives;
		this.name=name;
		ball = new Seat(Player_Initial_XPosition, Player_Initial_YPosition, playerColor, Player_ID);
	}
	/**
	 * Set the player lives to be the newLives that we pass as parameter
	 * @param newLives the Live value that we want the player to have
	 */
	public void setLives(int newLives){
		this.lives=newLives;
	}
	/**
	 * Set the player score to be the newScore that we pass as parameter
	 * @param newScore the new score that we want the player to have
	 */
	public void setScore(int newScore){
		this.score=newScore;
	}
	/**
	 * return the player currently score
	 * @return the player score
	 */
	public int getScore(){
		return score;
	}
	/**
	 * return the player currently lives 
	 * @return the player lives value
	 */
	public int getLives(){
		return lives;
	}
	/**
	 * return the player name
	 * @return player name
	 */
	public String getName(){
		return name;
	}
	/**
	 * we use this function to get the player seat .the seat that the 
	 * player use to move from one place to anther which i called ball. 
	 * @return the player seat (ball)
	 */
	public Seat getBall(){
		return ball;
	}
}
