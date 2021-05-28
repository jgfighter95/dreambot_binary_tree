package framework;

public class Leaf<T> extends Node<T> {
	private final Action<T> action;
	
	public Leaf(Action<T> action) {
		this.action = action;
	}
	
	@Override
	public Action execute() {
		return action;
	}
}