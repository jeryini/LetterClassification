/**
 * 
 */
package project2;

/**
 * @author Jernej Jerin
 * @author Tadej Vodopivec
 * 
 */
public class Filter {
	public Stage[] stages;
	public int numberOfStages;

	public Filter(int numberOfStages) {
		this.stages = new Stage[numberOfStages];
		for (int i = 0; i < numberOfStages; i++) {
			this.stages[i] = new Stage();
		}

		this.numberOfStages = numberOfStages;
	}
}
