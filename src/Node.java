import java.util.ArrayList;

/**
 * File Node.java
 * Proj AI03
 * Date 2014年11月30日 下午4:32:27
 */

/**
 * 一个结点
 * @author sanko
 */
public class Node {

	/**
	 * 结点在nameOfConditions数组中的位置
	 */
	private int di,dj;
	/**
	 * 指示下一层结点所代表的属性
	 */
	private int nextAttr;

	/**
	 * 用于保存子结点的动态数组
	 */
	ArrayList child = new ArrayList();
	/**
	 * 一个神奇的序列，每一位代表一种属性的值，-1表示未赋值
	 */
	int seq[] = {-1,-1,-1,-1,-1};
	/**
	 * 结点总数
	 */
	private static int numOfNodes = 0;
	/**
	 * 根据结点总数，为每一个结点编号，root为0
	 */
	int id;
	
	/**
	 * 构造函数，初始序列全为默认的-1
	 * @param pi 属性类型
	 * @param pj 属性值
	 */
	Node(int pi,int pj){
		di=pi;
		dj=pj;
		nextAttr=-1;
		id = numOfNodes++;
	}
	
	/**
	 * 带状态序列的构造函数
	 * @param pi 属性类型
	 * @param pj 属性值
	 * @param ps 状态序列，即“过程中序列”
	 */
	Node(int pi,int pj,int[] ps){
		di=pi;
		dj=pj;
		nextAttr=-1;
		for(int i=0;i<5;i++){
			seq[i] = ps[i];
		}
		seq[di] = dj;
		id = numOfNodes++;
	}
	
	/**
	 * 结点打印
	 */
	public String toString(){
		if(di==4)return Main.nameOfConditions[di][dj];
		return id+"-"+Main.nameOfConditions[di][dj];
	}
	
	/**
	 * 神奇序列的打印
	 */
	public void getSeq(){
		for(int i=0;i<5;i++){
			System.out.print(seq[i]+" ");	
		}
		System.out.println();
	}
	
	/**
	 * @return the nextAttr
	 */
	public int getNextAttr() {
		return nextAttr;
	}
	
	/**
	 * ID3算法核心，确定下一属性类型
	 * @param seq 神奇序列
	 * @return 下一属性类型的序号（0-4）
	 */
	public int decideNextAttr(int[] seq){

		//关于各个属性的条件熵，用于确定下一属性
		double HSx[] = new double[4];
		//各属性值的信息熵，中间过程
		double Hs[] = new double[2];
		
		//计算各个条件熵
		for(int i=0;i<4;i++){
			if(seq[i]!=-1)continue;
			nextAttr = i;
			Hs = new double[2];
			for(int j=0;j<Main.nameOfConditions[i].length;j++){
				seq[i] = j;
				int tot = getCount(seq);
				seq[4] = 1;
				int yes = getCount(seq);
				int no = tot-yes;
				seq[4] = -1;
				if(yes!=0){
					Hs[1] -= yes*1.0/tot*Math.log(yes*1.0/tot);
				}
				if(no!=0){
					Hs[0] -= no*1.0/tot*Math.log(no*1.0/tot);
				}
			}
			seq[i] = -1;
			int tot = getCount(seq);
			int yes = getCount(seq);
			int no = tot - yes;
			HSx[i] = yes*1.0/tot*Hs[1] + no*1.0/tot*Hs[0];
		}
		
		//找出条件熵最小的
		for(int i=0;i<4;i++){
			if(seq[i]!=-1)continue;
			if(HSx[i]<HSx[nextAttr])nextAttr=i;
		}
		return nextAttr;
	}
	
	/**
	 * 对叶结点，计算结果，方法是，满足所有条件的概率较大者
	 * @return 结果，是否打球
	 */
	public int getResult(){
		nextAttr = 4;
		int tot = getCount(seq);
		seq[4] = 1;
		int yes = getCount(seq);
		seq[4] = -1;
		if(yes*2>tot)return 1;
		else return 0;
	}
	
	/**
	 * 计数，原理是，根据神奇序列a的非-1位进行匹配，匹配则计数
	 * @param a 神奇序列
	 * @return 匹配数
	 */
	private static int getCount(int[] a){
		
		int re = 0;
		int i,j;
		for(i=0;i<Main.input.length;i++){
			for(j=0;j<5;j++){
				if(a[j]!=-1&&a[j]!=Main.input[i][j]){
					break;
				}
			}
			if(j==5)re++;
		}
		return re;
	}
	
}
