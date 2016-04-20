package org.deepamehta.reviews.migrations;

import de.deepamehta.core.Topic;
import de.deepamehta.core.TopicType;
import de.deepamehta.core.service.Inject;
import de.deepamehta.workspaces.WorkspacesService;
import de.deepamehta.core.service.Migration;
import org.deepamehta.reviews.ReviewService;


/**
 * This migration assigns all the custom topic types of this plugin to the public "DeepaMehta" Workspace.
 * */
public class Migration3 extends Migration {

    @Inject
    private WorkspacesService workspaceService;

    @Override
    public void run() {
        Topic deepaMehtaWs = workspaceService.getWorkspace(WorkspacesService.DEEPAMEHTA_WORKSPACE_URI);
        TopicType goodType = dm4.getTopicType(ReviewService.GOOD_TYPE);
        TopicType sosoType = dm4.getTopicType(ReviewService.SOSO_TYPE);
        TopicType scoreType = dm4.getTopicType(ReviewService.SCORE_TYPE);
        workspaceService.assignTypeToWorkspace(goodType, deepaMehtaWs.getId());
        workspaceService.assignTypeToWorkspace(sosoType, deepaMehtaWs.getId());
        workspaceService.assignTypeToWorkspace(scoreType, deepaMehtaWs.getId());
    }

}