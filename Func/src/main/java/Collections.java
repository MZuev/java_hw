import java.util.ArrayList;

public class Collections {

    public static <T, U> ArrayList<U> map(Function1<? super T, U> function, Iterable<T> oldList) {
        ArrayList<U> newList = new ArrayList<U>();
        for (T curElem : oldList) {
            newList.add(function.apply(curElem));
        }
        return newList;
    }

    public static <T> ArrayList<T> filter(Predicate<? super T> predicate, Iterable<T> oldList) {
        ArrayList<T> newList = new ArrayList<T>();
        for (T curElem : oldList) {
            if (predicate.apply(curElem)) {
                newList.add(curElem);
            }
        }
        return newList;
    }

    public static <T> ArrayList<T> takeWhile(Predicate<? super T> predicate, Iterable<T> oldList) {
        ArrayList<T> newList = new ArrayList<T>();
        for (T curElem : oldList) {
            if (!predicate.apply(curElem)) {
                break;
            }
            newList.add(curElem);
        }
        return newList;
    }

    public static <T> ArrayList<T> takeUnless(Predicate<? super T> predicate, Iterable<T> oldList) {
        ArrayList<T> newList = new ArrayList<T>();
        for (T curElem : oldList) {
            if (predicate.apply(curElem)) {
                break;
            }
            newList.add(curElem);
        }
        return newList;
    }

    public static <T, R> R foldl(Function2<? super R, ? super T, R> function, R startValue, Iterable<T> list) {
        R curValue = startValue;
        for (T curElem : list) {
            curValue = function.apply(curValue, curElem);
        }
        return curValue;
    }

    public static <T, R> R foldr(Function2<? super T, ? super R, R> function, R startValue, Iterable<T> list) {
        R curValue = startValue;
        ArrayList<T> tmpList = new ArrayList<T> ();
        for (T curElem : list) {
            tmpList.add(0, curElem);
        }
        for (T curElem : tmpList) {
            curValue = function.apply(curElem, curValue);
        }
        return curValue;
    }
}