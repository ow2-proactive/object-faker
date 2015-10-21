package objectFaker;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PropertyFinderTest {

	protected PropertyFinder finder = new PropertyFinder();
	
	@Test
	public void testFindProperties_ProtectedField() {
		class A {
			protected String att1;
			public String getAtt1() {return att1;}
			public void setAtt1(String att1) {this.att1 = att1;}
		}
		
		List<Field> properties = this.finder.findProperties(A.class);
		
		assertThat(properties, contains(hasProperty("name", equalTo("att1"))));
		assertThat(properties, hasSize(1));
		
	}
	
	
	@Test
	public void testFindProperties_PrivateField() {
		class A {
			private String att1;
			public String getAtt1() {return att1;}
			public void setAtt1(String att1) {this.att1 = att1;}
		}
		
		List<Field> properties = this.finder.findProperties(A.class);
		
		assertThat(properties, contains(hasProperty("name", equalTo("att1"))));
		assertThat(properties, hasSize(1));
	}
	
	
	@Test
	public void testFindProperties_ProtectedFieldWithoutGetter() {
		class A {
			protected String att1;
			public void setAtt1(String att1) {this.att1 = att1;}
		}
		
		List<Field> properties = this.finder.findProperties(A.class);
		
		assertThat(properties, hasSize(0));
	}
	
	
	@Test
	public void testFindProperties_ProtectedFieldWithoutSetter() {
		class A {
			protected String att1;
			public String getAtt1() {return att1;}
		}
		
		List<Field> properties = this.finder.findProperties(A.class);
		
		assertThat(properties, hasSize(0));
	}
	
	
	@Test
	public void testFindProperties_PublicField() {
		class A {
			public String att1;
		}
		
		List<Field> properties = this.finder.findProperties(A.class);
		
		assertThat(properties, hasSize(0));
	}
	
	
	@Test
	public void testFindProperties_InheritedField() {
		class A {
			protected String att1;
			public String getAtt1() {return att1;}
			public void setAtt1(String att1) {this.att1 = att1;}
		}
		
		class B extends A{}
		
		List<Field> properties = this.finder.findProperties(B.class);
		
		assertThat(properties, contains(hasProperty("name", equalTo("att1"))));
		assertThat(properties, hasSize(1));
	}
	
	@Test
	public void testFindProperties_InheritedFieldWithoutGetter() {
		class A {
			protected String att1;
			public void setAtt1(String att1) {this.att1 = att1;}
		}
		
		class B extends A{}
		
		List<Field> properties = this.finder.findProperties(B.class);
		
		assertThat(properties, hasSize(0));
	}
	
	
	@Test
	public void testFindProperties_InheritedFieldWithoutSetter() {
		class A {
			protected String att1;
			public String getAtt1() {return att1;}
		}
		
		class B extends A{}
		
		List<Field> properties = this.finder.findProperties(B.class);
		
		assertThat(properties, hasSize(0));
	}

}
