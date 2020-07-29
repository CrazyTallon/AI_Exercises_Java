import java.util.*;

import java.awt.Point;

public class Astar extends SearchMethod{

    PriorityQueue<Node> sequences;

    public String run(Board b1, Board b2){

        setStartState(b1);
        setGoalState(b2);

        if(!solutionCheck()){
            throw new IllegalArgumentException("impossible to get solution");
        }

        //setting up queue
        sequences = new PriorityQueue<Node>(4, new SequenceComparator());
        expand(startState, "");

        Boolean seqResult = false;
        Board currentBoard;
        String s = "";
        
        //loop searching for solution, will finish once loop has been found.
        while(seqResult == false) {
            Node n = sequences.remove();
            s = n.getSequence();

            //System.out.println("Removed From Queue: " + s);

            currentBoard = n.getResult();
            seqResult = equalityCheck(currentBoard, goalState);
            if(seqResult == false){expand(currentBoard, s);}
            if(seqResult == true){}
        }
        System.out.println("Astar Solution: " + s);
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
        //System.out.println();

        if(!(x == 0) && lastChar != 'd'){
            if(board[y][x-1] != '#'){
                Node na = new Node(s+"a");
                sequences.add(na); 
                //System.out.println(" Adding to Queue: " + s+"a" + ", Heuristic: " + na.getHeuristic());
            }
        }
        if(!(y == size-1) && lastChar != 'w'){
            if(board[y+1][x] != '#'){
                Node ns = new Node(s+"s");
                sequences.add(ns); 
                //System.out.println(" Adding to Queue: " + s+"s" + ", Heuristic: " + ns.getHeuristic());
            }
        }
        if(!(x == size-1) && lastChar != 'a'){
            if(board[y][x+1] != '#'){
                Node nd = new Node(s+"d");
                sequences.add(nd); 
                //System.out.println(" Adding to Queue: " + s+"d" + ", Heuristic: " + nd.getHeuristic());
            }
        }
        if(!(y == 0) && lastChar != 's'){
            if(board[y-1][x] != '#'){
                Node nw = new Node(s+"w");
                sequences.add(nw); 
                //System.out.println(" Adding to Queue: " + s+"w" + ", Heuristic: " + nw.getHeuristic());
            }
        }

    }


    class SequenceComparator implements Comparator<Node>{

        public int compare(Node n1, Node n2){
            int h1 = n1.getHeuristic();
            int h2 = n2.getHeuristic();
            int s1 = n1.getSequence().length();
            int s2 = n2.getSequence().length();

            //System.out.println(s1 + ": " + h1 + ",\n" + s2 + ": " + h2);
            if(h1 < h2){return -1;}
            else if(h1 > h2) {return 1;}
            else if(s1 < s2) {return -1;}
            else if(s1 > s2) {return 1;}
            else return 0;
        }

    }

    class Node {

        private String sequence;
        private Board result;
        private int heuristic;

        private Node(String s){
            this.sequence = s;
            this.result = runSequence(s);
            this.heuristic = findHeuristic(result, s);
        }


        private String getSequence(){
            return this.sequence;
        }

        private Board getResult(){
            return this.result;
        }

        private int getHeuristic(){
            return this.heuristic;
        }


        private int findHeuristic(Board b, String s){
        
            ArrayList<Character> list = b.getCharacterList();
    
            int goalDistance = 0;
            int gheuristic = 0;
            //int length = s.length();
            
            for(char c: list){
                Point boardPosition = b.findCharacter(c);
                int c_x = (int) boardPosition.getX();
                int c_y = (int) boardPosition.getY();
                
                Point goalPosition = Astar.this.goalState.findCharacter(c);
                int g_x = (int) goalPosition.getX();
                int g_y = (int) goalPosition.getY();
                
                goalDistance = Math.abs(c_x - g_x) + Math.abs(c_y - g_y);
                
                gheuristic = gheuristic + goalDistance;

            }
    
            int heuristic = gheuristic;
            return heuristic;
        }

    }

}

