package test.com.iteratorfile;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class NewFeature {
	
	public static void main(String[] args) {
		Node node = new Node("1","ronaldo","0");
		Node node2 = new Node("2","Messy","1");
		Node node3 = new Node("3","Rooy","1");
		Node node4 = new Node("4","Libaly","2");
		
		List<Node> list = new ArrayList<Node>();
		list.add(node);
		list.add(node2);
		list.add(node3);
		list.add(node4);
		
		List<Node> tar =  new ArrayList<Node>();
		
		list.stream().forEach(new Consumer<Node>() {

			@Override
			public void accept(Node t) {
				List<Node> children = list.stream().filter(l -> l.getParentId().equals(t.getId()) ).collect(Collectors.toList());
				if(children.size()>0){
					t.setChildren(children);
					tar.add(t);
				}
			}
		});
		
		tar.stream().forEach(t->System.out.println(t.getChildren()));
		
	}
	
}

 class Node {
	private String id;
	private String name;
	private String parentId;
	private List<Node> children;
	
	
	/**
	 * @return the id
	 */
	public String getId() {
	
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
	
		this.id = id;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
	
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
	
		this.name = name;
	}
	
	/**
	 * @return the parentId
	 */
	public String getParentId() {
	
		return parentId;
	}
	
	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(String parentId) {
	
		this.parentId = parentId;
	}

	/**
	 * @param id
	 * @param name
	 * @param parentId
	 */
	public Node(String id, String name, String parentId) {
	
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
	}

	
	/**
	 * @return the children
	 */
	public List<Node> getChildren() {
	
		return children;
	}

	
	/**
	 * @param children the children to set
	 */
	public void setChildren(List<Node> children) {
	
		this.children = children;
	}

	@Override
	public String toString() {
		return "Node [id=" + id + ", name=" + name + ", parentId=" + parentId + ", children=" + children + "]";
	}
	
	
}
