package FisherCooker.nodes;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import FisherCooker.util.VARS;


public class CookNode extends Node{
  
	private static final WidgetChild COOK_BUTTON = Widgets.get(1370, 38);
	@Override
	public boolean activate() {
		return VARS.fireLit;
	}

	@Override
	public void execute() {	
			while(SceneEntities.getNearest(VARS.BONFIRE_ID) == null){
				Task.sleep(50,70);
			}
			while(Inventory.contains(VARS.FISH_IDS)){
				cook();
				Task.sleep(1000, 2000);
			}
			VARS.fireLit = false;
		
	}
	
	public boolean cook(){
		SceneObject fire = SceneEntities.getNearest(VARS.BONFIRE_ID);
		while(Widgets.get(1251, 18 ).visible()){//CANCEL is open
			Task.sleep(50,75);
		}
		if(Inventory.contains(VARS.FISH_IDS[0])){
			Timer t = new Timer(2000);
			if(Inventory.getItem(VARS.FISH_IDS[0]).getWidgetChild().click(true)){
				while(!Inventory.isItemSelected() && t.isRunning()){
					Task.sleep(50, 70);
				}
				if(fire.click(true)){
					t.reset();
					while(!COOK_BUTTON.visible() && t.isRunning()){
						Task.sleep(300, 700);
					}
					if(COOK_BUTTON.click(true)){
						t.reset();
						while(Inventory.contains(VARS.FISH_IDS[0])){
							if(!t.isRunning() && !Widgets.get(1251, 18).visible()){
								return false;
							}
							Task.sleep(1000, 2000);
						}
					}
				}
			}
		}
		else if(Inventory.contains(VARS.FISH_IDS[1])){
			Timer t = new Timer(2000);
			if(Inventory.getItem(VARS.FISH_IDS[1]).getWidgetChild().click(true)){
				while(!Inventory.isItemSelected() && t.isRunning()){
					Task.sleep(50, 70);
				}
				if(fire.click(true)){
					t.reset();
					while(!COOK_BUTTON.visible() && t.isRunning()){
						Task.sleep(300, 700);
					}
					if(COOK_BUTTON.click(true)){
						t.reset();
						while(Inventory.contains(VARS.FISH_IDS[1])){
							if(!t.isRunning() && !Widgets.get(1251, 18).visible()){
								return false;
							}
							Task.sleep(1000, 2000);
						}
					}
				}
			}
		}
		return true;
	}
}
