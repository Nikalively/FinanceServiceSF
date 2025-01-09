package storage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FileStorage<K, V> implements Storage<K, V> {
    private final String filePath;
    private final Map<K, V> storage;
    private final Type type;
    private final Gson gson;

    public FileStorage(String filePath, Type type) {
        this.filePath = filePath;
        this.type = type;
        this.gson = new Gson();
        this.storage = new HashMap<>();
        load();
    }

    @Override
    public void save(V value) {
        K key = getKey(value);
        if (key != null) {
            storage.put(key, value);
            persist();
        } else {
            throw new IllegalArgumentException("Ошибка! Проверьте указанный ключ.");
        }
    }

    @Override
    public void delete(K key) {
        if (storage.containsKey(key)) {
            storage.remove(key);
            persist();
        }
    }

    @Override
    public Optional<V> find(K key) {
        return Optional.ofNullable(storage.get(key));
    }

    @Override
    public List<V> findAll() {
        return List.copyOf(storage.values());
    }

    private void load() {
        try (FileReader reader = new FileReader(filePath)) {
            Map<K, V> data = gson.fromJson(reader, type);
            if (data != null) {
                storage.putAll(data);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке данных из файла: " + e.getMessage());
        }
    }

    private void persist() {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(storage, writer);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении данных в файл: " + e.getMessage());
        }
    }

    private K getKey(V value) {
        // Используем старый подход без pattern matching
        if (value instanceof model.User) {
            model.User user = (model.User) value;
            return (K) user.getLogin();
        }
        throw new UnsupportedOperationException("Ошибка! Извлечение невозможно.");
    }
}