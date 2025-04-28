package lab02;

public class AndFilter<T> implements Filter<T> {
    private Filter<T> filter1;
    private Filter<T> filter2;

    public AndFilter(Filter<T> filter1, Filter<T> filter2){
        this.filter1 = filter1;
        this.filter2 = filter2;
    }

    @Override
    public boolean matches(T t){
        return filter1.matches(t) && filter2.matches(t);
    }
}
