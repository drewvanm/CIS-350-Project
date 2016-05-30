
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class MainWindow {

	JButton btnStart;
	private int numPlayers;
	private JFrame frmIKnowMovies;
	private JTextField txtNumPlayers;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		
		JLabel lblWelcomeTo = new JLabel("WELCOME TO I KNOW MOVIES LAST MAN STANDING");
		lblWelcomeTo.setBounds(285, 95, 313, 61);
		layeredPane.add(lblWelcomeTo);
		
		btnStart = new JButton("START");
		btnStart.setBounds(564, 258, 97, 25);
		layeredPane.add(btnStart);
		
		txtNumPlayers = new JTextField();
		txtNumPlayers.setBounds(218, 259, 116, 22);
		layeredPane.add(txtNumPlayers);
		txtNumPlayers.setColumns(10);
		SetUpEventListeners();
	}
	public void SetUpEventListeners(){
		Action StartGame = new AbstractAction()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		    	numPlayers = GetNumPlayers();
			    Game g = new Game(numPlayers);
			    frmIKnowMovies.dispose();
			    GameWindow.StartGame(g);
		    	
		    }
		};
		btnStart.addActionListener(StartGame);
		txtNumPlayers.addActionListener(StartGame);
	}
	
	private int GetNumPlayers(){
		try {
	        int num  = Integer.parseInt(txtNumPlayers.getText());
	        return num;

	    } catch (NumberFormatException e) {
	        return -1;
	    }
		
	}
	
	
	
}
