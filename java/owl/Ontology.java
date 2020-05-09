package uliege_owl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Set;
import java.util.Vector;
import java.util.stream.Stream;

import org.coode.owlapi.turtle.TurtleOntologyFormat;
import org.semanticweb.HermiT.ReasonerFactory;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.search.EntitySearcher;
import org.semanticweb.owlapi.vocab.XSDVocabulary;

@SuppressWarnings("deprecation")

public class Ontology {
	private OWLOntology o;
	private OWLDataFactory df;
	private OWLOntologyManager man;
	private OWLReasonerFactory rf;
	private OWLReasoner r;
	private IRI BASE_IRI = IRI.create("http://www.semanticweb.org/ontologies/2020/uliege#");
	private String last_result;

	private final String TYPES[] = {"object_property", "data_property"};
	private final String RANGES[] = {"boolean", "integer", "float", "string", "double"};
	private final String TRUE_VALUES[] = {"true", "1"};
	private final String FALSE_VALUES[] = {"false", "0"};
	
	public Ontology(String filename) {
		// Instantiate ontology manager
		man = OWLManager.createOWLOntologyManager();
		
		// Load ontology
		File ont_file = new File(filename);
		try {
			o = man.loadOntologyFromOntologyDocument(ont_file);
			last_result = "Ontology loaded";
		}
		catch(OWLOntologyCreationException e) {
			last_result = "Error while loading the ontology";
		}
		
		// Instantiate the DataFactory
		df = man.getOWLDataFactory();
		
		// Instantiate the ReasonerFactory and the reasoner
		rf = new ReasonerFactory();
		r = rf.createNonBufferingReasoner(o);
	}
	
	public void save(String filename) {
		// Save ontology
		File save_file = new File(filename);
		
		try {
			man.saveOntology(o, new TurtleOntologyFormat(), new FileOutputStream(save_file));
			last_result = "Ontology saved";
		} catch(FileNotFoundException |  OWLOntologyStorageException e) {
			last_result = "Error while saving the ontology";
		}
	}

	public Vector<String> get_property_types() {
		// Return property types as strings
		Vector<String> types = new Vector<>(Arrays.asList(TYPES));
		return types;
	}

	public Vector<String> get_data_ranges() {
		// Return data property ranges as strings
		Vector<String> ranges = new Vector<>(Arrays.asList(RANGES));
		return ranges;
	}
	
	public String request_result() {
		// Return log describing the result of last request
		return last_result;
	}
	
	public Vector<String> get_class_names() {
		// Return the class names (IRI suffixes) as strings
		Stream<OWLClass> classes = o.classesInSignature();
		Vector<String> class_names = new Vector<>();
		classes.forEach(c -> { class_names.add(c.getIRI().getFragment()); });
		
		return class_names;
	}
	
	public Vector<String> get_annotation_property_names() {
		// Return the annotation property names (IRI suffixes) as strings
		Stream<OWLAnnotationProperty> annotation_properties = o.annotationPropertiesInSignature();
		Vector<String> annotation_property_names = new Vector<>();
		annotation_properties.forEach(a -> { annotation_property_names.add(a.getIRI().getFragment()); });
		
		return annotation_property_names;
	}
	
	public Vector<String> get_data_property_names() {
		// Return the data property names (IRI suffixes) as strings
		Stream<OWLDataProperty> data_properties = o.dataPropertiesInSignature();
		Vector<String> data_property_names = new Vector<>();
		data_properties.forEach(p -> { data_property_names.add(p.getIRI().getFragment()); });
		
		return data_property_names;
	}
	
	public Vector<String> get_object_property_names() {
		// Return the object property names (IRI suffixes) as strings
		Stream<OWLObjectProperty> obj_properties = o.objectPropertiesInSignature();
		Vector<String> obj_property_names = new Vector<>();
		obj_properties.forEach(p -> { obj_property_names.add(p.getIRI().getFragment()); });
		
		return obj_property_names;
	}
	
