package benchmark;

public class MyObject {
    private int _value;
    private static MyObject[] _cache = {new MyObject(0), new MyObject(1), new MyObject(2)};

    public MyObject(int value) {
        _value = value;
    }
    public int value() {
        return _value;
    }
    public MyObject plus(MyObject val) {
        return new MyObject(_value + val._value);
    }

    public MyObject divide(MyObject val) {
        return new MyObject(_value / val._value);
    }

    public static MyObject valueOf(int val) {
        if (0 <= val && val < 3) {
            return _cache[val];
        } else {
            return new MyObject(val);
        }
    }
}