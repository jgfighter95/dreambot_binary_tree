package example;

import example.actions.BankAction;
import example.actions.WoodcuttingAction;
import framework.*;
import org.dreambot.api.methods.interactive.Players;

public class WoodcutterTreeBuilder extends TreeBuilder<WoodcutterContext> {
	private ActionController controller;
	
	public WoodcutterTreeBuilder(WoodcutterContext context) {
		super(context);
	}
	
	@Override
	public Node<WoodcutterContext> build(ActionController controller) {
		this.controller = controller;
		return inventoryIsFull();
	}
	
	private Node<WoodcutterContext> inventoryIsFull() {
		return new Branch<WoodcutterContext>()
				.validate(() -> Inventory.isFull()))
				.success(new Leaf<WoodcutterContext>(new BankAction(controller, context)))
				.fail(new Leaf<WoodcutterContext>(new WoodcuttingAction(controller, context)));
	}
}
