package uliege_jena;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Property;

import org.apache.jena.util.FileManager;

import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;


public class Jena {
    
    public static void make_query(Model model, String filename) throws IOException{
        // Build the query object
        Query query = QueryFactory.read(filename);
        
        // Execute the query
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();

        // Print the query results 
        ResultSetFormatter.out(System.out, results, query);
        
        qe.close();
    }

    public static void main(String[] args) {

        String BASE = "http://www.semanticweb.org/ontologies/2020/uliege#";
        
        // Load
        FileManager.get().addLocatorClassLoader(Jena.class.getClassLoader());
        Model model = FileManager.get().loadModel("resources/uliege.ttl", null, "TURTLE");
        
        try {
        	make_query(model, "resources/ml_publications.rq");
        	make_query(model, "resources/bad_students.rq");
        }
        catch(IOException e) {
            System.out.println("Query failed");
        }
        
        // Retrieve some useful properties
        Property hasAsRector = (Property) model.getProperty(BASE + "hasAsRector");
        Property followsCourse = (Property) model.getProperty(BASE + "followsCourse");
        
        // Get some resources and their attributes
        Resource uliege = (Resource) model.getResource("https://www.uliege.be");
        Resource gaspard = (Resource) model.getResource("https://www.uliege.be/cms/c_9054334/repertoire?uid=s161826");
        Resource yann = (Resource) model.getResource("https://www.uliege.be/cms/c_9054334/repertoire?uid=s161317");
        Resource big_data_project = (Resource) model.getResource("https://www.programmes.uliege.be/cocoon/20192020/cours/PROJ0016-1.html");
        Resource semantic_data = (Resource) model.getResource("https://www.programmes.uliege.be/cocoon/20192020/cours/INFO8005-1.html");
        
        Literal label_uliege = (Literal) uliege.getProperty(RDFS.label).getObject();
        Resource rector_uliege = (Resource) uliege.getProperty(hasAsRector).getObject();
        Literal label_rector_uliege = (Literal) rector_uliege.getProperty(RDFS.label).getObject();
        
        System.out.print(label_uliege);
        System.out.print(" has Rector ");
        System.out.println(label_rector_uliege);
        
        // Create new resources
        Resource victor_dachet = model.createResource("https://www.uliege.be/cms/c_9054334/repertoire?uid=s171488");
        Resource programmation_fonctionnelle = model.createResource("https://www.programmes.uliege.be/cocoon/20192020/cours/INFO0054-1.html");
        
        // Add properties for these resources
        victor_dachet.addProperty(RDFS.label, model.createLiteral("Victor Dachet"))
                     .addProperty(RDF.type, model.getResource(BASE + "BachelorStudent"))
                     .addProperty(followsCourse, programmation_fonctionnelle);
        
        programmation_fonctionnelle.addProperty(RDFS.label, model.createLiteral("Programmation Fonctionnelle", "fr"))
                                   .addProperty(RDFS.label, model.createLiteral("Functional programming", "en"))
                                   .addProperty(RDF.type, model.getResource(BASE + "BachelorCourse"));
        
        // Modify a certain resource: replace the course Semantic Data by Big Data Project for Gaspard
        if (gaspard.hasProperty(followsCourse)) {
            StmtIterator iter = gaspard.listProperties(followsCourse);
            Statement prop = null;
            try {
                while (iter.hasNext()) {
                    Statement stmt = iter.next();
                    if (stmt.getObject().asResource().equals(semantic_data))
                        prop = stmt;
                }
            }
            finally {
                if (iter != null)
                    iter.close();
            }
            if (prop != null) {
                prop.changeObject(big_data_project);
                System.out.println("Course changed from Semantic Data to Big Data Project for Gaspard");
            }
        }
        
        // Delete a certain resource
        yann.removeAll(followsCourse);
        
        try {
            make_query(model, "resources/persons.rq");
            make_query(model, "resources/papers.rq");
        }
        catch(IOException e) {
            System.out.println("Query failed");
        }

        // Save        
        FileOutputStream output = null;
        try {
            output = new FileOutputStream("resources/modified.ttl");
        } catch(Exception e) {}
        
        model.write(output, "TURTLE");
    }
}