import java.util.*;
import java.awt.Point;

public class IDS extends SearchMethod{

    Stack<String> sequences;

    public String run(Board b1, Board b2){

        setStartState(b1);
        setGoalState(b2);


        if(!solutionCheck()){
            throw new IllegalArgumentException("impossible to get solution");
        }

        //setting up queue
        sequences = new Stack<String>();
        


        Boolean seqResult = false;
        Boolean iterationFinished;
        Board currentBoard;
        String s = "";
        int i = 0;
        //loop searching for solution, will finish once loop has been found.
        while(seqResult == false) {
            
            i = i+1;
            //System.out.println("Starting Iteratation " + i);
            iterationFinished = false;
            expand(startState, "");

            while(iterationFinished == false){
            
                
                s = sequences.pop();
                //System.out.println("Popped from Stack:" + s);

                currentBoard = runSequence(s);

                seqResult = equalityCheck(currentBoard, goalState);
                
                if(s.length() < i) {expand(currentBoard, s);}
                if(sequences.empty()){iterationFinished = true;}
                if(seqResult == true){iterationFinished = true;}
            }
            //System.out.println();
        }
        System.out.println("IDS Solution: " + s);
        return s;
    }

    protected void expand(Board b, String s){
       
        Point agent = b.findCharacter('@');
        int x = (int) agent.getX();
        int y = (int) agent.getY();
        int size = b.getSize();
        char[][] board = b.getBoard();
        char lastChar = 't';
        if(s != "") {lastChar = s.charAt(s.length() - 1);}

        if(!(y == 0) && lastChar != 's'){
            if(board[y-1][x] != '#') {sequences.push(s+"w"); 
            //System.out.print(" Pushing onto stack: " + s+"w,");
            }
        }
        if(!(x == size-1) && lastChar != 'a'){
            if(board[y][x+1] != '#'){sequences.push(s+"d"); 
            //System.out.print(" Pushing onto stack: " + s+"d,");
            }
        }
        if(!(y == size-1) && lastChar != 'w'){
            if(board[y+1][x] != '#'){sequences.push(s+"s"); 
            //System.out.print(" Pushing onto stack: " + s+"s,");
            }
        }
        if(!(x == 0) && lastChar != 'd'){
            if(board[y][x-1] != '#'){sequences.push(s+"a"); 
            //System.out.print(" Pushing onto stack: " + s+"a,");
            }
        }

        //System.out.println();

    }

}