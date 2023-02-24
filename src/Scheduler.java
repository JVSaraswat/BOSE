import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Vector;

// Main class
public class Scheduler
{
    public static Vector<Activity> AllActivities = new Vector<>();
    public static Vector<Category> AllCategories = new Vector<>();

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // Main driver method
    public static void main(String[] args)
    {
        LocalDateTime aDateTime = LocalDateTime.of(2015, 7, 29, 19, 30, 40);
        LocalDateTime aDateTime2 = LocalDateTime.of(2014, 7, 29, 19, 30, 40);
        LocalDateTime aDateTime3 = LocalDateTime.of(2016, 7, 29, 19, 30, 40);
        //unit test#1
        Category Daily =  new Category("Daily","blue");
        AllCategories.add(Daily);
        Activity A1 = new Activity("Plan", Daily,aDateTime);
        Activity A2 = new Activity("Play", Daily,aDateTime);
//assertion,vogella junit
        System.out.println(AllActivities.get(0).ScheduledTime);
        viewActivity(aDateTime2,aDateTime3);//unit test#2
        DisplayALLActivitiesCategory(Daily);//unit test#3
        System.out.println("Print if units tests were successful");
        System.out.println(ANSI_RED
                + "Welcome to the Scheduler!"
                + ANSI_RESET);
        boolean runapp= true;
        while(runapp)
        {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("""
                Enter your Choice:
                1. Add New Category
                2. Schedule new activity
                3. Delete Category
                4. Assign Color
                5. Display all activities in a category
                6. View Activities in a duration
                7. Exit"""
        );
        String choice = myObj.nextLine();  // Read user input

            switch (choice)
            {
                case "1" ->
                {
                    String cat_name="";
                    System.out.println("Enter the name for a new category");
                    while (cat_name.length() != 0)
                    {cat_name= myObj.nextLine();}
                    System.out.println("Enter the Color for this category");
                    String cat_color = myObj.nextLine().toLowerCase();
                    AllCategories.add(new Category(cat_name, cat_color));
                    System.out.println("Category Created");
                }
                case "2" ->
                {
                    int syear=0,smonth = 0,sday = 0,shour = 0,sminutes = 0,sseconds = 0;
                    System.out.println("Enter the description of the new activity:");
                    String act_desc = myObj.nextLine();
                    System.out.println("Enter the category for this activity:");
                    String act_cat = myObj.nextLine();
                    LocalDateTime act_datetime = null;
                    System.out.println("Enter the date & time that you want to schedule this activity at:");
                    while(true)
                    {
                        while(true)
                        {
                            try
                            {
                                System.out.println("Please enter the YEAR to continue:");
                                syear=Integer.parseInt(myObj.nextLine());
                            } catch (Exception e) {System.out.println("Enter a valid Integer");}
                            if(syear>=2000&&syear<=2030) break;
                            else System.out.println("Enter a year between 2000 to 2030");
                        }

                        while(true)
                        {
                            try
                            {
                                System.out.println("Please enter the MONTH to continue:");
                                smonth=Integer.parseInt(myObj.nextLine());
                            } catch (Exception e) {System.out.println("Please enter a valid Integer");}
                            if(smonth>0&&smonth<13) break;
                            else System.out.println("Enter a month between 1 to 12");
                        }

                        while(true)
                        {
                            try
                            {
                                System.out.println("Please enter the DAY to continue:");
                                sday=Integer.parseInt(myObj.nextLine());
                            } catch (Exception e) {System.out.println("Please enter a valid Integer");}
                            if(sday>0&&sday<=31) break;
                            else System.out.println("Enter a day between 1 to 31");
                        }

                        while(true)
                        {
                            try
                            {
                                System.out.println("Enter the HOUR to continue:");
                                shour=Integer.parseInt(myObj.nextLine());
                            } catch (Exception e) {System.out.println("Please enter a valid Integer");}
                            if(shour>=0&&shour<24) break;
                            else System.out.println("Enter an hour between 0 to 23");
                        }

                        while(true)
                        {
                            try
                            {
                                System.out.println("Enter the MINUTES to continue:");
                                sminutes=Integer.parseInt(myObj.nextLine());
                            } catch (Exception e) {System.out.println("Enter a valid Integer");}
                            if(sminutes>=0&&sminutes<60) break;
                            else System.out.println("Enter a minute between 0 to 60");
                        }

                        while(true)
                        {
                            try
                            {
                                System.out.println("Enter the SECONDS to continue:");
                                sseconds=Integer.parseInt(myObj.nextLine());
                            } catch (Exception e) {System.out.println("Enter a valid Integer");}
                            if(sseconds>=0&&sseconds<60) break;
                            else System.out.println("Enter a second between 0 to 60");
                        }
                        try
                        {
                            act_datetime= LocalDateTime.of(syear, smonth, sday, shour, sminutes, sseconds);
                            break;
                        }
                        catch (Exception e) {System.out.println("The provided date for the month doesn't exist, please try again");}
                    }

                    boolean cat_exits=false;
                    for (int i = 0; i < AllCategories.size(); i++)
                    {
                        if(AllCategories.get(i).name.equals(act_cat))
                        {
                            System.out.println("Category entered matches previously existing category, assigning activity to this category");
                            AllActivities.add(new Activity(act_desc, AllCategories.get(i), act_datetime));
                            System.out.println("Activity Created");
                            cat_exits=true;
                            break;
                        }
                    }

                    if(!cat_exits)
                    {
                        AllCategories.add(new Category(act_cat));
                        AllActivities.add(new Activity(act_desc, new Category(act_cat), act_datetime));
                        System.out.println("Activity Created");
                    }
                }

                case "3" ->
                {
                    System.out.println("Enter the name of category you want to delete:");
                    String del_cat = myObj.nextLine();
                    for (int i = 0; i < AllCategories.size(); i++)
                        if (AllCategories.get(i).name.equals(del_cat)) {
                            AllCategories.remove(i);
                            System.out.println("Category removed.");
                        }
                }
                case "4" ->
                {
                    System.out.println("Enter the name of category you want to assign/change color for:");
                    String cat_selected = myObj.nextLine();
                    for (Category allCategory : AllCategories) {
                        if (allCategory.name.equals(cat_selected)) {
                            System.out.println("Category selected: " + allCategory.name);
                            System.out.println("What Color do you want to assign:");
                            allCategory.color = myObj.nextLine().toLowerCase();
                        }
                        else System.out.println("The entered category doesn't exist");
                    }
                }

                // Case
                case "5" ->
                {
                    System.out.println("Enter the name of category you want to display the activities for:");
                    String cat_select_display = myObj.nextLine();
                    for (Category allCategory : AllCategories)
                        if (allCategory.name.equals(cat_select_display))
                            DisplayALLActivitiesCategory(allCategory);
                        else System.out.println("The entered category doesn't exist");
                }


                case "6" ->
                {
                    int syear=0,smonth = 0,sday = 0,shour = 0,sminutes = 0,sseconds = 0,eyear = 0,emonth=0,eday=0,ehour=0,eminutes=0,eseconds=0;

                    System.out.println("Enter the start date-time elements as prompted:");
                    while(true)
                    {
                        try
                        {
                            System.out.println("Please enter the YEAR to continue:");
                            syear=Integer.parseInt(myObj.nextLine());
                        } catch (Exception e) {System.out.println("Enter a valid Integer");}
                        if(syear>=2000&&syear<=2030) break;
                        else System.out.println("Enter a year between 2000 to 2030");
                    }

                    while(true)
                    {
                        try
                        {
                            System.out.println("Please enter the MONTH to continue:");
                            smonth=Integer.parseInt(myObj.nextLine());
                        } catch (Exception e) {System.out.println("Please enter a valid Integer");}
                        if(smonth>0&&smonth<13) break;
                        else System.out.println("Enter a month between 1 to 12");
                    }

                    while(true)
                    {
                        try
                        {
                            System.out.println("Please enter the DAY to continue:");
                            sday=Integer.parseInt(myObj.nextLine());
                        } catch (Exception e) {System.out.println("Please enter a valid Integer");}
                        if(sday>0&&sday<=31) break;
                        else System.out.println("Enter a day between 1 to 31");
                    }

                    while(true)
                    {
                        try
                        {
                            System.out.println("Enter the HOUR to continue:");
                            shour=Integer.parseInt(myObj.nextLine());
                        } catch (Exception e) {System.out.println("Please enter a valid Integer");}
                        if(shour>=0&&shour<24) break;
                        else System.out.println("Enter an hour between 0 to 23");
                    }

                    while(true)
                    {
                        try
                        {
                            System.out.println("Enter the MINUTES to continue:");
                            sminutes=Integer.parseInt(myObj.nextLine());
                        } catch (Exception e) {System.out.println("Enter a valid Integer");}
                        if(sminutes>=0&&sminutes<60) break;
                        else System.out.println("Enter a minute between 0 to 60");
                    }

                    while(true)
                    {
                        try
                        {
                            System.out.println("Enter the SECONDS to continue:");
                            sseconds=Integer.parseInt(myObj.nextLine());
                        } catch (Exception e) {System.out.println("Enter a valid Integer");}
                        if(sseconds>=0&&sseconds<60) break;
                        else System.out.println("Enter a second between 0 to 60");
                    }

                    System.out.println("Enter the end date-time elements as prompted:");
                    while(true)
                    {
                        try
                        {
                            System.out.println("Please enter the YEAR to continue:");
                            eyear=Integer.parseInt(myObj.nextLine());
                        } catch (Exception e) {System.out.println("Enter a valid Integer");}
                        if(eyear>=2000&&eyear<=2030) break;
                        else System.out.println("Enter a year between 2000 to 2030");
                    }

                    while(true)
                    {
                        try
                        {
                            System.out.println("Please enter the MONTH to continue:");
                            emonth=Integer.parseInt(myObj.nextLine());
                        } catch (Exception e) {System.out.println("Please enter a valid Integer");}
                        if(emonth>0&&emonth<13) break;
                        else System.out.println("Enter a month between 1 to 12");
                    }

                    while(true)
                    {
                        try
                        {
                            System.out.println("Please enter the DAY to continue:");
                            eday=Integer.parseInt(myObj.nextLine());
                        } catch (Exception e) {System.out.println("Please enter a valid Integer");}
                        if(eday>0&&eday<=31) break;
                        else System.out.println("Enter a day between 1 to 31");
                    }

                    while(true)
                    {
                        try
                        {
                            System.out.println("Enter the HOUR to continue:");
                            ehour=Integer.parseInt(myObj.nextLine());
                        } catch (Exception e) {System.out.println("Please enter a valid Integer");}
                        if(ehour>=0&&ehour<24) break;
                        else System.out.println("Enter an hour between 0 to 23");
                    }

                    while(true)
                    {
                        try
                        {
                            System.out.println("Enter the MINUTES to continue:");
                            eminutes=Integer.parseInt(myObj.nextLine());
                        } catch (Exception e) {System.out.println("Enter a valid Integer");}
                        if(eminutes>=0&&eminutes<60) break;
                        else System.out.println("Enter a minute between 0 to 60");
                    }

                    while(true)
                    {
                        try
                        {
                            System.out.println("Enter the SECONDS to continue:");
                            eseconds=Integer.parseInt(myObj.nextLine());
                        } catch (Exception e) {System.out.println("Enter a valid Integer");}
                        if(eseconds>=0&&eseconds<60) break;
                        else System.out.println("Enter a second between 0 to 60");
                    }


                    try
                    {
                        LocalDateTime sdatetime = LocalDateTime.of(syear, smonth, sday, shour, sminutes, sseconds);
                        LocalDateTime edatetime = LocalDateTime.of(eyear, emonth, eday, ehour, eminutes, eseconds);
                        viewActivity(sdatetime, edatetime);
                    }
                    catch (Exception e) {System.out.println("The provided date for the month doesn't exist, please press 6 again to try again");}

                }
                case "7" -> runapp = false;
                default -> System.out.println("Invalid option");
            }
    }

        for (Category allCategory : AllCategories)
        {
            System.out.println(ANSI_YELLOW + allCategory.name + "  " + allCategory.color + ANSI_RESET);
        }
    }

