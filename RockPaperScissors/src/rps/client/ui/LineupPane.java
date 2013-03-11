/**
 * 
 */
package rps.client.ui;

import static javax.swing.BoxLayout.Y_AXIS;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Carsten Porth
 * 
 */
public class LineupPane {

	private final JPanel lineupPane = new JPanel();
	
	private final JButton newBtn = new JButton("Andere Aufstellung");
	private final JButton okBtn = new JButton("OK");
	private String[] lineup;


	public LineupPane(JPanel parent) {
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
	 * @param container Startaufstellung als Panel
	 */
	private void addLineup(JPanel container) {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 7));
		lineup = generateLineup();
				
		for(int i = 0; i < lineup.length; i++) {
			p.add(new JLabel(lineup[i]));
		}
		
		container.add(p, 1);
	}


	private void bindActions() {
		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Aufstellung registrieren
				// TODO LineupPane ausblenden und über RPS ersten Spieler bestimmen
				
			}			
		});
		
		newBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//addLineup(lineupPane);
				lineupPane.remove(1);
				addLineup(lineupPane);
				lineupPane.repaint();
				lineupPane.revalidate();
			}			
		});
		
	}


	/**
	 * r = Rock
	 * p = Paper
	 * s = Scissors
	 * t = Trap
	 * f = Flag
	 * @return Array mit zufälliger Startaufstellung
	 */
	public static String[] generateLineup() {
		// Array für Startaufstellung
		String[] lineup = new String[14];
		
		// Flagge und Falle immer in der Aufstellung:
		lineup[0] = "t";
		lineup[1] = "f";
		
		// Zur Auswahl stehende andere Figuren:
		String[] figures = {"r", "p", "s"};
		
		// 12 zufällige Figuren auswählen und der Startaufstellung hinzufügen:
		for (int n = 0; n < 12; n++) {
			int rand = (int) (Math.random() * 3);
			lineup[n+2] = figures[rand];
		}
		
		Collections.shuffle(Arrays.asList(lineup));	//Mischen
		return lineup;
	}

	public void show() {
		lineupPane.setVisible(true);
	}

	public void hide() {
		lineupPane.setVisible(false);
	}
}
