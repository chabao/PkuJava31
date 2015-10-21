import java.io.BufferedReader;
import java.io.InputStreamReader;

 
public class GobangGame {
	// ����ﵽӮ������������Ŀ
	private final int WIN_COUNT = 5;
	// �����û������X����
	private int posX = 0;
	// �����û������X����
	private int posY = 0;
	// ��������
	private Chessboard chessboard;

	/**
	 * �չ�����
	 */
	public GobangGame() {
	}

	/**
	 * ����������ʼ�����̺���������
	 * 
	 * @param chessboard
	 *            ������
	 */
	public GobangGame(Chessboard chessboard) {
		this.chessboard = chessboard;
	}

	/**
	 * ��������Ƿ�Ϸ���
	 * 
	 * @param inputStr
	 *            �ɿ���̨������ַ�����
	 * @return �ַ����Ϸ�����true,���򷵻�false��
	 */
	public boolean isValid(String inputStr) {
		// ���û�������ַ����Զ���(,)��Ϊ�ָ����ָ��������ַ���
		String[] posStrArr = inputStr.split(",");
		try {
			posX = Integer.parseInt(posStrArr[0]) - 1;
			posY = Integer.parseInt(posStrArr[1]) - 1;
		} catch (NumberFormatException e) {
			chessboard.printBoard();
			System.out.println("����(����,����)�ĸ�ʽ���룺");
			return false;
		}
		// ���������ֵ�Ƿ��ڷ�Χ֮��
		if (posX < 0 || posX >= Chessboard.BOARD_SIZE || posY < 0
				|| posY >= Chessboard.BOARD_SIZE) {
			chessboard.printBoard();
			System.out.println("X��Y����ֻ�ܴ��ڵ���1,��С�ڵ���" + Chessboard.BOARD_SIZE
					+ ",���������룺");
			return false;
		}
		// ��������λ���Ƿ��Ѿ�������
		String[][] board = chessboard.getBoard();
		if (board[posX][posY] != "ʮ") {
			chessboard.printBoard();
			System.out.println("��λ���Ѿ������ӣ����������룺");
			return false;
		}
		return true;
	}

	/**
	 * ��ʼ����
	 */
	public void start() throws Exception {
		// trueΪ��Ϸ����
		boolean isOver = false;
		chessboard.initBoard();
		chessboard.printBoard();
		// ��ȡ���̵�����
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = null;
		// br.readLine:ÿ����������һ�����ݰ��س���������������ݱ�br��ȡ��
		while ((inputStr = br.readLine()) != null) {
			isOver = false;
			if (!isValid(inputStr)) {
				// ������Ϸ���Ҫ���������룬�ټ���
				continue;
			}
			// �Ѷ�Ӧ������Ԫ�ظ�Ϊ"��"
			String chessman = Chessman.BLACK.getChessman();
			chessboard.setBoard(posX, posY, chessman);
			// �ж��û��Ƿ�Ӯ��
			if (isWon(posX, posY, chessman)) {
				isOver = true;

			} else {
				// ��������ѡ��λ������
				int[] computerPosArr = computerDo();
				chessman = Chessman.WHITE.getChessman();
				chessboard.setBoard(computerPosArr[0], computerPosArr[1],
						chessman);
				// �жϼ�����Ƿ�Ӯ��
				if (isWon(computerPosArr[0], computerPosArr[1], chessman)) {
					isOver = true;
				}
			}
			// �������ʤ�ߣ�ѯ���û��Ƿ������Ϸ
			if (isOver) {
				// ������������³�ʼ�����̣�������Ϸ
				if (isReplay(chessman)) {
					chessboard.initBoard();
					chessboard.printBoard();
					continue;
				}
				// ������������˳�����
				break;
			}
			chessboard.printBoard();
			System.out.println("����������������꣬Ӧ��x,y�ĸ�ʽ���룺");
		}
	}

