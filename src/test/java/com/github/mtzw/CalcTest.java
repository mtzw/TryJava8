package com.github.mtzw;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class CalcTest {

	@Test
	public void testPlus() {
		Calc calc = new Calc() {
			int __internal = 5;

			@Override
			public int plus(int plus) {
				return __internal + plus;
			}
		};

		assertThat(calc.plus(5), is(10));
	}

	@Test
	public void testInterface() {
		Calc mock = mock(Calc.class);

		when(mock.plus(10)).thenReturn(100);

		assertThat(mock.plus(10), is(100));

		verify(mock);
	}

	public static interface Calc {
		public int plus(int plus);
	}
}
