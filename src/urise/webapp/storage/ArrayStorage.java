package urise.webapp.storage;

import urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void update(Resume resume) {
        int index = getIndex(resume.uuid);
        if (index == -1) {
            System.out.println("Resume doesn't exist");
        } else {
            storage[index] = resume;
        }
    }

    public void save(Resume resume) {
        if (getIndex(resume.uuid) != -1) {
            System.out.println("Resume already exist");
        } else if (size == storage.length) {
            System.out.println("Storage overflow");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume doesn't exist");
            return null;
        }

        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume doesn't exist");
        } else {
            for (int j = index + 1; j < storage.length; j++) {
                storage[j - 1] = storage[j];
            }
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        for (int i = 0; i < size; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public int size() {
        return size;
    }
}
