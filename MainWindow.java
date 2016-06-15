//package game;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * 
 * @author Taylor
 *
 */
public class MainWindow {

	/**
	 * This button starts the game.
	 */
	private JButton btnStart;
	/**
	 * This determines how many players are playing.
	 */
	private int numPlayers;
	/**
	 * This is the frame of the window.
	 */
	private JFrame frmIKnowMovies;
	/**
	 * This field holds the number of players.
	 */
	private JTextField txtNumPlayers;

	/**
	 * Launch the application.
	 * @param args is the arguments.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmIKnowMovies.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIKnowMovies = new JFrame();
		frmIKnowMovies.setTitle("I Know Movies");
		frmIKnowMovies.setBounds(100, 100, 900, 500);
		frmIKnowMovies.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIKnowMovies.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLayeredPane layeredPane = new JLayeredPane();
		frmIKnowMovies.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		JLabel lblHowManyPeople = new JLabel("How Many People Are Playing?");
		lblHowManyPeople.setBounds(181, 219, 207, 45);
		layeredPane.add(lblHowManyPeople);
		
		JLabel lblWelcomeTo = new JLabel(
				"WELCOME TO I KNOW MOVIES LAST MAN STANDING");
		lblWelcomeTo.setBounds(285, 95, 313, 61);
		layeredPane.add(lblWelcomeTo);
		
		btnStart = new JButton("START");
		btnStart.setBounds(564, 258, 97, 25);
		layeredPane.add(btnStart);
		
		txtNumPlayers = new JTextField();
		txtNumPlayers.setBounds(218, 259, 116, 22);
		layeredPane.add(txtNumPlayers);
		txtNumPlayers.setColumns(10);
		setUpEventListeners();
	}
	
	/**
	 * This sets up event listeners.
	 */
	public final void setUpEventListeners() {
		Action startGame = new AbstractAction() {
		    /**
			 * This is necessary for serializable classes.
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public void actionPerformed(final ActionEvent e) {
		    	numPlayers = getNumPlayers();
		    	if (numPlayers > 0 && numPlayers <= 10) {
		    		Game g = new Game(numPlayers);
				    frmIKnowMovies.dispose();
				    GameWindow.startGame(g);
		    	} else {
		    		JOptionPane.showMessageDialog(
		    				null, "Please Enter Between 1 and 10 Players!");
		    	}
		    }
		};
		btnStart.addActionListener(startGame);
		txtNumPlayers.addActionListener(startGame);
	}
	
	/**
	 * This returns the number of players.
	 * @return is the number of players.
	 */
	private int getNumPlayers() {
		try {
	        int num  = Integer.parseInt(txtNumPlayers.getText());
	        return num;

	    } catch (NumberFormatException e) {
	        return -1;
	    }
		
	}
	
	
	
}