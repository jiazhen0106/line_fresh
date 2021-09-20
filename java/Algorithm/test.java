import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
public class test {

	public static void main(String[] args) {
		
		
//		List<Integer> Score = new ArrayList<Integer>();
//		Score.add(1);
//		Score.add(3);
//		Score.add(5);
//		Score.add(2);
//		Score.add(9);
//		int s[] = {1,3,5,2,9};
//		int ans=LIS(Score);
//		System.out.print("ans "+ans);
	}
	public static int LIS(List<Integer> score) {
		int length[]= new int [score.size()];
		for(int i =0;i<length.length;i++) {
			length[i]=1;
		}
		
		for (int i=0;i<score.size();i++) {
			for (int j=0;j<i;j++) {
				if (score.get(j)<score.get(i)) {
					length[i]=Math.max(length[i],length[j]+1);
				}
			}
		}
		int l = 0;
	    for (int i=0; i<score.size(); i++) {
	    	 l = Math.max(l, length[i]);
	    }
	    return l;
	}
	
}
