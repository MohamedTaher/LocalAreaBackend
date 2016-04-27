package com.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortByCheckin implements Sort{

	public SortByCheckin() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Checkin> sort(ArrayList<Checkin> list) {
		// TODO Auto-generated method stub
		Collections.sort(list, new Comparator<Checkin>() {

			@Override
			public int compare(Checkin o1, Checkin o2) {
				if(o1.getCheckinPlace().getNumberOfCheckins() > o2.getCheckinPlace().getNumberOfCheckins())
					return 1;
				else if(o1.getCheckinPlace().getNumberOfCheckins() < o2.getCheckinPlace().getNumberOfCheckins())
					return -1;
				return 0;
			}
			
		});
		return list;
	}

}
