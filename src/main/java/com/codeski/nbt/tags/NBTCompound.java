package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Fully formed tags, followed by an end tag.
 */
public class NBTCompound extends NBT implements List<NBT> {
	private List<NBT> payload;

	public NBTCompound(String name, List<NBT> payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public void add(int index, NBT element) {
		this.getPayload().add(index, element);
	}

	@Override
	public boolean add(NBT e) {
		return this.getPayload().add(e);
	}

	@Override
	public boolean addAll(Collection<? extends NBT> c) {
		return this.getPayload().addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends NBT> c) {
		return this.getPayload().addAll(index, c);
	}

	@Override
	public void clear() {
		this.getPayload().clear();
	}

	@Override
	public boolean contains(Object o) {
		return this.getPayload().contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return this.getPayload().containsAll(c);
	}

	@Override
	public boolean equals(Object obj) {
		for (int i = 0; i < this.size(); ++i)
			if (!this.get(i).equals(((NBTCompound) obj).get(i)))
				return false;
		return this.getName() == null && ((NBT) obj).getName() == null || this.getName().equals(((NBT) obj).getName());
	}

	@Override
	public NBT get(int index) {
		return this.getPayload().get(index);
	}

	public NBT get(String name) {
		for (NBT e : this.getPayload())
			if (e.getName().equals(name))
				return e;
		return null;
	}

	public NBTCompound getCompound(String name) {
		for (NBT e : this.getPayload())
			if (e.getName().equals(name))
				if (e.getType() == COMPOUND)
					return (NBTCompound) e;
		return null;
	}

	@Override
	public int getLength() {
		int length = 1;
		if (this.getName() != null)
			length += 3 + (short) this.getName().getBytes(Charset.forName("UTF-8")).length;
		for (NBT e : this.getPayload())
			length += e.getLength();
		return length;
	}

	@Override
	public List<NBT> getPayload() {
		return payload;
	}

	@Override
	public byte getType() {
		return NBT.COMPOUND;
	}

	@Override
	public int indexOf(Object o) {
		return this.getPayload().indexOf(o);
	}

	@Override
	public boolean isEmpty() {
		return this.getPayload().isEmpty();
	}

	@Override
	public Iterator<NBT> iterator() {
		return this.getPayload().iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		return this.getPayload().lastIndexOf(o);
	}

	@Override
	public ListIterator<NBT> listIterator() {
		return this.getPayload().listIterator();
	}

	@Override
	public ListIterator<NBT> listIterator(int index) {
		return this.getPayload().listIterator(index);
	}

	@Override
	public NBT remove(int index) {
		return this.getPayload().remove(index);
	}

	@Override
	public boolean remove(Object o) {
		return this.getPayload().remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return this.getPayload().removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return this.getPayload().retainAll(c);
	}

	@Override
	public NBT set(int index, NBT element) {
		return this.getPayload().set(index, element);
	}

	@Override
	public void setPayload(Object payload) {
		this.payload = (List<NBT>) payload;
	}

	@Override
	public int size() {
		return this.getPayload().size();
	}

	@Override
	public List<NBT> subList(int fromIndex, int toIndex) {
		return this.getPayload().subList(fromIndex, toIndex);
	}

	@Override
	public Object[] toArray() {
		return this.getPayload().toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return this.getPayload().toArray(a);
	}

	@Override
	public String toJSON() {
		String str = "";
		if (this.getName() != null)
			str += "\"" + this.getName() + "\": ";
		str += "{ ";
		for (NBT e : this.getPayload())
			str += e.toJSON() + ", ";
		str = str.substring(0, str.length() - 2);
		str += " }";
		return str;
	}

	@Override
	public String toString() {
		if (this.getName() != null)
			return this.getClass().getSimpleName() + " Name:\"" + this.getName() + "\" " + this.getPayload().size() + " entries";
		else
			return this.getClass().getSimpleName() + " " + this.getPayload().size() + " entries";
	}

	@Override
	public String toXML() {
		String str = "";
		if (this.getName() != null)
			str += "<" + this.getClass().getSimpleName() + " name=\"" + this.getName() + "\">";
		else
			str += "<" + this.getClass().getSimpleName() + ">";
		for (NBT e : this.getPayload())
			str += e.toXML();
		str += "</" + this.getClass().getSimpleName() + ">";
		return str;
	}

	@Override
	protected void writePayload(ByteBuffer bytes) {
		for (NBT e : this.getPayload())
			bytes.put(e.toNBT());
		new NBTEnd().writePayload(bytes);
	}
}
