//==============================================================================
//
//	Copyright (c) 2016-
//	Authors:
//	* Joachim Klein <klein@tcs.inf.tu-dresden.de> (TU Dresden)
//
//------------------------------------------------------------------------------
//
//	This file is part of PRISM.
//
//	PRISM is free software; you can redistribute it and/or modify
//	it under the terms of the GNU General Public License as published by
//	the Free Software Foundation; either version 2 of the License, or
//	(at your option) any later version.
//
//	PRISM is distributed in the hope that it will be useful,
//	but WITHOUT ANY WARRANTY; without even the implied warranty of
//	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//	GNU General Public License for more details.
//
//	You should have received a copy of the GNU General Public License
//	along with PRISM; if not, write to the Free Software Foundation,
//	Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
//
//==============================================================================

package common.iterable;

import de.tum.in.naturals.set.ReverseRangeIterator;
import it.unimi.dsi.fastutil.ints.IntIterable;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.ints.IntIterators;

/**
 * An Iterable that returns a Primitive.OfInt iterator for all the integers
 * between two values, first and last (inclusive).
 * If first > last, then the sequence is descending (first, first-1, ..., last+1, last),
 * otherwise it is ascending (first, first+1, ..., last-1, last).
 */
public class RangeIntIterable implements IntIterable
{
	/** The first integer of the sequence */
	private final int first;
	/** The last integer of the sequence */
	private final int last;
	/** Are we ascending? */
	private final boolean ascending;

	/**
	 * Constructor
	 * @param first the first integer in the sequence
	 * @param last the last integer in the sequence
	 */
	public RangeIntIterable(int first, int last)
	{
		this.first = first;
		this.last = last;
		ascending = (first <= last);
	}

	@Override public IntIterator iterator()
	{
		return ascending ? IntIterators.fromTo(first, last + 1) : new ReverseRangeIterator(first, last + 1);
	}
}
