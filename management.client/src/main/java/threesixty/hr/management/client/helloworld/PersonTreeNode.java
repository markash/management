package threesixty.hr.management.client.helloworld;

import org.eclipse.scout.rt.client.ui.basic.tree.AbstractTreeNode;

public class PersonTreeNode extends AbstractTreeNode {

	private String name;
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public PersonTreeNode withName(final String name) {
		
		this.setName(name);
		return this;
	}
}
