package org.deepamehta.plugins.review.service;

import de.deepamehta.core.Topic;
import de.deepamehta.core.service.ClientState;
import de.deepamehta.core.service.PluginService;

/**
 * A very stupid plugin for counting likes/dislikes on any kind of topics in DeepaMehta.
 *
 * @author Malte Reißig (<malte@mikromedia.de>)
 * @website https://github.com/mukil/org.deepamehta-reviews
 * @version 0.3.4
 *
 */

public interface ReviewService extends PluginService {

    Topic addToGood(long resourceId, ClientState clientState);

    Topic addToSoso(long resourceId, ClientState clientState);

    Topic upvoteResourceById(long resourceId, ClientState clientState);

    Topic downvoteResourceById(long resourceId, ClientState clientState);

}