	public Vector<String> get_named_individuals_iri() {
		// Return the IRI's of all named individuals
		Stream<OWLNamedIndividual> named_ind = o.individualsInSignature();
		Vector<String> named_ind_iri = new Vector<>();
		named_ind.forEach(n -> { named_ind_iri.add(n.getIRI().toString()); });
		
		return named_ind_iri;
	}
	
	public void create_property(String type, String p_name) {
		// Create a property with the specified type and name ('name' is the suffix of the IRI)
		IRI property_IRI = IRI.create(BASE_IRI + p_name);
		switch(type) {
			case "data_property":
				OWLDataProperty data_p = df.getOWLDataProperty(property_IRI);
				o.add(df.getOWLDeclarationAxiom(data_p));
				last_result = "Data property created";
				break;
				
			case "object_property":
				OWLObjectProperty obj_p = df.getOWLObjectProperty(property_IRI);
				o.add(df.getOWLDeclarationAxiom(obj_p));
				last_result = "Object property created";
				break;
		}
	}

	public void create_class(String name) {
		// Create a class with the specified name ('name' is the suffix of the IRI)
		IRI class_IRI = IRI.create(BASE_IRI + name);
		OWLClass new_class = df.getOWLClass(class_IRI);
		o.add(df.getOWLDeclarationAxiom(new_class));
		last_result = "Class created";
	}

	public void set_subclass(String subclass_name, String superclass_name) {
		// Define a class as the subclass of another class
		OWLClass subclass = df.getOWLClass(BASE_IRI + subclass_name);
		OWLClass superclass = df.getOWLClass(BASE_IRI + superclass_name);

		o.add(df.getOWLSubClassOfAxiom(subclass, superclass));
		last_result = "Subclass axiom created";
	}

	public void set_range(String type, String p_name, String range) {
		// Set the range of the property identified by 'p_name' to 'range'
		switch (type) {
			case "data_property":
				OWLDataProperty data_p = df.getOWLDataProperty(BASE_IRI + p_name);
				OWLDatatype data_range = null;
				switch (range) {
					case "boolean":
						data_range = df.getOWLDatatype(XSDVocabulary.BOOLEAN.getIRI());
						break;
					case "integer":
						data_range = df.getOWLDatatype(XSDVocabulary.INTEGER.getIRI());
						break;
					case "string":
						data_range = df.getOWLDatatype(XSDVocabulary.STRING.getIRI());
						break;
					case "float":
						data_range = df.getOWLDatatype(XSDVocabulary.FLOAT.getIRI());
						break;
					case "double":
						data_range = df.getOWLDatatype(XSDVocabulary.DOUBLE.getIRI());
						break;
					default:
						last_result = "Error while setting range";
				}
				// Add the OWLDataRange
				if (data_range != null)  {
					o.add(df.getOWLDataPropertyRangeAxiom(data_p, data_range));
					last_result = "Range set";
				}
				break;
				
			case "object_property":
				// Set object property range defined by the class suffix 'range'
				OWLObjectProperty obj_p = df.getOWLObjectProperty(BASE_IRI + p_name);
				OWLClass class_range = df.getOWLClass(BASE_IRI + range);
				o.add(df.getOWLObjectPropertyRangeAxiom(obj_p, class_range));
				last_result = "Range set";
				break;
		}
	}

	public void set_domain(String type, String p_name, String domain) {
		// Set the domain of the property identified by 'p_name' to 'domain'
		OWLClass class_domain = df.getOWLClass(BASE_IRI + domain);
		switch(type) {
			case "data_property":
				OWLDataProperty data_p = df.getOWLDataProperty(BASE_IRI + p_name);
				o.add(df.getOWLDataPropertyDomainAxiom(data_p, class_domain));
				last_result = "Domain set";
				break;
				
			case "object_property":
				OWLObjectProperty obj_p = df.getOWLObjectProperty(BASE_IRI + p_name);
				o.add(df.getOWLObjectPropertyDomainAxiom(obj_p, class_domain));
				last_result = "Domain set";
				break;
		}
	}
	
