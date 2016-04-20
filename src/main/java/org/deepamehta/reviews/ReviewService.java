package org.deepamehta.reviews;

import de.deepamehta.core.Topic;

/**
 * A plugin for counting likes/dislikes on any kind of topics in DeepaMehta.
 *
 * * Counting values seperately as "Good" and "So-so", depends on introducing Migration2 to your application model.
 * * Counting values as one accumulate "Score", depends on introducing Migration1 to your application model.
 *
 * @author Malte Reißig (<malte@mikromedia.de>)
 * @website https://github.com/mukil/dm4-reviews
 * @version 0.3.7
 *
 */
public interface ReviewService {
    
    static final String SCORE_TYPE = "org.deepamehta.reviews.score";
    static final String GOOD_TYPE = "org.deepamehta.reviews.good";
    static final String SOSO_TYPE = "org.deepamehta.reviews.soso";

    Topic addToGood(long resourceId);

    Topic addToSoso(long resourceId);

    Topic upvoteResourceById(long resourceId);

    Topic downvoteResourceById(long resourceId);

}
