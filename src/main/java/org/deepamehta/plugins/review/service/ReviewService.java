package org.deepamehta.plugins.review.service;

import de.deepamehta.core.Topic;
import de.deepamehta.core.service.PluginService;

/**
 * A very stupid plugin for counting likes/dislikes on any kind of topics in DeepaMehta.
 *
 * * Counting values seperately as "Good" and "So-so", depends on introducing Migration2 to your application model.
 * * Counting values as one accumulate "Score", depends on introducing Migration1 to your application model.
 *
 * @author Malte Reißig (<malte@mikromedia.de>)
 * @website https://github.com/mukil/dm4-reviews
 * @version 0.3.7
 *
 */

public interface ReviewService extends PluginService {

    Topic addToGood(long resourceId);

    Topic addToSoso(long resourceId);

    Topic upvoteResourceById(long resourceId);

    Topic downvoteResourceById(long resourceId);

}