	private OWLLiteral get_typed_literal(String type, String string) throws NumberFormatException {
		// Get the OWLLiteral corresponding to 'string' with type 'type'
		OWLLiteral literal = null;
		
		switch (type) {
			case "boolean":
				Boolean bool_value = null;
				
				if (Arrays.asList(TRUE_VALUES).contains(string))
					bool_value = true;
				else if (Arrays.asList(FALSE_VALUES).contains(string))
					bool_value = false;
				
				if (bool_value != null)
					literal = df.getOWLLiteral(bool_value);
				else
					throw new NumberFormatException("Non valid boolean");
				break;

			case "integer":
				Integer int_value = Integer.parseInt(string);
				literal = df.getOWLLiteral(int_value);
				break;
				
			case "string":
				literal = df.getOWLLiteral(string);
				break;
				
			case "float":
				Float float_value = Float.parseFloat(string);
				literal = df.getOWLLiteral(float_value);
				break;
				
			case "double":
				Double double_value = Double.parseDouble(string);
				literal = df.getOWLLiteral(double_value);			    
				break;
		}
		
		return literal;
	}

	public void assign_data_property(String subject, String predicate, String object, String type) {
		// Assign the data property defined by '(subject, predicate, object)' where 'object' is of
		// type 'type'
		OWLNamedIndividual subj = df.getOWLNamedIndividual(subject);
		OWLDataProperty pred = df.getOWLDataProperty(BASE_IRI + predicate);
		
		OWLLiteral lit = null;
		try {
			lit = get_typed_literal(type, object);
		}
		catch (NumberFormatException e) {
			last_result = "Error while parsing data. " + e.getMessage();
			return;
		}

		o.add(df.getOWLDataPropertyAssertionAxiom(pred, subj, lit));
		last_result = "Data property assigned to individual";
	}

	public void assign_object_property(String subject, String predicate, String object) {
		// Assign the object property defined by '(subject, predicate, object)'
		OWLNamedIndividual subj = df.getOWLNamedIndividual(subject);
		OWLObjectProperty pred = df.getOWLObjectProperty(BASE_IRI + predicate);
		OWLNamedIndividual obj = df.getOWLNamedIndividual(object);

		o.add(df.getOWLObjectPropertyAssertionAxiom(pred, subj, obj));
		last_result = "Object property assigned to individual";
	}
	
	public void assign_label(String label, String ind_IRI) {
		// Assign the rdfs:label defined by 'label' to the individual identified by 'ind_IRI'
        OWLNamedIndividual ind = df.getOWLNamedIndividual(IRI.create(ind_IRI));
        OWLAnnotation label_anno = df.getOWLAnnotation(df.getRDFSLabel(), df.getOWLLiteral(label));
        
        o.add(df.getOWLAnnotationAssertionAxiom(ind.getIRI(), label_anno));
		last_result = "Label assigned to individual";
    }


	public void add_individual(String individual_IRI, String class_name) {
		// Add the individual defined by the IRI 'individual_IRI' to the class identified by the suffix
		// 'class_name'
		OWLNamedIndividual individual = df.getOWLNamedIndividual(individual_IRI);
		OWLClass class_ref = df.getOWLClass(BASE_IRI + class_name);
		
		o.add(df.getOWLClassAssertionAxiom(class_ref, individual));
		last_result = "Individual added";
	}
	
	public Vector<String> get_subclasses(String parent_class, boolean shallow) {
		// Get the names of all direct (indirect if 'shallow' is false) subclasses of the class identified
		// by the suffix 'parent_class'
		if (! r.isConsistent()) {
			last_result = "Error: the ontology is inconsistent";
			return null;
		}
		
		Vector<String> class_names = new Vector<>();
		
		r.getSubClasses(df.getOWLClass(BASE_IRI + parent_class), shallow).forEach(
			a -> a.forEach(b -> class_names.add(b.getIRI().getFragment()))
		);

		last_result = "Done";
		return class_names;
	}

