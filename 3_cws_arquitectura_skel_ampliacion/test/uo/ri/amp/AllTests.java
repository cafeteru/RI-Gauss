package uo.ri.amp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ uo.ri.amp.domain.AllTests.class,
		uo.ri.amp.service.AllTests.class, uo.ri.amp.AllTestsModulos.class })

public class AllTests {

}
