package FisherCooker.util;

import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.Locatable;

public class Methods {

  public static void getOnScreen(Locatable l){
		if(!l.getLocation().isOnScreen())
			Camera.turnTo(l);
		if(!l.getLocation().isOnScreen())
			Walking.walk(l);
			
	}
}
