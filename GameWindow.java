import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * 
 * @author Taylor
 *
 */
public class GameWindow {
	/**
	 * This is the base frame of the game.
	 */
	private JFrame frmIKnowMovies;
	/**
	 * This layered pane holds content.
	 */
	private JLayeredPane layeredPane;
	/**
	 * This shows the profile pic.
	 */
	private JLabel profilePic;
	/**
	 * This is the head of the scoreboard.
	 */
	private JLabel scoreBoardTitle;
	/**
	 * This is where you can search.
	 */
	private JTextField searchBarBox;
	/**
	 * This is how you guess a movie.
	 */
	private JButton btnGuess;
	/**
	 * This is how you pass.
	 */
	private JButton btnPass;
	/**
	 * This holds the title for the guesses.
	 */
	private JLabel guessBoardTitle;
	/**
	 * This holds the console.
	 */
	private JTextArea txtAreaConsole;
	/**
	 * This holds the scoreboard.
	 */
	private JTextArea txtAreaScoreBoard;
	/**
	 * This holds the guessed movies.
	 */
	private JTextArea txtAreaGuessBoard;
	/**
	 * This holds the underlying game object.
	 */
	private Game game;
	/**
	 * This is the actor being guessed.
	 */
	private Actor currentActor;
	/**
	 * 
	 * @author Drew
	 * This holds the game state
	 */
	private enum GameState { ACTOR, MOVIES, NAME_PLAYERS };
	/**
	 * This holds the enum for state of the game.
	 */
	private GameState state;

