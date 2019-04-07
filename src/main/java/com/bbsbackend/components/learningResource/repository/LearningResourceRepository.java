package com.bbsbackend.components.learningResource.repository;

import java.util.stream.Stream;

import com.bbsbackend.components.learningResource.common.ResourceId;
import com.bbsbackend.components.learningResource.common.ResourceInfo;

public interface LearningResourceRepository {
    boolean saveResource(ResourceId id, ResourceInfo resourceInfo);

    boolean updateResource(ResourceId resourceId, ResourceInfo modSharer);

    ResourceInfo getResourceInfo(ResourceId resourceId);

    Stream<ResourceId> getAllResources();

    void deleteResource(ResourceId resourceId);
}
