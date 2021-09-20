import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class scenario_4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		HashMap<String, TreeMap> student = new HashMap<String, TreeMap>();
		List<List<Integer>> inputSubject = new ArrayList<>();
		String filePath = "C:\\Users\\至秦\\Desktop\\grade2.txt";
		readTxtFile(filePath, student);
		System.out.print("功能代號如下:\n" + "1.查詢學生單科成績\n" + "2.查詢學生所有成績\n" + "3.新增學生成績\n" + "4.刪除學生成績\n" + "5.排序學生成績\n"
				+ "6.離開系統\n" + "請輸入功能代號:\n");
		String functionID = sc.nextLine();
		while (functionID != "") {
			if (functionID.equals("1")) {
				System.out.print("請輸入學生學號及科目: ");
				String StudentSubject = sc.nextLine();
				String StudentSubjectsplit[] =StudentSubject.split(" ");
				String ans=searchGrade(StudentSubject,student);
				if (ans.equals("無")) {
					System.out.println(ans+StudentSubjectsplit[0]+"的"+StudentSubjectsplit[1]+"科目 成績");
				}
				else {
					System.out.println(ans);
				}
				
			} else if (functionID.equals("2")) {
				System.out.println("請輸入學生學號: ");
				String studentID = sc.nextLine();
				String ans =searchAllGrade(studentID,student);
				if(ans.equals("無此資料")) {
					System.out.println("無 "+studentID+"此資料");
				}
				else if (ans.equals("此學生並沒修課")) {
					System.out.println(studentID+"此學生並沒修課");
				}
				else {
				System.out.println("學生 "+studentID+" 的分數是 "+ ans);
				}
			} else if (functionID.equals("3")) {
				System.out.println("請輸入學生學號及科目成績: ");
				String StudentSubject = sc.nextLine();
				String ans = insertGrade(StudentSubject,student);
				if(ans.equals("無此資料")) {
					System.out.println("新增失敗");
				}
				else {
					System.out.println(StudentSubject+"新增成功");
				}
				
				
				
			} else if (functionID.equals("4")) {
				System.out.println("請輸入學生學號: ");
				String studentID = sc.nextLine();
				deleteGrade(studentID,student);
				String ans =searchAllGrade(studentID,student);
				if(ans.equals("無此資料")) {
					System.out.println("刪除成功");
				}
				else {
					System.out.println("刪除失敗");
				}
			} else if (functionID.equals("5")) {
				System.out.println("請輸入科目名稱:");
				String SortSubject = sc.nextLine();
				for (String id : student.keySet()) {
					for (Object subject : (student.get(id)).keySet()) {
						if (subject.equals(SortSubject)) {
							List<Integer> StudentScore = new ArrayList<Integer>();
							StudentScore.add(Integer.parseInt(id));
							StudentScore.add((Integer) ((student.get(id)).get(subject)));
							inputSubject.add(StudentScore);
						}
					}
				}
				// 儲存該科成績
				int subject[][] = new int[inputSubject.size()][inputSubject.get(0).size()];
				int needSort[] = new int[subject.length];
				// 印出
				for (int i = 0; i < inputSubject.size(); i++) {
					for (int j = 0; j < inputSubject.get(0).size(); j++) {
						subject[i][j] = inputSubject.get(i).get(j);
					}
					needSort[i] = subject[i][1];
				}
				System.out.println("請選擇排序方法：1.Insertion Sort 2.Merge Sort 3.Radix Sort");
				String SortFunction = sc.nextLine();
				int scoreSorded[] = new int[needSort.length];
				if (SortFunction.equals("1")) {
					scoreSorded = InsertionSort(needSort);
					System.out.println("使用Insertion Sort 排序");
				} else if (SortFunction.equals("2")) {
					scoreSorded = MergeSort(needSort);
					System.out.println("使用Merge Sort 排序");

				} else if (SortFunction.equals("3")) {
					List<Integer> needSortList = Arrays.stream(needSort).boxed().collect(Collectors.toList());
					List<Integer> scoreSordedList = RadixSort(needSortList);
					scoreSorded = scoreSordedList.stream().mapToInt(Integer::valueOf).toArray();
					System.out.println("使用Radix Sort 排序");
				}
				int[][] Ans = Inquire(scoreSorded, subject);
				for (int i = 0; i < Ans.length; i++) {
					System.out.println(Ans[i][0] + " " + Ans[i][1]);
				}
			} else if (functionID.equals("6")) {
				System.out.println("=======結束=======");
				break;
			}
			System.out.print("功能代號如下:\n" + "1.查詢學生單科成績\n" + "2.查詢學生所有成績\n" + "3.新增學生成績\n" + "4.刪除學生成績\n" + "5.排序學生成績\n"
					+ "6.離開系統\n" + "請輸入功能代號:\n");
			functionID = sc.nextLine();
		}

	}
	public static String searchGrade(String StudentSubject,HashMap<String, TreeMap>student) {
		String Ans="";
		int IdExist=0;
		int SubjectExist=0;
		String StudentSubjectsplit[]=StudentSubject.split(" ");
		String studentID=StudentSubjectsplit[0];
		String selectSubject=StudentSubjectsplit[1];
		for (String id : student.keySet()) {
			if (id.equals(studentID)) {
				IdExist=1;
				for (Object subject : (student.get(id)).keySet()) {
					if (subject.equals(selectSubject)) {
						SubjectExist=1;
						Ans= id+" 學生的 "+subject+" 分數是 "+(student.get(id)).get(subject);
					}
				}
			}		
		}
		if(Ans!="") {
			return Ans;
		}
		else {
			Ans ="無";
			return Ans;
		}
	}
	public static void deleteGrade(String studentID,HashMap<String, TreeMap>student) {
		student.remove(studentID);
	}
	public static String searchAllGrade(String studentID,HashMap<String, TreeMap>student) {
		String Ans="";
		int exist=0;
		for (String id : student.keySet()) {
			if (id.equals(studentID)) {
				exist=1;
				for (Object subject : (student.get(id)).keySet()) {
					Ans+=subject+" "+(student.get(id)).get(subject)+" ";
//					System.out.println(Ans);
				}
			}	
		}
		if(Ans =="" && exist==0) {
			Ans="無此資料";
			return Ans;
		}
		else if(Ans =="" && exist==1) {
			Ans="此學生並沒修課";
			return Ans;
		}
		else {
			return Ans;
		}
	}
	public static String insertGrade(String src,HashMap<String, TreeMap>student){
		String StudentScores[] = src.split(" ");
		TreeMap<String, Integer> scores = new TreeMap<>();
		for (int i = 0; i < StudentScores.length; i++) {
			if (i % 2 == 1) {
				scores.put(StudentScores[i], Integer.parseInt(StudentScores[i + 1]));
			}
		}
		student.put(StudentScores[0], scores);
		String Ans=searchAllGrade(StudentScores[0],student);
		return Ans;
	}
	public static void readTxtFile(String filePath, HashMap<String, TreeMap> student) {
		try {
			String encoding = "GBK";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判斷檔案是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考慮到編碼格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					String allStudentScores[] = lineTxt.split(" ");
					TreeMap<String, Integer> scores = new TreeMap<>();
					for (int i = 0; i < allStudentScores.length; i++) {
						if (i % 2 == 1) {
							scores.put(allStudentScores[i], Integer.parseInt(allStudentScores[i + 1]));
						}
					}
					student.put(allStudentScores[0], scores);
					System.out.println(lineTxt); // 一行一行的讀
				}
			} else {
				System.out.println("找不到指定的檔案");
			}
		} catch (Exception e) {
			System.out.println("讀取檔案內容出錯");
			e.printStackTrace();
		}
	}

	// Insertion Sort
	static int[] InsertionSort(int[] score) {// score 是未排序的成績陣列 num 用來記錄 交換次數跟比較次數的陣列
		for (int j = 1; j < score.length; j++) {
			int key = score[j];
			int i = j - 1;
			while (i >= 0 && score[i] < key) {
				score[i + 1] = score[i];
				i = i - 1;
			}
			score[i + 1] = key;
		}
		return score;
	}

	public static int[] MergeSort(int[] arr) {
		if (arr.length <= 1) {
			return arr;
		}
		int mid = (arr.length / 2);
		int left[] = new int[mid];
		int right[] = new int[arr.length - mid];
		System.arraycopy(arr, 0, left, 0, mid);
		System.arraycopy(arr, mid, right, 0, arr.length - mid);
		return Merge(MergeSort(left), MergeSort(right));
	}

	public static int[] Merge(int[] left, int[] right) {
		int[] result = new int[left.length + right.length];
		int k = 0;
		int j = 0;
		int i = 0;
		while (i < left.length && j < right.length) {
			if (left[i] >= right[j]) {
				result[k] = left[i];
				k++;
				i++;
			} else {
				result[k] = right[j];
				k++;
				j++;
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

	static int[][] Inquire(int[] scoreSorded, int[][] subject) {
		int Ans[][] = new int[subject.length][2];
		if (subject.length != 0) {
			int count = 0;
			int subject_copy[][] = new int[subject.length][2];
			System.arraycopy(subject, 0, subject_copy, 0, subject.length);
			for (int j = 0; j < scoreSorded.length; j++) {
				for (int i = 0; i < scoreSorded.length; i++) {
					if (subject_copy[i][1] == scoreSorded[count]) {
						Ans[count][0] = subject_copy[i][0];
						Ans[count][1] = subject_copy[i][1];
						subject_copy[i][1] = -1;
						count += 1;
						if (count == subject_copy.length) {
							count--;
						}
					}
				}
			}
			;
		}
		return Ans;
	}

	public static List<Integer> CountingSort(List<Integer> A, int radix) {
		List<Integer> resultlist = new ArrayList<Integer>();
		int result[] = new int[A.size()];
		int Aremainder[] = new int[A.size()];
		int C[] = new int[10];
		for (int i = 0; i < A.size(); i++) {
			Aremainder[i] = (A.get(i) / radix) % 10;
		}
		for (int j = 0; j < A.size(); j++) {
			C[Aremainder[j]] = C[Aremainder[j]] + 1;
		}
		for (int k = C.length - 2; k >= 0; k--) {
			C[k] = C[k] + C[k + 1];
		}
		for (int l = A.size() - 1; l >= 0; l--) {
			result[C[Aremainder[l]] - 1] = A.get(l);
			C[Aremainder[l]] = C[Aremainder[l]] - 1;
		}
		for (int i = 0; i < result.length; i++) {
			resultlist.add(result[i]);
		}
		return resultlist;
	}

	public static List<Integer> RadixSort(List<Integer> arr) {
		int Max = 100;
		int radix = 1;
		while (radix <= Max) {
			arr = CountingSort(arr, radix);
			radix *= 10;
		}
		return arr;
	}
}
