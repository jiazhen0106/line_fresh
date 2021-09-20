
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.ArrayList;
public class DFS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a,b;
		int presentnode=-1;
		int lesson[][] = {
							{1,0},
							{2,0},
							{3,1},
							{3,2}
						};
		int list[][]=new int [lesson.length][lesson.length];
		int size[]=new int [lesson.length];
		int prenode[]=new int[lesson.length];
		ArrayList<Integer> ans = new ArrayList<Integer>();
		for (int i =0;i<lesson.length;i++) {
			a=lesson[i][1];
			b=lesson[i][0];
			size[a]++;
			prenode[b]++;
			list[a][size[a]] = b;
		}
//		for (int i=0;i<list.length;i++) {
//			for (int j=0;j<list[i].length;j++) {
//				System.out.print(list[i][j]);
//			}
//			System.out.println("");
//		}
//		System.out.println("size");
//		for (int j=0;j<size.length;j++) {
//			System.out.print(size[j]);
//		}
		for (int i=0; i<prenode.length; i++) {
			if (prenode[i] == 0) {
				presentnode = i;
				break;
			}
		}
		Deque<Integer> S = new ArrayDeque<Integer>();
//		S.add(-1);
		dfs(presentnode,ans,lesson,S,size,0);
		for (int i=0;i<ans.size();i++) {
			System.out.println(ans.get(i));
		}
	}
	static void dfs(int n, ArrayList<Integer> ans ,int list[][],Deque<Integer> S,int size[],int time){
		time+=1;
		for(int j=0;j<list[n].length;j++) {
			if (S.isEmpty()&&time!=1) {
				return;
			}
			else if (list[n][j]>0) {
				S.add(j);
				size[n]--;
				n=list[n][j];
				list[n][j]=-1;
				dfs(n,ans,list,S,size,time);
			}
			else if(list[n][j]<0 && size[n]<0) {
				int remove = S.element();
				ans.add(remove);
				S.remove();
				n = S.element();
				dfs(n,ans,list,S,size,time);
			}
		}
	}
}
