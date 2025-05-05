package lab02.filter;

public interface Filter<T> {
    boolean matches(T t);
}
