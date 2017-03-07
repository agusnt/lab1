package es.unizar.tmdad.lab0.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.social.twitter.api.StreamDeleteEvent;
import org.springframework.social.twitter.api.StreamListener;
import org.springframework.social.twitter.api.StreamWarningEvent;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.util.MimeTypeUtils;

public class SimpleStreamListener implements StreamListener {
   private SimpMessageSendingOperations op;
   private String q;

   public SimpleStreamListener(SimpMessageSendingOperations  m, String query){
       this.op = m;
       this.q = query;
   }

   //Action over every tweet
   @Override
   public void onTweet(Tweet tweet) {
       Map<String,Object> map = new HashMap<>();
       map.put(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);
       op.convertAndSend("/queue/search/" + q, tweet, map);
   }

    @Override
    public void onDelete(StreamDeleteEvent deleteEvent) {}
    @Override
    public void onLimit(int numberOfLimitedTweets) {}
    @Override
    public void onWarning(StreamWarningEvent warningEvent) {}
}
