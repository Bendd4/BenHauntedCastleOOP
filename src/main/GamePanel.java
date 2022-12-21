package main;

import gamestates.Gamestate;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import java.awt.*;
import javax.swing.JLabel;


import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;;

public class GamePanel extends JPanel {
        
	private MouseInputs mouseInputs;
	private Game game;
                  private JLabel jl1 = new JLabel("");
                  
	public GamePanel(Game game) {
		mouseInputs = new MouseInputs(this);
		this.game = game;
                                    this.setLayout(null);
                                    this.add(jl1);  
                                    jl1 .setBounds((int) (45 * Game.SCALE), (int) (29 * Game.SCALE), 50, 50);
                                    setPanelSize();
                                    addKeyListener(new KeyboardInputs(this));
                                    addMouseListener(mouseInputs);
                                    addMouseMotionListener(mouseInputs);
                  }



	private void setPanelSize() {
		Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
		setPreferredSize(size);
		System.out.println("Size: " + GAME_WIDTH + " / " + GAME_HEIGHT);
	}

	public void paintComponent(Graphics g) {
                
		super.paintComponent(g);
		game.render(g);
                                    if (Gamestate.state == Gamestate.PLAYING) {
                                               game.getPlaying().getScore();
                                               jl1.setText(game.getPlaying().getScore()+"k $");
                                               jl1 .setBounds((int) (45 * Game.SCALE), (int) (29 * Game.SCALE), 50, 50);
                                               if(game.getPlaying().getGameComplete()){
                                                       jl1.setFont(new Font("Verdana", Font.PLAIN, 18));
                                                       jl1 .setBounds((int) (335 * Game.SCALE), (int) (175 * Game.SCALE),150, 50);
                                            }
                           }
                                    else if (Gamestate.state == Gamestate.MENU) {
                                            jl1.setText("");
                                   }
	}

	public Game getGame() {
		return game;
	}

}
