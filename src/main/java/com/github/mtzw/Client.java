package com.github.mtzw;

import java.util.Comparator;
import java.util.Optional;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import com.github.mtzw.twitter.GatewayImpl;

public class Client {
	
	public static void main(String[] args) {
//		Twitter twitter = GatewayImpl.Instance.twitter();
		Twitter twitter = GatewayImpl.Mock.twitter();

		try {
			Optional.ofNullable(twitter.getUserTimeline("screen_name"))
				.ifPresent(list -> list.stream()
					.limit(10)
					.sorted(new StatusComparator())
					.map(status -> status.getText())
					.forEach(System.out::println));
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

	static class StatusComparator implements Comparator<Status> {
		@Override
		public int compare(Status o1, Status o2) {
			return o1.getCreatedAt().before(o2.getCreatedAt()) ? -1 : 1;
		}
	}
	
}
