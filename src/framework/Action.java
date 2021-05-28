package framework;

public abstract class Action<T> {
	protected final ActionController actionController;
	protected final T context;
	
	public Action(ActionController actionController, T context) {
		this.actionController = actionController;
		this.context = context;
	}
	
	public abstract int onLoop();
	
	public abstract void onStart();
}
