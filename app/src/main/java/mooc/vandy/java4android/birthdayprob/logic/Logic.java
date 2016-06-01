package mooc.vandy.java4android.birthdayprob.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import mooc.vandy.java4android.birthdayprob.ui.OutputInterface;

/**
 * This is where the logic of this App is centralized for this assignment.
 * <p>
 * The assignments are designed this way to simplify your early Android interactions.
 * Designing the assignments this way allows you to first learn key 'Java' features without
 * having to beforehand learn the complexities of Android.
 *
 */
public class Logic implements LogicInterface {

    // This is a String to be used in Logging (if/when you decide you need it for debugging)
    public static final String TAG = Logic.class.getName();

    /*
    * This is the variable that stores our OutputInterface instance.
    * <p>
    * This is how we will interact with the User Interface [MainActivity.java].
    * <p>
    * it is called 'out' because it is where we 'out-put' our results. (It is also the 'in-put'
    * from where we get values from, but it only needs 1 name, and 'out' is good enough)
    */
    OutputInterface out;

    /**
     * This is the constructor of this class.
     * <p>
     * It assigns the passed in [MainActivity] instance
     * (which implements [OutputInterface]) to 'out'
     */
    public Logic(OutputInterface _out){
        out = _out;
    }

    /**
     * This is the method that will (eventually) get called when the on-screen button labelled
     * 'Process...' is pressed.
     */
    public void process() {

        int groupSize = out.getSize();
        int simulationCount = out.getCount();

        if (groupSize < 2 || groupSize > 365) {
            out.makeAlertToast("Group Size must be in the range 2-365.");
            return;
        }
        if (simulationCount <= 0) {
            out.makeAlertToast("Simulation Count must be positive.");
            return;
        }

        double percent = calculate(groupSize, simulationCount);

        // report results
        out.println("For a group of " + groupSize + " people, the percentage");
        out.println("of times that two people share the same birthday is");
        out.println(String.format("%.2f%% of the time.", percent));

    }

    /**
     * This is the method that actually does the calculations.
     * <p>
     * We provide you this method that way we can test it with unit testing.
     */
    public double calculate(int size, int count){
        boolean check = false;
        double ans = 0.0;
       int count2 = count;
        int size2 = size;
        while(count2!=0){
            Map<Integer, Integer> myMap = new HashMap<Integer,Integer>();
            Random rand = new Random();
            rand.setSeed(count2);
            while(size2!=0){
                int birthday = rand.nextInt(365)+0;
                if(myMap.containsKey(birthday)){
                    check = true;
                    break;
                }else{
                    myMap.put(birthday,count2);
                }
            size2--;
            }
         if(check == true){
             ans++;
             check = false;
         }
        count2--;
            size2 = size;
        }
        double end = (ans*100.0)/count;
        return end;

       
    }

    // TODO -- add your code here
     
}
