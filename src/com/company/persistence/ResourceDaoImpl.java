package com.company.persistence;

import com.company.request.NotExistException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by igoru on 23-Apr-17.
 */
public class ResourceDaoImpl implements ResourcesDao {
    private String rootDirPath;
    private File rootDir;
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

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
    public boolean create(String pathStr, byte[] bytes) {
        try {
            lock.writeLock().lock();
            final Path path = Paths.get(rootDirPath, pathStr);
            Files.createDirectories(path.getParent());
            Files.deleteIfExists(path);
            Files.createFile(path);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            lock.writeLock().unlock();
        }
        return true;
    }

    @Override
    public byte[] read(String path) {
        try {
            lock.readLock().lock();
            final Path existing = Paths.get(rootDirPath, path);
            final File file = existing.toFile();
            if (file.exists() && file.isFile()) {
                return Files.readAllBytes(existing);
            } else {
                throw new NotExistException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
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
            lock.writeLock().lock();
            return Files.deleteIfExists(Paths.get(rootDirPath, path));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            lock.writeLock().unlock();
        }
    }

    private Path getPath(String pathString) {
        try {
            final Path existing = Paths.get(rootDirPath, pathString);
            final File file = existing.toFile();
            return existing;
        } catch (Exception ex) {
            throw new NotExistException(ex);
        }
    }
}
