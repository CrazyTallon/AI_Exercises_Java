import java.util.*;
import java.awt.Point;

public class DFS extends SearchMethod{

    Stack<String> sequences;

    public String run(Board b1, Board b2){

        setStartState(b1);
        setGoalState(b2);


        if(!solutionCheck()){
            throw new IllegalArgumentException("impossible to get solution");
        }

        //setting up queue
        sequences = new Stack<String>();
        expand(startState,"");


        Boolean seqResult = false;
        Board currentBoard;
        String s = "";
        int i = 0;
        
        //loop searching for solution, will finish once loop has been found.
        while(seqResult == false) {
            i = i + 1;
            s = sequences.pop();
            //if(i<10){System.out.println("Cycle " + i + " Popped from stack: " + s);}
            currentBoard = runSequence(s);
            seqResult = equalityCheck(currentBoard, goalState);
            expand(currentBoard, s);
        }
        System.out.println("DFS Solution: " + s);
        System.out.println("Sequence length = " + s.length());
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

        ArrayList<Character> list = new ArrayList<Character>();

        if(!(x == 0) && lastChar != 'd'){
            if(board[y][x-1] != '#'){list.add('a');}
        }
        if(!(y == size-1) && lastChar != 'w'){
            if(board[y+1][x] != '#'){list.add('s');}
        }
        if(!(x == size-1) && lastChar != 'a'){
            if(board[y][x+1] != '#'){list.add('d');}
        }
        if(!(y == 0) && lastChar != 's'){
            if(board[y-1][x] != '#'){list.add('w');}
        }

        Collections.shuffle(list);

        for(Character c : list){
            String seq = s + c;
            this.sequences.push(seq);
        }

    }
}