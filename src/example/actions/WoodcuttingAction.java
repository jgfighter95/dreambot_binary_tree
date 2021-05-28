package example.actions;

import example.AnimationConstants;
import example.WoodcutterContext;
import framework.Action;
import framework.ActionController;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.wrappers.interactive.GameObject;

public class WoodcuttingAction extends Action<WoodcutterContext> {
	public WoodcuttingAction(ActionController actionController, WoodcutterContext context) {
		super(actionController, context);
	}
	
	@Override
	public int onLoop() {
		if (context.treeArea.contains(Players.localPlayer())) {
			if (Inventory.isFull()) {
				MethodProvider.log("Inventory is full");
				// the action is now done, so tell controller to re-evaluate tree & get next action
				actionController.nextNode();
			} else {
				if (Players.localPlayer().getAnimation() != AnimationConstants.WOODCUTTING) {
					MethodProvider.log("Finding a tree");
					GameObject tree = GameObjects.closest(gameObject -> {
						boolean isATree = gameObject.getName().equals("Tree");
						boolean canChop = gameObject.hasAction("Chop down");
						return isATree && canChop;
					});
					if (tree.interact("Chop down")) {
						MethodProvider.log("Chopping the tree");
						MethodProvider.sleepUntil(() -> Players.localPlayer().getAnimation() == AnimationConstants.WOODCUTTING, 5000);
					}
				}
			}
		} else {
			MethodProvider.log("Walking to trees");
			if (Walking.shouldWalk()) {
				Walking.walk(context.treeTile);
			}
			MethodProvider.sleepUntil(Walking::shouldWalk, Players.localPlayer()::isMoving, 3000, 500);
		}
		return 1000;
	}
	
	@Override
	public void onStart() {
		// no initialisation logic required
	}
}
