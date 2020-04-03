package benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@Fork(value = 1)
@Warmup(iterations = 3)
@Measurement(iterations = 3)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ObjectHash {

    private static Random s_r = new Random(7);
    private static int next() { return s_r.nextInt() % 1000; }
    private static MyHash obj1 = new MyHash(next());
    private static MyHash obj2 = new MyHash(next());
    private static MyHash obj3 = new MyHash(next());

    private static int objects_hash() {
        return Objects.hash(obj1, obj2, obj3);
    }

    @Benchmark
    public void test_objects_hash(Blackhole bh) {
        bh.consume(objects_hash());
    }

    private static int objects_hash_local() {
        return myObjectHash(obj1, obj2, obj3);
    }

    private static int myObjectHash(Object... values) {
        return myArraysHashcode(values);
    }

    private static int myArraysHashcode(Object[] a) {
        if (a == null)
            return 0;
        int result = 1;
        for (Object element : a)
            result = 31 * result + (element == null ? 0 : element.hashCode());
        return result;
    }

    @Benchmark
    public void test_objects_hash_local(Blackhole bh) {
        bh.consume(objects_hash_local());
    }

    private static int object_hashcode() {
        int result = 1;
        result = 31 * result + (obj1 == null ? 0 : obj1.hashCode());
        result = 31 * result + (obj2 == null ? 0 : obj2.hashCode());
        result = 31 * result + (obj3 == null ? 0 : obj3.hashCode());
        return result;
    }

    @Benchmark
    public void test_object_hashcode(Blackhole bh) {
        bh.consume(object_hashcode());
    }
}

class MyHash {
    int hashCode;

    public MyHash(int h) {
        hashCode = h;
    }

    public int hashCode() {
        return hashCode;
    }
}