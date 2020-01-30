package benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Random;

@State(Scope.Benchmark)
@Fork(value = 1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
public class SimpleLoop {

    private static Random s_r = new Random(7);
    private static int next() { return s_r.nextInt() % 1000; }

    private static int simple_loop_valueOf(int loopCount, int initialValue) {
        Integer sum = Integer.valueOf(initialValue);
        for (int i = 0; i < loopCount; i++) {
            Integer i_val = Integer.valueOf(i);
            sum += i_val;
        }

        return sum.intValue();
    }

    @Benchmark
    public void test_simple_loop_valueOf(Blackhole bh) {
        bh.consume(simple_loop_valueOf(100_000, next()));
    }

    private static int simple_loop_new(int loopCount, int initialValue) {
        Integer sum = new Integer(initialValue);
        for (int i = 0; i < loopCount; i++) {
            Integer i_val = new Integer(i);
            sum += i_val;
        }

        return sum.intValue();
    }

    @Benchmark
    public void test_simple_loop_new(Blackhole bh) {
        bh.consume(simple_loop_new(100_000, next()));
    }

    private static int simple_loop_myobject_new(int loopCount, int initialValue) {
        MyObject sum = new MyObject(initialValue);
        for (int i = 0; i < loopCount; i++) {
            MyObject i_val = new MyObject(i);
            sum = sum.plus(i_val);
        }

        return sum.value();
    }

    @Benchmark
    public void test_simple_loop_myobject_new(Blackhole bh) {
        bh.consume(simple_loop_myobject_new(100_000, next()));
    }

    private static int simple_loop_myobject_valueof(int loopCount, int initialValue) {
        MyObject sum = MyObject.valueOf(initialValue);
        for (int i = 0; i < loopCount; i++) {
            MyObject i_val = MyObject.valueOf(i);
            sum = sum.plus(i_val);
        }

        return sum.value();
    }

    @Benchmark
    public void test_simple_loop_myobject_valueof(Blackhole bh) {
        bh.consume(simple_loop_myobject_valueof(100_000, next()));
    }

}