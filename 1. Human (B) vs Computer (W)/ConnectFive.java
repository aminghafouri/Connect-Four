import java.util.Scanner;


public class ConnectFive
{

	private static int maxColumnW;
	private static int maxColumnB;
	static int count = 0;
	public static int check=0;
	static int run=0;
	static int cutoff=0;
	static int[][] killerW = new int[100][2];
	static int[][] killerB = new int[100][2];
	static int[] win = new int[8];

	/////////////////////////////////////////////////////// Initial board

	public static String[][] createPattern()
	{
		String[][] f = new String[10][8];

		for (int i =0;i<f.length;i++)
		{
			for (int j =0;j<f[i].length;j++)
			{
				f[i][j] = " ";
			}
		}

		for (int i = 0; i < 100; i++)
		{
			killerW[i][0] = 3;
			killerW[i][1] = 4;
		}

		for (int i = 0; i < 100; i++)
		{
			killerB[i][0] = 3;
			killerB[i][1] = 4;
		}


		return f;
	}

	/////////////////////////////////////////////////////// Print board

	public static void printPattern(String[][] f)
	{
		System.out.println();
		for (int i =0;i<f.length;i++)
		{
			for (int j=0;j<f[i].length;j++)
			{
				if (j==0) System.out.print("|");
				System.out.print(f[i][j] + "|");
			}
			System.out.println();
		}
		System.out.println("-----------------");
	}

	/////////////////////////////////////////////////////// Insert and Remove 

	public static boolean columnFull(String[][]f, int i) {
		return f[0][i] != " ";
	}
	public static void insertDisk(String[][] f, int i)
	{
		for (int j =9;j>=0;j--)
		{
			if (f[j][i] == " " && count%2 == 0)
			{
				f[j][i] = "B";
				break;
			}
			if (f[j][i] == " " && count%2 == 1)
			{
				f[j][i] = "W";
				break;
			}
		}
	}

	//Remove
	public static void removeDisk(String[][] f, int i)
	{
		for (int j =0;j<=9;j++)
		{
			if (f[j][i] != " ")
			{
				f[j][i] = " ";
				break;
			}
		}
	}


	public static boolean isOdd(int i)
	{
		return (i%2!=0);
	}


	/////////////////////////////////////////////////////// Black plays

	public static void dropBlackPattern(String[][] f)
	{

		System.out.println("Drop a black disk at column (0 7): ");
		run++;
		//maxValueB(f,0,-1000,1000);
		//int c = maxColumnB;
		Scanner scan = new Scanner (System.in);
		int c = scan.nextInt();
		if (-1<c && c<8)
		{
			insertDisk(f, c);

		}
		else 
		{
			dropBlackPattern(f);
		} 

	}

	/////////////////////////////////////////////////////// White plays

	public static void dropWhitePattern(String[][] f)
	{
		System.out.println("Drop a white disk at column (0 7): ");
		run++;
		maxValueW(f, 0, -1000, 1000);
		int c = maxColumnW;
		//Scanner scan = new Scanner (System.in);
		//int c = scan.nextInt();
		if (-1 <c && c<8)
		{
			insertDisk(f, c);
		}
		else 
		{
			dropWhitePattern(f);
		}  

	}

	/////////////////////////////////////////////////////// Black Evaluation

