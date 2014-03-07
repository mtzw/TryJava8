package com.github.mtzw.twitter;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.Test;

import twitter4j.RateLimitStatus;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;

public class GatewayTest {

	Gateway IMPL = new Gateway() {

		@Override
		public Twitter twitter() {
			Twitter mock = mock(Twitter.class);
			try {
				when(mock.getHomeTimeline()).thenReturn(new ListForTesting<>());
				when(mock.getScreenName()).thenReturn("GatewayTest");
			} catch (TwitterException e) {
				e.printStackTrace();
			}
			return mock;
		}

		@Override
		public TwitterStream twitterStream() {
			return mock(TwitterStream.class);
		}

	};

	@Test
	public void testTwitter_getHomeTimeline() {
		try {
			assertThat(IMPL.twitter().getHomeTimeline(), is(notNullValue()));
		} catch (TwitterException e) {
			fail();
		}
	}

	@Test
	public void testTwitter_getScreenName() {
		try {
			assertThat(IMPL.twitter().getScreenName(), is("GatewayTest"));
		} catch (TwitterException e) {
			fail();
		}
	}

	@Test
	public void testTwitterStream() {
		try {
			IMPL.twitterStream().user();
		} catch (Exception e) {
			fail();
		}
	}

	class ListForTesting<Status> extends ArrayList<Status> implements
			ResponseList<Status> {
		private static final long serialVersionUID = 1L;

		@Override
		public int getAccessLevel() {
			return 0;
		}

		@Override
		public RateLimitStatus getRateLimitStatus() {
			return null;
		}
	}

}
