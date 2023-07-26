package com.bus.demo.entity;

import java.util.Comparator;

public class SortBySeatId implements Comparator<Seat> { 

	public int compare(Seat a, Seat b)
    {
        return (int) (a.getSeatId() - b.getSeatId());
    }

}
