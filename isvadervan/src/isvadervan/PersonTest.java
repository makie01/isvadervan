package isvadervan;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

class PersonTest {

	@Test
	void test() {
		Person rene = new Person(null);
		Person dries = new Person(rene);
		Person jo = new Person(rene);
		Person cisse = new Person(jo);
		Person max = new Person(dries);
		Person rik = new Person(dries);
		
		
		assertEquals(rene, dries.getVader());
		assertEquals(rene, jo.getVader());
		assertEquals(dries, max.getVader());
		assertEquals(Set.of(rik, max), dries.getKinderen());
		jo.verwijderKind(cisse);
		assertEquals(Set.of(), jo.getKinderen());
	}

}
