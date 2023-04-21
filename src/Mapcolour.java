import java.sql.Array;
import java.util.*;




public class Mapcolour {
    public static final int VERTICES = 13;
    ArrayList<LinkedList<Integer>> adjacencyList;
    String[] color;

    HashMap<Integer,String> colorMap;
    HashMap<Integer,Integer> distanceMap;
    HashMap<Integer,List<Integer>> treeLevelsMap;

    int[] state;
    int[] predecessor;

    public Mapcolour(){
        adjacencyList=new ArrayList<LinkedList<Integer>>();
        color=new String[]{"None","Blue", "Brown", "Green", "Lavender", "Orange", "Pink", "Red", "Yellow", "Violet",
                           "Gold", "Gray", "Indigo", "Silver"};
        colorMap=new HashMap<>(VERTICES);
        distanceMap=new HashMap<>(VERTICES+1);
        state=new int[VERTICES+1];
        predecessor=new int[VERTICES+1];
        treeLevelsMap=new HashMap<>();


    }
    public void createAdjacencyList(int n){
        for(int i=0;i<=n;i++){
        adjacencyList.add(new LinkedList<Integer>());
        }
        adjacencyList.get(1).add(2);
        adjacencyList.get(1).add(3);
        adjacencyList.get(1).add(4);
        adjacencyList.get(1).add(5);
        adjacencyList.get(1).add(6);
        adjacencyList.get(1).add(7);
        adjacencyList.get(1).add(8);
        adjacencyList.get(1).add(9);
        adjacencyList.get(1).add(10);
        adjacencyList.get(1).add(11);
        adjacencyList.get(1).add(12);

        adjacencyList.get(2).add(1);
        adjacencyList.get(2).add(3);

        adjacencyList.get(3).add(1);
        adjacencyList.get(3).add(2);
        adjacencyList.get(3).add(4);

        adjacencyList.get(4).add(1);
        adjacencyList.get(4).add(3);
        adjacencyList.get(4).add(5);

        adjacencyList.get(5).add(1);
        adjacencyList.get(5).add(4);

        adjacencyList.get(6).add(1);
        adjacencyList.get(6).add(5);
        adjacencyList.get(6).add(7);
        adjacencyList.get(6).add(8);

        adjacencyList.get(7).add(1);
        adjacencyList.get(7).add(6);
        adjacencyList.get(7).add(8);
        adjacencyList.get(7).add(9);
        adjacencyList.get(7).add(13);

        adjacencyList.get(8).add(6);
        adjacencyList.get(8).add(7);

        adjacencyList.get(9).add(1);
        adjacencyList.get(9).add(7);
        adjacencyList.get(9).add(10);
        adjacencyList.get(9).add(11);
        adjacencyList.get(9).add(13);

        adjacencyList.get(10).add(1);
        adjacencyList.get(10).add(9);
        adjacencyList.get(10).add(11);

        adjacencyList.get(11).add(1);
        adjacencyList.get(11).add(9);
        adjacencyList.get(11).add(10);
        adjacencyList.get(11).add(12);
        adjacencyList.get(11).add(13);

        adjacencyList.get(12).add(1);
        adjacencyList.get(12).add(11);

        adjacencyList.get(13).add(7);
        adjacencyList.get(13).add(9);
        adjacencyList.get(13).add(11);

    }
    public void displayAdjacencyList(){
        System.out.println("\nThe adjacency list for South America graph is:");
        for(int i=1;i<=VERTICES;i++){
        System.out.println("Vertex "+i+" is adjacent to->"+adjacencyList.get(i));
        }
    }
    public void printColors(){
        System.out.println("\nColors used for each country:");
        for (Integer name: distanceMap.keySet()) {
            String key = name.toString();
            int value = distanceMap.get(name);
            System.out.println(key + " " + color[value]);

        }
        System.out.println("\nTotal no: of colors used:" + new HashSet<>(distanceMap.values()).size());
    }


    public void breadthFirstSearch(int sourceNode) {
        Queue<Integer> q=new LinkedList<>();
        state[sourceNode]=1;
        distanceMap.put(sourceNode,1);
        predecessor[sourceNode]=0;
        if(q.isEmpty()){
            q.add(sourceNode);
        }
        while (!(q.isEmpty())){
            int u=q.poll();

            for (Integer v : adjacencyList.get(u)) {
                if (state[v] == 0) {
                    state[v] = 1;
                    distanceMap.put(v, distanceMap.get(u) + 1);
                    predecessor[v] = u;
                    q.add(v);
                } else if (state[v] == 1) {
                    if (distanceMap.get(u) + 1 > distanceMap.get(v)) {
                        distanceMap.put(v, distanceMap.get(u) + 1);
                    }
                }
            }
            state[u]=2;

        }

    }
    public void printTree() {
        for (Integer vertex : distanceMap.keySet()) {
            int distance = distanceMap.get(vertex);
            if(!treeLevelsMap.containsKey(distance)) {
                treeLevelsMap.put(distance,new ArrayList<>());
            }
            treeLevelsMap.get(distance).add(vertex);
        }

        System.out.println("\nPrinting the BFS tree by levels:");
        for(Map.Entry<Integer, List<Integer>> entry: treeLevelsMap.entrySet()) {
            System.out.println("Level " + entry.getKey() + ": " + entry.getValue().toString());
        }
    }

    public static void main(String[] args) {
        Mapcolour mapColourObject=new Mapcolour();
        mapColourObject.createAdjacencyList(VERTICES);
        mapColourObject.displayAdjacencyList();
        mapColourObject.breadthFirstSearch(1);
        mapColourObject.printColors();
        mapColourObject.printTree();





    }




}
