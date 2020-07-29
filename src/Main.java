
public class Main {

    public static void main(String args[]){

        Board b1 = new Board(4);
        b1.addTile('T', 3, 1);
        b1.addTile('#', 1, 1);
        //b1.addTile('C', 3, 2);
        b1.addTile('@', 0, 1);

        Board b2 = new Board(4);
        b2.addTile('T', 2, 1);
        //b2.addTile('B', 2, 1);
        //b2.addTile('C', 3, 1);

        BFS bfsTest = new BFS();
        DFS dfsTest = new DFS();
        IDS idsTest = new IDS();
        Astar astarTest = new Astar();
        
        long startTime = System.nanoTime();
        
        System.out.println("BFS:");
        bfsTest.run(b1, b2);
        long step1Time = System.nanoTime();

        System.out.println("\nDFS:");
        dfsTest.run(b1, b2);
        long step2Time = System.nanoTime();

        System.out.println("\nIDS:");
        idsTest.run(b1, b2);
        long step3Time = System.nanoTime();

        System.out.println("\nAstar:");
        astarTest.run(b1, b2);
        long step4Time = System.nanoTime();

        long bfsTime = (step1Time - startTime)/1000000;
        long dfsTime = (step2Time - step1Time)/1000000;
        long idsTime = (step3Time - step2Time)/1000000;
        long aStarTime = (step4Time - step3Time)/1000000;
        System.out.println("\nTimes:");
        System.out.println("BFS: " + bfsTime + "ms, DFS: " + dfsTime + "ms, IDS: " + idsTime + "ms, Astar:" + aStarTime + "ms");


    }


}