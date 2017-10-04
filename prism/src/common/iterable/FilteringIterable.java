//==============================================================================
//
//	Copyright (c) 2016-
//	Authors:
//	* Steffen Maercker <maercker@tcs.inf.tu-dresden.de> (TU Dresden)
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

import it.unimi.dsi.fastutil.doubles.DoubleIterable;
import it.unimi.dsi.fastutil.doubles.DoubleIterator;
import it.unimi.dsi.fastutil.ints.IntIterable;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.longs.LongIterable;
import it.unimi.dsi.fastutil.longs.LongIterator;

import java.util.Iterator;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

/**
 * Base class for Iterables around FilteringIterators,
 * static constructors for deduplicating entries. */
public abstract class FilteringIterable<T> implements Iterable<T>
{
	protected final Iterable<T> iterable;

	public FilteringIterable(final Iterable<T> iterable)
	{
		this.iterable = iterable;
	}

	public static class Of<T> extends FilteringIterable<T>
	{
		private final Predicate<? super T> predicate;

		public Of(Iterable<T> iterable, Predicate<? super T> predicate)
		{
			super(iterable);
			this.predicate = predicate;
		}

		@Override
		public Iterator<T> iterator()
		{
			return new FilteringIterator.Of<>(iterable, predicate);
		}
	}

	public static class OfInt extends FilteringIterable<Integer> implements IntIterable
	{
		private final IntPredicate predicate;

		public OfInt(IntIterable iterable, IntPredicate predicate)
		{
			super(iterable);
			this.predicate = predicate;
		}

		@Override
		public IntIterator iterator()
		{
			return new FilteringIterator.OfInt((IntIterable) iterable, predicate);
		}
	}

	public static class OfLong extends FilteringIterable<Long> implements LongIterable
	{
		private final LongPredicate predicate;

		public OfLong(LongIterable iterable, LongPredicate predicate)
		{
			super(iterable);
			this.predicate = predicate;
		}

		@Override
		public LongIterator iterator()
		{
			return new FilteringIterator.OfLong((LongIterable) iterable, predicate);
		}
	}

	public static class OfDouble extends FilteringIterable<Double> implements DoubleIterable
	{
		private final DoublePredicate predicate;

		public OfDouble(DoubleIterable iterable, DoublePredicate predicate)
		{
			super(iterable);
			this.predicate = predicate;
		}

		@Override
		public DoubleIterator iterator()
		{
			return new FilteringIterator.OfDouble((DoubleIterable) iterable, predicate);
		}
	}

	public static IntIterable dedupe(IntIterable iterable)
	{
		return () -> FilteringIterator.dedupe(iterable.iterator());
	}

	public static LongIterable dedupe(LongIterable iterable)
	{
		return () -> FilteringIterator.dedupe(iterable.iterator());
	}

	public static DoubleIterable dedupe(DoubleIterable iterable)
	{
		return () -> FilteringIterator.dedupe(iterable.iterator());
	}

	public static <T> Iterable<T> dedupe(Iterable<T> iterable)
	{
		return () -> FilteringIterator.dedupe(iterable.iterator());
	}
}
