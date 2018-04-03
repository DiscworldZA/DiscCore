package disc.mods.core.util.queue;


import net.minecraft.util.NonNullList;

import java.util.Iterator;

public class SimpleObjectQueue<T extends IQueueable, H extends IQueueHandler> {
	public final NonNullList<T> list = NonNullList.<T>create();
	public T current;
	private Iterator<T> iterator = list.iterator();

	private H handler;

	public SimpleObjectQueue(IQueueHandler handler) {
		this.handler = (H) handler;
	}

	public void add(T t) {
		t.setHandler(handler.getHandler());
		list.add(t);
	}

	public T get(int index) {
		return list.get(index);
	}

	public boolean has() {
		return list.size() > 0;
	}

	public T getFirst() {
		if (has()) return (iterator = list.iterator()).next();
		return null;
	}

	public boolean hasNext() {
		return iterator.hasNext();
	}

	public T getNext() {
		if (hasNext()) return iterator.next();
		return null;
	}

	public void remove(int index) {
		list.remove(index);
	}

	public void remove(T t) {
		list.remove(t);
	}

	public T pop() {
		return (current = getNext());
	}

	public void pushBack() {
		remove(current);
		add(current);
		iterator = list.iterator();
	}

	public NonNullList<T> all() {
		return list;
	}
}
