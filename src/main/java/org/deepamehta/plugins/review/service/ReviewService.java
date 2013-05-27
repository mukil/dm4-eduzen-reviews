package org.deepamehta.plugins.review.service;


import de.deepamehta.core.Topic;
import de.deepamehta.core.RelatedTopic;
import de.deepamehta.core.ResultSet;
import de.deepamehta.core.model.TopicModel;
import de.deepamehta.core.service.ClientState;
import de.deepamehta.core.service.PluginService;



/**
 * A very stupid plugin for counting likes/dislikes on any kind of topics in DeepaMehta 4.
 *
 * @author Malte Reißig (<malte@mikromedia.de>)
 * @website https://github.com/mukil/org.deepamehta-reviews
 * @version 0.2
 *
 */

public interface ReviewService extends PluginService {

    Topic upvoteResourceById(long resourceId, ClientState clientState);

    Topic downvoteResourceById(long resourceId, ClientState clientState);

}
