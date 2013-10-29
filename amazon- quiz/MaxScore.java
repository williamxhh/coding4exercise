import java.io.*;
import java.util.*;

public class MaxScore{
	//��̬�滮��˼�룬steps�����ж೤����֤���ж����ֿ��Ե�ѡ��ȥ����ÿһ��ѡ���ҵ����з����ķ���
	//��ʵ�����滹���Ż��Ŀռ䣬��Ϊ���steps�д����ظ���ֵ�Ļ���ʵ����ֻ����һ�ξͿ�����
	public static int maxScore(List<Integer> scores,List<Integer> steps){
		int max = 0;
		HashSet<Integer> uniqueSteps = new HashSet<Integer>(steps);
		int choises = uniqueSteps.size();
		for(Iterator<Integer> iterator = uniqueSteps.iterator();iterator.hasNext();){
			List<Integer> subScores = scores.subList(iterator.next(),scores.size());

			steps.remove(iterator.next());

			int temp = maxScore(subScores,steps);
			if(max<temp){
				max = temp;
			}
		}
		return scores.get(0)+max;
	}

	public static void main(String[] args){
		String line = "";
		try{
			BufferedReader reader = new BufferedReader(new FileReader("test.txt"));
			line = reader.readLine();
			int sizeOfScores = Integer.parseInt(line.split(" ")[0]);
			int sizeOfSteps = Integer.parseInt(line.split(" ")[1]);

			line = reader.readLine();
			String[] scoresString = line.split(" ");
			ArrayList<Integer> scores = new ArrayList<Integer>();
			for(int i = 0;i<sizeOfScores;i++){
				scores.add(Integer.parseInt(scoresString[i]));
			}

			line = reader.readLine();
			String[] stepsString = line.split(" ");
			ArrayList<Integer> steps = new ArrayList<Integer>();
			for(int i=0;i<sizeOfSteps;i++){
				steps.add(Integer.parseInt(stepsString[i]));
			}

			reader.close();
			System.out.println(maxScore(scores,steps));
		}catch(IOException ioe){

		}
		
	}
}