package isvadervan;

import java.util.HashSet;
import java.util.Set;
import logicalcollections.*;

/**
 * @invar | getVader() == null || getVader().getKinderen().contains(this)
 * @invar | getKinderen() != null
 * @invar | getKinderen().stream().allMatch(k-> k!=null)
 * @invar | getKinderen().stream().allMatch(k-> k.getVader().equals(this))
 */

public class Person {
	/**
	 * @invar | vader == null || vader.kinderen.contains(this)
	 * @peerObject
	 */
	private Person vader;
	/**
	 * @representationObject				
	 * @invar | kinderen != null
	 * @invar | kinderen.stream().allMatch(k -> k != null)
	 * @invar | kinderen.stream().allMatch(k -> k.vader == this)
	 * @peerObjects
	 */
	private Set<Person> kinderen= new HashSet<>();
	/**
	 * @peerObject
	 */
	public Person getVader() {
		return vader;
	}
	/**
	 * @peerObjects
	 * @creates | result
	 */
	public Set<Person> getKinderen() {
		return Set.copyOf(kinderen);
	}
	/**
	 * @post | getVader() == vader
	 * @mutates_properties | vader.getKinderen()
	 * Met deze mutates_properties zeg je dat enkel de eigenschap van vader zelf, namelijk de kinderen, enkel gewijzigd
	 * wordt, en de peerobjecten en de eigenschappen van deze peerobjecten ongewijzigd blijven.
	 * @post | vader == null || vader.getKinderen().equals(LogicalSet.plus(old(vader.getKinderen()),this))
	 * Hier moet je eerst schrijven vader == null want je zou ook null kunnen meegeven als argument als 
	 * deze persoon bijvoorbeeld geen vader heeft in de graaf.
	 * @post | getKinderen().isEmpty()
	 */
	public Person(Person vader) {
		this.vader = vader;
		if (vader != null)
			vader.kinderen.add(this);
	}
	// In oplossing worden deze functies niet aangemaakt, aangezien er in de constructor al een vader
	// wordt toegekend, en dit volstaat.
	/**
	 * @mutates | this, vader
	 * @post | getVader() == vader
	 * @post | getVader().getKinderen().equals(LogicalSet.plus(old(getVader().getKinderen()), this))
	 */
	public void geefVader(Person vader) {
		this.vader = vader;
		vader.kinderen.add(this);
	}
	
	/**
	 * @mutates | this, kind
	 * @post | kind.getVader() == this
	 * @post | getKinderen().equals(LogicalSet.plus(old(getKinderen()), kind))
	 */
	public void geefKind(Person kind) {
		this.kinderen.add(kind);
		kind.vader = this;
	}
	
	/**
	 * @mutates | this
	 * @post | getVader() == null
	 * @post | old(getVader()).getKinderen().equals(LogicalSet.minus(old(getVader().getKinderen()),this))
	 */
	public void verwijderVader() {
		vader.kinderen.remove(this);
		this.vader = null;
	}
	/**
	 * @mutates | this
	 * @post | kind.getVader() == null
	 * @post | getKinderen().equals(LogicalSet.minus(old(getKinderen()), kind))
	 */
	public void verwijderKind(Person kind) {
		kind.vader = null;
		this.kinderen.remove(kind);
	}
}
