abstract public class Function2<T, R, U> {
    abstract public U apply(T x, R y);

    public <O> Function2<T, R, O> compose(final Function1<? super U, O> g) {
        return new Function2<T, R, O> () {
            public O apply(T x, R y) {
                return g.apply(Function2.this.apply(x, y));
            }
        };
    }

    public Function1<R, U> bind1(final T x) {
        return new Function1<R, U> () {
            public U apply(R y) {
                return Function2.this.apply(x, y);
            }
        };
    }

    public Function1<T, U> bind2(final R y) {
        return new Function1<T, U> () {
            public U apply(T x) {
                return Function2.this.apply(x, y);
            }
        };
    }

    public Function1<T, Function1<R, U>> curry() {
        return new Function1<T, Function1<R, U>>() {
            public Function1<R, U> apply(final T x) {
                return new Function1<R, U>() {
                    public U apply(R y) {
                        return Function2.this.apply(x, y);
                    }
                };
            }
        };
    }
}