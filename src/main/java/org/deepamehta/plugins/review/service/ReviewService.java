package org.deepamehta.plugins.review.service;


import de.deepamehta.core.Topic;
import de.deepamehta.core.RelatedTopic;
import de.deepamehta.core.ResultSet;
import de.deepamehta.core.model.TopicModel;
import de.deepamehta.core.service.ClientState;
import de.deepamehta.core.service.PluginService;



public interface ReviewService extends PluginService {

    Topic addToGood(long resourceId, ClientState clientState);

    Topic addToSoso(long resourceId, ClientState clientState);

    Topic upvoteResourceById(long resourceId, ClientState clientState);

    Topic downvoteResourceById(long resourceId, ClientState clientState);

}
