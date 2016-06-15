
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Dialog popup used for changing the names of the game's players.
 * @author Trevor
 *
 */
public class SetNames {
	
	private Game game;
	
	/**
	 * List of new names for the players.
	 */
	private ArrayList<JTextField> newNames;
	
	/**
	 * Main buttons for accepting or aborting input.
	 */
	private JButton accept, cancel;
	
	/**
	 * Main popup's dialog frame.
	 */
	private JFrame popup;
	
	private JTextArea textArea;
	
	/**
	 * Main constructor to launch the SetNames popup and allow
	 * the user to update names of the players in the game.
	 * @param p list of players passed from the active game
	 */
	public SetNames(final Game g, JTextArea txtA) {
		this.game = g;
		this.textArea = txtA;
		
		final int textWidth = 25, left = 0, right = 1, height = 5,
				frameWidth = 400, scrollSpeed = 16,
				frameHeight = 100 + game.players.size() * 20;
		int row = 0;
		
		popup = new JFrame();
		popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popup.setTitle("Set Player Names");
		popup.setSize(frameWidth, frameHeight);
	
		JPanel dialog = new JPanel();
		dialog.setLayout(new GridBagLayout());
		GridBagConstraints position = new GridBagConstraints();
		position.gridheight = height;
		
		newNames = new ArrayList<JTextField>();
		
		for (int i = 0; i < game.players.size(); i++) {
			JLabel oldName = new JLabel(
							game.players.get(i).getName() + ":   ");			
			position.gridx = left;
			position.gridy = row;
			position.anchor = GridBagConstraints.EAST;
			dialog.add(oldName, position);
			
			JTextField newName = new JTextField(
					game.players.get(i).getName(), textWidth);
			newNames.add(newName);
			position.gridx = right;
			position.gridy = row;
			dialog.add(newName, position);
			
			row += height;
		}
		
		setUpEventListeners();
		
		JPanel buttonCont = new JPanel();
		buttonCont.add(accept, BorderLayout.LINE_START);
		buttonCont.add(cancel, BorderLayout.LINE_END);
		
		
		
		dialog.setVisible(true);
		dialog.setAlignmentX(JPanel.LEFT_ALIGNMENT);
		JScrollPane scroll = new JScrollPane(dialog);
		scroll.getVerticalScrollBar().setUnitIncrement(scrollSpeed);
		scroll.setVisible(true);
		
		popup.getContentPane().add(scroll, BorderLayout.CENTER);
		popup.getContentPane().add(buttonCont, BorderLayout.SOUTH);
		popup.setLocationRelativeTo(null);
		popup.setVisible(true);
	}
	
	/**
	 * This sets up the event listeners.
	 */
	public final void setUpEventListeners() {
		Action acceptInput = new AbstractAction() {
			/**
			 * This is necessary for serializable classes.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(final ActionEvent e) {
				for (int i = 0; i < game.players.size(); i++) {
					if (newNames.get(i).getText().equals("")) {
						JOptionPane.showMessageDialog(
			    				null, "Cannot leave a name blank!");
						return;
					}
					else if (newNames.get(i).getText().length() >= 12){
						JOptionPane.showMessageDialog(
			    				null, newNames.get(i).getText() + " is too long of a name");
						return;
					}
				}
				
				for (int i = 0; i < game.players.size(); i++) {
					game.players.get(i).setName(newNames.get(i).getText());
				}

				JOptionPane.showMessageDialog(
	    				null, "Names updated!");
				
				textArea.setText(game.getNamesAndScores());
				popup.dispose();
			}
		};
		Action cancelInput = new AbstractAction() {
			/**
			 * This is necessary for serializable classes.
			 */
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(final ActionEvent e) {
					popup.dispose();
			}
		};
		
		accept = new JButton("Accept");
		cancel = new JButton("Cancel");
		accept.addActionListener(acceptInput);
		cancel.addActionListener(cancelInput);
	}

}
