package uliege_owl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import org.coode.owlapi.turtle.TurtleOntologyFormat;
import org.semanticweb.HermiT.ReasonerFactory;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.search.EntitySearcher;

@SuppressWarnings("deprecation")

public class Owl {
	
	public static void print_label (OWLAnnotation a) {
		if (a.getValue() instanceof OWLLiteral) {
		    OWLLiteral val = (OWLLiteral) a.getValue();
		    System.out.println(val.getLiteral());
		   }
	}
			     		     
	public static void main(String[] args) throws Exception { // un peu baraki le throws Exception mais ça me saoule de les try catch a chaque fois
		
		String BASE = "http://www.semanticweb.org/ontologies/2020/uliege#";
		IRI BASE_IRI = IRI.create(BASE); 

		// Instantiate ontology manager
		OWLOntologyManager man = OWLManager.createOWLOntologyManager();
		
		// Load ontology
		File file = new File("resources/uliege.ttl");
		OWLOntology o = man.loadOntologyFromOntologyDocument(file);
		
		// Add Class, ObjectProperty and Individual axiom to the ontology
		OWLDataFactory df = man.getOWLDataFactory();
		OWLClass animal = df.getOWLClass(BASE_IRI + "Animal");
		OWLObjectProperty belongs_to = df.getOWLObjectProperty(BASE_IRI + "belongsTo");
		o.add(df.getOWLDeclarationAxiom(belongs_to));
		o.add(df.getOWLDeclarationAxiom(animal));
		o.add(df.getOWLObjectPropertyDomainAxiom(belongs_to, animal));
		o.add(df.getOWLObjectPropertyRangeAxiom(belongs_to, df.getOWLClass(BASE_IRI + "Person")));
		
		// Add an individual to the ontology with an existing property
		OWLIndividual victor_dachet = df.getOWLNamedIndividual("https://www.uliege.be/cms/c_9054334/repertoire?uid=s173564");
		OWLAnnotation label_victor = df.getOWLAnnotation(df.getRDFSLabel(), df.getOWLLiteral("Victor Dachet"));
		OWLAxiom label_ax_vic = df.getOWLAnnotationAssertionAxiom(victor_dachet.asOWLNamedIndividual().getIRI(), label_victor);
		o.add(label_ax_vic);
		
		// Victor Dachet is a BachelorStudent
		OWLClass bachelor_student = df.getOWLClass(BASE_IRI + "BachelorStudent");
		OWLClassAssertionAxiom class_assertion = df.getOWLClassAssertionAxiom(bachelor_student, victor_dachet);
		o.add(class_assertion);
		
		// Add programmation fonctionnelle
		OWLIndividual programmation_fonc = df.getOWLNamedIndividual("https://www.programmes.uliege.be/cocoon/20192020/cours/INFO0054-1.html");
		OWLAnnotation label_pf = df.getOWLAnnotation(df.getRDFSLabel(), df.getOWLLiteral("INFO0054 - Programmation Fonctionnelle", "fr"));
		OWLAxiom label_ax_pf = df.getOWLAnnotationAssertionAxiom(programmation_fonc.asOWLNamedIndividual().getIRI(), label_pf);
		o.add(label_ax_pf);
		
		// Victor Dachet follows programmation fonctionnelle
		OWLObjectProperty followsCourse = df.getOWLObjectProperty(BASE_IRI + "followsCourse");
		OWLObjectPropertyAssertionAxiom course_ax_vic = df.getOWLObjectPropertyAssertionAxiom(followsCourse, victor_dachet, programmation_fonc);
		o.add(course_ax_vic);

		// Get algebra course
		OWLIndividual algebra = df.getOWLNamedIndividual("https://www.programmes.uliege.be/cocoon/20192020/cours/MATH0013-1.html");
		OWLIndividual francois_rozet = df.getOWLNamedIndividual("https://www.uliege.be/cms/c_9054334/repertoire?uid=s161024"); 
		OWLObjectPropertyAssertionAxiom course_ax_francois = df.getOWLObjectPropertyAssertionAxiom(followsCourse, francois_rozet, algebra);
		o.add(course_ax_francois);
		
		// Get Francois Rozet's rdfs:label
		List<String> literals = new LinkedList<String>();
		Stream<OWLAnnotation> annotations_francois = EntitySearcher.getAnnotations(IRI.create("https://www.uliege.be/cms/c_9054334/repertoire?uid=s161024"), o, df.getRDFSLabel());
		annotations_francois.forEach(a -> {if (a.getValue() instanceof OWLLiteral) {
										    OWLLiteral val = (OWLLiteral) a.getValue();
										    String literal = val.getLiteral();
										    literals.add(literal);
										   }});
		System.out.println(literals);
		
		// Add subclass axiom to the ontology (animal needs to be "get" beforehand!)
		OWLClass dog = df.getOWLClass(BASE_IRI + "Dog");
		OWLSubClassOfAxiom dog_sub_an = df.getOWLSubClassOfAxiom(dog, animal);
		o.add(dog_sub_an);
		
		// Delete an axiom from the ontology. The axiom needs to be created AS IT EXISTS in the ontology beforehand.
		// Here, it already exists because we re-use the one above.
		o.remove(course_ax_vic);
		
		// Add an equivalent class to dog, "doggy"
		OWLClass doggy = df.getOWLClass(BASE_IRI + "Doggy");
		OWLAxiom doggy_is_dog = df.getOWLEquivalentClassesAxiom(doggy, dog);
		o.add(doggy_is_dog);

		// Create dog instance
		OWLIndividual rex = df.getOWLNamedIndividual(BASE_IRI + "rex");
		OWLObjectPropertyAssertionAxiom rex_belongs_to = df.getOWLObjectPropertyAssertionAxiom(belongs_to, rex, victor_dachet);
		o.add(rex_belongs_to);


		// Save ontology
		File save_file = new File("resources/modified.ttl");
		man.saveOntology(o, new TurtleOntologyFormat(), new FileOutputStream(save_file));


		// Introduce reasoner
		OWLReasonerFactory rf = new ReasonerFactory();
		OWLReasoner r = rf.createReasoner(o);
		r.precomputeInferences(InferenceType.CLASS_HIERARCHY);
		
		// Get all subclasses of "Person" and print the stream
		System.out.println("Subclasses of Person:");
		r.getSubClasses(df.getOWLClass(BASE_IRI + "Person"), false).forEach(System.out::println);
		
		// Get all the disjoint classes (we have none)
		System.out.println("Disjoint classes:");
		r.getDisjointClasses(animal).forEach(System.out::println);
		
		// Get all the students
		System.out.println("Students:");
		r.getInstances(df.getOWLClass(BASE_IRI + "Student"), false).forEach(System.out::println);
		
		// Get all the persons
		System.out.println("Persons:");
		r.getInstances(df.getOWLClass(BASE_IRI + "Person"), false).forEach(System.out::println);
		
		// Get all the office instances
		System.out.println("Places:");
		r.getInstances(df.getOWLClass(BASE_IRI + "Office"), false).forEach(System.out::println);
	}
}
