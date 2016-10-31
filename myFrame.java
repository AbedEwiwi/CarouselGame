import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * This class represent the myFrame class ,which is the frame that you see 
 * at the beginning of the game,and its component the button (jump) and the two labels 
 * scoreLabel,livesLabel,and the most important component the panel that holds all this component,
 * The reason that i implement a class for the frame
 * is that the frame match itself to the size of the client screen,
 * so no matter what the screen size you have you can play the game 
 * @author mac
 *
 */

public class myFrame {
	private JFrame mainFrame = new JFrame("carousel_game");
	public static  int frameHigh = (int) (Toolkit.getDefaultToolkit().getScreenSize()).getHeight()
	,frameWidth = (int) (Toolkit.getDefaultToolkit().getScreenSize()).getWidth() ;
	private JButton jumpButton  ;
	private JPanel panel;
	private final int SCORE_LABEL_WIDTH = 120 , 
			SCORE_LABEL_HIGH = 70 , JUMP_BUTTON_WIDTH =250 ,
			JUMP_BUTTON_HIGHT = 40; 
	private final String SCORE_LABEL ="SCORE :" ,LIVES_LABEL = "LIVES :";
	private JLabel scoreLabel , livesLabel;
	/**
	 * a constructor to construct a new frame 
	 * NOTE: the frame also contain the panel itself ,which we use 
	 * if we want to add other component 
	 */
	public myFrame(){
		mainFrame.setSize(frameWidth,frameHigh);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		panel.setLayout(null);
		install();
		mainFrame.setVisible(true);
	}
	/**
	 * This Function add a panel to the frame so then we can see what we 
	 * edit or add to the frame ,  
	 * @param comp the panel that we want to add to the frame
	 */
	public void addPanel(JPanel comp)
	{
		mainFrame.add(comp);
		mainFrame.setVisible(true);
		
	} 
	/**
	 * dispose the frame we invoke this function when the player lose 
	 * all the lives that he has ,and by default the game will start again    
	 */
	public void dispose(){
		mainFrame.dispose();
	}
	/**
	 * helper function which i use to create the jumbButton and 
	 * the scoreLabel ,livesLabel ,and to place them in the exact place   
	 */
	private void install(){
		int width = myFrame.frameWidth;
		scoreLabel = new JLabel(SCORE_LABEL+Player.INITIAL_SCORE);
		scoreLabel.setBounds(width - (width/4), 10, SCORE_LABEL_WIDTH, SCORE_LABEL_HIGH);
		scoreLabel.setFont(new Font("", 1, 14));
		livesLabel = new JLabel(LIVES_LABEL+Player.INITIAL_LIVES);
		livesLabel.setBounds(width - width/4, 10+SCORE_LABEL_HIGH, SCORE_LABEL_WIDTH, SCORE_LABEL_HIGH);
		livesLabel.setFont(new Font("",1,14));
		jumpButton = new JButton("JUMP");
		jumpButton.setBounds(frameWidth/2 -frameWidth/4, frameHigh -frameHigh/5, JUMP_BUTTON_WIDTH, JUMP_BUTTON_HIGHT);
		panel.add(jumpButton);
		panel.add(scoreLabel);
		panel.add(livesLabel);
		mainFrame.add(panel);
	}
	/**
	 * return the jumbButton which is the button that the player use to jump 
	 * from one place to another , we use this function if we want to edit
	 * the action that this button suppose to do.
	 * @return The button that we already add as jumpButton 
	 */
	public JButton getJumpButton(){
		return jumpButton;
	}
	/**
	 * return the panel that this frame have made so then we can add 
	 * other component to this frame.
	 * @return The panel that this frame have made (panel)
	 */
	public JPanel getPanel(){
		return panel;
	}
	/**
	 * update the score label that the frame have made ,which hold the 
	 * score that the player have reach ,and we invoke this method every 
	 * time that the player succeed to move from the start part to the finish part  
	 * @param newScore the score that we want the frame to present
	 */
	public void updateScoreLabel(int newScore){
		scoreLabel.setText(SCORE_LABEL + newScore);
	}
	/**
	 *  update the live label ,so the live value will have the new value
	 *  we invoke this function when the player have made mistake and 
	 *  we want to reduce the lives that he have. 
	 * @param newLives the live value that we want the frame to present
	 */
	public void updateLivesLabel(int newLives){
		livesLabel.setText(LIVES_LABEL+newLives);
	}

	
}
