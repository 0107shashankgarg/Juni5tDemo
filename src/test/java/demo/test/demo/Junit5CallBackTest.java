package demo.test.demo;


import demo.jupiter.extension.TestExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;



@ExtendWith({TestExtension.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Junit5CallBackTest {


    @BeforeAll
    public void beforeALL() {
        System.out.println("Before all");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("Before each");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("After each");
    }

    @AfterAll
    public void afterAll() {
        System.out.println("After All");
    }


    @Test
    void simpleTest1() {
        System.out.println("test 1");
    }

    @Test
    void simpleTest2() {
        System.out.println("test 2 ");
    }


    @Test
    void exceptionTest() throws Exception {
        throw new Exception("Just a custom Exception message");
    }


}
