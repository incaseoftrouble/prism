package common.iterable;

import it.unimi.dsi.fastutil.doubles.DoubleIterable;
import it.unimi.dsi.fastutil.doubles.DoubleIterators;
import it.unimi.dsi.fastutil.ints.IntIterable;
import it.unimi.dsi.fastutil.ints.IntIterators;
import it.unimi.dsi.fastutil.longs.LongIterable;
import it.unimi.dsi.fastutil.longs.LongIterators;

import java.util.Iterator;
import java.util.PrimitiveIterator.OfDouble;
import java.util.PrimitiveIterator.OfInt;
import java.util.PrimitiveIterator.OfLong;
import java.util.function.*;

/** Helpers for mapping Iterators to another Iterator, performing some mapping on the elements */
public abstract class MappingIterator<S, T> implements Iterator<T>
{
	protected final Iterator<S> iterator;

	public MappingIterator(Iterable<S> iterable)
	{
		this(iterable.iterator());
	}

	public MappingIterator(Iterator<S> iterator)
	{
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext()
	{
		return iterator.hasNext();
	}

	@Override
	public void remove()
	{
		iterator.remove();
	}

	/** Map an Iterator<S> to an Iterator<T> using the given mapping function */
	public static class From<S, T> extends MappingIterator<S, T>
	{
		protected final Function<? super S, T> function;

		public From(Iterable<S> iterable, Function<? super S, T> function)
		{
			this(iterable.iterator(), function);
		}

		public From(Iterator<S> iterator, Function<? super S, T> function)
		{
			super(iterator);
			this.function = function;
		}

		@Override
		public T next()
		{
			return function.apply(iterator.next());
		}
	}

	/** Map an Iterable<Integer> to a PrimitiveIterator.OfInt */
	public static OfInt toInt(Iterable<Integer> iterable)
	{
		return IntIterators.asIntIterator(iterable.iterator());
	}

	/** Map an Iterator<Integer> to a PrimitiveIterator.OfInt */
	public static OfInt toInt(Iterator<Integer> iterator)
	{
		return IntIterators.asIntIterator(iterator);
	}

	/** Map an Iterable<Double> to a PrimitiveIterator.OfDouble */
	public static OfDouble toDouble(Iterable<Double> iterable)
	{
		return DoubleIterators.asDoubleIterator(iterable.iterator());
	}

	/** Map an Iterator<Double> to a PrimitiveIterator.OfDouble */
	public static OfDouble toDouble(Iterator<Double> iterator)
	{
		return DoubleIterators.asDoubleIterator(iterator);
	}

	/** Map an Iterable<Long> to a PrimitiveIterator.OfLong */
	public static OfLong toLong(Iterable<Long> iterable)
	{
		return LongIterators.asLongIterator(iterable.iterator());
	}

	/** Map an Iterator<Long> to a PrimitiveIterator.OfLong */
	public static OfLong toLong(Iterator<Long> iterator)
	{
		return LongIterators.asLongIterator(iterator);
	}

	/** Map an iterator over integers to an iterator over T */
	public static class FromInt<T> extends MappingIterator<Integer, T>
	{
		protected IntFunction<T> function;

		public FromInt(IntIterable iterable, IntFunction<T> function)
		{
			this(iterable.iterator(), function);
		}

		public FromInt(OfInt iterator, IntFunction<T> function)
		{
			super(iterator);
			this.function = function;
		}

		@Override
		public T next()
		{
			return function.apply(((OfInt) iterator).nextInt());
		}
	}

	/** Map an iterator over integers to another iterator over integers */
	public static class FromIntToInt extends MappingIterator<Integer, Integer> implements OfInt
	{
		protected IntUnaryOperator function;

		public FromIntToInt(IntIterable iterable, IntUnaryOperator function)
		{
			this(iterable.iterator(), function);
		}

		public FromIntToInt(OfInt iterator, IntUnaryOperator function)
		{
			super(iterator);
			this.function = function;
		}

		@Override
		public int nextInt()
		{
			return function.applyAsInt(((OfInt) iterator).nextInt());
		}
	}

	/** Map an iterator over integers to iterator over doubles */
	public static class FromIntToDouble extends MappingIterator<Integer, Double> implements OfDouble
	{
		protected IntToDoubleFunction function;

		public FromIntToDouble(IntIterable iterable, IntToDoubleFunction function)
		{
			this(iterable.iterator(), function);
		}

		public FromIntToDouble(OfInt iterator, IntToDoubleFunction function)
		{
			super(iterator);
			this.function = function;
		}

		@Override
		public double nextDouble()
		{
			return function.applyAsDouble(((OfInt) iterator).nextInt());
		}
	}

	/** Map an iterator over integers to iterator over longs */
	public static class FromIntToLong extends MappingIterator<Integer, Long> implements OfLong
	{
		protected IntToLongFunction function;

		public FromIntToLong(IntIterable iterable, IntToLongFunction function)
		{
			this(iterable.iterator(), function);
		}

		public FromIntToLong(OfInt iterator, IntToLongFunction function)
		{
			super(iterator);
			this.function = function;
		}

		@Override
		public long nextLong()
		{
			return function.applyAsLong(((OfInt) iterator).nextInt());
		}
	}

	/** Map an iterator over doubles to an iterator over T */
	public static class FromDouble<T> extends MappingIterator<Double, T>
	{
		protected DoubleFunction<T> function;

		public FromDouble(DoubleIterable iterable, DoubleFunction<T> function)
		{
			this(iterable.iterator(), function);
		}

		public FromDouble(OfDouble iterator, DoubleFunction<T> function)
		{
			super(iterator);
			this.function = function;
		}

		@Override
		public T next()
		{
			return function.apply(((OfDouble) iterator).nextDouble());
		}
	}

	/** Map an iterator over doubles to an iterator over integers */
	public static class FromDoubleToInt extends MappingIterator<Double, Integer> implements OfInt
	{
		protected DoubleToIntFunction function;

		public FromDoubleToInt(DoubleIterable iterable, DoubleToIntFunction function)
		{
			this(iterable.iterator(), function);
		}

		public FromDoubleToInt(OfDouble iterator, DoubleToIntFunction function)
		{
			super(iterator);
			this.function = function;
		}

		@Override
		public int nextInt()
		{
			return function.applyAsInt(((OfDouble) iterator).nextDouble());
		}
	}

	/** Map an iterator over doubles to another iterator over doubles */
	public static class FromDoubleToDouble extends MappingIterator<Double, Double> implements OfDouble
	{
		protected DoubleUnaryOperator function;

		public FromDoubleToDouble(DoubleIterable iterable, DoubleUnaryOperator function)
		{
			this(iterable.iterator(), function);
		}

		public FromDoubleToDouble(OfDouble iterator, DoubleUnaryOperator function)
		{
			super(iterator);
			this.function = function;
		}

		@Override
		public double nextDouble()
		{
			return function.applyAsDouble(((OfDouble) iterator).nextDouble());
		}
	}

	/** Map an iterator over doubles to an iterator over longs */
	public static class FromDoubleToLong extends MappingIterator<Double, Long> implements OfLong
	{
		protected DoubleToLongFunction function;

		public FromDoubleToLong(DoubleIterable iterable, DoubleToLongFunction function)
		{
			this(iterable.iterator(), function);
		}

		public FromDoubleToLong(OfDouble iterator, DoubleToLongFunction function)
		{
			super(iterator);
			this.function = function;
		}

		@Override
		public long nextLong()
		{
			return function.applyAsLong(((OfDouble) iterator).nextDouble());
		}
	}

	/** Map an iterator over longs to an iterator over T */
	public static class FromLong<T> extends MappingIterator<Long, T>
	{
		protected LongFunction<T> function;

		public FromLong(LongIterable iterable, LongFunction<T> function)
		{
			this(iterable.iterator(), function);
		}

		public FromLong(OfLong iterator, LongFunction<T> function)
		{
			super(iterator);
			this.function = function;
		}

		@Override
		public T next()
		{
			return function.apply(((OfLong) iterator).nextLong());
		}
	}

	/** Map an iterator over longs to an iterator over integers */
	public static class FromLongToInt extends MappingIterator<Long, Integer> implements OfInt
	{
		protected LongToIntFunction function;

		public FromLongToInt(LongIterable iterable, LongToIntFunction function)
		{
			this(iterable.iterator(), function);
		}

		public FromLongToInt(OfLong iterator, LongToIntFunction function)
		{
			super(iterator);
			this.function = function;
		}

		@Override
		public int nextInt()
		{
			return function.applyAsInt(((OfLong) iterator).nextLong());
		}
	}

	/** Map an iterator over longs to an iterator over doubles */
	public static class FromLongToDouble extends MappingIterator<Long, Double> implements OfDouble
	{
		protected LongToDoubleFunction function;

		public FromLongToDouble(LongIterable iterable, LongToDoubleFunction function)
		{
			this(iterable.iterator(), function);
		}

		public FromLongToDouble(OfLong iterator, LongToDoubleFunction function)
		{
			super(iterator);
			this.function = function;
		}

		@Override
		public double nextDouble()
		{
			return function.applyAsDouble(((OfLong) iterator).nextLong());
		}
	}

	/** Map an iterator over longs to another iterator over longs */
	public static class FromLongToLong extends MappingIterator<Long, Long> implements OfLong
	{
		protected LongUnaryOperator function;

		public FromLongToLong(LongIterable iterable, LongUnaryOperator function)
		{
			this(iterable.iterator(), function);
		}

		public FromLongToLong(OfLong iterator, LongUnaryOperator function)
		{
			super(iterator);
			this.function = function;
		}

		@Override
		public long nextLong()
		{
			return function.applyAsLong(((OfLong) iterator).nextLong());
		}
	}
}
