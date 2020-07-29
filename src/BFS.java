import java.util.Queue;
import java.util.LinkedList;
import java.awt.Point;

public class BFS extends SearchMethod{

    Queue<String> sequences;

    public String run(Board b1, Board b2){

        setStartState(b1);
        setGoalState(b2);

        if(!solutionCheck()){
            throw new IllegalArgumentException("impossible to get solution");
        }

        //setting up queue
        sequences = new LinkedList<>();
        
        expand(startState,"");

        Boolean seqResult = false;
        Board currentBoard;
        String s = "";
        
        //loop searching for solution, will finish once loop has been found.
        while(seqResult == false) {
            s = sequences.remove();

            //System.out.println("\nRemoved From Queue: " + s);

            currentBoard = runSequence(s);
            seqResult = equalityCheck(currentBoard, goalState);
            expand(currentBoard, s);
            if(seqResult == true){}
        }

        System.out.println("BFS Solution: " + s);
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
        
        if(!(x == 0) && lastChar != 'd'){
            if(board[y][x-1] != '#'){sequences.add(s+"a"); 
            //System.out.print(" Adding to Queue: " + s+"a,");
            }
        }
        if(!(y == size-1) && lastChar != 'w'){
            if(board[y+1][x] != '#'){sequences.add(s+"s"); 
            //System.out.print(" Adding to Queue: " + s+"s,");
            }
        }
        if(!(x == size-1) && lastChar != 'a'){
            if(board[y][x+1] != '#'){sequences.add(s+"d"); 
            //System.out.print(" Adding to Queue: " + s+"d,");
            }
        }
        if(!(y == 0) && lastChar != 's'){
            if(board[y-1][x] != '#') {sequences.add(s+"w"); 
            //System.out.print(" Adding to Queue: " + s+"w,");
            }
        }
    }

}