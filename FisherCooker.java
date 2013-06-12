package FisherCooker;


import java.awt.Graphics;

import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.*;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Timer;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

import FisherCooker.nodes.*;
import FisherCooker.util.*;

@Manifest(authors = { "Smart" }, description = "Fishes and cooks trout and salmon at Barbarian Village", name = "SmartBarbFisher")

public class FisherCooker extends ActiveScript implements PaintListener,MessageListener, MouseListener {
  
private static final Node[] COOK_JOBS = {new FishNode(), new ChopNode(), new FireNode(), new CookNode()};
private static final Node[] POWER_JOBS = {new FishNode(), new DropNode()};
private static final Node ANTIBAN = new AntibanNode();

private static int startFishingExp = 0;
private static int startCookingExp = 0;
private static int randNum = 0;
private static int troutCaught = 0;
private static int salmonCaught = 0;

private static boolean paint = true;
private static boolean optionChosen = false;
	@Override
	public void onStart(){
		while(!optionChosen){
			Task.sleep(50,75);
		}
		startFishingExp = Skills.getExperience(Skills.FISHING);
		startCookingExp = Skills.getExperience(Skills.COOKING);
	}

	@Override
	public int loop() {	
		randNum = Random.nextInt(0, 30);
		if(randNum > 28){
			ANTIBAN.execute();
			return 200;
		}
		else{
			if(VARS.cook){
				for (Node job : COOK_JOBS) {
					if(job.activate()) {
						job.execute();
						return 200;
					}
				}		
			}
			else{
				for (Node job : POWER_JOBS) {
					if(job.activate()) {
						job.execute();
						return 200;
					}
				}	
			}
		}
		return 50;
	}
	
	 private static Image getImage(String url) {
	        try {
	            return ImageIO.read(new URL(url));
	        } catch(IOException e) {
	            return null;
	        }
	    }
	 private static final Color color1 = new Color(255, 255, 255);
	 private static final Color color2 = new Color(102, 102, 255, 149);
	 private static final Color color3 = new Color(0, 0, 0);
	 private static final Color color4 = new Color(51, 51, 255);
	 private static final Color color5 = new Color(204, 204, 255);
	 private static final BasicStroke stroke1 = new BasicStroke(1);
	 private static final Font font1 = new Font("Arial", 1, 12);
	 private static final Font font2 = new Font("Arial", 1, 14);
	 private static final Image img1 = getImage("http://i44.tinypic.com/30xdd09.png");
	 private static final Timer timer = new Timer(0);



	    public void onRepaint(Graphics g1) {
	    	if(optionChosen){
		    	if(paint){
			        Graphics2D g = (Graphics2D)g1;
			        g.drawImage(img1, -3, 354, null);
			        g.setFont(font1);
			        g.setColor(color1);
			        g.drawString(timer.toElapsedString(), 124, 404);
			        g.drawString(Skills.getExperience(Skills.FISHING) - startFishingExp + " (" + ((long)3600000*(Skills.getExperience(Skills.FISHING) - startFishingExp))/(timer.getElapsed()) + " p/H) in Fishing and "+ ((Skills.getExperience(Skills.COOKING) - startCookingExp) + " (" + ((long)3600000*(Skills.getExperience(Skills.COOKING) - startCookingExp))/(timer.getElapsed()) + " p/H) in Cooking") , 112, 428);
			        g.drawString(String.valueOf(troutCaught), 118, 456);
			        g.drawString(String.valueOf(salmonCaught), 134, 482);
		    	}
	    	}
	    	else{
	    	   Graphics2D g = (Graphics2D)g1;
	           g.setColor(color2);
	           g.fillRoundRect(31, 353, 443, 99, 16, 16);
	           g.setColor(color3);
	           g.setStroke(stroke1);
	           g.drawRoundRect(31, 353, 443, 99, 16, 16);
	           g.setColor(color4);
	           g.fillRect(46, 384, 186, 39);
	           g.setColor(color3);
	           g.drawRect(46, 384, 186, 39);
	           g.setColor(color4);
	           g.fillRect(257, 383, 188, 39);
	           g.setColor(color3);
	           g.drawRect(257, 383, 188, 39);
	           g.setFont(font1);
	           g.setColor(color5);
	           g.drawString("Powerfish", 109, 408);
	           g.drawString("Fish and Cook", 313, 409);
	           g.setFont(font2);
	           g.setColor(color3);
	           g.drawString("Click on the preferred option!", 137, 373);
	    	}
	    }
	    public void messageReceived(MessageEvent e) {
	        if (e.getMessage().contains("catch a trout"))
	                troutCaught++;
	        else if (e.getMessage().contains("catch a salmon"))
	        		salmonCaught++;
	        
	    }

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if(optionChosen){
				if(paint)
					paint = false;
				else
					paint = true;
			}
			else{
				if(Mouse.getY()< 423 && Mouse.getY() > 382){
					if(Mouse.getX() < 235 && Mouse.getX() > 47){
						VARS.cook = false;
						optionChosen = true;
					}
					else if(Mouse.getX() < 445 && Mouse.getX() > 258){
						VARS.cook = true;
						optionChosen = true;
					}
				}	
			}
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			
		}
	   


	
}
