import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Algorithm_homework_3 {
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		LinkedHashMap<String, TreeMap> student = new LinkedHashMap<String, TreeMap>();
		List<List<Integer>> inputSubject = new ArrayList<>();
		String filePath = "C:\\Users\\�ܯ�\\Desktop\\grade2.txt";//�ɮ׸��|
		readTxtFile(filePath, student);
		System.out.print("�\��N���p�U:\n" + "1.�d�߾ǥͳ�즨�Z\n" + "2.�d�߾ǥͩҦ����Z\n" + "3.�s�W�ǥͦ��Z\n" + "4.�R���ǥͦ��Z\n" + "5.�ƧǾǥͦ��Z\n"
				 +"6.�̪����W���Z�l�ǦC\n"+"7.�ƽ�\n"+ "8.���}�t��\n"+ "�п�J�\��N��:\n");
		String functionID = sc.nextLine();
		while (functionID != "") {
			if (functionID.equals("1")) {
				System.out.print("�п�J�ǥ;Ǹ��ά��: ");
				String StudentSubject = sc.nextLine();
				String StudentSubjectsplit[] =StudentSubject.split(" ");
				String ans=searchGrade(StudentSubject,student);
				if (ans.equals("�L")) {
					System.out.println(ans+StudentSubjectsplit[0]+"��"+StudentSubjectsplit[1]+"��� ���Z");
				}
				else {
					System.out.println(ans);
				}
				
			} else if (functionID.equals("2")) {
				System.out.println("�п�J�ǥ;Ǹ�: ");
				String studentID = sc.nextLine();
				String ans =searchAllGrade(studentID,student);
				if(ans.equals("�L�����")) {
					System.out.println("�L "+studentID+"�����");
				}
				else if (ans.equals("���ǥͨèS�׽�")) {
					System.out.println(studentID+"���ǥͨèS�׽�");
				}
				else {
				System.out.println("�ǥ� "+studentID+" �����ƬO "+ ans);
				}
			} else if (functionID.equals("3")) {
				System.out.println("�п�J�ǥ;Ǹ��ά�ئ��Z: ");
				String StudentSubject = sc.nextLine();
				String ans = insertGrade(StudentSubject,student);
				if(ans.equals("�L�����")) {
					System.out.println("�s�W����");
				}
				else {
					System.out.println(StudentSubject+"�s�W���\");
				}
				
				
				
			} else if (functionID.equals("4")) {
				System.out.println("�п�J�ǥ;Ǹ�: ");
				String studentID = sc.nextLine();
				deleteGrade(studentID,student);
				String ans =searchAllGrade(studentID,student);
				if(ans.equals("�L�����")) {
					System.out.println("�R�����\");
				}
				else {
					System.out.println("�R������");
				}
			} else if (functionID.equals("5")) {
				System.out.println("�п�J��ئW��:");
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
				// �x�s�Ӭ즨�Z
				int subject[][] = new int[inputSubject.size()][inputSubject.get(0).size()];
				int needSort[] = new int[subject.length];
				// �L�X
				for (int i = 0; i < inputSubject.size(); i++) {
					for (int j = 0; j < inputSubject.get(0).size(); j++) {
						subject[i][j] = inputSubject.get(i).get(j);
					}
					needSort[i] = subject[i][1];
				}
				System.out.println("�п�ܱƧǤ�k�G1.Insertion Sort 2.Merge Sort 3.Radix Sort");
				String SortFunction = sc.nextLine();
				int scoreSorded[] = new int[needSort.length];
				if (SortFunction.equals("1")) {
					scoreSorded = InsertionSort(needSort);
					System.out.println("�ϥ�Insertion Sort �Ƨ�");
				} else if (SortFunction.equals("2")) {
					scoreSorded = MergeSort(needSort);
					System.out.println("�ϥ�Merge Sort �Ƨ�");

				} else if (SortFunction.equals("3")) {
					List<Integer> needSortList = Arrays.stream(needSort).boxed().collect(Collectors.toList());
					List<Integer> scoreSordedList = RadixSort(needSortList);
					scoreSorded = scoreSordedList.stream().mapToInt(Integer::valueOf).toArray();
					System.out.println("�ϥ�Radix Sort �Ƨ�");
				}
				int[][] Ans = Inquire(scoreSorded, subject);
				for (int i = 0; i < Ans.length; i++) {
					System.out.println(Ans[i][0] + " " + Ans[i][1]);
				}
			} else if (functionID.equals("6")) {
				System.out.println("�п�J��ئW��:");
				String LISSubject = sc.nextLine();
				List<Integer> Score = new ArrayList<Integer>();
				for (String id : student.keySet()) {
					for (Object subject : (student.get(id)).keySet()) {
						if (subject.equals(LISSubject)) {
							Score.add((Integer) ((student.get(id)).get(subject)));
						}
					}
				}
				int LIS=LIS(Score);
				System.out.println( LISSubject+"���Z�ǦC�� "+ Score + "�A�̪����W�l�ǦC���׬�"+LIS);
			}
			else if (functionID.equals("7")) {
				System.out.println("�п�J�ҵ{�`�ƩM���M����C��:");
		    	List<Node> nodeall= new ArrayList<>();
		    	List<Node> ans= new ArrayList<>();
		    	String input =sc.nextLine();
		    	input=input.replace("[","");
		    	input=input.replace("]","");
		    	input=input.replace(" ","");
		    	String inputsp[] =input.split(",");
		    	int listLength=Integer.parseInt(inputsp[0]);
		    	int test[][] =new int [listLength][2];
		    	int testNum=1;
		    	for(int i=0;i<test.length;i++) {
		    		for (int j=0;j<test[i].length;j++) {
		    			test[i][j] = Integer.parseInt(inputsp[testNum]);
		    			testNum++;
		    		}
		    	}
		    	for(int i=0;i<4;i++) {
		    		Node node =new Node(i);
		    		nodeall.add(node);
		    	}
		    	for(int i=0;i<test.length;i++) {
		    		nodeall.get(test[i][1]).addneighbours(nodeall.get(test[i][0]));
		    	}
		    	Algorithm_homework_3 DFS = new Algorithm_homework_3();
		    	DFS.dfs(nodeall.get(0),nodeall,ans);
		        List<Integer> Ans= new ArrayList<>();
		        for (int i=3;i>=0;i--) {
		    		Node node=ans.get(i);
		    		Ans.add(node.data);
		    	}
		        System.out.println(Ans);

			}
			else if (functionID.equals("8")) {
				System.out.println("=======����=======");
				break;
			}
			System.out.print("�\��N���p�U:\n" + "1.�d�߾ǥͳ�즨�Z\n" + "2.�d�߾ǥͩҦ����Z\n" + "3.�s�W�ǥͦ��Z\n" + "4.�R���ǥͦ��Z\n" + "5.�ƧǾǥͦ��Z\n"
					 +"6.�̪����W���Z�l�ǦC\n"+"7.�ƽ�\n"+ "8.���}�t��\n"+ "�п�J�\��N��:\n");
			functionID = sc.nextLine();
		}

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
						Ans= id+" �ǥͪ� "+subject+" ���ƬO "+(student.get(id)).get(subject);
					}
				}
			}		
		}
		if(Ans!="") {
			return Ans;
		}
		else {
			Ans ="�L";
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
			Ans="�L�����";
			return Ans;
		}
		else if(Ans =="" && exist==1) {
			Ans="���ǥͨèS�׽�";
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
	public static void readTxtFile(String filePath, LinkedHashMap<String, TreeMap> student) {
		try {
			String encoding = "GBK";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // �P�_�ɮ׬O�_�s�b
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// �Ҽ{��s�X�榡
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					String allStudentScores[] = lineTxt.split(" ");
					TreeMap<String , Integer> scores = new TreeMap<>();
					for (int i = 0; i < allStudentScores.length; i++) {
						if (i % 2 == 1) {
							scores.put(allStudentScores[i], Integer.parseInt(allStudentScores[i + 1]));
						}
					}
					student.put(allStudentScores[0], scores);
					System.out.println(lineTxt); // �@��@�檺Ū
				}
			} else {
				System.out.println("�䤣����w���ɮ�");
			}
		} catch (Exception e) {
			System.out.println("Ū���ɮפ��e�X��");
			e.printStackTrace();
		}
	}

	// Insertion Sort
	static int[] InsertionSort(int[] score) {// score �O���ƧǪ����Z�}�C num �ΨӰO�� �洫���Ƹ������ƪ��}�C
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
