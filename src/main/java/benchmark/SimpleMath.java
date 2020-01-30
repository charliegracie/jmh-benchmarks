package benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Random;

@State(Scope.Benchmark)
@Fork(value = 1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
public class SimpleMath {

    private static Random s_r = new Random(7);
    private static int next() { return s_r.nextInt() % 1000; }

    private static int simple_math(int val1, int val2) {
        Integer v1 = val1;
        Integer v2 = val2;
        return v1 + v2;
    }

    @Benchmark
    public void test_simple_math(Blackhole bh) {
        bh.consume(simple_math(next(), next()));
    }

    private static int simple_math_new(int val1, int val2) {
        Integer v1 = new Integer(val1);
        Integer v2 = new Integer(val2);
        return new Integer(v1.intValue() + v2.intValue());
    }

    @Benchmark
    public void test_simple_math_new(Blackhole bh) {
        bh.consume(simple_math_new(next(), next()));
    }

    private static int simple_math_myobject_valueof(int val1, int val2) {
        MyObject v1 = MyObject.valueOf(val1);
        MyObject v2 = MyObject.valueOf(val2);
        return v1.plus(v2).value();
    }

    @Benchmark
    public void test_simple_math_myobject_valueof(Blackhole bh) {
        bh.consume(simple_math_myobject_valueof(next(), next()));
    }

    private static int simple_math_myobject(int val1, int val2) {
        MyObject v1 = new MyObject(val1);
        MyObject v2 = new MyObject(val2);
        return v1.plus(v2).value();
    }

    @Benchmark
    public void test_simple_math_myobject(Blackhole bh) {
        bh.consume(simple_math_myobject(next(), next()));
    }
}
