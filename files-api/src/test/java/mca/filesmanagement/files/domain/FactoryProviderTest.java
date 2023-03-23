package mca.filesmanagement.files.domain;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mca.filesmanagement.files.domain.files.FactoryFileAggregate;

@ExtendWith(SpringExtension.class)
@Tag("UnitTest")
@DisplayName("Factory tests")
public class FactoryProviderTest {

	/** Test factory providers. */
	@Test
	@DisplayName("Test factory providers")
	public void givenAFactoryProvidersWhenCreateThenReturnProcessAggregate() {
		IFactoryFileAggregate factoryProcessAggregate = FactoryProvider.getFactoryFileAggregate();
		assertNotNull(factoryProcessAggregate);
		assertTrue(FactoryFileAggregate.class.isInstance(factoryProcessAggregate));
	}
}
