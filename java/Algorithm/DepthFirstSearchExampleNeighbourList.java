import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;;
 
public class DepthFirstSearchExampleNeighbourList
{ 
 
    static class Node
    {
        int data;
        boolean visited;
        String color;
        int d_time;
        int f_time;
        List<Node> neighbours;
 
        Node(int data)
        {
            this.data=data;
            this.neighbours=new ArrayList<>();
            this.color="white";
            this.d_time =-1;
            this.f_time =-1;
 
        }
        public void addneighbours(Node neighbourNode)
        {
            this.neighbours.add(neighbourNode);
        }
        public List<Node> getNeighbours() {
            return neighbours;
        }
    }
 
    public  void dfs(Node node,List<Node> all,List<Node>ans) {
    	int time = 0;
		node=all.get(0);
		dfsVisit(node,time,ans);
    }
    public  void dfsVisit(Node node,int time,List<Node> ans) {
    	time = time+1;
    	node.d_time=time;
    	node.color ="gray";
    	for(int i=0;i<node.neighbours.size();i++) {
    		if (node.neighbours.get(i).color.equals("white")) {
    			dfsVisit(node.neighbours.get(i),time,ans);
    		}
    	}
    	node.color = "black";
    	time=time+1;
    	node.f_time=time;
    	ans.add(node);
    }
//    public static void main(String arg[])
//    {	
//    	Scanner sc = new Scanner(System.in);
//    	List<Node> nodeall= new ArrayList<>();
//    	List<Node> ans= new ArrayList<>();
//    	String input =sc.nextLine();
//    	input=input.replace("[","");
//    	input=input.replace("]","");
//    	input=input.replace(" ","");
//    	String inputsp[] =input.split(",");
//    	int listLength=Integer.parseInt(inputsp[0]);
//    	int test[][] =new int [listLength][2];
//    	int testNum=1;
//    	for(int i=0;i<test.length;i++) {
//    		for (int j=0;j<test[i].length;j++) {
//    			test[i][j] = Integer.parseInt(inputsp[testNum]);
//    			testNum++;
//    		}
//    	}
//    	for(int i=0;i<4;i++) {
//    		Node node =new Node(i);
//    		nodeall.add(node);
//    	}
//    	for(int i=0;i<test.length;i++) {
//    		nodeall.get(test[i][1]).addneighbours(nodeall.get(test[i][0]));
//    	}
//        DepthFirstSearchExampleNeighbourList dfsExample = new DepthFirstSearchExampleNeighbourList();
//        dfsExample.dfs(nodeall.get(0),nodeall,ans);
//        List<Integer> Ans= new ArrayList<>();
//        for (int i=3;i>=0;i--) {
//    		Node node=ans.get(i);
//    		Ans.add(node.data);
//    	}
//        System.out.print(Ans);
//    }
}