    public static class Activity
    {
        String description;
        Category category;
        LocalDateTime ScheduledTime;

       public Activity(String description,Category category, LocalDateTime ScheduledTime)
       {
           this.description=description;
           this.category=category;
           this.ScheduledTime=ScheduledTime;
           AllActivities.add(this);
       }
    }
    public static class Category
    {
        String name;
        String color="";

        public Category(String Name, String color)
        {
            this.color=color.toLowerCase();
            this.name=Name;
        }

        public Category(String name)
        {
            this.name=name;
        }
    }
    public static void DisplayALLActivitiesCategory(Category cat)
    {
        String cat_col= cat.color;
        for (Activity allActivity : AllActivities) {
            if (allActivity.category.name.equals(cat.name))
            {
                switch (cat_col) {
                    case "black" -> System.out.println(ANSI_BLACK + allActivity.description + "  " + allActivity.ScheduledTime + ANSI_RESET);
                    case "red" -> System.out.println(ANSI_RED + allActivity.description + "  " + allActivity.ScheduledTime + ANSI_RESET);
                    case "green" -> System.out.println(ANSI_GREEN + allActivity.description + "  " + allActivity.ScheduledTime + ANSI_RESET);
                    case "yellow" -> System.out.println(ANSI_YELLOW + allActivity.description + "  " + allActivity.ScheduledTime + ANSI_RESET);
                    case "blue" -> System.out.println(ANSI_BLUE + allActivity.description + "  " + allActivity.ScheduledTime + ANSI_RESET);
                    case "purple" -> System.out.println(ANSI_PURPLE + allActivity.description + "  " + allActivity.ScheduledTime + ANSI_RESET);
                    case "cyan" -> System.out.println(ANSI_CYAN + allActivity.description + "  " + allActivity.ScheduledTime + ANSI_RESET);
                    case "white" -> System.out.println(ANSI_WHITE + allActivity.description + "  " + allActivity.ScheduledTime + ANSI_RESET);
                    default -> System.out.println(allActivity.description + "  " + allActivity.ScheduledTime);
                }
            }
        }
    }
    public static void viewActivity(LocalDateTime Start, LocalDateTime End)
    {
        for (Activity allActivity : AllActivities) {
            if (allActivity.ScheduledTime.isAfter(Start) && allActivity.ScheduledTime.isBefore(End)) {
                switch (allActivity.category.color) {
                    case "black" -> System.out.println(ANSI_BLACK + allActivity.description + "  " + allActivity.ScheduledTime + ANSI_RESET);
                    case "red" -> System.out.println(ANSI_RED + allActivity.description + "  " + allActivity.ScheduledTime + ANSI_RESET);
                    case "green" -> System.out.println(ANSI_GREEN + allActivity.description + "  " + allActivity.ScheduledTime + ANSI_RESET);
                    case "yellow" -> System.out.println(ANSI_YELLOW + allActivity.description + "  " + allActivity.ScheduledTime + ANSI_RESET);
                    case "blue" -> System.out.println(ANSI_BLUE + allActivity.description + "  " + allActivity.ScheduledTime + ANSI_RESET);
                    case "purple" -> System.out.println(ANSI_PURPLE + allActivity.description + "  " + allActivity.ScheduledTime + ANSI_RESET);
                    case "cyan" -> System.out.println(ANSI_CYAN + allActivity.description + "  " + allActivity.ScheduledTime + ANSI_RESET);
                    case "white" -> System.out.println(ANSI_WHITE + allActivity.description + "  " + allActivity.ScheduledTime + ANSI_RESET);
                    default -> System.out.println(allActivity.description + "  " + allActivity.ScheduledTime);
                }
            }
        }
    }

}
