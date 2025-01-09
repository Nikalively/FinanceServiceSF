package storage;

import java.util.List;
import java.util.Optional;

public interface Storage<K, V> {
    void save(V value);

    void delete(K key);

    Optional<V> find(K key);

    List<V> findAll();
}