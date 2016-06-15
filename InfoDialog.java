
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.TmdbMovies.MovieMethod;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.model.Genre;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.ProductionCompany;
import info.movito.themoviedbapi.model.ProductionCountry;
import info.movito.themoviedbapi.model.Reviews;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import info.movito.themoviedbapi.model.people.PersonCast;

/**
 * Dialog popup used for displaying the passed movie's information.
 * @author Trevor
 *
 */
public class InfoDialog {
	
	/**
	 * Text fields for the movie information.
	 */
	private JTextArea titleTXT, castTXT, genreTXT, overviewTXT, 
				popularityTXT, productionCompanyTXT, productionCountryTXT, 
				releaseDateTXT, reviewsTXT; 
				//userRatingTXT;
	
	/**
	 * Scroll Panes for the movie information.
	 */
	private JScrollPane titleS, castS, genreS, overviewS, 
				popularityS, productionCompanyS, productionCountryS,
				releaseDateS, reviewsS;
				//, userRatingS;
	
	/**
	 * Working JDialog for the screen.
	 */
	private JPanel dialog;
	
	/**
	 * This string represents the given API Key for TMDB.
	 */
	private final String apiKey = "7c7f9dfdf36e478e9e109e6c6902c175";
	
	/**
	 * This is the API object for TMDB allowing work with the API.
	 */
	private final TmdbApi tmdbApi = new TmdbApi(apiKey);
	
	
	/**
	 * Constructor for the movie information dialog.
	 * @param movieTitle movie title to be looked up.
	 */
	public InfoDialog(final String movieTitle) {
		final int boxWidth = 325, largeBox = 150, mediumBox = 100,
				smallBox = 50, frameWidth = 400, frameHeight = 500,
				scrollSpeed = 16;
		
		JFrame popup = new JFrame();
		popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popup.setTitle("Information: " + movieTitle);
		popup.setSize(frameWidth, frameHeight);
		
		dialog = new JPanel();
		dialog.setLayout(new GridBagLayout());
		GridBagConstraints position = new GridBagConstraints();
		
		updateInfo(movieTitle);
		
		ArrayList<JTextArea> textAreas = new ArrayList<JTextArea>();
		textAreas.add(titleTXT);
		textAreas.add(castTXT);
		textAreas.add(genreTXT);
		textAreas.add(overviewTXT);
		textAreas.add(popularityTXT);
		textAreas.add(productionCompanyTXT);
		textAreas.add(productionCountryTXT);
		textAreas.add(releaseDateTXT);
		textAreas.add(reviewsTXT);
		//textAreas.add(userRatingTXT);
		
		for (JTextArea area : textAreas) {
			area.setWrapStyleWord(true);
			area.setLineWrap(true);
			area.setEditable(false);
		}
		
		titleS = new JScrollPane();
		castS = new JScrollPane();
		genreS = new JScrollPane();
		overviewS = new JScrollPane();
		popularityS = new JScrollPane();
		productionCompanyS = new JScrollPane();
		productionCountryS = new JScrollPane();
		releaseDateS = new JScrollPane();
		reviewsS = new JScrollPane();
		//userRatingS = new JScrollPane();
		
		titleS.setBorder(
				BorderFactory.createTitledBorder("Movie Title"));
		castS.setBorder(
				BorderFactory.createTitledBorder("Cast"));
		genreS.setBorder(
				BorderFactory.createTitledBorder("Genre(s)"));
		overviewS.setBorder(
				BorderFactory.createTitledBorder("Overview"));
		popularityS.setBorder(
				BorderFactory.createTitledBorder("Popularity"));
		productionCompanyS.setBorder(
				BorderFactory.createTitledBorder("Production Company"));
		productionCountryS.setBorder(
				BorderFactory.createTitledBorder("Production Country"));
		releaseDateS.setBorder(
				BorderFactory.createTitledBorder("Release Date"));
		reviewsS.setBorder(
				BorderFactory.createTitledBorder("Review(s)"));
		//userRatingS.setBorder(
				//BorderFactory.createTitledBorder("User Rating"));
				
		titleS.setViewportView(titleTXT);
		castS.setViewportView(castTXT);
		genreS.setViewportView(genreTXT);
		overviewS.setViewportView(overviewTXT);
		popularityS.setViewportView(popularityTXT);
		productionCompanyS.setViewportView(productionCompanyTXT);
		productionCountryS.setViewportView(productionCountryTXT);
		releaseDateS.setViewportView(releaseDateTXT);
		reviewsS.setViewportView(reviewsTXT);
		//userRatingS.setViewportView(userRatingTXT);
				
		castS.setViewportView(castTXT);
		genreS.setViewportView(genreTXT);
		overviewS.setViewportView(overviewTXT);
		popularityS.setViewportView(popularityTXT);
		productionCompanyS.setViewportView(productionCompanyTXT);
		productionCountryS.setViewportView(productionCountryTXT);
		releaseDateS.setViewportView(releaseDateTXT);
		reviewsS.setViewportView(reviewsTXT);
		//userRatingS.setViewportView(userRatingTXT);
		
		titleS.setPreferredSize(new Dimension(boxWidth, smallBox));
		castS.setPreferredSize(new Dimension(boxWidth, largeBox));
		genreS.setPreferredSize(new Dimension(boxWidth, mediumBox));
		overviewS.setPreferredSize(new Dimension(boxWidth, largeBox));
		popularityS.setPreferredSize(new Dimension(boxWidth, smallBox));
		productionCompanyS.setPreferredSize(new Dimension(boxWidth, mediumBox));
		productionCountryS.setPreferredSize(new Dimension(boxWidth, smallBox));
		releaseDateS.setPreferredSize(new Dimension(boxWidth, smallBox));
		reviewsS.setPreferredSize(new Dimension(boxWidth, largeBox));
		//userRatingS.setPreferredSize(new Dimension(PANEL_WIDTH, SMALL_BOX));
		
		ArrayList<JScrollPane> scrollPanes = new ArrayList<JScrollPane>();
		scrollPanes.add(titleS);
		scrollPanes.add(releaseDateS);
		scrollPanes.add(castS);
		scrollPanes.add(genreS);
		scrollPanes.add(overviewS);
		scrollPanes.add(popularityS);
		scrollPanes.add(productionCompanyS);
		scrollPanes.add(productionCountryS);
		scrollPanes.add(reviewsS);
		//scrollPanes.add(userRatingS);
		
		int row = 0;
		position.anchor = GridBagConstraints.WEST;
		for (JScrollPane sp: scrollPanes) {
			position.gridy = row;
			dialog.add(sp, position);
			row++;
		}
		
		dialog.setVisible(true);
		dialog.setAlignmentX(JPanel.LEFT_ALIGNMENT);
		JScrollPane scroll = new JScrollPane(dialog);
		scroll.getVerticalScrollBar().setUnitIncrement(scrollSpeed);
		
		popup.getContentPane().add(scroll, BorderLayout.CENTER);
		popup.setLocationRelativeTo(null);
		popup.setVisible(true);
	}

