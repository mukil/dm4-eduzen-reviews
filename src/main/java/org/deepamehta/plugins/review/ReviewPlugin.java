package org.deepamehta.plugins.review;

import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;

import de.deepamehta.core.Topic;
import de.deepamehta.core.TopicType;
import de.deepamehta.core.model.AssociationDefinitionModel;
import de.deepamehta.core.service.ClientState;
import de.deepamehta.core.service.Directives;
import de.deepamehta.core.storage.spi.DeepaMehtaTransaction;
import de.deepamehta.core.osgi.PluginActivator;
import java.util.Collection;
import org.deepamehta.plugins.review.service.ReviewService;



/**
 * A very stupid plugin for counting likes/dislikes on any kind of topics in DeepaMehta 4.1.2.
 *
 * * Counting values seperately as "Good" and "So-so", depends on introduucing Migration2 to your application model.
 *
 * * Counting values as one accumulate "Score", depends on introducing Migration1 to your application model.
 *
 * @author Malte Reißig (<malte@mikromedia.de>)
 * @website https://github.com/mukil/org.deepamehta-reviews
 * @version 0.3.2
 *
 */

@Path("/review")
@Consumes("application/json")
@Produces("application/json")
public class ReviewPlugin extends PluginActivator implements ReviewService {

    private Logger log = Logger.getLogger(getClass().getName());

    private final static String SCORE_TYPE_URI = "org.deepamehta.reviews.score";
    private final static String GOOD_TYPE_URI = "org.deepamehta.reviews.good";
    private final static String SOSO_TYPE_URI = "org.deepamehta.reviews.soso";


    /** Increments the number of supportive voices (yelling "Good!"). */

    @GET
    @Path("/good/{id}")
    @Produces("application/json")
    @Override
    public Topic addToGood(@PathParam("id") long resourceId, @HeaderParam("Cookie") ClientState clientState) {

        DeepaMehtaTransaction tx = dms.beginTx();
        Topic topic = null;
        try {
            // fixme: check if SCORE_URI is part of given topics type definition
            topic = dms.getTopic(resourceId, true);
            TopicType typeDef = dms.getTopicType(topic.getTypeUri());
            Collection<AssociationDefinitionModel> typeModel = typeDef.getModel().getAssocDefs();
            boolean hasScoreType = false;
            for (AssociationDefinitionModel associationDefinitionModel : typeModel) {
                if (associationDefinitionModel.getChildTypeUri().equals(GOOD_TYPE_URI)) hasScoreType = true;
            }
            if (hasScoreType) {
                int score = topic.getCompositeValue().getModel().getInt(GOOD_TYPE_URI) + 1;
                topic.getCompositeValue().set(GOOD_TYPE_URI, score, clientState, new Directives());
                tx.success();
            } else {
                throw new WebApplicationException(new RuntimeException("The TypeDefinition (model) of the given topic "
                        + "does not contain the \"org.deepamehta.reviews.good\"-type"));
            }
        } catch (Exception e) {
            throw new WebApplicationException(new RuntimeException("something went wrong", e));
        } finally {
            tx.finish();
        }
        return topic;
    }

    @GET
    @Path("/soso/{id}")
    @Produces("application/json")
    @Override
    public Topic addToSoso(@PathParam("id") long resourceId, @HeaderParam("Cookie") ClientState clientState) {

        DeepaMehtaTransaction tx = dms.beginTx();
        Topic topic = null;
        try {
            // fixme: check if SCORE_URI is part of given topics type definition
            topic = dms.getTopic(resourceId, true);
            TopicType typeDef = dms.getTopicType(topic.getTypeUri());
            Collection<AssociationDefinitionModel> typeModel = typeDef.getModel().getAssocDefs();
            boolean hasScoreType = false;
            for (AssociationDefinitionModel associationDefinitionModel : typeModel) {
                if (associationDefinitionModel.getChildTypeUri().equals(SOSO_TYPE_URI)) hasScoreType = true;
            }
            if (hasScoreType) {
                int score = topic.getCompositeValue().getModel().getInt(SOSO_TYPE_URI) + 1;
                topic.getCompositeValue().set(SOSO_TYPE_URI, score, clientState, new Directives());
                tx.success();
            } else {
                throw new WebApplicationException(new RuntimeException("The TypeDefinition (model) of the given topic "
                        + "does not contain the \"org.deepamehta.reviews.soso\"-type"));
            }
        } catch (Exception e) {
            throw new WebApplicationException(new RuntimeException("something went wrong", e));
        } finally {
            tx.finish();
        }
        return topic;
    }

    /** Increments the score of any given topic. */

    @GET
    @Path("/upvote/{id}")
    @Produces("application/json")
    @Override
    public Topic upvoteResourceById(@PathParam("id") long resourceId,
            @HeaderParam("Cookie") ClientState clientState) {

        DeepaMehtaTransaction tx = dms.beginTx();
        Topic topic = null;
        try {
            // fixme: check if SCORE_URI is part of given topics type definition
            topic = dms.getTopic(resourceId, true);
            TopicType typeDef = dms.getTopicType(topic.getTypeUri());
            Collection<AssociationDefinitionModel> typeModel = typeDef.getModel().getAssocDefs();
            boolean hasScoreType = false;
            for (AssociationDefinitionModel associationDefinitionModel : typeModel) {
                if (associationDefinitionModel.getChildTypeUri().equals(SCORE_TYPE_URI)) hasScoreType = true;
            }
            if (hasScoreType) {
                int score = topic.getCompositeValue().getModel().getInt(SCORE_TYPE_URI) + 1;
                topic.getCompositeValue().set(SCORE_TYPE_URI, score, clientState, new Directives());
                tx.success();
            } else {
                throw new WebApplicationException(new RuntimeException("The TypeDefinition (model) of the given topic "
                        + "does not contain the \"org.deepamehta.reviews.score\"-type"));
            }
        } catch (Exception e) {
            throw new WebApplicationException(new RuntimeException("something went wrong", e));
        } finally {
            tx.finish();
        }
        return topic;
    }

    /** Decrements the score of any given topic. */

    @GET
    @Path("/downvote/{id}")
    @Produces("application/json")
    @Override
    public Topic downvoteResourceById(@PathParam("id") long resourceId,
            @HeaderParam("Cookie") ClientState clientState) {

        DeepaMehtaTransaction tx = dms.beginTx();
        Topic topic = null;
        try {
            // fixme: check if SCORE_URI is part of given topics type definition
            topic = dms.getTopic(resourceId, true);
            TopicType typeDef = dms.getTopicType(topic.getTypeUri());
            Collection<AssociationDefinitionModel> typeModel = typeDef.getModel().getAssocDefs();
            boolean hasScoreType = false;
            for (AssociationDefinitionModel associationDefinitionModel : typeModel) {
                if (associationDefinitionModel.getChildTypeUri().equals(SCORE_TYPE_URI)) hasScoreType = true;
            }
            if (hasScoreType) {
                int score = topic.getCompositeValue().getModel().getInt(SCORE_TYPE_URI) - 1;
                topic.getCompositeValue().set(SCORE_TYPE_URI, score, clientState, new Directives());
                tx.success();
            } else {
                throw new WebApplicationException(new RuntimeException("The TypeDefinition (model) of the given topic "
                        + "does not contain the \"org.deepamehta.reviews.score\"-type"));
            }
        } catch (Exception e) {
            throw new WebApplicationException(new RuntimeException("something went wrong", e));
        } finally {
            tx.finish();
        }
        return topic;
    }

}
