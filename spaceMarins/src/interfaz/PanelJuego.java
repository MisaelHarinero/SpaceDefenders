package interfaz;

import model.Marine;
import model.Sprite;
import model.Zerg;
import sprites.MarineSprites;
import sprites.SpriteLife;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;

public class PanelJuego implements IGameScreen {
	private ArrayList<Marine> squad;
	private final static int MAX_WIDTH = 40;
	private final static int INVULNERABILITY = 20;
	private double seconds;
	private JLabel jlPoints;
	private BufferedImage asteroid;
	private Timer time;
	private BufferedImage background;
	private int cristal;
	private MarineSprites spritesVector;
	private boolean resizeBackground;
	private ArrayList<Zerg>zergs;
	private final static int COST_MARINE = 10;
	private final static  Point SQUADPOS [] = {new Point(0,0),new Point(10,-10),new Point(10,10),new Point(-10,10),new Point(-10,-10),new Point(20,-20),new Point(20,20),new Point(-20,20),new Point(-20,-20)
			,new Point(30,-30),new Point(30,30),new Point(-30,30),new Point(-30,-30)};
	private int refreshTime;
	private PantallaJuego pantallaJuego;
	public PanelJuego(PantallaJuego pantallaJuego) {
		this.pantallaJuego = pantallaJuego;
	}




	public void paintBackground(Graphics g) {
		g.drawImage(this.background,0,0,null);
	}
	public void paintSpraits(Graphics g){
		if (this.refreshTime>=INVULNERABILITY){
			//Sprite.comprobarSprites(this.squad);
		}
		for (int i = 0; i <this.squad.size() ; i++) {
			g.drawImage(this.squad.get(i).getCanvas(), this.squad.get(i).getX(), this.squad.get(i).getY(), this.squad.get(i).getWidth(), this.squad.get(i).getHeight(), null);
		}
		for (int i = 0; i <this.squad.size() ; i++) {
			g.drawImage(this.squad.get(i).getLifeBar().getBf(),this.squad.get(i).getLifeBar().getX(),this.squad.get(i).getLifeBar().getY(), SpriteLife.MAX_WIDTH,SpriteLife.MAX_HEIGHT,null);
		}
		for (int i = 0; i <this.squad.size() ; i++) {
			this.squad.get(i).doAction(this.zergs);
		}

		this.jlPoints.setText("SPACEMARINS   ||||||||       Cristal: "+this.cristal+"    	    Soldiers : "+(this.squad.size()==SQUADPOS.length ? "MAX":this.squad.size()));
		this.refreshTime++;
	}
	public void paintEnemies(Graphics graphics){


		for (int i = 0; i < this.zergs.size(); i++) {
			graphics.drawImage(this.zergs.get(i).getCanvas(), this.zergs.get(i).getX(), this.zergs.get(i).getY(), this.zergs.get(i).getWidth(), this.zergs.get(i).getHeight(), null);

		}
		for (int i = 0; i <this.zergs.size() ; i++) {
			graphics.drawImage(this.zergs.get(i).getLifeBar().getBf(),this.zergs.get(i).getLifeBar().getX(),this.zergs.get(i).getLifeBar().getY(), SpriteLife.MAX_WIDTH,SpriteLife.MAX_HEIGHT,null);
		}
		for (int i = 0; i <this.zergs.size() ; i++) {
				zergs.get(i).doAction(pantallaJuego.getWidth(),pantallaJuego.getHeight(),this.squad);
		}

	}



