import java.util.ArrayList;

/**
 * File Node.java
 * Proj AI03
 * Date 2014��11��30�� ����4:32:27
 */

/**
 * һ�����
 * @author sanko
 */
public class Node {

	/**
	 * �����nameOfConditions�����е�λ��
	 */
	private int di,dj;
	/**
	 * ָʾ��һ���������������
	 */
	private int nextAttr;

	/**
	 * ���ڱ����ӽ��Ķ�̬����
	 */
	ArrayList child = new ArrayList();
	/**
	 * һ����������У�ÿһλ����һ�����Ե�ֵ��-1��ʾδ��ֵ
	 */
	int seq[] = {-1,-1,-1,-1,-1};
	/**
	 * �������
	 */
	private static int numOfNodes = 0;
	/**
	 * ���ݽ��������Ϊÿһ������ţ�rootΪ0
	 */
	int id;
	
	/**
	 * ���캯������ʼ����ȫΪĬ�ϵ�-1
	 * @param pi ��������
	 * @param pj ����ֵ
	 */
	Node(int pi,int pj){
		di=pi;
		dj=pj;
		nextAttr=-1;
		id = numOfNodes++;
	}
	
	/**
	 * ��״̬���еĹ��캯��
	 * @param pi ��������
	 * @param pj ����ֵ
	 * @param ps ״̬���У��������������С�
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
	 * ����ӡ
	 */
	public String toString(){
		if(di==4)return Main.nameOfConditions[di][dj];
		return id+"-"+Main.nameOfConditions[di][dj];
	}
	
	/**
	 * �������еĴ�ӡ
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
	 * ID3�㷨���ģ�ȷ����һ��������
	 * @param seq ��������
	 * @return ��һ�������͵���ţ�0-4��
	 */
	public int decideNextAttr(int[] seq){

		//���ڸ������Ե������أ�����ȷ����һ����
		double HSx[] = new double[4];
		//������ֵ����Ϣ�أ��м����
		double Hs[] = new double[2];
		
		//�������������
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
		
		//�ҳ���������С��
		for(int i=0;i<4;i++){
			if(seq[i]!=-1)continue;
			if(HSx[i]<HSx[nextAttr])nextAttr=i;
		}
		return nextAttr;
	}
	
	/**
	 * ��Ҷ��㣬�������������ǣ��������������ĸ��ʽϴ���
	 * @return ������Ƿ����
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
	 * ������ԭ���ǣ�������������a�ķ�-1λ����ƥ�䣬ƥ�������
	 * @param a ��������
	 * @return ƥ����
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