	/**
	 * Launch the application.
	 * @param g The game object to be played
	 */
	public static void startGame(final Game g) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow(g);
					window.frmIKnowMovies.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * create the application.
	 * @param g the game object to be played
	 */
	public GameWindow(final Game g) {
		initialize(g);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param g The game object to be played
	 */
	private void initialize(final Game g) {
		game = g;
		state = GameState.ACTOR;
		frmIKnowMovies = new JFrame();
		frmIKnowMovies.setTitle("I Know Movies");
		frmIKnowMovies.setBounds(100, 100, 1004, 671);
		frmIKnowMovies.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		layeredPane = new JLayeredPane();
		frmIKnowMovies.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		scoreBoardTitle = new JLabel("<html><strong>Score Board<strong></html>",
				SwingConstants.CENTER);
		scoreBoardTitle.setBorder(BorderFactory.createLineBorder(
				Color.BLACK, 2, true));
		scoreBoardTitle.setBounds(136, 40, 276, 47);
		layeredPane.add(scoreBoardTitle);
		
		searchBarBox = new JTextField();
		searchBarBox.setBounds(136, 241, 276, 47);
		layeredPane.add(searchBarBox);
		
		btnGuess = new JButton("Guess");
		btnGuess.setBounds(136, 397, 106, 25);
		layeredPane.add(btnGuess);
		
		btnPass = new JButton("No Idea");
		btnPass.setVerticalAlignment(SwingConstants.BOTTOM);
		btnPass.setBounds(296, 396, 116, 25);
		layeredPane.add(btnPass);
		
		guessBoardTitle = new JLabel(
				"<html><strong>Movies Guessed</html></strong>",
				SwingConstants.CENTER);
		guessBoardTitle.setBorder(BorderFactory.createLineBorder(
				Color.BLACK, 2, true));
		guessBoardTitle.setBounds(136, 448, 276, 38);
		layeredPane.add(guessBoardTitle);
		
		
		txtAreaConsole = new JTextArea("");
		txtAreaConsole.setEditable(false);
		txtAreaConsole.setWrapStyleWord(true);
		txtAreaConsole.setText("Enter Text Above...");
		txtAreaConsole.setBorder(BorderFactory.createLineBorder(
				Color.BLACK, 2, true));
		txtAreaConsole.setBounds(136, 293, 276, 92);
		layeredPane.add(txtAreaConsole);
		
		txtAreaScoreBoard = new JTextArea(game.getNamesAndScores());
		txtAreaScoreBoard.setEditable(false);
		txtAreaScoreBoard.setWrapStyleWord(true);
		txtAreaScoreBoard.setBorder(BorderFactory.createLineBorder(
				Color.BLACK, 2, true));
		txtAreaScoreBoard.setBounds(136, 87, 276, 100);
		layeredPane.add(txtAreaScoreBoard);
		
		txtAreaGuessBoard = new JTextArea("");
		txtAreaGuessBoard.setEditable(false);
		txtAreaGuessBoard.setWrapStyleWord(true);
		txtAreaGuessBoard.setBorder(BorderFactory.createLineBorder(
				Color.BLACK, 2, true));
		txtAreaGuessBoard.setBounds(136, 486, 276, 125);
		layeredPane.add(txtAreaGuessBoard);
		
		
		
		displayNewRound();
		setUpEventListeners();
	}
	
	/**
	 * This sets up the event listeners.
	 */
	public final void setUpEventListeners() {
		Action searchForResults = new AbstractAction() {
		    /**
			 * This is necessary for serializable classes.
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public void actionPerformed(final ActionEvent e) {
		    	if (state.equals(GameState.ACTOR)) {
			    	currentActor = new Actor(searchBarBox.getText());
			    	updateProfilePic();
			    	state = GameState.MOVIES;
			    	txtAreaConsole.setText(game.getCurrentPlayer().getName()
			    			+ ", now name a movie with "
			    			+ currentActor.getActorName() + " in it");
			    	btnGuess.setText("Guess");
		    	} else if (state.equals(GameState.MOVIES)) {
		    		if (currentActor.actorInMovie(searchBarBox.getText())) {
		    			
		    			txtAreaConsole.setText("Correct");
		    			game.nextPlayer();
		    			txtAreaConsole.append("\n"
		    					+ game.getCurrentPlayer().getName()
		    					+ ", name a movie");
		    			txtAreaGuessBoard.append(searchBarBox.getText() + "\n");
		    			clearSearhBar();
		    		} else {
		    			if (game.getNumOfEliminatedPlayers() == 0) {
		    				game.setNextToChoose(game.getCurrentPlayer());
		    			}
		    			txtAreaConsole.setText("Wrong");
		    			txtAreaConsole.append("\n"
		    					+ game.getCurrentPlayer().getName()
		    					+ " is out of the round");
		    			game.getCurrentPlayer().loseRound();
		    			game.nextPlayer();
		    			txtAreaConsole.append("\n"
		    					+ game.getCurrentPlayer().getName()
		    					+ ", name a movie");
		    			
		    			checkForWin();
		    			clearSearhBar();
		    		}
		    		
		    	}
		    	
		    }
		};
		Action pass = new AbstractAction() {
			/**
			 * This is necessary for serializable classes.
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(final ActionEvent e) {
				if (state.equals(GameState.ACTOR)) {
					game.nextPlayer();
					txtAreaConsole.setText(game.getCurrentPlayer().getName()
							+ ", choose an actor");
					clearSearhBar();
				} else if (state.equals(GameState.MOVIES)) {
					//Doesn't allow player who picks actor to pass
					if (game.getNumOfEliminatedPlayers() == 0
							&& txtAreaGuessBoard.getText()
							.trim().length() == 0) {
						txtAreaConsole.setText("You cannot pass"
								+ "if you picked the actor");
					
					} else {
					txtAreaConsole.setText("Pass");
	    			txtAreaConsole.append("\n"
	    					+ game.getCurrentPlayer().getName()
	    					+ " is out of the round");
	    			game.getCurrentPlayer().loseRound();
	    			game.nextPlayer();
	    			txtAreaConsole.append("\n"
	    					+ game.getCurrentPlayer().getName()
	    				+ ", name a movie");
	    			
	    			checkForWin();
					}
				}
			}
		};
		btnGuess.addActionListener(searchForResults);
		btnPass.addActionListener(pass);
	}
	
	/**
	 * Resets the labels to show a new round, prompting player to pick an actor.
	 */
	public final void displayNewRound() {
		if (profilePic != null) {
				layeredPane.remove(profilePic);
		}
		btnGuess.setText("Choose");
		btnPass.setText("Pass");
		game.setCurrentPlayer(game.getNextToChoose());
		txtAreaConsole.setText(game.getNextToChoose().getName()
				+ ", please choose an actor.");
		txtAreaGuessBoard.setText("");
		txtAreaConsole.setText(game.getCurrentPlayer().getName()
				+ " choose an Actor to start the round.");
		
	}
	
	/**
	 * Clears search bar.
	 */
	private void clearSearhBar() {
		searchBarBox.setText("");
	}
	
	/**
	 * Checks if there is one player left who is not eliminated and 
	 * congratulates them.
	 */
	private void checkForWin() {
		if (game.getNumOfEliminatedPlayers() == game.getNumOfPlayers() - 1) {
			game.getCurrentPlayer().winRound();
			JOptionPane.showMessageDialog(null, 
					"Congratulations " + game.getCurrentPlayer().getName()
						+ " you Won this round!",
					"Congrats",
					JOptionPane.PLAIN_MESSAGE);
			
			txtAreaScoreBoard.setText(game.getNamesAndScores());
			game.newRound();
			state = GameState.ACTOR;
			displayNewRound();
		}
	}
	
	/**
	 * Creates the actor profile picture and adds it to the GUI.
	 */
	private void updateProfilePic() {
		profilePic = currentActor.getActorProfilePic(300, 300);
		profilePic.setBorder(BorderFactory.createLineBorder(
				Color.BLACK, 2, true));
		profilePic.setBounds(500, 40, 300, 300);
		layeredPane.add(profilePic);
	}
}