	public static int EvalB(String[][] f) {

		int k=0;
		
		// vertical four & three
		for (int i=1;i<7;i++)
		{
			for (int j=0;j<8;j++)
			{
				if((f[i][j] != " ")
						&& ((f[i][j] == f[i+1][j])
								&& (f[i+1][j] == f[i+2][j]))) {
					if (f[(i-1)][j] == " " && f[i+3][j]!=f[i][j]) {
						if (f[i][j]=="B") k++;
						else if (f[i][j]=="W") k--;
					}
					else if (f[(i-1)][j] == " ")	{
						if (f[i][j]=="B") k+=10;
						else if (f[i][j]=="W") k-=10;
					}
				}
			}
		}

		// horizontal three and four
		int n=0;
		int b=0;
		int w=0;

		for (int i =0;i<10;i++)
		{
			for (int j=0;j<8;j++)
			{
				if ((i==9) || (f[i+1][j % 8]!=" " && f[i+1][(j+1) % 8]!=" " && f[i+1][(j+2) % 8]!=" " && f[i+1][(j+3) % 8]!=" " && f[i+1][(j+4) % 8]!=" ")) {
					if (f[i][(j) % 8] == " ") n++;
					else if (f[i][(j) % 8] == "B") b++;
					else if (f[i][(j) % 8] == "W") w++;

					if (f[i][(j+1) % 8] == " ") n++;
					else if (f[i][(j+1) % 8] == "B") b++;
					else if (f[i][(j+1) % 8] == "W") w++;

					if (f[i][(j+2) % 8] == " ") n++;
					else if (f[i][(j+2) % 8] == "B") b++;
					else if (f[i][(j+2) % 8] == "W") w++;

					if (f[i][(j+3) % 8] == " ") n++;
					else if (f[i][(j+3) % 8] == "B") b++;
					else if (f[i][(j+3) % 8] == "W") w++;

					if (f[i][(j+4) % 8] == " ") n++;
					else if (f[i][(j+4) % 8] == "B") b++;
					else if (f[i][(j+4) % 8] == "W") w++;

					if ((b!=0 && w!=0) || n>2)	{ }
					else if (n==1) {
						if (w!=0)	k-=10;
						else	k+=10;
					}
					else if (n==2) {
						if (f[i][(j+4) % 8]==" " && f[i][j % 8]==" ") {
							if (w!=0)	k-=6;
							else	k+=6;
						}
						else if (w!=0) k--;
						else k++;
					}
				}
				n=0;
				b=0;
				w=0;
			}
		}


		n=0;
		b=0;
		w=0;

		// diagonal decline four
		for (int i =0;i<6;i++)
		{
			for (int j=0;j<4;j++)
			{
				if ((i==5) || (f[i+1][j]!=" " && f[i+2][j+1]!=" " && f[i+3][j+2]!=" " && f[i+4][j+3]!=" " && f[i+5][j+4]!=" ")) {
					if (f[i][j] == " ") n++;
					else if (f[i][j] == "B") b++;
					else if (f[i][j] == "W") w++;

					if (f[i+1][j+1] == " ") n++;
					else if (f[i+1][j+1] == "B") b++;
					else if (f[i+1][j+1] == "W") w++;

					if (f[i+2][j+2] == " ") n++;
					else if (f[i+2][j+2] == "B") b++;
					else if (f[i+2][j+2] == "W") w++;

					if (f[i+3][j+3] == " ") n++;
					else if (f[i+3][j+3] == "B") b++;
					else if (f[i+3][j+3] == "W") w++;

					if (f[i+4][j+4] == " ") n++;
					else if (f[i+4][j+4] == "B") b++;
					else if (f[i+4][j+4] == "W") w++;

					if ((b!=0 && w!=0) || n>2)	{ }
					else if (n==1) {
						if (w!=0)	{k-=10;}
						else	k+=10;
					}
					else if (n==2) {
						if (w!=0) k--;
						else k++;
					}
				}
			}
			n=0;
			b=0;
			w=0;
		}

		// diagonal incline four
		for (int i=9;i>3;i--)
		{
			for (int j=0;j<4;j++)
			{
				if ((i==9) || (f[i+1][j]!=" " && f[i][j+1]!=" " && f[i-1][j+2]!=" " && f[i-2][j+3]!=" " && f[i-3][j+4]!=" ")) {
					if (f[i][j] == " ") n++;
					else if (f[i][j] == "B") b++;
					else if (f[i][j] == "W") w++;

					if (f[i-1][j+1] == " ") n++;
					else if (f[i-1][j+1] == "B") b++;
					else if (f[i-1][j+1] == "W") w++;

					if (f[i-2][j+2] == " ") n++;
					else if (f[i-2][j+2] == "B") b++;
					else if (f[i-2][j+2] == "W") w++;

					if (f[i-3][j+3] == " ") n++;
					else if (f[i-3][j+3] == "B") b++;
					else if (f[i-3][j+3] == "W") w++;

					if (f[i-4][j+4] == " ") n++;
					else if (f[i-4][j+4] == "B") b++;
					else if (f[i-4][j+4] == "W") w++;

					if ((b!=0 && w!=0) || n>2)	{ }
					else if (n==1) {
						if (w!=0)	k-=10;
						else	k+=10;
					}
					else if (n==2) {
						if (w!=0) k--;
						else k++;
					}
				}
			}
			n=0;
			b=0;
			w=0;
		}
		//printPattern(f);
		//System.out.println(k);

		check++;
		return k;
	}