	public Vector<String> filter_by_label(String label) {
		// Return the IRI (as strings) of all individuals having 'label' as rdfs:label
		
		// Get all instances from the ontology
		if (! r.isConsistent()) {
			last_result = "Error: the ontology is inconsistent";
			return null;
		}

		// Loop over all individuals to check their rdfs:label, if it exists
		Set<OWLNamedIndividual> instances = r.getInstances(df.getOWLThing(), false).getFlattened();
		Vector<String> iri = new Vector<>();
		
		for (OWLNamedIndividual i : instances) {
			Stream<OWLAnnotation> annotations_i = EntitySearcher.getAnnotations(i.getIRI(), o, df.getRDFSLabel());
			annotations_i.forEach(a -> {if (a.getValue() instanceof OWLLiteral) {
											    OWLLiteral val = (OWLLiteral) a.getValue();
											    String literal = val.getLiteral();
											    
											    if (literal.equals(label)) {
											    	iri.add(i.getIRI().toString());
											    }
											   }});
		}
		last_result = "Done";
		return iri;
	}
	
	public Vector<String> filter_by_class(String class_name) {
		// Get the IRI (as strings) of all the individuals belonging to the class identified by
		// the suffix 'class_name'
		if (! r.isConsistent()) {
			last_result = "Error: the ontology is inconsistent";
			return null;
		}
		
		Vector<String> iri = new Vector<>();
		r.instances(df.getOWLClass(BASE_IRI + class_name)).forEach(i -> { iri.add(i.getIRI().toString()); });

		last_result = "Done";
		return iri;
	}
	
	public Vector<String> filter_by_object_property(String p_name, String ind_name) {
		// Get the IRI (as strings) of all the individuals satisfying the object property identified by
		// the suffix 'p_name' with the role filler identified by the IRI 'ind_name', if any.
		if (! r.isConsistent()) {
			last_result = "Error: the ontology is inconsistent";
			return null;
		}
		
		Vector<String> iri = new Vector<>();
		OWLObjectProperty obj_p = df.getOWLObjectProperty(BASE_IRI + p_name);
		
		if (ind_name.isEmpty()) {
			r.instances(df.getOWLObjectSomeValuesFrom(obj_p, df.getOWLThing())).forEach(ind -> { iri.add(ind.getIRI().toString()); });
		}
		else {
			Set<OWLNamedIndividual> instances = r.getInstances(df.getOWLThing(), false).getFlattened();
			
			for (OWLNamedIndividual i : instances) {
				Set<OWLNamedIndividual> ind = r.getObjectPropertyValues(i, obj_p).getFlattened();
				if (ind.contains(df.getOWLNamedIndividual(IRI.create(ind_name)))) {
					iri.add(i.getIRI().toString());
				}
			}
		}
		
		last_result = "Done";
		return iri;
	}
	
	public Vector<String> filter_by_data_property(String p_name, String value, String type) {
		// Get the IRI (as strings) of all the individuals satisfying the data property identified by
		// the suffix 'p_name' with the role filler being of type 'type', with value 'value', if any.
		if (! r.isConsistent()) {
			last_result = "Error: the ontology is inconsistent";
			return null;
		}
		
		Vector<String> iri = new Vector<>();
		OWLDataProperty data_p = df.getOWLDataProperty(BASE_IRI + p_name);
		
		if (value.isEmpty()) {
			r.instances(df.getOWLDataSomeValuesFrom(data_p, df.getTopDatatype()), false).forEach(ind -> { iri.add(ind.getIRI().toString()); });
		}
		else {
			Set<OWLNamedIndividual> instances = r.getInstances(df.getOWLThing(), false).getFlattened();
			
			for (OWLNamedIndividual i : instances) {
				Set<OWLLiteral> values = r.getDataPropertyValues(i, data_p);
				
				// Set correct typed OWLLiteral
				OWLLiteral lit = null;
				try {
					lit = get_typed_literal(type, value);
				}
				catch (NumberFormatException e) {
					last_result = "Error while parsing data. " + e.getMessage();
					return null;
				}
				
				if (values.contains(lit)) {
					iri.add(i.getIRI().toString());
				}
			}
		}

		last_result = "Done";
		return iri;
	}
}
