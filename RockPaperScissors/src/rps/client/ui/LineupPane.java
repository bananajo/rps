/**
 * 
 */
package rps.client.ui;

import static javax.swing.BoxLayout.Y_AXIS;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import rps.game.Game;
import rps.game.data.FigureKind;
import rps.game.data.Player;

/**
 * @author Carsten Porth
 * 
 */
public class LineupPane {

	private final JPanel lineupPane = new JPanel();

	private final JButton newBtn = new JButton("Andere Aufstellung");
	private final JButton okBtn = new JButton("OK");
	private String[] lineup;

	private Game game;
	private Player player;

	public LineupPane(JPanel parent, Game game, Player player) {
		this.game = game;
		this.player = player;

		lineupPane.setLayout(new BoxLayout(lineupPane, Y_AXIS));
		lineupPane.add(new JLabel("Bitte wähle deine Startaufstellung"));

		addLineup(lineupPane);

		lineupPane.add(newBtn);
		lineupPane.add(okBtn);

		parent.add(lineupPane);

		bindActions();
	}

	/**
	 * Setzt die Startaufstellung zusammen und gibt sie als Panel zurück
	 * 
	 * @param container
	 *            Startaufstellung als Panel
	 */
	private void addLineup(JPanel container) {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 7));
		lineup = generateLineup();

		for (int i = 0; i < lineup.length; i++) {
			p.add(new JLabel(lineup[i]));
		}

		container.add(p, 1);
	}

	private void bindActions() {
		okBtn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				FigureKind[] assignment = new FigureKind[14];
				for (int n = 0; n < lineup.length; n++) {
					switch (lineup[n]) {
					case "r":
						assignment[n] = FigureKind.ROCK;
						break;
					case "p":
						assignment[n] = FigureKind.PAPER;
						break;
					case "s":
						assignment[n] = FigureKind.SCISSORS;
						break;
					case "f":
						assignment[n] = FigureKind.FLAG;
						break;
					case "t":
						assignment[n] = FigureKind.TRAP;
						break;
					}
				}
				
				try {
					game.setInitialAssignment(player, assignment);
				} catch (RemoteException e1) {
					// TODO Automatisch generierter Erfassungsblock
					e1.printStackTrace();
				}
				
				lineupPane.hide();
			}
		});

		newBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// addLineup(lineupPane);
				lineupPane.remove(1);
				addLineup(lineupPane);
				lineupPane.repaint();
				lineupPane.revalidate();
			}
		});

	}

	/**
	 * r = Rock p = Paper s = Scissors t = Trap f = Flag
	 * 
	 * @return Array mit zufälliger Startaufstellung
	 */
	public static String[] generateLineup() {
		// Array für Startaufstellung
		String[] lineup = { "f", "t", "s", "s", "s", "s", "r", "r", "r", "r",
				"p", "p", "p", "p" };
		Collections.shuffle(Arrays.asList(lineup)); // Mischen
		return lineup;
	}

	public void show() {
		lineupPane.setVisible(true);
	}

	public void hide() {
		lineupPane.setVisible(false);
	}
}