	/**
	 * �Ƿ����¿�ʼ���塣
	 * 
	 * @param chessman
	 *            "��"Ϊ�û���"��"Ϊ�������
	 * @return ��ʼ����true�����򷵻�false��
	 */
	public boolean isReplay(String chessman) throws Exception {
		chessboard.printBoard();
		String message = chessman.equals(Chessman.BLACK.getChessman()) ? "��ϲ������Ӯ�ˣ�"
				: "���ź��������ˣ�";
		System.out.println(message + "����һ�֣�(y/n)");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if (br.readLine().equals("y")) {
			// ��ʼ��һ��
			return true;
		}
		return false;

	}

	/**
	 * �����AI����
	 * ����˼��,��������λ�õ�Ȩֵ��Ȼ��ѡ��Ȩֵ����λ������
	 */
	public int[] computerDo() {
		int[][] weight = new int[Chessboard.BOARD_SIZE][Chessboard.BOARD_SIZE];
		//��¼���������λ��
		int[] pos = new int[2];
		int max = 0;
		String[][] board = chessboard.getBoard();
		//��������λ�õ�Ȩֵ���Ѿ��������ӵ�λ�ã�ȨֵΪ-1
		for(int i=0; i<Chessboard.BOARD_SIZE; i++){
			for(int j=0; j<Chessboard.BOARD_SIZE; j++){
				if(board[i][j] != "ʮ")weight[i][j] = -1;
				//����Ȩֵ
				else weight[i][j] = weightCal(i,j);
			}
		}
		//����Ѱ��Ȩֵ����λ��
		for(int m=0; m<Chessboard.BOARD_SIZE; m++){
			for(int n=0; n<Chessboard.BOARD_SIZE; n++){
				if(weight[m][n] > max){
					max = weight[m][n];
					pos[0] = m;
					pos[1] = n;
				}
			}
		}
		return pos;
	}

	/**
	 * �����λ��Ȩֵ��
	 * 
	 * @param x  ��Ҫ����λ�õ�x����
	 * @param y  ��Ҫ����λ�õ�y����
	 * @return ��λ�õ�Ȩֵ��
	 */
	public int weightCal(int x,int y){
		int weight = 0;   //��λ�õ���Ȩֵ
		int[] weights = new int[4];    //�����λ���ĸ������Ȩֵ
		int w1 = 100000;    //���������ӣ�ȨֵΪw1
		int w2 = 50000;	    //��������ӣ�ȨֵΪw2
		int w3 = 10000;	    //����������
		int w4 = 5000;      //���������
		int w5 = 1000;      //���Զ�����
		int w6 = 500;       //��Ҷ�����
		int w7 = 100;	    //����һ����
		int w8 = 50;	    //���һ����
		String white = Chessman.WHITE.getChessman();
		String black = Chessman.BLACK.getChessman();
		//�����λ���ĸ�����ĵ���������
		weights[0] = Cal1(x,y,white);
		weights[1] = Cal2(x,y,white);
		weights[2] = Cal3(x,y,white);
		weights[3] = Cal4(x,y,white);
		//�ۼ�Ȩֵ
		for(int i=0; i<weights.length; i++){
			if(weights[i] == 4)weight += w1;
			else if(weights[i] == 3)weight += w3;
			else if(weights[i] == 2)weight += w5;
			else if(weights[i] == 1)weight += w7;
		}
		//�����λ���ĸ���������������
		weights[0] = Cal1(x,y,black);
		weights[1] = Cal2(x,y,black);
		weights[2] = Cal3(x,y,black);
		weights[3] = Cal4(x,y,black);
		//�ۼ�Ȩֵ
		for(int i=0; i<weights.length; i++){
			if(weights[i] == 4)weight += w2;
			else if(weights[i] == 3)weight += w4;
			else if(weights[i] == 2)weight += w6;
			else if(weights[i] == 1)weight += w8;
		}
		return weight;
	}
	
	
	/**
	 * �����λ�ú�����������
	 * 
	 * @param x  ��Ҫ����λ�õ�x����
	 * @param y  ��Ҫ����λ�õ�y����
	 * @return ��λ�õĶ�Ӧ��ɫ��������
	 */
	public int Cal1(int x,int y,String ico){
		int[] border = new int[4];
		border = border(x,y);
		int count1 = 0;
		int count2 = 0;
		int i = x-1;
		String[][] board = chessboard.getBoard();
		//�Ӹ�λ��������
		while( i >= border[0] && board[i][y] == ico){
			++count1;
			--i;
		}
		//�Ӹ�λ��������
		i = x+1;
		while(i <= border[2] && board[i][y] == ico){
			++count2;
			++i;
		}
		//���������������������ϴ���
		return count1 > count2 ? count1 : count2;
	}
	
