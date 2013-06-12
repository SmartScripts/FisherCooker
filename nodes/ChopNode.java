package FisherCooker.nodes;


import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.SceneObject;

import FisherCooker.util.VARS;
import FisherCooker.util.Methods;
public class ChopNode extends Node{
	
	
	@Override
	public boolean activate() {
		return Inventory.getCount(VARS.FISH_IDS) > 24 && !Inventory.contains(VARS.LOGS_ID) && !VARS.fireLit ;
	}

	@Override
	public void execute() {		
		SceneObject tree = SceneEntities.getNearest(VARS.TREE_IDS);
	
		if(Inventory.isFull() && Inventory.contains(VARS.FISH_IDS)){
			dropFish();
		}
		if(tree != null){
			if(Players.getLocal().getLocation().distance(tree) < 15){
				Methods.getOnScreen(tree);
				Timer t = new Timer(4000);
				if(tree.interact("Chop")){
					while(!Inventory.contains(VARS.LOGS_ID) && t.isRunning()){
						Task.sleep(50,70);
					}
				}
			}
		}
		else{
			Task.sleep(50, 75);
		}
	}
	
	private void dropFish(){
		int inventoryCount = Inventory.getCount();
		
		if(Inventory.getItem(VARS.FISH_IDS).getWidgetChild().interact("Drop")){
			Timer t = new Timer(5000);
			while(inventoryCount == Inventory.getCount() && t.isRunning()){
				Task.sleep(50,75);
			}
		}
	}
}
