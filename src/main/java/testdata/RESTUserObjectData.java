package testdata;

import contexts.RESTUserContext;

public class RESTUserObjectData {
	
	public static String getUserObjectString(RESTUserContext context) {
		// Test Block
		String petObj = """
				{
				    "id": -1,
				       "name": "%1$s",
				       "username": "%2$s",
				       "email": "%3$s",
				       "address": {
				           "street": "%4$s",
				           "suite": "Suite 29",
				           "city": "%5$s",
				           "zipcode": "90566-555",
				           "geo": {
				               "lat": "-43.9509",
				               "lng": "-34.4618"
				           }
				       },
				       "phone": "%6$s",
				       "website": "%7$s",
				       "company": {
				           "name": "%8$s",
				           "catchPhrase": "Proactive didactic contingency",
				           "bs": "synergize scalable supply-chains"
				       }
				   }""";

		return petObj.formatted(
				context.getName(),
				context.getUsername(),
				context.getEmail(),
				context.getAddress_street(),
				context.getAddress_city(),
				context.getPhone(),
				context.getWebsite(),
				context.getCompany_name());
	}
	
	public static RESTUserContext getSampleRESTUserContext() {
		RESTUserContext context = new RESTUserContext();
		context.setName("Zaman Blake");
		context.setUsername("zblake");
		context.setEmail("zaman.blake@gmail.com");
		context.setAddress_street("Melder Place");
		context.setAddress_city("Colombo");
		context.setPhone("940412524899");
		context.setWebsite("zamanblake.net");
		context.setCompany_name("Test All The Things");
		
		return context;
	}
}
