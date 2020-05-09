package uliege_owl;

import java.util.Vector;

public class TestOntology {

	public static void main(String[] args) {
				
		Ontology ontology = new Ontology("resources/uliege.ttl");
		Vector<String> property_types = ontology.get_property_types();
		Vector<String> data_ranges = ontology.get_data_ranges();
		System.out.println(property_types);
		System.out.println(data_ranges);
		
		Vector<String> class_names = ontology.get_class_names();
		Vector<String> annotation_property_names = ontology.get_annotation_property_names();
		Vector<String> data_property_names = ontology.get_data_property_names();
		Vector<String> object_property_names = ontology.get_object_property_names();
		System.out.println(class_names);
		System.out.println(class_names.size() + " classes");
		System.out.println(annotation_property_names);
		System.out.println(annotation_property_names.size() + " annotation properties");
		System.out.println(data_property_names);
		System.out.println(data_property_names.size() + " data properties");
		System.out.println(object_property_names);
		System.out.println(object_property_names.size() + " object properties");
		
		Vector<String> named_individuals_iri = ontology.get_named_individuals_iri();
		System.out.println(named_individuals_iri);
		System.out.println(named_individuals_iri.size() + " individuals");
		
		ontology.create_property("object_property", "belongsTo");
        ontology.create_class("Animal");
        ontology.create_class("Dog");
        ontology.set_subclass("Dog", "Animal");
        
        ontology.create_property("object_property", "belongsTo");
        ontology.set_domain("object_property", "belongsTo", "Animal");
        ontology.set_range("object_property", "belongsTo", "Person");
        
        ontology.create_property("data_property", "doi");
        ontology.set_range("data_property", "doi", "string");
        
        ontology.add_individual("http://www.example.org/Rex", "Dog");
        ontology.assign_label("rex", "http://www.example.org/Rex");
        ontology.assign_data_property("http://www.example.org/Dog", "name", "Rex", "data_property");
        ontology.assign_object_property("http://www.example.org/Dog", "belongsTo", "https://www.uliege.be/cms/c_9054334/repertoire?uid=s161317");

        ontology.assign_label("Rex", "http://example.org/Rex");
        
        ontology.save("resources/modified.ttl");
	}

}
