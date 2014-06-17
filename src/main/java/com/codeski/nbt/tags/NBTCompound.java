package com.codeski.nbt.tags;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.codeski.nbt.Main;

public class NBTCompound extends NBT implements List<NBT> {
	public static final byte TYPE = 10;
	private List<NBT> payload;

	public NBTCompound(String name, List<NBT> payload) {
		super(name);
		this.payload = payload;
	}

	@Override
	public void add(int index, NBT element) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		this.getPayload().clear();
	}

	@Override
	public boolean contains(Object o) {
		return this.getPayload().contains(o);
	}

	public boolean contains(String name) {
		for (NBT e : this.getPayload())
			if (e.getName().equals(name))
				return true;
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return this.getPayload().containsAll(c);
	}

	@Override
	public NBT get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public NBT get(String name) {
		for (NBT e : this.getPayload())
			if (e.getName().equals(name))
				return e;
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
		return TYPE;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<NBT> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<NBT> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NBT remove(int index) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
		StringBuilder sb = new StringBuilder();
		sb.append("[" + this.getClass().getSimpleName() + "] Name:\"" + name + "\" " + this.size() + " entries\n");
		Main.tabs += "  ";
		for (NBT nbt : payload)
			sb.append(Main.tabs + nbt + "\n");
		Main.tabs = Main.tabs.substring(0, Main.tabs.length() - 2);
		sb.append(Main.tabs + "[/" + this.getClass().getSimpleName() + "]\n");
		return sb.substring(0, sb.length() - 1);
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
	public void writePayload(ByteBuffer bytes) {
		for (NBT e : this.getPayload())
			bytes.put(e.toNBT());
		new NBTEnd().writePayload(bytes);
	}
}
