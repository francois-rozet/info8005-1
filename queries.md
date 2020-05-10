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

* Every `Article` that has *Gilles Louppe* as author

	```
	Article and hasAsAuthor value 'Gilles Louppe'
	```

* Every instance that is prerequisite of *Semantic Data*

	```
	preRequisiteOf value 'Semantic Data'
	```

* Every `Student` that has passed some `Course` prerequisite of *Semantic Data*

	```
	Student and hasPassed some (Course and preRequisiteOf value 'Semantic Data')
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
