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
        new Scheduler.Activity("Read", Daily,aDateTime);
        Assertions.assertEquals( 1 , Scheduler.AllActivities.size());
        new Scheduler.Activity("meditate",Daily,aDateTime2);
        Assertions.assertEquals( 2 , Scheduler.AllActivities.size());
        new Scheduler.Activity("Sing",Daily,aDateTime);
        Assertions.assertEquals( 3 , Scheduler.AllActivities.size());
    }

    @Tag("PROD")
  //  @Disabled
    @Test

    void testViewActivities()
    {
        Scheduler.Category weekly = new Scheduler.Category("weekly","blue");
        System.out.println("======TEST THREE EXECUTED=======");
        new Scheduler.Activity("Read", weekly,aDateTime);
        Assertions.assertEquals( 1 , Scheduler.AllActivities.size());
        new Scheduler.Activity("meditate",weekly,aDateTime2);
        Assertions.assertEquals( 2 , Scheduler.AllActivities.size());
        new Scheduler.Activity("Sing",weekly,aDateTime);
        Scheduler.DisplayALLActivitiesCategory(weekly);

        Assertions.assertEquals( 3 , Scheduler.AllActivities.size());
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