package FisherCooker.util;

import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Locatable;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Methods {

	public static void getOnScreen(Locatable l){
		if(!l.getLocation().isOnScreen())
			Camera.turnTo(l);
		if(!l.getLocation().isOnScreen())
			Walking.walk(l);
			
	}
	
	public static boolean clickObject(SceneObject o, String phrase){
		Timer t = new Timer(5000);
		if(o.click(false)){
			t.reset();
			while(!Menu.isOpen() && t.isRunning()){
				Task.sleep(50,75);
			}
		}
		if(Menu.isOpen()){
			for(int i = 0; i < Menu.getItems().length; i++){
				if(Menu.getItems()[i].contains(phrase)){
					if(Menu.clickIndex(i)){
						t.reset();
						while(Menu.isOpen() && t.isRunning()){
							Task.sleep(50,70);
						}
						return true;
					}
				}
			}
		}
		return false;
	}

}