	/////////////////////////////////////////////////////// Black Alpha

	private static int maxValueB(String[][] f, int depth, int alpha, int beta) {
		if (checkWinner(f) != null) {
			if (checkWinner(f) == "B") return 50;
			else if (checkWinner(f) == "W") return -50;
			else if (checkWinner(f) == "F") return EvalB(f);
		}

		if (depth > 5) {
			return EvalB(f);
		}

		depth = depth + 1;
		int column = 0;



		for (int k = 3; k < 13; k++) {
			int i = k % 8; 
			if (!columnFull(f,i)) {
				insertDisk(f, i);
				count++;
				int value = minValueB(f, depth, alpha, beta);
				if (value > alpha) {
					alpha = value;
					column = i;
				}
				removeDisk(f, i);
				count--;

				if (alpha >= beta)
				{
					cutoff++;
					return alpha;
				}
			}
		}
		maxColumnB = column;

		return alpha;
	}

	/////////////////////////////////////////////////////// Black Beta

	private static int minValueB(String[][] f, int depth, int alpha, int beta) {

		if (checkWinner(f) != null) {
			if (checkWinner(f) == "B") return 50;
			else if (checkWinner(f) == "W") return -50;
			else if (checkWinner(f) == "F") return EvalB(f);
		}

		if (depth > 5) {
			return EvalB(f);
		}

		depth = depth + 1;



		for (int k = 6; k < 14; k++) {
			int i = k % 8;
			if (!columnFull(f,i)) {
				insertDisk(f, i);
				count++;
				int value = maxValueB(f, depth, alpha, beta);

				if (value < beta) {
					beta = value;
				}
				removeDisk(f, i);
				count--;

				if (beta <= alpha)
				{
					cutoff++;
					return beta;
				}
			}
		}

		return beta;
	}


	/////////////////////////////////////////////////////// White Evaluation function

