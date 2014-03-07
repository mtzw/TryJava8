package com.github.mtzw.twitter;

import twitter4j.Twitter;
import twitter4j.TwitterStream;

public interface Gateway {

	public Twitter twitter();

	public TwitterStream twitterStream();

}