	public BufferedImage chargeSprite(String resource){
		BufferedImage bf = null;
		try {
			File f = new File(resource);
			if (f.exists()){
				bf	= ImageIO.read(f);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bf;
	}
	public void chargeImages(){
		this.background = chargeSprite("resource/background.jpg");
		this.asteroid = this.spritesVector.getmDp();


	}
	public void genComponents(){
		GridBagConstraints settingsObject = new GridBagConstraints();
		settingsObject.gridx= 1;
		settingsObject.gridy=1;
		settingsObject.weightx=1;
		settingsObject.weighty =1;
		settingsObject.fill = GridBagConstraints.BOTH;
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		pantallaJuego.add(panel,settingsObject);
		settingsObject = new GridBagConstraints();
		settingsObject.ipadx = 20;
		settingsObject.gridx=0;
		settingsObject.gridy=0;
		settingsObject.weightx=0;
		settingsObject.weighty =0;
		settingsObject.insets = new Insets(20,20,0,0);
		settingsObject.anchor =GridBagConstraints.NORTHEAST;
		this.jlPoints = new JLabel();
		this.jlPoints.setBackground(Color.darkGray);
		this.jlPoints.setBorder(BorderFactory.createRaisedBevelBorder());
		this.jlPoints.setForeground(Color.WHITE);
		this.jlPoints.setOpaque(true);
		this.jlPoints.setHorizontalAlignment(JLabel.CENTER);
		this.jlPoints.setFont(new Font(Font.SANS_SERIF,Font.BOLD,18));
		pantallaJuego.add(this.jlPoints,settingsObject);
	}
	public BufferedImage resizeImage(int width,int height,BufferedImage img){
		Image image  = img.getScaledInstance(width,height,0);
		BufferedImage bf = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = bf.createGraphics();
		g2d.drawImage(image,0,0,null);
		g2d.dispose();
		return bf;
	}

	public void comprobarMuertos(){
		for (int i = 0; i < this.zergs.size() ; i++) {
				if (this.zergs.get(i).getState().equals("M")){
					this.zergs.remove(i);
					this.cristal+=10;
				}
		}
		for (int i = 0; i <this.squad.size() ; i++) {
				if (this.squad.get(i).getState().equals("M")){
					this.squad.remove(i);
				}
		}
	}
	public void comprobarMuertesEnemigos(){
		for (int i = 0; i <this.squad.size() ; i++) {
			if (this.squad.get(i).getEnemy()!=null &&this.squad.get(i).getEnemy().getState().equals("M") ){
				this.squad.get(i).setEnemy(null);
			}
		}
		for (int i = 0; i <this.zergs.size() ; i++) {
			if (this.zergs.get(i).getEnemy()!=null &&this.zergs.get(i).getEnemy().getState().equals("M") ){
				this.zergs.get(i).setEnemy(null);
			}
		}
	}

	@Override
	public void startComponents() {
		this.spritesVector = new MarineSprites(MAX_WIDTH,MAX_WIDTH);
		Sprite.setMarineSprites(this.spritesVector);
		this.squad = new ArrayList<>();
		this.zergs = new ArrayList<>();

		chargeImages();
		this.cristal = 50;
		this.seconds =0;
		this.time  = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				seconds +=1;
				if (seconds %5 == 0){
					cristal++;
				}
				if (seconds%5 == 0){
					for (int i = 0; i <seconds/5 ; i++) {
						zergs.add(new Zerg(pantallaJuego.getWidth(),pantallaJuego.getHeight()));
					}
				}
			}
		});
		this.refreshTime = 0;
		this.resizeBackground = false;
		pantallaJuego.setLayout(new GridBagLayout());
		genComponents();
		this.zergs.add(new Zerg(pantallaJuego.getWidth(),pantallaJuego.getHeight()));

	}

	@Override
	public void paintElements(Graphics g) {
		if (!this.resizeBackground){
			this.background = resizeImage(pantallaJuego.getWidth(),pantallaJuego.getHeight(),this.background);
			time.start();
			this.resizeBackground = true;
		}
		paintBackground(g);
		paintSpraits(g);
		paintEnemies(g);
		comprobarMuertesEnemigos();
		comprobarMuertos();

	}

	@Override
	public void exeFrame() {
		pantallaJuego.repaint();
	}

	@Override
	public void mouseMove(MouseEvent e) {

	}

	@Override
	public void mouseClick(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)){
			if (cristal >= COST_MARINE){
				this.squad.add(new Marine());
				cristal-=COST_MARINE;
			}
		}else{
			for (int i = 0; i < this.squad.size() ; i++) {
				if (!this.squad.get(i).getState().equals("M")){
					this.squad.get(i).setEndPoints(e.getX()+(int)SQUADPOS[i].getX(),e.getY()+(int)SQUADPOS[i].getY());
				}
			}


		}
	}

	@Override
	public void resizeScreen(ComponentEvent e) {
		this.background = resizeImage(pantallaJuego.getWidth(),pantallaJuego.getHeight(),this.background);
	}
}
