package framework;

public abstract class TreeBuilder<T> {
	protected final T context;
	
	public TreeBuilder(T context) {
		this.context = context;
	}
	
	public abstract Node build(ActionController controller);
}