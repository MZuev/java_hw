abstract public class Function1<T, R> {
    public abstract R apply(T t);

    public <U> Function1 <T, U> compose(final Function1<? super R, U> g) {
        return new Function1<T, U> () {
            @Override
            public U apply(T t) {
                return g.apply(Function1.this.apply(t));
            }
        };
    }
}