	public static int EvalW(String[][] f) {

		int k=0;


		// vertical four & three
		for (int i=1;i<7;i++)
		{
			for (int j=0;j<8;j++)
			{
				if((f[i][j] != " ")
						&& ((f[i][j] == f[i+1][j])
								&& (f[i+1][j] == f[i+2][j]))) {
					if (f[(i-1)][j] == " " && f[i+3][j]!=f[i][j]) {
						if (f[i][j]=="W") k++;
						else if (f[i][j]=="B") k--;
					}
					else if (f[(i-1)][j] == " ")	{
						if (f[i][j]=="W") k+=10;
						else if (f[i][j]=="B") k-=10;
					}
				}
			}
		}


		// horizontal three and four
		int n=0;
		int b=0;
		int w=0;

		for (int i =0;i<10;i++)
		{
			for (int j=0;j<8;j++)
			{
				if ((i==9) || (f[i+1][j % 8]!=" " && f[i+1][(j+1) % 8]!=" " && f[i+1][(j+2) % 8]!=" " && f[i+1][(j+3) % 8]!=" " && f[i+1][(j+4) % 8]!=" ")) {
					if (f[i][(j) % 8] == " ") n++;
					else if (f[i][(j) % 8] == "B") b++;
					else if (f[i][(j) % 8] == "W") w++;

					if (f[i][(j+1) % 8] == " ") n++;
					else if (f[i][(j+1) % 8] == "B") b++;
					else if (f[i][(j+1) % 8] == "W") w++;

					if (f[i][(j+2) % 8] == " ") n++;
					else if (f[i][(j+2) % 8] == "B") b++;
					else if (f[i][(j+2) % 8] == "W") w++;

					if (f[i][(j+3) % 8] == " ") n++;
					else if (f[i][(j+3) % 8] == "B") b++;
					else if (f[i][(j+3) % 8] == "W") w++;

					if (f[i][(j+4) % 8] == " ") n++;
					else if (f[i][(j+4) % 8] == "B") b++;
					else if (f[i][(j+4) % 8] == "W") w++;

					if ((b!=0 && w!=0) || n>2)	{ }
					else if (n==1) {
						if (w!=0)	k+=10;
						else	k-=10;
					}
					else if (n==2) {
						if (f[i][(j+4) % 8]==" " && f[i][j % 8]==" ") {
							if (w!=0)	k+=6;
							else	k-=6;
						}
						else if (w!=0) k++;
						else k--;
					}
				}
				n=0;
				b=0;
				w=0;
			}
		}

		n=0;
		b=0;
		w=0;


		// diagonal decline four
		for (int i =0;i<6;i++)
		{
			for (int j=0;j<4;j++)
			{
				if ((i==5) || (f[i+1][j]!=" " && f[i+2][j+1]!=" " && f[i+3][j+2]!=" " && f[i+4][j+3]!=" " && f[i+5][j+4]!=" ")) {
					if (f[i][j] == " ") n++;
					else if (f[i][j] == "B") b++;
					else if (f[i][j] == "W") w++;

					if (f[i+1][j+1] == " ") n++;
					else if (f[i+1][j+1] == "B") b++;
					else if (f[i+1][j+1] == "W") w++;

					if (f[i+2][j+2] == " ") n++;
					else if (f[i+2][j+2] == "B") b++;
					else if (f[i+2][j+2] == "W") w++;

					if (f[i+3][j+3] == " ") n++;
					else if (f[i+3][j+3] == "B") b++;
					else if (f[i+3][j+3] == "W") w++;

					if (f[i+4][j+4] == " ") n++;
					else if (f[i+4][j+4] == "B") b++;
					else if (f[i+4][j+4] == "W") w++;

					if ((b!=0 && w!=0) || n>2)	{ }
					else if (n==1) {
						if (w!=0)	k+=10;
						else	k-=10;
					}
					else if (n==2) {
						if (w!=0) k++;
						else k--;
					}
				}
			}
			n=0;
			b=0;
			w=0;
		}


		// diagonal incline four
		for (int i=9;i>3;i--)
		{
			for (int j=0;j<4;j++)
			{
				if ((i==9) || (f[i+1][j]!=" " && f[i][j+1]!=" " && f[i-1][j+2]!=" " && f[i-2][j+3]!=" " && f[i-3][j+4]!=" ")) {
					if (f[i][j] == " ") n++;
					else if (f[i][j] == "B") b++;
					else if (f[i][j] == "W") w++;

					if (f[i-1][j+1] == " ") n++;
					else if (f[i-1][j+1] == "B") b++;
					else if (f[i-1][j+1] == "W") w++;

					if (f[i-2][j+2] == " ") n++;
					else if (f[i-2][j+2] == "B") b++;
					else if (f[i-2][j+2] == "W") w++;

					if (f[i-3][j+3] == " ") n++;
					else if (f[i-3][j+3] == "B") b++;
					else if (f[i-3][j+3] == "W") w++;

					if (f[i-4][j+4] == " ") n++;
					else if (f[i-4][j+4] == "B") b++;
					else if (f[i-4][j+4] == "W") w++;

					if ((b!=0 && w!=0) || n>2)	{ }
					else if (n==1) {
						if (w!=0)	k+=10;
						else	k-=10;
					}
					else if (n==2) {
						if (w!=0) k++;
						else k--;
					}
				}
			}
			n=0;
			b=0;
			w=0;
		}
		//printPattern(f);
		//System.out.println(k);

		check++;
		return k;
	}

	/////////////////////////////////////////////////////// White Alpha

