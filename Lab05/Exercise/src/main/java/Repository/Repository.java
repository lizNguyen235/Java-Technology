package Repository;

public interface Repository <T> {
    void save(T t);
    void update(T t);
    void delete(T t);
    T findById(String username);
}
