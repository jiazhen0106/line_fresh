import java.util.Scanner;
public class merge_sort_and_insertion_sort {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int num[] = {0,0};//����������� �洫���ƪ��}�C
		String allStudentScore[]= //���������ǥͪ��U��ئ��Z���}�C
			{
				"97501 DS 80 DM 76 LA 63",
				"97502 DS 53 DM 79 LA 98",
				"97523 DS 83 DM 49 LA 78"
			};
		//���O�O���U�쪺�ǥͦ��Z���}�C
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
		//��J�Q�n���檺�\��N��
		System.out.print("�\��N���p�U:\n" + 
						"1.�d�߾ǥͳ�즨�Z\n" + 
						"2.�d�߾ǥͩҦ����Z\n" + 
						"3.�s�W�ǥͦ��Z\n"+ 
						"4.�R���ǥͦ��Z\n" + 
						"5.�ƧǾǥͦ��Z\n" + 
						"6.���}�t��\n" + 
						"�п�J�\��N��:\n");
		int functionID= Integer.parseInt(sc.nextLine()) ;//functuonID �N�� �\��N��
		//�\��N�X== 5 �ϥΥ\��ƧǾǥͦ��Z
		if (functionID == 5) {
			System.out.println("�п�J��ئW��:");
			String selectSubject = sc.nextLine();//selectSubject �N��ҿ�J����ئW��
			if (selectSubject.equals("DS")) { //�p�G��J�O DS
				System.out.println("�п�ܱƧǤ�k�G1.Insertion Sort 2.Merge Sort:");
				int sortfunction = sc.nextInt();//sortfunction �N��ƧǤ�k
				if (sortfunction == 1) {
					double subjectScore[] = new double [DS.length];//subjectScore�}�C�Ψ��x�s�Ӭ�ت��Ҧ����Z 
					for (int i=0;i<subjectScore.length;i++) {
						subjectScore[i] = DS[i][1];
					}
					double sortedScore[] =InsertionSort(subjectScore,num);//�ϥ�insertion sort�ƦC
					double Answer[][]=Inquire(sortedScore,DS);//�̷Ӧ��Z�ƦC����A���۹������ǥ;Ǹ�
					//�L�X�ƦC�ᵲ�G
					for (int j =0;j<Answer.length;j++) {
						System.out.println(Answer[j][0]+" DS "+Answer[j][1]);
					}
					//num[0]�O������� num[1]�O�洫����
					System.out.println("�ϥ�Insertion Sort�ƧǡA�t�Χ����ƧǦ@���"+num[0]+"���A�洫����"+num[1]+"��");
				}
					//�u�O�ƧǤ�k���P
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
					System.out.println("�ϥ�Merge Sort�ƧǡA�t�Χ����ƧǦ@���"+num[0]+"���A�洫����"+num[1]+"��");
				}
			}
			//���P��ةҥH������ذ}�C���P
			else if (selectSubject.equals("DM")){
				System.out.println("�п�ܱƧǤ�k�G1.Insertion Sort 2.Merge Sort:");
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
					System.out.println("�ϥ�Insertion Sort�ƧǡA�t�Χ����ƧǦ@���"+num[0]+"���A�洫����"+num[1]+"��");
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
					System.out.println("�ϥ�Merge Sort�ƧǡA�t�Χ����ƧǦ@���"+num[0]+"���A�洫����"+num[1]+"��");
				}
			}
			//���P��ةҥH������ذ}�C���P
			else if (selectSubject.equals("LA")){
				System.out.println("�п�ܱƧǤ�k�G1.Insertion Sort 2.Merge Sort:");
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
					System.out.println("�ϥ�Insertion Sort�ƧǡA�t�Χ����ƧǦ@���"+num[0]+"���A�洫����"+num[1]+"��");
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
					System.out.println("�ϥ�Merge Sort�ƧǡA�t�Χ����ƧǦ@���"+num[0]+"���A�洫����"+num[1]+"��");
				}
			}
			
		}
		else {
			System.out.print("���\�ॼ�}�o");
		}		
	}
	// Insertion Sort
	static double[] InsertionSort(double[] score,int num[]) {//score �O���ƧǪ����Z�}�C num �ΨӰO�� �洫���Ƹ������ƪ��}�C
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
                num[0]+=1;//�������
            } else {
                result[k] = right[j];
                k++;
                j++;
                num[0]+=1;
                num[1]+=1;//�洫����
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
