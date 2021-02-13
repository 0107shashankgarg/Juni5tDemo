package demo.test.demo;


import demo.jupiter.extension.ExceptionExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(ExceptionExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JunitExceptionTest {


    /**
     * show how one can handle Exception thrown in a different  life cycle of a test
     */


    @BeforeAll
    public void beforeALL() throws Exception {

        throw new Exception("Exception throwm by a beforeALL ");
    }

    @BeforeEach
    public void beforeEach() throws Exception {
        //  throw  new Exception("Exception throwm by a beforeEach");
    }

    @AfterEach
    public void afterEach() throws Exception {
        // throw  new Exception("Exception throwm by a afterEach");
    }

    @AfterAll
    public void afterAll() throws Exception {
        throw new Exception("Exception throwm by a afterAll");
    }


    @Test
    void simpleTest1() throws Exception {
        throw new Exception("Exception throwm by a method ");

    }


}
