package FisherCooker.nodes;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.SceneObject;

import FisherCooker.util.*;

public class FireNode extends Node{
  
	@Override
	public boolean activate() {
		return !VARS.fireLit && Inventory.contains(VARS.LOGS_ID);
	}

	@Override
	public void execute() {		
		SceneObject fire = SceneEntities.getNearest(VARS.BONFIRE_ID);
	
		if(fireIsLit()){
			if(Inventory.contains(VARS.LOGS_ID))
				Inventory.getItem(VARS.LOGS_ID).getWidgetChild().interact("Drop");
			Methods.getOnScreen(fire);
			VARS.fireLit = true;
		}
		else {
			Timer t = new Timer(15000);
			if(Inventory.getItem(VARS.LOGS_ID).getWidgetChild().interact("Light")){
				while(!fireIsLit() && t.isRunning()){
					Task.sleep(50,70);
				}
			}
			if(fireIsLit()){
				if(Inventory.contains(VARS.LOGS_ID))
					Inventory.getItem(VARS.LOGS_ID).getWidgetChild().interact("Drop");
				VARS.fireLit = true;
			}
		}
	}
	
	public boolean fireIsLit(){
		SceneObject fire = SceneEntities.getNearest(VARS.BONFIRE_ID);
		
		if(fire != null){
			if(fire.getLocation().distanceTo() < 2)
				return true;	
		}
		return false;
	}
}