	/**
	 * �����λ��������������
	 * 
	 * @param x  ��Ҫ����λ�õ�x����
	 * @param y  ��Ҫ����λ�õ�y����
	 * @return ��λ�õĶ�Ӧ��ɫ��������
	 */
	public int Cal2(int x,int y,String ico){
		int[] border = new int[4];
		border = border(x,y);
		int count1 = 0;
		int count2 = 0;
		int i = y-1;
		String[][] board = chessboard.getBoard();
		//�Ӹ�λ��������
		while( i >= border[1] && board[x][i] == ico){
			++count1;
			--i;
		}
		//�Ӹ�λ��������
		i = y+1;
		while(i <= border[3] && board[x][i] == ico){
			++count2;
			++i;
		}
		//���������������������ϴ���
		return count1 > count2 ? count1 : count2;
	}
	
	/**
	 * �����λ��б�·�����������
	 * 
	 * @param x  ��Ҫ����λ�õ�x����
	 * @param y  ��Ҫ����λ�õ�y����
	 * @return ��λ�õĶ�Ӧ��ɫ��������
	 */
	public int Cal3(int x,int y,String ico){
		int[] border = new int[4];
		border = border(x,y);
		int count1 = 0;
		int count2 = 0;
		int i = x-1;
		int j = y-1;
		String[][] board = chessboard.getBoard();
		//�Ӹ�λ�������Ϸ�����
		while( i >= border[0] && j >= border[1] && board[i][j] == ico){
			++count1;
			--i;
			--j;
		}
		//�Ӹ�λ�������·�����
		i = x+1;
		j = y+1;
		while(i <= border[2] && j <= border[3] && board[i][j] == ico){
			++count2;
			++i;
			++j;
		}
		//���������������������ϴ���
		return count1 > count2 ? count1 : count2;
	}
	
	/**
	 * �����λ��б�Ϸ�����������
	 * 
	 * @param x  ��Ҫ����λ�õ�x����
	 * @param y  ��Ҫ����λ�õ�y����
	 * @return ��λ�õĶ�Ӧ��ɫ��������
	 */
	public int Cal4(int x,int y,String ico){
		int[] border = new int[4];
		border = border(x,y);
		int count1 = 0;
		int count2 = 0;
		int i = x-1;
		int j = y+1;
		String[][] board = chessboard.getBoard();
		//�Ӹ�λ�������Ϸ�����
		while( i >= border[0] && j <= border[3] && board[i][j] == ico){
			++count1;
			--i;
			++j;
		}
		//�Ӹ�λ�������·�����
		i = x+1;
		j = y-1;
		while(i <= border[2] && j >= border[1] && board[i][j] == ico){
			++count2;
			++i;
			--j;
		}
		//���������������������ϴ���
		return count1 > count2 ? count1 : count2;
	}
	
