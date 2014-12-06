package org.deepamehta.plugins.review;

import de.deepamehta.core.ChildTopics;
import de.deepamehta.core.Topic;
import de.deepamehta.core.TopicType;
import de.deepamehta.core.model.AssociationDefinitionModel;
import de.deepamehta.core.osgi.PluginActivator;
import de.deepamehta.core.service.Transactional;
import java.util.Collection;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.deepamehta.plugins.review.service.ReviewService;



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

@Path("/review")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReviewPlugin extends PluginActivator implements ReviewService {

    private Logger log = Logger.getLogger(getClass().getName());

    public final static String SCORE_TYPE_URI = "org.deepamehta.reviews.score";
    public final static String GOOD_TYPE_URI = "org.deepamehta.reviews.good";
    public final static String SOSO_TYPE_URI = "org.deepamehta.reviews.soso";


    /** Increments the number of supportive voices (yelling "Good!").
     * @param resourceId
     **/
    
    @GET
    @Path("/good/{id}")
    @Produces("application/json")
    @Override
    @Transactional
    public Topic addToGood(@PathParam("id") long resourceId) {
        Topic topic = null;
        try {
            topic = dms.getTopic(resourceId);
            TopicType typeDef = dms.getTopicType(topic.getTypeUri());
            Collection<AssociationDefinitionModel> typeModel = typeDef.getModel().getAssocDefs();
            boolean hasGoodTypeDef = false;
            for (AssociationDefinitionModel associationDefinitionModel : typeModel) {
                if (associationDefinitionModel.getChildTypeUri().equals(GOOD_TYPE_URI)) hasGoodTypeDef = true;
            }
            if (hasGoodTypeDef) {
                topic.loadChildTopics(GOOD_TYPE_URI);
                addOne(topic.getChildTopics(), GOOD_TYPE_URI);
                dms.updateTopic(topic.getModel()); // ### timestamp bug in 4.4
            } else {
                throw new WebApplicationException(new RuntimeException("The TypeDefinition (model) of the given topic "
                        + "does not contain the \"org.deepamehta.reviews.good\"-type"));
            }
        } catch (Exception e) {
            throw new RuntimeException("something went wrong", e);
        }
        return topic;
    }
    
    
    /** Increments the number of supportive voices (yelling "Well, so so.").
     * @param resourceId
     **/

    @GET
    @Path("/soso/{id}")
    @Produces("application/json")
    @Override
    @Transactional
    public Topic addToSoso(@PathParam("id") long resourceId) {
        Topic topic = null;
        try {
            topic = dms.getTopic(resourceId);
            TopicType typeDef = dms.getTopicType(topic.getTypeUri());
            Collection<AssociationDefinitionModel> typeModel = typeDef.getModel().getAssocDefs();
            boolean hasSosoTypeDef = false;
            for (AssociationDefinitionModel associationDefinitionModel : typeModel) {
                if (associationDefinitionModel.getChildTypeUri().equals(SOSO_TYPE_URI)) hasSosoTypeDef = true;
            }
            if (hasSosoTypeDef) {
                topic.loadChildTopics(SOSO_TYPE_URI);
                addOne(topic.getChildTopics(), SOSO_TYPE_URI);
                dms.updateTopic(topic.getModel()); // ### timestamp bug in 4.4
            } else {
                throw new WebApplicationException(new RuntimeException("The TypeDefinition (model) of the given topic "
                        + "does not contain the \"org.deepamehta.reviews.soso\"-type"));
            }
        } catch (Exception e) {
            throw new RuntimeException("something went wrong", e);
        }
        return topic;
    }

    /** Increments the score of any given topic.
     * @param resourceId
     **/

    @GET
    @Path("/upvote/{id}")
    @Produces("application/json")
    @Override
    @Transactional
    public Topic upvoteResourceById(@PathParam("id") long resourceId) {
        Topic topic = null;
        try {
            topic = dms.getTopic(resourceId);
            TopicType typeDef = dms.getTopicType(topic.getTypeUri());
            Collection<AssociationDefinitionModel> typeModel = typeDef.getModel().getAssocDefs();
            boolean hasScoreTypeDef = false;
            for (AssociationDefinitionModel associationDefinitionModel : typeModel) {
                if (associationDefinitionModel.getChildTypeUri().equals(SCORE_TYPE_URI)) hasScoreTypeDef = true;
            }
            if (hasScoreTypeDef) {
                topic.loadChildTopics(SCORE_TYPE_URI);
                addOne(topic.getChildTopics(), SCORE_TYPE_URI);
                dms.updateTopic(topic.getModel()); // ### timestamp bug in 4.4
            } else {
                throw new RuntimeException("The TypeDefinition (model) of the given topic "
                        + "does not contain the \"org.deepamehta.reviews.score\"-type");
            }
        } catch (Exception e) {
            throw new RuntimeException("something went wrong", e);
        }
        return topic;
    }

    /** Decrements the score of any given topic.
     * @param resourceId
     * @param clientState */

    @GET
    @Path("/downvote/{id}")
    @Produces("application/json")
    @Override
    @Transactional
    public Topic downvoteResourceById(@PathParam("id") long resourceId) {
        Topic topic = null;
        try {
            topic = dms.getTopic(resourceId);
            TopicType typeDef = dms.getTopicType(topic.getTypeUri());
            Collection<AssociationDefinitionModel> typeModel = typeDef.getModel().getAssocDefs();
            boolean hasScoreTypeDef = false;
            for (AssociationDefinitionModel associationDefinitionModel : typeModel) {
                if (associationDefinitionModel.getChildTypeUri().equals(SCORE_TYPE_URI)) hasScoreTypeDef = true;
            }
            if (hasScoreTypeDef) {
                topic.loadChildTopics(SCORE_TYPE_URI);
                substractOne(topic.getChildTopics(), SCORE_TYPE_URI);
                dms.updateTopic(topic.getModel()); // ### timestamp bug in 4.4
            } else {
                throw new RuntimeException("The TypeDefinition (model) of the given topic "
                        + "does not contain the \"org.deepamehta.reviews.score\"-type");
            }
        } catch (Exception e) {
            throw new RuntimeException("something went wrong", e);
        }
        return topic;
    }
    
    private void addOne (ChildTopics childTopics, String childTypeUri) {
        int score = 1;
        try {
            if (!childTopics.getModel().has(childTypeUri)) {
                // initialize with +1
                childTopics.set(childTypeUri, score);
            } else {
                // add 1
                score = childTopics.getModel().getInt(childTypeUri) + 1;
                childTopics.set(childTypeUri, score); 
            }
        } catch (ClassCastException ce) {
            // dm4-webclient has written a string into our dm4.core.numbers field
            String value = "1";
            try {
                score = Integer.parseInt(childTopics.getModel().getString(childTypeUri));
                value = "" + (score + 1);
            } catch (NumberFormatException ne) {
                // initialize string number (cause this is the type written to neo4j by dm4-core via dm4-webclient)
                // with "1"
            }
            childTopics.set(childTypeUri, value);
        }
    }
    
    private void substractOne (ChildTopics childTopics, String childTypeUri) {
        int score = -1;
        try {
            if (!childTopics.getModel().has(childTypeUri)) {
                // initialize with -1
                childTopics.set(childTypeUri, score);
            } else {
                // subtract 1
                score = childTopics.getModel().getInt(childTypeUri) - 1;
                childTopics.set(childTypeUri, score);
            }
        } catch (ClassCastException ce) {
            // dm4-webclient has written a string into our dm4.core.numbers field
            String value = "-1";
            try {
                score = Integer.parseInt(childTopics.getModel().getString(childTypeUri));
                value = "" + (score - 1);
            } catch (NumberFormatException ne) {
                // initialize string number (cause this is the type written to neo4j by dm4-core via dm4-webclient)
                // with "-1"
            }
            childTopics.set(childTypeUri, value);
        }
    }

}