	private static int maxValueW(String[][] f, int depth, int alpha, int beta) {

		if (checkWinner(f) != null) {
			if (checkWinner(f) == "W") return 50;
			else if (checkWinner(f) == "B") return -50;
			else if (checkWinner(f) == "F") return EvalW(f);
		}

		if (depth > 6) {
			return EvalW(f);
		}

		depth = depth + 1;
		int column = 0;

		for (int k = 1; k < 9; k++) {
			int i = k % 8; 
			if (!columnFull(f,i)) {
				insertDisk(f, i);
				count++;
				int value = minValueW(f, depth, alpha, beta);
				if (value > alpha) {
					alpha = value;
					column = i;
				}
				removeDisk(f, i);
				count--;

				if (alpha >= beta)
				{
					cutoff++;
					return alpha;
				}
			}
		}
		maxColumnW = column;

		return alpha;
	}

	/////////////////////////////////////////////////////// White Beta

	private static int minValueW(String[][] f, int depth, int alpha, int beta) {

		if (checkWinner(f) != null) {
			if (checkWinner(f) == "W") return 50;
			else if (checkWinner(f) == "B") return -50;
			else if (checkWinner(f) == "F") return EvalW(f);
		}

		if (depth > 6) {
			return EvalW(f);
		}

		depth = depth + 1;


		for (int k = 7; k < 15; k++) {
			int i = k % 8;
			if (!columnFull(f,i)) {
				insertDisk(f, i);
				count++;
				int value = maxValueW(f, depth, alpha, beta);

				if (value < beta) {
					beta = value;
				}
				removeDisk(f, i);
				count--;

				if (beta <= alpha)
				{
					cutoff++;
					return beta;
				}
			}
		}

		return beta;
	}


	/////////////////////////////////////////////////////// Main()

	public static void main (String[] args)
	{


		String[][] f = createPattern();
		boolean loop = true;
		printPattern(f);
		while(loop)
		{
			if (count % 2 == 0) dropBlackPattern(f);
			else dropWhitePattern(f);
			count++;
			printPattern(f);

			if (checkWinner(f) != null)
			{
				if (checkWinner(f) == "B")
					System.out.println("The Black player won.");
				else if (checkWinner(f)== "W")
					System.out.println("The White player won.");
				else if (checkWinner(f)== "F")
					System.out.println("Draw.");
				loop = false;
			}
		}
	}

	/////////////////////////////////////////////////////// Check Winner

	public static String checkWinner(String[][] f)
	{

		for (int i =0;i<10;i++)
		{
			for (int j=0;j<8;j++)
			{
				if ((f[i][j % 8] != " ")
						&& ((f[i][j % 8] == f[i][(j+1) % 8])
								&& (f[i][(j+1) % 8] == f[i][(j+2) % 8])
								&& (f[i][(j+2) % 8] == f[i][(j+3) % 8])
								&& (f[i][(j+3) % 8] == f[i][(j+4) % 8])))
					return f[i][j];  
			}
		}


		for (int i=0;i<8;i++)
		{
			for (int j =0;j<6;j++)
			{
				if((f[j][i] != " ")
						&& ((f[j][i] == f[j+1][i])
								&& (f[j+1][i] == f[j+2][i])
								&& (f[j+2][i] == f[j+3][i])
								&& (f[j+3][i] == f[j+4][i])))
					return f[j][i];  
			}  
		}

		for (int i=0;i<6;i++)
		{

			for (int j=0;j<4;j++)
			{
				if((f[i][j] != " ")
						&& ((f[i][j] == f[i+1][j+1])
								&& (f[i+1][j+1] == f[i+2][j+2])
								&& (f[i+2][j+2] == f[i+3][j+3])
								&& (f[i+3][j+3] == f[i+4][j+4])))
					return f[i][j];  
			}  
		}

		for (int i=0;i<6;i++)
		{
			for (int j=4;j<8;j++)
			{
				if((f[i][j] != " ")
						&& ((f[i][j] == f[i+1][j-1])
								&& (f[i+1][j-1] == f[i+2][j-2])
								&& (f[i+2][j-2] == f[i+3][j-3])
								&& (f[i+3][j-3] == f[i+4][j-4])))
					return f[i][j];  
			}  
		}

		for (int i=0; i<8;i++) {
			if (!columnFull(f,i))	break;
			else if (i==7) return "F";
		}

		return null;
	}
}