/**
 * 
 */
package rps.client.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.rmi.RemoteException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComponent;

import rps.game.Game;
import rps.game.data.Player;

/**
 * @author Carsten Porth
 *
 */
public class PlayingArea extends JPanel {
	
	private Game game = null;
	private Player player;
	int width = 800, height = 600, fieldsX = 7, fieldsY = 6;
	
	/**
	 * Construktor
	 * Legt die Eigenschaften des Spielfeldes fest.
	 * - Anzahl der Felder
	 * - Sichtbarkeit
	 * - Hintergrundfarbe
	 * - Layout (GridLayout)
	 */
	public PlayingArea() {
		setPreferredSize(new Dimension(width, height));
		this.setLayout(new GridLayout(fieldsY, fieldsX));
		setVisible(true);
		setBackground(Color.green);
	}

	/**
	 * Initalisierung des Spielfeldes
	 * @param game Übergabe des gestarteten Spiels
	 * @param player Spieler
	 */
	public void init (Game game, Player player) {
		this.game = game;
		this.player = player;
		try {
			createGameField();
		} catch (RemoteException e) {
			// TODO Automatisch generierter Erfassungsblock
			e.printStackTrace();
		}
	}

	private void createGameField() throws RemoteException {
		for (int i = 0; i < fieldsY; i++)
			for (int j = 0; j < fieldsX; j++) {
				JComponent field;
				
				if (game.getField()[i*fieldsY+j] == null) {
					field = createGras(i,j);
					System.out.println("Erzeuge Gras " + (i++) + "/" + (j++));
				} else {
					field = createFigure(i,j);
				}
				
				field.setPreferredSize(new Dimension(width/fieldsX, height/fieldsY));
				field.setVisible(true);
				add(field);
			}
	}

	private JComponent createFigure(int y, int x) {
		JLabel jl = new JLabel("Figur: "+ x + ","+ y);
		jl.setBackground(Color.black);
		return jl;
	}

	private JComponent createGras(int y, int x) {
		JLabel jl = new JLabel("Gras: " + x + "," + y);
		jl.setBackground(Color.green);
		return jl;
	}

	

}
