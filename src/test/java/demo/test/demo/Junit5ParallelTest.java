package demo.test.demo;


import demo.jupiter.displayname.HumanizeNameWithTestCaseId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.parallel.ExecutionMode.SAME_THREAD;


/**
 * Configuration parameters to execute top-level classes in parallel but methods in same thread
 * Configuration parameters to execute top-level classes in parallel but methods in same thread
 * junit.jupiter.execution.parallel.enabled = true
 * junit.jupiter.execution.parallel.mode.default = same_thread
 * junit.jupiter.execution.parallel.mode.classes.default = concurrent
 * <p>
 * <p>
 * Configuration parameters to execute top-level classes sequentially but their methods in parallel
 * junit.jupiter.execution.parallel.enabled = true
 * junit.jupiter.execution.parallel.mode.default = concurrent
 * junit.jupiter.execution.parallel.mode.classes.default = same_thread
 * <p>
 * <p>
 * <p>
 * dynamic
 * Computes the desired parallelism based on the number of available processors/cores multiplied by the junit.jupiter.execution.parallel.config.dynamic.factor configuration parameter (defaults to 1).
 * <p>
 * fixed
 * Uses the mandatory junit.jupiter.execution.parallel.config.fixed.parallelism configuration parameter as the desired parallelism.
 * <p>
 * custom
 * Allows you to specify a custom ParallelExecutionConfigurationStrategy implementation via the mandatory junit.jupiter.execution.parallel.config.custom.class configuration parameter to determine the desired configuration.
 * <p>
 * <p>
 * <p>
 * When access to shared resources is declared using the @ResourceLock annotation, the JUnit Jupiter engine uses this information to ensure that no conflicting tests are run in parallel.
 */


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

