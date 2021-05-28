package framework;

import java.util.function.BooleanSupplier;

public class Branch<T> extends Node<T> {
	private BooleanSupplier validate;
	private Node<T> success;
	private Node<T> fail;
	
	public Branch<T> validate(BooleanSupplier validate) {
		this.validate = validate;
		return this;
	}
	
	public Branch<T> success(Node<T> node) {
		success = node;
		return this;
	}
	
	public Branch<T> fail(Node<T> node) {
		fail = node;
		return this;
	}
	
	@Override
	public Action<T> execute() {
		if (validate.getAsBoolean()) {
			return success.execute();
		}
		return fail.execute();
	}
}