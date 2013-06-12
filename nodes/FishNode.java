package FisherCooker.nodes;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Locatable;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import FisherCooker.util.*;


public class FishNode extends Node{
  
	private static final int FISHING_SPOT_ID = 328;
	
	private static final WidgetChild EAT_TROUT_ACTIONBAR = Widgets.get(640, 70);
	private static final WidgetChild EAT_SALMON_ACTIONBAR = Widgets.get(640, 75);
	
	@Override
	public boolean activate() {
		if(VARS.cook)
			return Inventory.getCount(VARS.FISH_IDS) <= 24 ;
		else
			return Inventory.getCount() <= 14;
	}

	@Override
	public void execute() {		
		NPC fishingSpot = NPCs.getNearest(FISHING_SPOT_ID);
		if(Widgets.get(1186, 1).visible()){//Inventory can't hold anymore..
			dropFish();
		}
		else if(Widgets.get(1218, 76).visible()){//Skill Advance Guide
			Widgets.get(1218, 76).click(true);
		}
		if(fishingSpot != null){
			getOnScreen(fishingSpot);
			if(Players.getLocal().getInteracting() == null && fishingSpot.interact("Lure")){
				while(Players.getLocal().getInteracting() != null && Inventory.contains(VARS.COOKED_FISH_IDS) ){
					dropFish();
					Task.sleep(50, 75);
				}
			}
		}
	}
	private void getOnScreen(Locatable l){
		if(!l.getLocation().isOnScreen())
			Camera.turnTo(l);
		if(!l.getLocation().isOnScreen())
			Walking.walk(l);
			
	}

	
	private boolean dropFish(){
		while(Inventory.contains(VARS.COOKED_FISH_IDS[0])){
			if(EAT_TROUT_ACTIONBAR.click(false)){
				Timer t = new Timer(1000);
				while(!Menu.isOpen() && t.isRunning()){
					Task.sleep(200,300);
				}
				Menu.clickIndex(1);
			}
			if(Players.getLocal().getInteracting() == null)
				return false;
		} 
		while(Inventory.contains(VARS.COOKED_FISH_IDS[1])){
			if(EAT_SALMON_ACTIONBAR.click(false)){
				Timer t = new Timer(1000);
				while(!Menu.isOpen() && t.isRunning()){
					Task.sleep(200,300);
				}
				Menu.clickIndex(1);
			}
			if(Players.getLocal().getInteracting() == null)
				return false;
		} 
		return true;
	}
}


