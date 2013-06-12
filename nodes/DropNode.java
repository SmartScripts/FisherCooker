package FisherCooker.Nodes;


import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.widget.WidgetChild;
import FisherCooker.util.*;

public class DropNode extends Node{
  
	private final WidgetChild DROP_TROUT_ACTIONBAR = Widgets.get(640, 111);
	private final WidgetChild DROP_SALMON_ACTIONBAR = Widgets.get(640, 106);

	@Override
	public boolean activate() {
		return Inventory.getCount() > 14 ;
	}

	@Override
	public void execute() {		
		while(Inventory.contains(VARS.FISH_IDS[0])){
			if(DROP_TROUT_ACTIONBAR.contains(Mouse.getLocation())){// - on Action Bar
				if(Mouse.click(true))
					Task.sleep(50, 200);
			}
			else{
				Mouse.move(DROP_TROUT_ACTIONBAR.getCentralPoint(), 2, 2);
			}
		} 
		while(Inventory.contains(VARS.FISH_IDS[1])){
			if(DROP_SALMON_ACTIONBAR.contains(Mouse.getLocation())){// 0 on Action Bar
				if(Mouse.click(true))
					Task.sleep(50, 200);
			}
			else{
				Mouse.move(DROP_SALMON_ACTIONBAR.getCentralPoint(), 2, 2);
			}
		} 
	}
}
