package demo.test.demo;


import demo.jupiter.displayname.HumanizeNameWithTestCaseId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.parallel.ExecutionMode.SAME_THREAD;


@Execution(SAME_THREAD)
@DisplayNameGeneration(HumanizeNameWithTestCaseId.class)
public class Junit5ParallelTest  {

@BeforeEach
    void beforeEach() {
    System.out.println("Before each Call");
}
@AfterEach
    void afterEach()
{
    System.out.println("After each Call");
}

@Test
    void parallelTest1 () throws InterruptedException {
    System.out.println("Parallel Test 1 start");
    TimeUnit.SECONDS.sleep(5);
    System.out.println("Parallel Test 1");
}

    @Test
    void parallelTest2() throws InterruptedException {
        System.out.println("Parallel Test 2 start");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("Parallel Test 2");
    }


}

