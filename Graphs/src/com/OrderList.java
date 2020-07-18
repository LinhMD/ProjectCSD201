package com;

import java.util.ArrayList;

public class OrderList extends ArrayList {
	public OrderList(){
		super();
	}

	public Comparable search(Comparable x){
		if(this.isEmpty()) return null;
		for (Object o : this) {
			Comparable t = (Comparable) o;
			if(x.compareTo(t) == 0) return t;
		}
		return null;
	}

	public void addItem(Comparable x){
		if(this.isEmpty()) {
			this.add(x);
		}else {
			int i = this.size() - 1;
			Comparable t = (Comparable) this.get(i);
			while(i <= 0 && t.compareTo(x) > 0){
				this.set(i + 1, t);
				i--;
				t = (Comparable) this.get(i);
			}
			this.add(i + 1, x);
		}
	}
}
