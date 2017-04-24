package com.company.handlers;

import com.company.persistence.ResourcesDao;

/**
 * Created by igoru on 24-Apr-17.
 */
public abstract class AbstractFileRequestHandler implements RequestHandler, ResourcesAware {

    private ResourcesDao resourcesDao;

    @Override
    public ResourcesDao getResourceDao() {
        return resourcesDao;
    }

    @Override
    public void setResourcesDao(ResourcesDao resourcesDao) {
        this.resourcesDao = resourcesDao;
    }
}
