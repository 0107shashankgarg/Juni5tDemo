package demo.test.demo;


import org.junit.jupiter.api.*;


@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class JunitTestInstanceTest {


    /**
     * show Difference  between TestInstance.Lifecycle.PER_CLASS and TestInstance.Lifecycle.PER_METHOD
     */


    private String dummy = "Junit4";


    @BeforeAll
    public static void beforeALL() {
        System.out.println("Before all");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("After All");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("Before each");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("After each");
    }

    @Test
    void simpleTest1() {
        dummy = "Junit5";
        System.out.println(dummy);

    }

    @Test
    void simpleTest2() {
        System.out.println(dummy);
    }


}
