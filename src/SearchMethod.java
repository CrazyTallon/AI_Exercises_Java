import java.util.*;
import java.awt.Point;

public abstract class SearchMethod{

    protected Board startState;
    protected Board goalState;
    

    final protected void setStartState(Board b1){
        this.startState = b1;
    }

    final protected void setGoalState(Board b2){
        this.goalState = b2;
    }


    abstract protected String run(Board b1, Board b2);

    abstract protected void expand(Board b, String s);


    final protected Board runSequence(String s){
        
        char[] moves = s.toCharArray();
        Board currentState = new Board(startState);
        
        Point agent;
        for(char c: moves){
            agent = currentState.findCharacter('@');
            int y = (int) agent.getY();
            int x = (int) agent.getX();
            currentState.moveAgent(y, x, c);
        }
        return currentState;
    }

    final protected Boolean equalityCheck(Board board1, Board board2){
        int size = board1.getSize();
        boolean value = true;
        char[][] b1 = board1.getBoard();
        char[][] b2 = board2.getBoard();

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){

                if( (b1[i][j] == '@' && b2[i][j] == ' ') || (b1[i][j] == ' ' && b2[i][j] == '@') ) {}
                else if( (b1[i][j] == '#' && b2[i][j] == ' ') || (b1[i][j] == ' ' && b2[i][j] == '#') ) {}
                else if( (b1[i][j] == '@' && b2[i][j] == '#') || (b1[i][j] == '#' && b2[i][j] == '@') ) {}
                else if(b1[i][j] != b2[i][j]) {value = false;}
            
            }
        }
            
        return value;

    }

    final protected Boolean solutionCheck(){
        ArrayList<Character> list1 = startState.getCharacterList();
        ArrayList<Character> list2 = goalState.getCharacterList();
        if(list1 == null || list2 == null || list1.size() != list2.size()) {return false;}
        
        boolean v = true;
        for(char c: list1){
            if(!list2.contains(c)){
                v = false;
            }
        }

        Point p;
        int x;
        int y;
        char[][] b = startState.getBoard();
        for(char c: list2){
            p = goalState.findCharacter(c);
            x = (int) p.getX();
            y = (int) p.getY();
            if(b[y][x] == '#') {v = false;}
        }
        return v;
    }


}