/**
 * 
 */
package project2;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Jernej Jerin
 * @author Tadej Vodopivec
 * 
 */
public class Stage {
	public Set<Integer> points;
	public Set<Integer[]> edges;

	public Stage() {
		points = new HashSet<Integer>();
		edges = new HashSet<Integer[]>();
	}
}
