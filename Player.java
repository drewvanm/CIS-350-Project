

public class Player {
	/**
	 * Player's score.
	 */
	private int score;
	
	/**
	 * Player's name.
	 */
	private String name;
	
	/**
	 * Flag for whether or not player is active in round.
	 */
	private boolean active;
	
	/**
	 * Constructor: initialize player with score of zero, active flag set true and passed name.
	 * @param pName: player's name
	 */
	public Player(String pName) {
		this.score = 0;
		this.name = pName;
		this.active = true;
	}
	
	/**
	 * Method to return player's score for the score board.
	 * @return score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Method to set name if desired after initial constructor set.
	 * @param pName name to set player's name to
	 */
	public void setName(String pName) {
		this.name = pName;
	}
	
	/**
	 * Method to return player's name for score board and game panel.
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Method to return whether or not a player is active in the current round.
	 * @return active
	 */
	public boolean isActive() {
		return active;
	}
	
	/**
	 * Method to increment players score upon winning a round.
	 */
	public void winRound() {
		score++;
	}
	
	/**
	 * Method to remove player from current round after failing to answer correctly (set active flag false).
	 */
	public void loseRound() {
		this.active = false;
	}
		
	/**
	 * Private method to reset player's score in the event of a new game.
	 */
	private void reset() {
		this.score = 0;
	}
	
	/**
	 * Method to reset active flag at the start of a new round.
	 */
	public void newRound() {
		this.active = true;
	}

	/**
	 * Method run to reset player at the start of a new game.
	 */
	public void newGame() {
		reset();
		newRound();
	}	
}
