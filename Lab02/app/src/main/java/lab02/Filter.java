package lab02;

public interface Filter<T> {
    boolean matches(T t);
}
