import org.junit.jupiter.api.*;
import java.time.LocalDateTime;


public class AppTest {
    LocalDateTime aDateTime = LocalDateTime.of(2015, 7, 29, 19, 30, 40);
    LocalDateTime aDateTime2 = LocalDateTime.of(2014, 7, 29, 19, 30, 40);
    Scheduler.Category Daily =  new Scheduler.Category("Daily","green");
    @BeforeAll
    static void setup(){
        System.out.println("Setting up tests...");
    }

    @BeforeEach
    void setupThis(){
        System.out.println("Initiating test");
    }

    @Tag("DEV")
    @Test
    void testAddCategories()
    {
        System.out.println("======TEST ONE EXECUTED=======");
        Scheduler.AllCategories.add(Daily);
        Assertions.assertEquals( 1 , Scheduler.AllCategories.size());
        Scheduler.AllCategories.add(new Scheduler.Category("Weekly"));
        Assertions.assertEquals( 2 , Scheduler.AllCategories.size());
    }
    @Test
    void testAddActivity()
    {
        System.out.println("======TEST TWO EXECUTED=======");
        Assertions.assertEquals( 3 , Scheduler.AllActivities.size());
    }

    @Tag("PROD")
  //  @Disabled
    @Test

    void testViewActivities()
    {
        System.out.println("======TEST THREE EXECUTED=======");
        Scheduler.Activity act1 =  new Scheduler.Activity("Read", Daily,aDateTime);
        Scheduler.Activity act2 = new Scheduler.Activity("meditate",Daily,aDateTime2);
        Scheduler.Activity act4 =  new Scheduler.Activity("Sing",Daily,aDateTime);
        Scheduler.DisplayALLActivitiesCategory(Daily);
    }
    @AfterEach
    void tearThis(){
        System.out.println("Test Successful");
    }

    @AfterAll
    static void tear(){
        System.out.println("All tests were completed successfully");
    }
}