	/**
	 * Use passed movieTitle to gather more information on the 
	 * movie of interest and update dialog fields.
	 * @param movieTitle movie to get information for.
	 */
	private void updateInfo(final String movieTitle) {

		TmdbSearch search = new TmdbSearch(tmdbApi);
		MovieResultsPage results = search.searchMovie(
				movieTitle, null, "en", true, null);
		List<MovieDb> movieList = results.getResults();
		
		int id = movieList.get(0).getId();
		
		TmdbMovies movies = tmdbApi.getMovies();
		
		// Get information on a movie
		MovieDb movie = movies.getMovie(
				id, "en", MovieMethod.credits,
				MovieMethod.images, MovieMethod.reviews);
		
		fillCast(movie.getCast());
		fillGenres(movie.getGenres());
		fillProductionCompanies(movie.getProductionCompanies());
		fillProductionCountries(movie.getProductionCountries());
		fillReviews(movie.getReviews());
		
		popularityTXT = new JTextArea("" + movie.getPopularity());
		releaseDateTXT = new JTextArea(movie.getReleaseDate());
		overviewTXT = new JTextArea(movie.getOverview());
		titleTXT = new JTextArea(movie.getTitle());
		//userRatingTXT = new JTextArea("" + movie.getUserRating()); 
	}
	
	/**
	 * Fill the cast text with a list of the cast.
	 * @param cast cast list.
	 */
	private void fillCast(final List<PersonCast> cast) {
		castTXT = new JTextArea();
		castTXT.setText("");
		for (int i = 0; i < cast.size(); i++) {
			if (i != 0) {
				castTXT.append("\n");
			}
			castTXT.append(cast.get(i).getName());
		}
	}
	
	/**
	 * Fill the genre text with a list of the applicable genres.
	 * @param genres movie genre(s)
	 */
	private void fillGenres(final List<Genre> genres) {
		genreTXT = new JTextArea();
		for (int i = 0; i < genres.size(); i++) {
			if (i != 0) {
				genreTXT.append("\n");
			}
			genreTXT.append(genres.get(i).getName());
		}
	}
	
	/**
	 * Fill the production companies text with a 
	 * list of the production companies.
	 * @param prodCos movie production companies
	 */
	private void fillProductionCompanies(
			final List<ProductionCompany> prodCos) {
		productionCompanyTXT = new JTextArea();
		for (int i = 0; i < prodCos.size(); i++) {
			if (i != 0) {
				productionCompanyTXT.append("\n");
			}
			productionCompanyTXT.append(prodCos.get(i).getName());
		}
	}
	
	/**
	 * Fill the production companies text with a list 
	 * of the production companies.
	 * @param prodCountries movie production countries
	 */
	private void fillProductionCountries(
			final List<ProductionCountry> prodCountries) {
		productionCountryTXT = new JTextArea();
		for (int i = 0; i < prodCountries.size(); i++) {
			if (i != 0) {
				productionCountryTXT.append("\n");
			}
			productionCountryTXT.append(prodCountries.get(i).getName());
		}
	}
	
	/**
	 * Fill the reviews text with a list of the reviews.
	 * @param reviews movie reviews
	 */
	private void fillReviews(final List<Reviews> reviews) {
		reviewsTXT = new JTextArea();
		for (int i = 0; i < reviews.size(); i++) {
			if (i != 0) {
				reviewsTXT.append("\n");
			}
			
			if (!reviews.get(i).getAuthor().isEmpty()) {
				reviewsTXT.append(reviews.get(i).getAuthor() + ": ");
			}
			reviewsTXT.append(reviews.get(i).getContent());
		}
	}

}
