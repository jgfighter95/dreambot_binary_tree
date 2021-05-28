package framework;

public class ActionController {
	private final Node root;
	private Action action;
	
	public ActionController(TreeBuilder treeBuilder) {
		this.root = treeBuilder.build(this);
	}
	
	public void start() {
		nextNode();
	}
	
	public int onLoop() {
		return action.onLoop();
	}
	
	public void nextNode() {
		action = root.execute();
		action.onStart();
	}
}