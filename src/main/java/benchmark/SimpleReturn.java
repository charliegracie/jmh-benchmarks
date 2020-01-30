package benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Random;

@State(Scope.Benchmark)
@Fork(value = 1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
public class SimpleReturn {

    private static Random s_r = new Random(7);
    private static int next() { return s_r.nextInt() % 1000; }

    private static int simple_return_valueOf(int val) {
        return Integer.valueOf(val);
    }

    @Benchmark
    public void test_simple_return_valueOf(Blackhole bh) {
        bh.consume(simple_return_valueOf(next()));
    }

    private static int simple_return_new(int val) {
        return new Integer(val).intValue();
    }

    @Benchmark
    public void test_simple_return_new(Blackhole bh) {
        bh.consume(simple_return_new(next()));
    }

    private static int simple_return_myobject_valueof(int val) {
        return MyObject.valueOf(val).value();
    }

    @Benchmark
    public void test_simple_return_myobject_valueof(Blackhole bh) {
        bh.consume(simple_return_myobject_valueof(next()));
    }

    private static int simple_return_myobject(int val) {
        return new MyObject(val).value();
    }

    @Benchmark
    public void test_simple_return_myobject(Blackhole bh) {
        bh.consume(simple_return_myobject(next()));
    }
}
