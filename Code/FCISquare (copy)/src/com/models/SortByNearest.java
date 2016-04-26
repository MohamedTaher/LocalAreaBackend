package com.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortByNearest implements Sort{
	
	private double lat;
	private double lng;
	public SortByNearest() {
		// TODO Auto-generated constructor stub
	}
	
	public SortByNearest(int userID) {
		UserModel um = UserModel.search(userID);
		lat = um.getLat();
		lng = um.getLon();
	}

	@Override
	public ArrayList<Checkin> sort(ArrayList<Checkin> list) {
		Collections.sort(list, new Comparator<Checkin>() {

			@Override
			public int compare(Checkin o1, Checkin o2) {
				double dist1 = Math.abs(lat - o1.getCheckinPlace().getLat()) + Math.abs(lng - o1.getCheckinPlace().getLng());
				double dist2 = Math.abs(lat - o2.getCheckinPlace().getLat()) + Math.abs(lng - o2.getCheckinPlace().getLng());
				if(dist1 > dist2)
					return 1;
				if(dist1 < dist2)
					return -1;
				return 0;
			}
			
		});
		return list;
	}
}
