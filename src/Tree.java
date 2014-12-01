import java.util.LinkedList;
import java.util.Queue;

/**
 * File Tree.java
 * Proj AI03
 * Date 2014年11月30日 下午12:49:06
 */

/**
 * 整棵树
 * @author sanko
 */
public class Tree {
	
	/**
	 * 队列，宽度优先构建决策树
	 */
	private Queue q = new LinkedList();
	
	/**
	 * 树的生成
	 */
	public void generateTree(){
		
		// 根结点
		Node root = new Node(5,0);
		//当前操作结点，即队列顶部的结点
		Node cur;
		
		//队列操作，对整棵树进行宽度优先构建
		q.offer(root);		
		while(!q.isEmpty()){
			cur = (Node) q.poll();
			expandTree(cur);
			System.out.println(cur+" -> "+ Main.nameOfAttrs[cur.getNextAttr()] +
					cur.child);
		}
		
	}
	
	/**
	 * 对结点进行扩展，即，生成子结点
	 * @param n 指定扩展的结点，即，父结点
	 */
	private void expandTree(Node n){
		
		//指定扩展的属性类型
		int r = n.decideNextAttr(n.seq);
		Node genNode;
		//此种情况表示已经到达叶结点
		if(r==-1){
			genNode = new Node(4, n.getResult(), n.seq);
			n.child.add(genNode);
			return;
		}
		//中间过程，对每一种属性值生成相应的子结点并加入队列
		for(int i=0;i<Main.nameOfConditions[r].length;i++){
			genNode = new Node(r,i,n.seq);
			n.child.add(genNode);
			q.offer(genNode);
		}
		
	}
	
}
