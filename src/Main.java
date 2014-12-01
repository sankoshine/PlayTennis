import java.util.jar.Attributes.Name;

/**
 * File Main.java
 * Proj AI03
 * Date 2014年11月30日 上午11:37:00
 */

/**
 * 程序主入口
 * @author sanko
 */
public class Main {

	/**
	 * 各种属性值的字符串名称，用于打印结果
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
	 * 各种属性类型的字符串名称，用于打印结果
	 */
	public static final String[] nameOfAttrs = {
		"Outlook","Temperature","Humidity","Windy","PlayTennis"
	};
	
	/**
	 * 输入的数据，以序号表示
	 */
	public static final int input[][] = {
			{0,0,0,0,0},{0,0,0,1,0},{1,0,0,0,1},{2,1,0,0,1},
			{2,2,1,0,1},{2,2,1,1,0},{1,2,1,1,1},{0,1,0,0,0},
			{0,2,1,0,1},{2,1,1,0,1},{0,1,1,1,1},{1,1,0,1,1},
			{1,0,1,0,1},{2,1,0,1,0}
	};
	
	/**
	 * 入口在这里
	 * @param args
	 */
	public static void main(String[] args) {
		
		Tree t = new Tree();
		t.generateTree();
	}
	
	/**
	 * 打印输入内容，检查一下。。。
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
