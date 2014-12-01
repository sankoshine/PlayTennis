import java.util.jar.Attributes.Name;

/**
 * File Main.java
 * Proj AI03
 * Date 2014��11��30�� ����11:37:00
 */

/**
 * ���������
 * @author sanko
 */
public class Main {

	/**
	 * ��������ֵ���ַ������ƣ����ڴ�ӡ���
	 */
	public static final String[][] nameOfConditions = {
			{"sunny","ovecast","rain"},
			{"hot","mild","cool"},
			{"high","normal"},
			{"false","true"},
			{"no","yes"},
			{"root"}
	};
	
	/**
	 * �����������͵��ַ������ƣ����ڴ�ӡ���
	 */
	public static final String[] nameOfAttrs = {
		"Outlook","Temperature","Humidity","Windy","PlayTennis"
	};
	
	/**
	 * ��������ݣ�����ű�ʾ
	 */
	public static final int input[][] = {
			{0,0,0,0,0},{0,0,0,1,0},{1,0,0,0,1},{2,1,0,0,1},
			{2,2,1,0,1},{2,2,1,1,0},{1,2,1,1,1},{0,1,0,0,0},
			{0,2,1,0,1},{2,1,1,0,1},{0,1,1,1,1},{1,1,0,1,1},
			{1,0,1,0,1},{2,1,0,1,0}
	};
	
	/**
	 * ���������
	 * @param args
	 */
	public static void main(String[] args) {
		
		Tree t = new Tree();
		t.generateTree();
	}
	
	/**
	 * ��ӡ�������ݣ����һ�¡�����
	 */
	public static void checkInput(){
		
		for(int i=0;i<input.length;i++){
			System.out.print(i+1+"\t");
			for(int j=0;j<5;j++){
				System.out.print(nameOfConditions[j][input[i][j]]+"\t");
			}
			System.out.println();
		}
	}

}
