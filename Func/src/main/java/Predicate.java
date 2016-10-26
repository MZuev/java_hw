abstract public class Predicate<T> extends Function1<T, Boolean> {
    abstract public Boolean apply(T t);

    public Predicate<T> or(final Predicate<? super T> secondPredicate) {
        return new Predicate<T> () {
            public Boolean apply(T t) {
                return Predicate.this.apply(t) || secondPredicate.apply(t);
            }
        };
    }

    public Predicate<T> and(final Predicate<? super T> secondPredicate) {
        return new Predicate<T> () {
            public Boolean apply(T t) {
                return Predicate.this.apply(t) && secondPredicate.apply(t);
            }
        };
     }

    public Predicate<T> not(){
        return new Predicate<T> () {
            public Boolean apply(T t) {
                return !Predicate.this.apply(t);
            }
        };
    }

    static public <T> Predicate<T> ALWAYS_TRUE() {
        return new Predicate<T> () {
            public Boolean apply(T t) {
                return true;
            }
        };
    }

    static public <T> Predicate<T> ALWAYS_FALSE() {
        return new Predicate<T> () {
            public Boolean apply(T t) {
                return false;
            }
        };
    }
}