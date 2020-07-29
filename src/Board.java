import java.awt.Point;
import java.util.*;

public class Board {

    private char[][] board;
    private int size;
    private ArrayList<Character> characterList;
    private boolean agentBoolean;



    public ArrayList<Character> getCharacterList(){
        return characterList;
    }

    public int getSize(){
        return size;
    }

    public char[][] getBoard(){
        char[][] b = this.board;
        char[][] copy = new char[this.size][this.size];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){

                copy[i][j] = b[i][j];

            }
        }
        return copy;
    }

    public boolean getAgentBoolean(){
        return agentBoolean;
    }
    //method to create the 2d array to simulate the board
    public Board(int size){
        char[][] board = new char[size][size];
        
        for(int y = 0; y<size; y++){
            for(int x = 0; x<size; x++){
                board[y][x] = ' ';
            }   
        }
        this.board = board;
        this.size = size;
        characterList = new ArrayList<Character>();
        
    }

    public Board(Board b){
        this.board = b.getBoard();
        this.size = b.getSize();
        this.characterList = b.getCharacterList();
        this.agentBoolean = b.getAgentBoolean();
    }

    //method to add tiles, tiles are represented by char, ' ' represents and empty space, agents will be represented by the 'A' char.
    //will delete any tile already existing in location. 
    public void addTile(char label, int yVector, int xVector) throws IllegalArgumentException{
        
        
        if(yVector>=size || xVector>=size){
            throw new IllegalArgumentException("Tile posistion is off the board!");
        } else if(label != ' ' && characterList.contains(label)) {
            throw new IllegalArgumentException("Tile already exists");
        } else if(label == '@' && this.agentBoolean == true) {
            throw new IllegalArgumentException("Agent already exists");
        } else {
            Character removed = this.board[yVector][xVector];
            characterList.remove(removed);
            this.board[yVector][xVector] = label;
            if(label != '@' && label != ' ' && label != '#'){
                characterList.add(label);
            }

        }

    }

    //
    public char moveAgent(int agent_yP, int agent_xP, char dir) throws IllegalArgumentException{
        if(this.board[agent_yP][agent_xP]!='@' || agent_yP >= this.size || agent_xP >= this.size){
            throw new IllegalArgumentException("Invalid Move");
        } else {

            switch (dir) {

                case 's':
                        if(agent_yP == size-1){return 's';}
                        if(this.board[agent_yP+1][agent_xP] == '#'){return 's';}
                        swapTiles(agent_yP, agent_xP, agent_yP+1, agent_xP);
                        return 's';

                case 'w':
                        if(agent_yP == 0){return 'w';}
                        if(this.board[agent_yP-1][agent_xP] == '#'){return 'w';}
                        swapTiles(agent_yP, agent_xP, agent_yP-1, agent_xP);
                        return 'w';

                case 'a': 
                        if(agent_xP == 0){return 'a';}
                        if(this.board[agent_yP][agent_xP-1] == '#'){return 'a';}
                        swapTiles(agent_yP, agent_xP, agent_yP, agent_xP-1);
                        return 'a';
                    
                case 'd':
                        if(agent_xP == size-1){return 'd';}
                        if(this.board[agent_yP][agent_xP+1] == '#'){return 'd';}
                        swapTiles(agent_yP, agent_xP, agent_yP, agent_xP+1);
                        return 'd';
                        
                default: throw new IllegalArgumentException("Unreconised Move");

            }

        }

    }

    private void swapTiles(int y1, int x1, int y2, int x2){
        
        char store = board[y2][x2];
        board[y2][x2] = board[y1][x1];
        board[y1][x1] = store;

    }

    public void printBoard(){
        
        for(int i = 0; i < board.length; i++){
            System.out.print("|");

            for(int j = 0; j < board[i].length; j++){

                System.out.print(board[i][j] + "|");

            }

            System.out.print("\n");
        }
    }

    public Point findCharacter(char c) throws IllegalArgumentException{

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(c == board[i][j]){
                    return new Point(j,i);
                }
            }
        }

        throw new IllegalArgumentException("character does not exist on Board");

    }


}