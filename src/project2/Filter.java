/**
 * 
 */
package project2;

/**
 * @author Jernej Jerin
 *
 */
public class Filter {
	public Stage[] stages;
	private int numberOfStages;
	
	public Filter(int numberOfStages) {
		this.stages = new Stage[numberOfStages];
		this.numberOfStages = numberOfStages;
	}
}
