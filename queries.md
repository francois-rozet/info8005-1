# Queries

## Manchester Syntax

### Simple

* Every `Student`

	```
	Student
	```

* Every instance that follows some `Course`

	```
	followsCourse some Course
	```

* Every `Person` that is rector of an `University`

	```
	Person and rectorOf some University
	```

* Every `Publication` that has *Gilles Louppe* as author

	```
	Publication and hasAsAuthor value 'Gilles Louppe'
	```

* Every instance that is prerequisite of *Semantic Data*

	```
	prerequisiteOf value 'Semantic Data'
	```

* Every `Student` that has passed some `Course` prerequisite of *Semantic Data*

	```
	Student and hasPassed some (Course and prerequisiteOf value 'Semantic Data')
	```

* Every instance who's name is `"François Rozet"`

	```
	name value "François Rozet"
	```

	> There could be more than one.

* Every instance that has a `SecondaryDiploma`

	```
	hasDiploma some SecondaryDiploma
	```

### Complex

* Every `Student` that attended some lecture starting at *9:00* the *6 May, 2020*

	```
	Student and attends some
		(Lecture and 'has time' some
			('Time interval' and 'has beginning' some
				('Time instant' and  'in XSD Date-Time' value "2020-05-06T09:00:00"^^xsd:dateTime)))
	```

* Every instance that follows some `Course` taught by some `Employee` that is the author of some `Article` about *Electric power system*

	```
	followsCourse some
		(Course and taughtBy some (
			Employee and authorOf some
			(Article and aboutTopic value 'Electric power system')))
	```

* Every instance that follows some `Course` taught by some `Employee` that is the author of some `Article` about *Electric power system* or included in both *Master of Science in Computer Science and Engineering* and *Master of Science in Data Science and Engineering*

	```
	followsCourse some
		(Course and taughtBy some
			(Employee and authorOf some
				(Article and aboutTopic value 'Electric power system')) or
			(includedIn value 'Master of Science in Computer Science and Engineering' and
			includedIn value 'Master of Science in Data Science and Engineering'))
	```

## SPARQL

* Retrieve all labels of every `Person`

	```
	SELECT ?person_name WHERE
	{
		?person rdf:type :Person .
		?person rdfs:label ?person_name .
	}
	ORDER BY ASC(?person_name)
	```

* Retrieve every `Publication` with its authors' name

	```
	SELECT ?paper_label ?author_name
	WHERE
	{
		?publication a :Publication ;
					 rdfs:label ?paper_label ;
					 :hasAsAuthor ?author .
		?author :name ?author_name .
	}
	ORDER BY ASC(?paper_label)
	```

* Retrieve every *bad* `Student`, *i.e.* a student that didn't attend every lecture of a `Course`

	```
	SELECT ?bad_student_name ?course_label
	WHERE
	{
		?student :followsCourse ?course ;
				 :name ?bad_student_name .
		?course rdfs:label ?course_label .
		?lecture a :Lecture ;
				 :includedIn ?course .
		FILTER NOT EXISTS {
			?lecture :attendedBy ?student .
		}
	}
	ORDER BY ASC(?student)
	```

* Retrieve every `Student` that follows a `Course` taught by a `Professor` who's the author of a `Publication` about *Machine Learning*

	```
	SELECT ?student_name
		   ?professor_name
		   ?ml_publication_label
	WHERE
	{
		?student :followsCourse ?course .
		?course :taughtBy ?professor .
		?ml_publication :hasAsAuthor ?professor.
		?ml_publication :aboutTopic <https://en.wikipedia.org/wiki/Machine_learning> .
		?student :name ?student_name .
		?professor :name ?professor_name .
		?ml_publication rdfs:label ?ml_publication_label .
	}
	ORDER BY ASC(?student_name)
	```

### Header

```
PREFIX : <http://www.semanticweb.org/ontologies/2020/uliege#>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
```
