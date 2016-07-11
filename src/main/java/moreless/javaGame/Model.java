package moreless.javaGame;


import java.util.ArrayList;


public class Model {

    private int start; //start of range
    private int end;    //end of range
    private int goal;   //number which should be guessed by user
    private ArrayList<Integer> logList = new ArrayList(); //log of user's attempts

    // The Program logic
   
    /**
     * this method sets goal
     * value
     * @param goal
     * @return sum
     */
    private void setGoal(int goal) {
        this.goal = goal;
    }
    
    /**
     * this method fills goal with random value in certain range.
     * Value is generated randomly  in range (start,end)
     * @param start
     * @param end
     */
    public void rand(int start, int end){
        this.start=start+1;
        this.end=end-1;
        setGoal(this.start + (int)(Math.random() * ((this.end - this.start) + 1)));//random value is generated in (start; end) range

    }

    /**
     * this method compares user's number and goal.
     * If user failed, range will be
     * shortened considering user's number
     * @param userPoint
     * @return result
     */
    public boolean tryGuess(int userPoint){
        logList.add(userPoint);                 //fill the log with user's numbers
        if(userPoint==goal){
            return true;
        }
        else{
            if(userPoint>goal)
                this.end=userPoint-1;
            else
                this.start=userPoint+1;
            return false;
        }
    }

    /**
     * this method returns start
     * @return start
     */
    public int getStart() {
        return start;
    }

    /**
     * this method returns end
     * @return end
     */
    public int getEnd() {
        return end;
    }

    /**
     * this returns log
     * of user's attempts
     * @return logList
     */
    public ArrayList<Integer>  log(){
                return logList;
    }


}
