import java.util.ArrayList;

/**
 * This class represents a game to guess movies.
 * 
 * @author drewv
 *
 */
public class Game {
	/**
	 * An ArrayList of Players who are playing.
	 */
	public ArrayList<Player> players = new ArrayList<Player>();
	/** A player who will choose the next actor. */
	private Player nextToChoose;
	/** The active player who has control of the game. */
	private Player currentPlayer;

	/**
	 * Constructor: takes how many players and creates them with default names.
	 * 
	 * @param numOfPlayers
	 *            how many players are in the game
	 */
	public Game(final int numOfPlayers) {
		for (int i = 0; i < numOfPlayers; i++) {
			players.add(new Player("Player " + (i + 1)));
		}
		nextToChoose = players.get(0);
		currentPlayer = players.get(0);
	}

	/**
	 * This method is used to set who is next to choose the actor for top of
	 * round. The player who is next to choose is the first person to lose the
	 * round
	 * 
	 * @param nextChooser
	 *            A Player who becomes the next to choose
	 */
	public final void setNextToChoose(final Player nextChooser) {
		nextToChoose = nextChooser;
	}

	/**
	 * Gets the next person to choose.
	 * 
	 * @return nextToChoose who chooses next.
	 */
	public final Player getNextToChoose() {
		return nextToChoose;
	}

	/**
	 * Sets current player to whoever is legally allowed to be next.
	 */
	public final void nextPlayer() {
		if (players.indexOf(currentPlayer) == players.size() - 1) {
			currentPlayer = players.get(0);

		} else {
			currentPlayer = players.get(players.indexOf(currentPlayer) + 1);
		}
		if (!currentPlayer.isActive()) {
			nextPlayer();
		}
	}

	/**
	 * Sets the current player based on the argument.
	 * 
	 * @param curr
	 *            the player you want to be set to the current player
	 */
	public final void setCurrentPlayer(final Player curr) {
		currentPlayer = curr;
	}

	/**
	 * Sets the players names.
	 * 
	 * @param playerIndex
	 *            the player index.
	 * @param name
	 *            the player name.
	 */
	public final void setPlayerName(final int playerIndex, final String name) {
		players.get(playerIndex).setName(name);

	}

	/**
	 * Gets the name and scores of players for the scoreboard.
	 * 
	 * @return result the result of names and scores.
	 */
	public final String getNamesAndScores() {
		String result = "";
		for (int i = 0; i < players.size(); i++) {
			result += players.get(i).getName() + "\t\t Score: "
					+ players.get(i).getScore() + "\n";
		}
		return result;
	}

	/**
	 * Get the current player.
	 * 
	 * @return currentPlayer current player.
	 */
	public final Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Sets all players to active again.
	 */
	public final void newRound() {
		for (int i = 0; i < players.size(); i++) {
			// All players are back in for the next round
			players.get(i).newRound();
		}
		currentPlayer = nextToChoose;
	}

	/**
	 * Get any player.
	 * 
	 * @param indexOfPlayer
	 *            player index.
	 * @return player player.
	 */
	public final Player getPlayer(final int indexOfPlayer) {
		return players.get(indexOfPlayer);
	}

	/**
	 * Gets the number of players.
	 * 
	 * @return playerSize number of players.
	 */
	public final int getNumOfPlayers() {
		return players.size();
	}

	/**
	 * Get number of eliminated players.
	 * 
	 * @return count number of eliminated players.
	 */
	public final int getNumOfEliminatedPlayers() {
		int count = 0;
		for (int i = 0; i < players.size(); i++) {
			if (!players.get(i).isActive()) {
				count++;
			}
		}
		return count;

	}

}