
import info.movito.themoviedbapi.TmdbPeople;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.people.Person;
import info.movito.themoviedbapi.model.people.PersonCredit;
import info.movito.themoviedbapi.model.people.PersonCredits;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * This is a class that represents an Actor.
 * 
 * @author Jonathon Vinsko
 */
public class Actor {
	/**
	 * This is the name of the actor.
	 */
	private String actorName;
	/**
	 * This is a TMDB Person object representing the actor.
	 */
	private Person actorPersonObject;
	/**
	 * This is an integer that represents the actor in the TMDB database.
	 */
	private int actorId;
	/**
	 * This list holds all of the movies the actor is in according to TMDB.
	 */
	private List<PersonCredit> actorMovies;
	/**
	 * This holds the most recent guessed movie name exactly.
	 */
	private String mostRecentMovie;
	/**
	 * Contains the guessed movies for this actor.
	 */
	private List<String> moviesGuessed;
	/**
	 * This string represents the given API Key for TMDB.
	 */
	private final String apiKey = "7c7f9dfdf36e478e9e109e6c6902c175";
	/**
	 * This is the API object for TMDB allowing work with the API.
	 */
	private final TmdbApi tmdbApi = new TmdbApi(apiKey);

	/**
	 * This is a constructor of the Actor class which creates a Person object.
	 * It utilizes The Movie DB API to get an actor's movies.
	 * 
	 * @param pActorName
	 *            is the name of an actor to create an object of.
	 */
	public Actor(final String pActorName) {
		actorName = pActorName;
		moviesGuessed = new ArrayList<String>();

		TmdbSearch tmdbSearch = new TmdbSearch(tmdbApi);
		TmdbPeople.PersonResultsPage personResultsPage;
		personResultsPage = tmdbSearch.searchPerson(actorName, true, null);
		List<Person> actorResults = personResultsPage.getResults();
		actorPersonObject = actorResults.get(0);
		actorName = actorPersonObject.getName();
		actorId = actorPersonObject.getId();

		TmdbPeople people = tmdbApi.getPeople();
		PersonCredits resultCredits = people.getPersonCredits(actorId);
		actorMovies = resultCredits.getCast();
	}

	/**
	 * This is a function that determines if an Actor/Actress is in a movie.
	 * 
	 * @param movieGuess
	 *            is the name of a movie that the Actor may be in.
	 * @return is true if the actor is in the movie, false if not.
	 */
	public final boolean actorInMovie(final String movieGuess) {
		int possibleNums = 5;
		TmdbSearch tmdbSearch = new TmdbSearch(tmdbApi);
		MovieResultsPage movieResultsPage;
		movieResultsPage = tmdbSearch.searchMovie(
				movieGuess, null, "en", true, null);
		List<MovieDb> movieResults = movieResultsPage.getResults();
		for (int j = 0; j < possibleNums && j < movieResults.size(); j++) {
			for (int i = 0; i < actorMovies.size(); i++) {
				if (movieResults.get(j).getTitle().equals(
						actorMovies.get(i).getMovieTitle())) {
					if (!(movieAlreadyGuessed(
							actorMovies.get(i).getMovieTitle()))) {
						mostRecentMovie = actorMovies.get(i).getMovieTitle();
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * This checks if the movie was already guessed or not.
	 * @param guess is the movie being guessed
	 * @return true if it was already guessed. False if not.
	 */
	public final boolean movieAlreadyGuessed(final String guess) {
		if (moviesGuessed.isEmpty()) {
			return false;
		}
		for (int i = 0; i < moviesGuessed.size(); i++) {
			if (guess.equals(moviesGuessed.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Adds to the current actor's movies guessed list.
	 * @param movieName is the movie guessed correctly.
	 */
	public final void addToMoviesGuessed(final String movieName) {
		moviesGuessed.add(movieName);
	}
	
	/**
	 * This is a get function for the List of the Actor's Movies.
	 * 
	 * @return is the list of the Actor's Movies.
	 */
	public final List<PersonCredit> getActorMovies() {
		return actorMovies;
	}

	/**
	 * This is a get function for the Actor's ID.
	 * 
	 * @return is the int representing the Actor's ID.
	 */
	public final int getActorId() {
		return actorId;
	}

	/**
	 * This is a get function for the Actor's name.
	 * 
	 * @return is the string representing the actor's name.
	 */
	public final String getActorName() {
		return actorName;
	}
	
	/**
	 * This is a get funcion for the last guessed movie.
	 * 
	 * @return is the last guessed movie.
	 */
	public final String getMostRecentMovie() {
		return mostRecentMovie;
	}

	/**
	 * This is a get function for the Actor's profile picture.
	 * 
	 * @param width
	 *            the width to create the Jlabel being returned
	 * @param height
	 *            the height to create the Jlabel being returned
	 * @return is the JLabel containing the Actor's profile picture.
	 */
	public final JLabel getActorProfilePic(final int width, final int height) {
		try {
			URL url = new URL("http://image.tmdb.org/t/p//original"
					+ actorPersonObject.getProfilePath());
			ImageIcon pic = new ImageIcon(url);
			Image img = pic.getImage();
			Image newimg = img.getScaledInstance(
					width, height, java.awt.Image.SCALE_SMOOTH);
			ImageIcon newicon = new ImageIcon(newimg);
			JLabel picture = new JLabel(newicon);

			return picture;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		JLabel error = new JLabel("Could Not Find Picture");
		return error;
	}

	/**
	 * This is a get function for the Movie's poster from the most.
	 * 	recent movie
	 * @param width
	 *            the width to create the Jlabel being returned
	 * @param height
	 *            the height to create the Jlabel being returned
	 * @return is the JLabel containing the Actor's profile picture.
	 */
	public final JLabel getMoviePosterPic(final int width, final int height) {
		try {
			TmdbSearch tmdbSearch = new TmdbSearch(tmdbApi);
			MovieResultsPage movieResultsPage;
			movieResultsPage = tmdbSearch.searchMovie(
					mostRecentMovie, null, "en", true, null);
			List<MovieDb> movieResults = movieResultsPage.getResults();
			URL url = new URL("http://image.tmdb.org/t/p//original"
					+ movieResults.get(0).getPosterPath());
			ImageIcon pic = new ImageIcon(url);
			Image img = pic.getImage();
			Image newimg = img.getScaledInstance(
					width, height, java.awt.Image.SCALE_SMOOTH);
			ImageIcon newicon = new ImageIcon(newimg);
			JLabel picture = new JLabel(newicon);

			return picture;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		JLabel error = new JLabel("Could Not Find Picture");
		return error;
	}
}
