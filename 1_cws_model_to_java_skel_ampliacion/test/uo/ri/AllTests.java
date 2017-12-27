package uo.ri;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ uo.ri.associations.AllTests.class, uo.ri.domain.AllTests.class,
		uo.ri.GaussTest.class, uo.ri.GaussTestAmpliacion.class, })
public class AllTests {
}
