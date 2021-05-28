package example;

import framework.ActionController;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;

@ScriptManifest(name="Woodcutter Binary Tree", author = "JGFighter95", category = Category.WOODCUTTING, version = 1)
public class WoodcutterScript extends AbstractScript {
	private ActionController controller;
	
	@Override
	public int onLoop() {
		return controller.onLoop();
	}
	
	@Override
	public void onStart() {
		controller = new ActionController(new WoodcutterTreeBuilder(new WoodcutterContext()));
		controller.start();
	}
}