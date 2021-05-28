package example.actions;

import example.WoodcutterContext;
import framework.Action;
import framework.ActionController;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.walking.impl.Walking;

public class BankAction extends Action<WoodcutterContext> {
	public BankAction(ActionController actionController, WoodcutterContext context) {
		super(actionController, context);
	}
	
	@Override
	public int onLoop() {
		if (context.bankArea.contains(Players.localPlayer())) {
			if (Bank.isOpen()) {
				if (Inventory.count(item -> !item.getName().toLowerCase().contains("axe")) > 0)	{
					MethodProvider.log("Depositing all items except axe");
					Bank.depositAllExcept(item -> item.getName().toLowerCase().contains("axe"));
				} else {
					Bank.close();
					MethodProvider.log("Closing bank");
					// the action is now done, so tell controller to re-evaluate tree & get next action
					actionController.nextNode();
				}
			} else {
				MethodProvider.log("Opening bank");
				Bank.open();
			}
		} else {
			MethodProvider.log("Walking to bank");
			if (Walking.shouldWalk()) {
				Walking.walk(context.bankTile);
			}
			MethodProvider.sleepUntil(Walking::shouldWalk, Players.localPlayer()::isMoving, 3000, 500);
		}
		return 1000;
	}
	
	@Override
	public void onStart() {
	
	}
}
