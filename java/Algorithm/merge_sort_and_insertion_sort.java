import java.util.Scanner;
public class merge_sort_and_insertion_sort {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int num[] = {0,0};//紀錄比較次數 交換次數的陣列
		String allStudentScore[]= //紀錄全部學生的各科目成績的陣列
			{
				"97501 DS 80 DM 76 LA 63",
				"97502 DS 53 DM 79 LA 98",
				"97523 DS 83 DM 49 LA 78"
			};
		//分別記錄各科的學生成績的陣列
		double DS[][] = new double[allStudentScore.length][2];
		double DM[][] = new double[allStudentScore.length][2];
		double LA[][] = new double[allStudentScore.length][2];
		
		for (int i=0;i<allStudentScore.length;i++) {
			String temp[]= allStudentScore[i].split(" ");
			DS[i][0]=Double.parseDouble(temp[0]);
			DS[i][1]=Double.parseDouble(temp[2]);
			DM[i][0]=Double.parseDouble(temp[0]);
			DM[i][1]=Double.parseDouble(temp[4]);
			LA[i][0]=Double.parseDouble(temp[0]);
			LA[i][1]=Double.parseDouble(temp[6]);			
		}
		//輸入想要執行的功能代號
		System.out.print("功能代號如下:\n" + 
						"1.查詢學生單科成績\n" + 
						"2.查詢學生所有成績\n" + 
						"3.新增學生成績\n"+ 
						"4.刪除學生成績\n" + 
						"5.排序學生成績\n" + 
						"6.離開系統\n" + 
						"請輸入功能代號:\n");
		int functionID= Integer.parseInt(sc.nextLine()) ;//functuonID 代表 功能代號
		//功能代碼== 5 使用功能排序學生成績
		if (functionID == 5) {
			System.out.println("請輸入科目名稱:");
			String selectSubject = sc.nextLine();//selectSubject 代表所輸入的科目名稱
			if (selectSubject.equals("DS")) { //如果輸入是 DS
				System.out.println("請選擇排序方法：1.Insertion Sort 2.Merge Sort:");
				int sortfunction = sc.nextInt();//sortfunction 代表排序方法
				if (sortfunction == 1) {
					double subjectScore[] = new double [DS.length];//subjectScore陣列用來儲存該科目的所有成績 
					for (int i=0;i<subjectScore.length;i++) {
						subjectScore[i] = DS[i][1];
					}
					double sortedScore[] =InsertionSort(subjectScore,num);//使用insertion sort排列
					double Answer[][]=Inquire(sortedScore,DS);//依照成績排列之後，找到相對應的學生學號
					//印出排列後結果
					for (int j =0;j<Answer.length;j++) {
						System.out.println(Answer[j][0]+" DS "+Answer[j][1]);
					}
					//num[0]是比較次數 num[1]是交換次數
					System.out.println("使用Insertion Sort排序，系統完成排序共比較"+num[0]+"次，交換元素"+num[1]+"次");
				}
					//只是排序方法不同
				else if(sortfunction == 2){
					double subjectScore[] = new double [DS.length];
					for (int i=0;i<subjectScore.length;i++) {
						subjectScore[i] = DS[i][1];
					}
					double sortedScore[] =MergeSort(subjectScore,num);
					double Answer[][]=Inquire(sortedScore,DS);
					for (int j =0;j<Answer.length;j++) {
						System.out.println(Answer[j][0]+" DS "+Answer[j][1]);
					}
					System.out.println("使用Merge Sort排序，系統完成排序共比較"+num[0]+"次，交換元素"+num[1]+"次");
				}
			}
			//不同科目所以拿的科目陣列不同
			else if (selectSubject.equals("DM")){
				System.out.println("請選擇排序方法：1.Insertion Sort 2.Merge Sort:");
				int sortfunction = sc.nextInt();
				if (sortfunction == 1) {
					double subjectScore[] = new double [DM.length];
					for (int i=0;i<subjectScore.length;i++) {
						subjectScore[i] = DM[i][1];
					}
					double sortedScore[] =InsertionSort(subjectScore,num);
					double Answer[][]=Inquire(sortedScore,DM);
					for (int j =0;j<Answer.length;j++) {
						System.out.println(Answer[j][0]+" DM "+Answer[j][1]);
					}
					System.out.println("使用Insertion Sort排序，系統完成排序共比較"+num[0]+"次，交換元素"+num[1]+"次");
				}
				else if(sortfunction == 2){
					double subjectScore[] = new double [DM.length];
					for (int i=0;i<subjectScore.length;i++) {
						subjectScore[i] = DM[i][1];
					}
					double sortedScore[] =MergeSort(subjectScore,num);
					double Answer[][]=Inquire(sortedScore,DM);
					for (int j =0;j<Answer.length;j++) {
						System.out.println(Answer[j][0]+" DM "+Answer[j][1]);
					}
					System.out.println("使用Merge Sort排序，系統完成排序共比較"+num[0]+"次，交換元素"+num[1]+"次");
				}
			}
			//不同科目所以拿的科目陣列不同
			else if (selectSubject.equals("LA")){
				System.out.println("請選擇排序方法：1.Insertion Sort 2.Merge Sort:");
				int sortfunction = sc.nextInt();
				if (sortfunction == 1) {
					double subjectScore[] = new double [LA.length];
					for (int i=0;i<subjectScore.length;i++) {
						subjectScore[i] = LA[i][1];
					}
					double sortedScore[] =InsertionSort(subjectScore,num);
					double Answer[][]=Inquire(sortedScore,LA);
					for (int j =0;j<Answer.length;j++) {
						System.out.println(Answer[j][0]+" LA "+Answer[j][1]);
					}
					System.out.println("使用Insertion Sort排序，系統完成排序共比較"+num[0]+"次，交換元素"+num[1]+"次");
				}
				else if(sortfunction == 2){
					double subjectScore[] = new double [LA.length];
					for (int i=0;i<subjectScore.length;i++) {
						subjectScore[i] = LA[i][1];
					}
					double sortedScore[] =MergeSort(subjectScore,num);
					double Answer[][]=Inquire(sortedScore,LA);
					for (int j =0;j<Answer.length;j++) {
						System.out.println(Answer[j][0]+" LA "+Answer[j][1]);
					}
					System.out.println("使用Merge Sort排序，系統完成排序共比較"+num[0]+"次，交換元素"+num[1]+"次");
				}
			}
			
		}
		else {
			System.out.print("此功能未開發");
		}		
	}
	// Insertion Sort
	static double[] InsertionSort(double[] score,int num[]) {//score 是未排序的成績陣列 num 用來記錄 交換次數跟比較次數的陣列
		for(int j=1;j<score.length;j++) {
			double key = score[j];
			int i =j-1;
			while(i>=0 && score[i]<key ){
				num[1]+=1;
				num[0]+=1;
				score[i+1]=score[i];
				i=i-1;
			}
			score[i+1]=key;
			num[0]+=1;
		}
		return score;
	}
	public static double[] MergeSort(double[] arr , int [] num) {
        if (arr.length <= 1) {
            return arr;
        }
        int mid = (arr.length / 2);
        double left[] = new double[mid];
        double right[] = new double[arr.length - mid];
        System.arraycopy(arr, 0, left, 0, mid);
        System.arraycopy(arr, mid, right, 0, arr.length - mid);
        return Merge(MergeSort(left,num), MergeSort(right,num),num);
    }
	public static double[] Merge(double[] left, double[] right,int [] num) {
		double[] result = new double[left.length + right.length];
        int k = 0;
        int j = 0;
        int i = 0;
        while (i < left.length && j < right.length) {
            if (left[i] >= right[j]) {
                result[k] = left[i];
                k++;
                i++;
                num[0]+=1;//比較次數
            } else {
                result[k] = right[j];
                k++;
                j++;
                num[0]+=1;
                num[1]+=1;//交換次數
            }
        }
        while (i < left.length) {
            result[k] = left[i];
            k++;
            i++;
        }
        while (j < right.length) {
            result[k] = right[j];
            k++;
            j++;
        }
        return result;
    }
	static double[][] Inquire(double[] scoreSorded,double[][] subject) {
		double Ans[][] =new double [subject.length][2];
		if(subject.length != 0) {
			int count=0;
			double subject_copy[][]= new double [subject.length][2];
			System.arraycopy(subject,0,subject_copy,0,subject.length);
			for (int j=0;j<scoreSorded.length;j++) {
				for (int i=0;i<scoreSorded.length;i++) {
					if (subject_copy[i][1]==scoreSorded[count]) {
						Ans[count][0]=subject_copy[i][0];
						Ans[count][1]=subject_copy[i][1];
						subject_copy[i][1]=-1;	
						count+=1;
						if (count == subject_copy.length) {
							count--;
						}
					}
				}
			};
		}
		return Ans;
	}
	
}
