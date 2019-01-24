package interfaz;

import model.Base;
import model.Marine;
import model.Sprite;
import model.Zerg;
import sprites.MarineSprites;
import sprites.SpriteLife;
import sprites.ZergsSprites;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PanelJuego implements IGameScreen {
	private final static int MAX_WIDTH = 40;
	private final static int INVULNERABILITY = 20;

	private int seconds;
	private JLabel jlPoints;
	private Timer time;
	private BufferedImage background;
	private int cristal;
	private MarineSprites spritesVector;
	private boolean resizeBackground;
	private ArrayList<Zerg>zergs;
	private int zergsDie;
	private int squadSelected;
	private boolean gameOver;
	private Base  base;
	/**
	 * Cuenta atras para cambiar de Ronda
	 */
	private final static int TIME_BETWEEN_ROUNDS = 10;
	private int cuentaAtrasRonda;
	/**
	 * Array  de ArrayList de Marines que nos permite controlar diferentes stats
	 */
	private ArrayList<Marine> squads [];
	private final static int MAX_SQUAD = 4;
	private final static int COST_MARINE = 10;

	/**
	 * Numero de la ronda
	 */
	private  int round;
	private final static  Point SQUADPOS [] = {new Point(0,0),new Point(10,-10),new Point(10,10),new Point(-10,10),new Point(-10,-10),new Point(20,-20),new Point(20,20),new Point(-20,20),new Point(-20,-20)
			,new Point(30,-30),new Point(30,30),new Point(-30,30),new Point(-30,-30)};
	private final static int ROUNDS_NUMBER [] = {5,10,15,20,30};
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
		for (int i = 0; i <this.squads.length ; i++) {
			for (int j = 0; j <this.squads[i].size() ; j++) {
				g.drawImage(this.squads[i].get(j).getCanvas(), this.squads[i].get(j).getX(), this.squads[i].get(j).getY(), this.squads[i].get(j).getWidth(), this.squads[i].get(j).getHeight(), null);

			}
		}
		for (int i = 0; i <this.squads.length ; i++) {
			for (int j = 0; j <this.squads[i].size() ; j++) {
				g.drawImage(this.squads[i].get(j).getLifeBar().getBf(),this.squads[i].get(j).getLifeBar().getX(),this.squads[i].get(j).getLifeBar().getY(), SpriteLife.MAX_WIDTH,SpriteLife.MAX_HEIGHT,null);
			}
		}
		for (int i = 0; i <this.squads.length ; i++) {
			for (int j = 0; j <this.squads[i].size() ; j++) {
				this.squads[i].get(j).doAction(this.zergs);
			}
		}

		this.jlPoints.setText("SPACEMARINS   ||||||||       Cristal: "+this.cristal+"    	    Soldiers : "+(this.squads[this.squadSelected].size()==SQUADPOS.length ? "MAX":this.squads[this.squadSelected].size())+"      |||||      SQUAD_SELECTED : "+(this.squadSelected != -1? this.squadSelected+1 :" NONE"));
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
				zergs.get(i).doAction(pantallaJuego.getWidth(),pantallaJuego.getHeight(),this.squads);
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
					this.zergsDie++;
				}
		}
		for (int i = 0; i <this.squads.length ; i++) {
			for (int j = 0; j < this.squads[i].size(); j++) {
				if (this.squads[i].get(j).getState().equals("M")){
					this.squads[i].remove(j);
				}
			}
		}
	}
	public void paintBase(Graphics graphics){
		graphics.drawImage(base.getCanvas(),base.getX(),base.getY(),base.getWidth(),base.getHeight(),null);
		graphics.drawImage(base.getLifeBar().getBf(),base.getLifeBar().getX(),base.getLifeBar().getY(),base.getWidth(),SpriteLife.MAX_HEIGHT,null);
		this.gameOver = base.doACtion();
		if (this.gameOver){
			this.pantallaJuego.removeAll();
			LooseScreen looseScreen = new LooseScreen(this.pantallaJuego);
			looseScreen.startComponents();
			this.time.stop();
			this.pantallaJuego.setGameScreen(looseScreen);
		}
	}
	public void comprobarMuertesEnemigos(){

		for (int i = 0; i <this.squads.length ; i++) {
			for (int j = 0; j < this.squads[i].size(); j++) {
				if (this.squads[i].get(j).getEnemy()!=null &&this.squads[i].get(j).getEnemy().getState().equals("M") ){
					this.squads[i].get(j).setEnemy(null);
				}
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
		Sprite.setZergSprites(new ZergsSprites());
		this.squads  = new ArrayList [4];
		for (int i = 0; i <squads.length ; i++) {
			this.squads[i] = new ArrayList<>();
		}
		this.base = new Base(pantallaJuego.getWidth()/2,pantallaJuego.getHeight()/2);
		this.zergs = new ArrayList<>();
		this.zergsDie = 0;
		this.round = -1;
		this.gameOver = false;
		this.squadSelected  = 0;
		chargeImages();
		this.cristal = 50;
		this.seconds =0;
		this.time  = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				seconds +=1;
				if (base.getSecondsConstruction()>=0){
					base.setSecondsConstruction(base.getSecondsConstruction()-1);
				}
				if (seconds %5 == 0){
					cristal++;
				}
				if ( base.getSecondsConstruction()<0){
					if (cuentaAtrasRonda >=0){
						cuentaAtrasRonda--;
					}
				}
			}
		});
		this.refreshTime = 0;
		this.resizeBackground = false;
		pantallaJuego.setLayout(new GridBagLayout());
		genComponents();
	}

	@Override
	public void paintElements(Graphics g) {
		if (!this.resizeBackground){
			this.background = resizeImage(pantallaJuego.getWidth(),pantallaJuego.getHeight(),this.background);
			time.start();
			this.resizeBackground = true;
		}
		paintBackground(g);
		paintBase(g);
		paintSpraits(g);
		paintEnemies(g);
		comprobarMuertesEnemigos();
		comprobarMuertos();
		if (base.getSecondsConstruction()<0){
			comprobarWin();
		}

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
				if (this.squadSelected != -1 && this.squads[this.squadSelected].size()<=SQUADPOS.length){
					this.squads [this.squadSelected].add(new Marine());
					cristal-=COST_MARINE;
				}
			}
		}else{
			for (int i = 0; i < this.squads[this.squadSelected].size() ; i++) {

				if (!this.squads[this.squadSelected].get(i).getState().equals("M")){
					this.squads[this.squadSelected].get(i).setEndPoints(e.getX()+(int)SQUADPOS[i].getX(),e.getY()+(int)SQUADPOS[i].getY());
				}
			}

		}
	}

	@Override
	public void resizeScreen(ComponentEvent e) {
		this.background = resizeImage(pantallaJuego.getWidth(),pantallaJuego.getHeight(),this.background);
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		switch (e.getKeyCode()){
			case KeyEvent.VK_1:{
				this.squadSelected = 0;
				break;
			}
			case KeyEvent.VK_2:{
				this.squadSelected = 1;

				break;
			}
			case KeyEvent.VK_3:{
				this.squadSelected = 2;
				break;
			}
			case KeyEvent.VK_4:{
				this.squadSelected = 3;

				break;
			}
		}
	}
	public void comprobarWin(){
		if (this.zergs.size()==0 && cuentaAtrasRonda<0){
			this.round++;
			System.out.println(round);
			cuentaAtrasRonda=TIME_BETWEEN_ROUNDS;
		}
		if (this.round >= ROUNDS_NUMBER.length){
			WinScreen winScreen = new WinScreen(this.pantallaJuego);
			winScreen.startComponents();
			this.time.stop();
			this.pantallaJuego.setGameScreen(winScreen);
		}else{
			if (cuentaAtrasRonda ==0){

				for (int i = 0; i < ROUNDS_NUMBER[round] ; i++) {
					this.zergs.add(new Zerg(pantallaJuego.getWidth(),pantallaJuego.getHeight(),base));
				}
				cuentaAtrasRonda = -1;
			}

		}
	}
}
