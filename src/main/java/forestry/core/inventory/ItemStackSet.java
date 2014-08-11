/*******************************************************************************
 * Copyright (c) 2011-2014 SirSengir.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 * 
 * Various Contributors including, but not limited to:
 * SirSengir (original work), CovertJaguar, Player, Binnie, MysteriousAges
 ******************************************************************************/
package forestry.core.inventory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.minecraft.item.ItemStack;

/**
 * This is a custom data structure designed specifically for using ItemStacks as
 * elements of a Set.
 *
 * Its backed by an ArrayList, so as expected, most operations result in
 * traversing the list one or more times.
 *
 * @author CovertJaguar <http://www.railcraft.info>
 */
public class ItemStackSet implements Set<ItemStack> {

	private final List<ItemStack> set = new ArrayList<ItemStack>();

	@Override
	public int size() {
		return set.size();
	}

	@Override
	public boolean isEmpty() {
		return set.isEmpty();
	}

	@Override
	public boolean contains(Object obj) {
		if (!(obj instanceof ItemStack)) {
			return false;
		}
		ItemStack check = (ItemStack) obj;
		for (ItemStack stack : set) {
			if (InvTools.isItemEqual(stack, check)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<ItemStack> iterator() {
		return set.iterator();
	}

	@Override
	public Object[] toArray() {
		return set.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return set.toArray(a);
	}

	@Override
	public boolean add(ItemStack e) {
		if (contains(e)) {
			return false;
		}
		set.add(e);
		return true;
	}

	@Override
	public boolean remove(Object obj) {
		if (!(obj instanceof ItemStack)) {
			return false;
		}
		boolean changed = false;
		ItemStack check = (ItemStack) obj;
		Iterator<ItemStack> it = set.iterator();
		while (it.hasNext()) {
			ItemStack stack = it.next();
			if (InvTools.isItemEqual(stack, check)) {
				it.remove();
				changed = true;
			}
		}
		return changed;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object obj : c) {
			if (!(obj instanceof ItemStack)) {
				return false;
			}
			if (!contains(obj)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends ItemStack> c) {
		boolean changed = false;
		for (ItemStack stack : c) {
			changed |= add(stack);
		}
		return changed;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		boolean changed = false;
		Iterator<ItemStack> it = set.iterator();
		while (it.hasNext()) {
			ItemStack stack = it.next();
			if (!c.contains(stack)) {
				it.remove();
				changed = true;
			}
		}
		return changed;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean changed = false;
		Iterator<ItemStack> it = set.iterator();
		while (it.hasNext()) {
			ItemStack stack = it.next();
			if (c.contains(stack)) {
				it.remove();
				changed = true;
			}
		}
		return changed;
	}

	@Override
	public void clear() {
		set.clear();
	}
}
