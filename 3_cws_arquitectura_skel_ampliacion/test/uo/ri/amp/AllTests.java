package uo.ri.amp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ uo.ri.amp.domain.AllTests.class,
		uo.ri.amp.service.AllTests.class, uo.ri.amp.TestsForUoMod_0.class,
		uo.ri.amp.TestsForUoMod_1.class, uo.ri.amp.TestsForUoMod_2.class, })

public class AllTests {

}
