import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class hashmap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		HashMap<String, TreeMap> student = new HashMap<String, TreeMap>();
		List<List<Integer>> inputSubject = new ArrayList<>();
		String filePath = "C:\\Users\\�ܯ�\\Desktop\\grade1.txt";
		readTxtFile(filePath, student);
		System.out.print("�\��N���p�U:\n" + "1.�d�߾ǥͳ�즨�Z\n" + "2.�d�߾ǥͩҦ����Z\n" + "3.�s�W�ǥͦ��Z\n" + "4.�R���ǥͦ��Z\n" + "5.�ƧǾǥͦ��Z\n"
				+ "6.���}�t��\n" + "�п�J�\��N��:\n");
		String functionID = sc.nextLine();
		while (functionID != "") {
			if (functionID.equals("1")) {
				System.out.print("�п�J�ǥ;Ǹ��ά��: ");
				String StudentSubject = sc.nextLine();
				String StudentSubjectsplit[]=StudentSubject.split(" ");
				String studentID=StudentSubjectsplit[0];
				String selectSubject=StudentSubjectsplit[1];
				for (String id : student.keySet()) {
					if (id.equals(studentID)) {
						for (Object subject : (student.get(id)).keySet()) {
							if (subject.equals(selectSubject)) {
								System.out.println(id+" �ǥͪ� "+subject+" ���ƬO "+(student.get(id)).get(subject));
							}
						}
					}		
				}
				
			} else if (functionID.equals("2")) {
				System.out.print(functionID);
			} else if (functionID.equals("3")) {
				System.out.print(functionID);
			} else if (functionID.equals("4")) {
				System.out.print(functionID);
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
				break;
			}
			System.out.print("�\��N���p�U:\n" + "1.�d�߾ǥͳ�즨�Z\n" + "2.�d�߾ǥͩҦ����Z\n" + "3.�s�W�ǥͦ��Z\n" + "4.�R���ǥͦ��Z\n" + "5.�ƧǾǥͦ��Z\n"
					+ "6.���}�t��\n" + "�п�J�\��N��:\n");
			functionID = sc.nextLine();
		}

	}

	public static void readTxtFile(String filePath, HashMap<String, TreeMap> student) {
		try {
			String encoding = "GBK";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // �P�_�ɮ׬O�_�s�b
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// �Ҽ{��s�X�榡
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
//					System.out.println(lineTxt); // �@��@�檺Ū
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
