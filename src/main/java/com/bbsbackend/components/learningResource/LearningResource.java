package com.bbsbackend.components.learningResource;

import java.util.Optional;
import java.util.stream.Stream;

import com.bbsbackend.components.idGenerator.IdGenerator;
import com.bbsbackend.components.learningResource.common.ResourceId;
import com.bbsbackend.components.learningResource.common.ResourceInfo;
import com.bbsbackend.components.learningResource.repository.LearningResourceRepository;
/*
 * 没看这部分
 */
public interface LearningResource {
    Optional<ResourceId> pubResource(ResourceInfo resourceInfo);

    Optional<ResourceInfo> resourceInfo(ResourceId resourceId);

    Stream<ResourceId> allResources();

    default Stream<ResourceId> resourcesOfMajor(String majorCode) {
        return allResources().filter(r ->
                resourceInfo(r).map(ri -> ri.majorCode.equals(majorCode)).orElse(false));
    }

    boolean changeSharer(ResourceId resourceId, String newSharer);

    boolean changeMajorCode(ResourceId resourceId, String newMajorCode);

    boolean changeTitle(ResourceId resourceId, String newTitle);

    boolean changeContent(ResourceId resourceId, String newContent);

    boolean changeAttachedFileIdentifier(ResourceId resourceId, String newAttachedFileIdentifier);

    default Stream<ResourceId> searchResourcesByTitle(String title) {
        return allResources().filter(r ->
                resourceInfo(r).map(ri -> ri.title.matches(".*" + title + ".*")).orElse(false));
    }

    default Stream<ResourceId> searchResourcesByContent(String content) {
        return allResources().filter(r ->
                resourceInfo(r).map(ri -> ri.content.matches(".*" + content + ".*")).orElse(false));
    }

    void removeResource(ResourceId resourceId);

    String getName();

    static LearningResource defau1t(String componentName, LearningResourceRepository repository, IdGenerator idGenerator) {
        return new LearningResourceImpl(componentName, repository, idGenerator);
    }
}
