package com.company.persistence;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by igoru on 23-Apr-17.
 */
public class ResourceDaoImpl implements ResourcesDao {
    private String rootDirPath;
    private File rootDir;

    public ResourceDaoImpl(String rootDirPath) {
        this(new File(rootDirPath));
    }

    public ResourceDaoImpl(File rootDir) {
        if (rootDir == null) {
            throw new NullPointerException("rootDir is null");
        }
        if (!rootDir.exists() || !rootDir.canWrite()) {
            throw new IllegalArgumentException("illegal rootDirPath");
        }
        this.rootDir = rootDir;
        this.rootDirPath = rootDir.getPath();
    }

    @Override
    public boolean create(String path, byte[] bytes) {
        try {
            Files.write(Paths.get(path), bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public byte[] read(String path) {
        try {
            return Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(String path, byte[] bytes) {
        return create(path, bytes);
    }

    @Override
    public boolean delete(String path) {
        try {
            return Files.deleteIfExists(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
