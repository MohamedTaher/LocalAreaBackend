package com.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortByRate implements Sort{

	public SortByRate() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Checkin> sort(ArrayList<Checkin> list) {
		Collections.sort(list, new Comparator<Checkin>() {

			@Override
			public int compare(Checkin o1, Checkin o2) {
				if(o1.getCheckinPlace().getRateSum() * o2.getCheckinPlace().getUserNum() > o2.getCheckinPlace().getRateSum() * o1.getCheckinPlace().getUserNum())
					return 1;
				else if(o1.getCheckinPlace().getRateSum() * o2.getCheckinPlace().getUserNum() < o2.getCheckinPlace().getRateSum() * o1.getCheckinPlace().getUserNum())
					return -1;
				return 0;
			}
			
		});
		return list;
	}

}
