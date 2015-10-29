import java.io.BufferedReader;
import java.io.InputStreamReader;

class LinkInfo
{
	public boolean isLinkN;
	public int isLive[];		//0������1�������2����([0]-[3]�������б��б)
	public int linknum[];		//([0]������[1]�������[2]����)
	LinkInfo()
	{
		isLinkN=false;
		isLive=new int[4];
		linknum=new int[3];
	}
	public String toString()
	{
		String r=new String("isLinkN="+isLinkN+";isLive[0~3]="+isLive[0]+isLive[1]+isLive[2]+isLive[3]+";linknum[0~2]="+linknum[0]+linknum[1]+linknum[2]);
		return r;
	}
	public int getLiveNum()
	{
		int lvn=0;
		for(int i=0;i<4;i++)
			if(isLive[i]==2) lvn++;
		return lvn;
	}
	public int getHLiveNum()
	{
		int lvn=0;
		for(int i=0;i<4;i++)
			if(isLive[i]==1) lvn++;
		return lvn;
	}
	public int getDeathNum()
	{
		int lvn=0;
		for(int i=0;i<4;i++)
			if(isLive[i]==0) lvn++;
		return lvn;
	}
} 
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
	 * ������������
	 */
	public int[] computerDo() {
		
		int posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		int posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		String[][] board = chessboard.getBoard();
		while (board[posX][posY] != "ʮ") {
			posX = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
			posY = (int) (Math.random() * (Chessboard.BOARD_SIZE - 1));
		}
		
		//////////////////////////////////////////////////
		int[] result = {0, 0} ;
		
		LinkInfo linkinfo=new LinkInfo();
		int maxpriv=0;
/*		for(int i=0;i<N;i++)
		{
			for(int j=0;j<N;j++)
			{
				if(isChessOn[i][j]==NONE)
				{
					int priv=0;
					priv=getPriv(i,j,side);
					if(priv>=maxpriv)
					{
						maxpriv=priv;
						result[0]=i;
						result[1]=j;					
					}
				}
			}//endfor
		}//endfor
*/		
		return result;
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
		String strVertical = null;
		String strHorizon = null;
		String strL2R = null;
		String strR2L = null;
		String[][] board = chessboard.getBoard();
		
		//Create Vertical String
		for(int v = posY-4; v<posY+4; v++)
		{
			if(v < 0 || v >= Chessboard.BOARD_SIZE)
				continue;
			strVertical += board[posX][v];
		}
		
		if(isWinInString(strVertical, ico))
			return true;
		//Create Horizontal String 
		for(int h = posX-4; h<posX+4; h++)
		{
			if(h<0 || h>=Chessboard.BOARD_SIZE-1)
				continue;
			strHorizon += board[h][posY];
		}
		if(isWinInString(strHorizon, ico))
			return true;
		
		//Create L2R String
		int lX, lY;
		for(lX=posX-4, lY=posY-4; lX<posX+4; lX++, lY++)
		{
			if(lX<0 || lX>=Chessboard.BOARD_SIZE || lY<0 || lY>=Chessboard.BOARD_SIZE)
				continue;
			strL2R += board[lX][lY];
		}
		if(isWinInString(strL2R, ico))
			return true;
		//Create R2L String
		int rX, rY;
		for(rX=posX+4, rY=posY+4; rX>posX-4; rX--, rY--)
		{
			if(rX<0 || rX>=Chessboard.BOARD_SIZE || rY<0 || rY>=Chessboard.BOARD_SIZE)
				continue;
			strR2L += board[rX][rY];
		}
		if(isWinInString(strR2L, ico))
			return true;
		
		return false;
	}
	
	//�ж��ַ��� str���Ƿ���5������
	public boolean isWinInString(String str, String ico)
	{
		String strTarget = null;
		for(int i=0; i<5; i++)
		{
			strTarget += ico;
		}
		
		return str.contains(strTarget);
	}
	public static void main(String[] args) throws Exception {

		GobangGame gb = new GobangGame(new Chessboard());
		gb.start();
	}
}
