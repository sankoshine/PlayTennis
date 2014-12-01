import java.util.LinkedList;
import java.util.Queue;

/**
 * File Tree.java
 * Proj AI03
 * Date 2014��11��30�� ����12:49:06
 */

/**
 * ������
 * @author sanko
 */
public class Tree {
	
	/**
	 * ���У�������ȹ���������
	 */
	private Queue q = new LinkedList();
	
	/**
	 * ��������
	 */
	public void generateTree(){
		
		// �����
		Node root = new Node(5,0);
		//��ǰ������㣬�����ж����Ľ��
		Node cur;
		
		//���в����������������п�����ȹ���
		q.offer(root);		
		while(!q.isEmpty()){
			cur = (Node) q.poll();
			expandTree(cur);
			System.out.println(cur+" -> "+ Main.nameOfAttrs[cur.getNextAttr()] +
					cur.child);
		}
		
	}
	
	/**
	 * �Խ�������չ�����������ӽ��
	 * @param n ָ����չ�Ľ�㣬���������
	 */
	private void expandTree(Node n){
		
		//ָ����չ����������
		int r = n.decideNextAttr(n.seq);
		Node genNode;
		//���������ʾ�Ѿ�����Ҷ���
		if(r==-1){
			genNode = new Node(4, n.getResult(), n.seq);
			n.child.add(genNode);
			return;
		}
		//�м���̣���ÿһ������ֵ������Ӧ���ӽ�㲢�������
		for(int i=0;i<Main.nameOfConditions[r].length;i++){
			genNode = new Node(r,i,n.seq);
			n.child.add(genNode);
			q.offer(genNode);
		}
		
	}
	
}
