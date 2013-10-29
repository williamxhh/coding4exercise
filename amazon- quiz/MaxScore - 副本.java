import java.io.*;

public class MaxScore{
	//动态规划的思想，steps数组有多长，就证明有多少种可以的选择，去遍历每一种选择，找到其中分最大的方案
	//其实这里面还有优化的空间，因为如果steps中存在重复的值的话，实际上只用做一次就可以了
	public static int maxScore(int[] scores,int[] steps){
		int max = 0;
		int choises = steps.length;
		for(int i=0;i<choises;i++){
			//如果当前选择走steps[i]步的话，那么就重新调整scores数组，只要它steps[i]这一格后面的东西
			int[] subScores = new int[scores.length-steps[i]];
			for(int j=steps[i];j<scores.length;j++){
				subScores[j-steps[i]] = scores[j];
			}

			//这里搞这么麻烦，其实就是想把已经用掉的那个step从steps数组里面剔除掉，数据结构没选好，要是用arraylist可能更方便了
			int[] subSteps = new int[steps.length-1];
			for(int j=0;j<i;j++){
				subSteps[j] = steps[j];
			}
			for(int j=i+1;j<steps.length;j++){
				subSteps[j-1] = steps[j]; 
			}

			int temp = maxScore(subScores,subSteps);
			if(max<temp){
				max = temp;
			}
		}
		return scores[0]+max;
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
			int[] scores = new int[sizeOfScores];
			for(int i = 0;i<sizeOfScores;i++){
				scores[i] = Integer.parseInt(scoresString[i]);
			}

			line = reader.readLine();
			String[] stepsString = line.split(" ");
			int[] steps = new int[sizeOfSteps];
			for(int i=0;i<sizeOfSteps;i++){
				steps[i] = Integer.parseInt(stepsString[i]);
			}

			reader.close();
			System.out.println(maxScore(scores,steps));
		}catch(IOException ioe){

		}
		
	}
}