package com.github.mtzw.twitter;

import org.mockito.Mockito;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.UserStreamAdapter;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public abstract class GatewayImpl implements Gateway {

	abstract void configure();

	private GatewayImpl() {
		configure();
	}

	public static final Gateway Instance = new GatewayImpl() {
		Twitter __twitter;
		TwitterStream __twitterStream;

		@Override
		public TwitterStream twitterStream() {
			return __twitterStream;
		}

		@Override
		public Twitter twitter() {
			return __twitter;
		}

		@Override
		void configure() {
			ConfigurationBuilder builder = new ConfigurationBuilder();
			builder.setOAuthAccessToken("your_access_token");
			builder.setOAuthAccessTokenSecret("your_access_token_secret");
			builder.setOAuthConsumerKey("your_consumer_key");
			builder.setOAuthConsumerSecret("your_consumer_secret");
			builder.setUseSSL(true);

			Configuration configuration = builder.build();

			createTwitter(configuration);
			createTwitterStream(configuration);
		}

		private void createTwitterStream(Configuration configuration) {
			__twitterStream = new TwitterStreamFactory(configuration)
					.getInstance();
			__twitterStream.addListener(new UserStreamAdapter() {
				@Override
				public void onStatus(Status status) {
					System.out.println(status.getText());
				}
			});
		}

		private void createTwitter(Configuration configuration) {
			__twitter = new TwitterFactory(configuration).getInstance();
		}

	};

	public static final Gateway Mock = new GatewayImpl() {

		@Override
		public Twitter twitter() {
			return Mockito.mock(Twitter.class);
		}

		@Override
		public TwitterStream twitterStream() {
			return Mockito.mock(TwitterStream.class);
		}

		@Override
		void configure() {
			// nothing to do.
		}

	};

}
