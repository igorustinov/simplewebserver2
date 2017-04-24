package com.company.persistence;

/**
 * Created by igoru on 23-Apr-17.
 */
public interface ResourcesDao {
    boolean create(String path, byte[] bytes);
    byte[] read(String path);
    boolean update(String path, byte[] bytes);
    boolean delete(String path);
}
