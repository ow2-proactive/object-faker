package objectFaker;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import objectFaker.model.BD;
import objectFaker.model.Person;

import org.junit.Test;

public class DataFakerTest {

	@Test
	public void testFakeStringProperty() {
		DataFaker<Person> faker = new DataFaker<Person>(Person.class);
		Person person = faker.fake();
		assertThat(person, hasProperty("firstname", equalTo("firstname1")));
	}
	
	
	@Test
	public void testFakeInheritedStringProperty() {
		DataFaker<BD> faker = new DataFaker<BD>(BD.class);
		BD bd = faker.fake();
		assertThat(bd, hasProperty("title", equalTo("title1")));
	}

}