	public int[] border(int x,int y){
		int[] border = new int[4];
		border[0] = 0;
		border[1] = 0;
		border[2] = chessboard.BOARD_SIZE-1;
		border[3] = chessboard.BOARD_SIZE-1; 
		int size = posX - WIN_COUNT + 1;
		border[0] = size < 0 ? 0 : size;
		size = posY - WIN_COUNT + 1;
		border[1] = size < 0 ? 0 : size;
		size = posX + WIN_COUNT - 1;
		border[2] = size > chessboard.BOARD_SIZE-1 ? chessboard.BOARD_SIZE-1 : size;
		size = posY + WIN_COUNT - 1;
		border[3] = size > chessboard.BOARD_SIZE-1 ? chessboard.BOARD_SIZE-1 : size;
		return border;
		
	}
	/**
	 * �ж���Ӯ
	 * 
	 * @param posX
	 *            ���ӵ�X���ꡣ
	 * @param posY
	 *            ���ӵ�Y����
	 * @param ico
	 *            ��������
	 * @return ��������������������һ��ֱ�ӣ������棬�����෴��
	 */
	public boolean isWon(int posX, int posY, String ico) { 
		//�����ӵ�Ϊ���ģ����ĸ�����ɨ�裬start_x_chess��¼���ӵ���벿��
		//��һ����������ɫ������x���꣬end_x_chess��¼�Ұ벿�ֵ�һ����������ɫ������x����
		int[] border =new int[4];
		border = border(posX,posY);
		
		int start_x_chess = posX;
		int start_y_chess = posY;
		int end_x_chess = posX;
		int end_y_chess = posY;
		boolean is_win = false;
		
		String[][] board = chessboard.getBoard();
		//�����ӵ�Ϊ���ĺ���ɨ�裬��¼���ߵ�һ����������ɫ�����ӵ�x���ꡣ
		while(start_x_chess >= border[0] && board[start_x_chess][posY] == ico)--start_x_chess;
		while(end_x_chess <= border[2] && board[end_x_chess][posY] == ico)++end_x_chess;
		//��Ϊ��¼���Ƿ�������ɫ������x���꣬�������Ϊ6����˵����ʤ��
		if(end_x_chess - start_x_chess == 6)is_win = true;
		//�����ӵ�Ϊ��������ɨ�裬��¼���ߵ�һ����������ɫ�����ӵ�y����
		start_x_chess = posX;
		start_y_chess = posY;
		end_x_chess = posX;
		end_y_chess = posY;
		if(!is_win){
			while(start_y_chess >= border[1] && board[posX][start_y_chess] == ico)--start_y_chess;
			while(end_y_chess <= border[3] && board[posX][end_y_chess] ==ico)++end_y_chess;
			if(end_y_chess - start_y_chess == 6)is_win = true;
		}
		//б��ɨ��
		start_x_chess = posX;
		start_y_chess = posY;
		end_x_chess = posX;
		end_y_chess = posY;
		if(!is_win){
			while(start_y_chess >= border[1] && start_x_chess >= border[0] && board[start_x_chess][start_y_chess] == ico){
				--start_y_chess;
				--start_x_chess;
			}
			while(end_y_chess <= border[3] && end_x_chess <= border[2] && board[end_x_chess][end_y_chess] ==ico){
				++end_y_chess;
				++end_x_chess;
			}
			if(end_y_chess - start_y_chess == 6)is_win = true;
		}
		//б��ɨ��
		start_x_chess = posX;
		start_y_chess = posY;
		end_x_chess = posX;
		end_y_chess = posY;
		if(!is_win){
			while(start_y_chess <= border[3] && start_x_chess >= border[0] && board[start_x_chess][start_y_chess] == ico){
				++start_y_chess;
				--start_x_chess;
			}
			while(end_y_chess >= border[1] && end_x_chess <= border[2] && board[end_x_chess][end_y_chess] ==ico){
				--end_y_chess;
				++end_x_chess;
			}
			if(end_x_chess - start_x_chess == 6)is_win = true;
		}
		return is_win;
	}

	public static void main(String[] args) throws Exception {

		GobangGame gb = new GobangGame(new Chessboard());
		gb.start();
	}
}
