package com.company.handlers;

import com.company.persistence.ResourcesDao;

/**
 * Created by igoru on 24-Apr-17.
 */
public interface ResourcesAware {
    ResourcesDao getResourceDao();
    void setResourcesDao(ResourcesDao resourcesDao);
}
