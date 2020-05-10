package uliege_owl;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.util.Vector;

public class OwlInterface {

	Ontology ontology;
	Vector<String> classes;
	Vector<String> propertyTypes;
	Vector<String> dataPropertyNames;
	Vector<String> objectPropertyNames;
	Vector<String> dataRanges;
	Vector<String> annotationPropertyNames;
	Vector<String> namedIndividuals;

	// Frame
	JFrame frame;
	JTabbedPane tabbedPanel;
	JPanel editPanel;
	JPanel reasoningPanel;

	JPanel statusPanel;
	JLabel statusLabel;

	// Edit
	JPanel createPropertyForm;
	JComboBox<String> createPropertyType;
	JTextField createPropertyName;
	final JButton createPropertyButton = new JButton("Submit");

	JPanel createClassForm;
	JTextField createClassName;
	final JButton createClassButton = new JButton("Submit");

	JPanel setRangeForm;
	JComboBox<String> setRangeType;
	JComboBox<String> setRangeProperty;
	JComboBox<String> setRangeRange;
	final JButton setRangeButton = new JButton("Submit");

	JPanel setDomainForm;
	JComboBox<String> setDomainType;
	JComboBox<String> setDomainProperty;
	JComboBox<String> setDomainDomain;
	final JButton setDomainButton = new JButton("Submit");

	JPanel setSubclassForm;
	JComboBox<String> setSubclassChild;
	JComboBox<String> setSubclassParent;
	final JButton setSubclassButton = new JButton("Submit");

	JPanel createIndividualForm;
	JTextField createIndividualIRI;
	JComboBox<String> createIndividualType;
	final JButton createIndividualButton = new JButton("Submit");

	JPanel assignLabelForm;
	JComboBox<String> assignLabelIRI;
	JTextField assignLabelLabel;
	JTextField assignLabelLanguage;
	final JButton assignLabelButton = new JButton("Submit");

	JPanel assignObjectPropertyForm;
	JComboBox<String> assignObjectPropertySubject;
	JComboBox<String> assignObjectPropertyProperty;
	JComboBox<String> assignObjectPropertyObject;
	final JButton assignObjectPropertyButton = new JButton("Submit");

	JPanel assignDataPropertyForm;
	JComboBox<String> assignDataPropertySubject;
	JComboBox<String> assignDataPropertyProperty;
	JTextField assignDataPropertyData;
	JComboBox<String> assignDataPropertyType;
	final JButton assignDataPropertyButton = new JButton("Submit");

	JPanel saveForm;
	JTextField saveName;
	final JButton saveButton = new JButton("Save");

	// Edit
	JPanel getLabelForm;
	JComboBox<String> getLabelIRI;
	JButton getLabelButton = new JButton("Search");

	JPanel filterByLabelForm;
	JTextField filterByLabelLabel;
	final JButton filterByLabelButton = new JButton("Search");

	JPanel filterByObjectPropertyForm;
	JComboBox<String> filterByObjectPropertyProperty;
	JTextField filterByObjectPropertyObject;
	final JButton filterByObjectPropertyButton = new JButton("Search");

	JPanel filterByDataPropertyForm;
	JComboBox<String> filterByDataPropertyProperty;
	JTextField filterByDataPropertyData;
	JComboBox<String> filterByDataPropertyType;
	final JButton filterByDataPropertyButton = new JButton("Search");

	JPanel filterByClassForm;
	JComboBox<String> filterByClassClass;
	final JButton filterByClassButton = new JButton("Search");

	JPanel getSubclassesForm;
	JComboBox<String> getSubclassesClass;
	JCheckBox getSubclassesShallow;
	final JButton getSubclassesButton = new JButton("Search");

	JPanel getDisjointForm;
	JComboBox<String> getDisjointClass;
	final JButton getDisjointButton = new JButton("Search");

	JPanel resultPanel;
	JTextArea resultText;

	public void centerFrame(JFrame frame) {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) (screen.getWidth() / 2 - frame.getWidth() / 2);
		int y = (int) (screen.getHeight() / 2 - frame.getHeight() / 2);
		frame.setLocation(x, y);
	}

	public void createEdit() {
		// createPropertyForm
		createPropertyForm = new JPanel();
		createPropertyForm.setBorder(
			BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Create Property"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			)
		);
		createPropertyForm.setLayout(new GridBagLayout());

		// createClass
		createClassForm = new JPanel();
		createClassForm.setBorder(
			BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Create Class"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			)
		);
		createClassForm.setLayout(new GridBagLayout());

		// setRangePanel
		setRangeForm = new JPanel();
		setRangeForm.setBorder(
			BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Set Range"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			)
		);
		setRangeForm.setLayout(new GridBagLayout());

		// setDoeditPanel
		setDomainForm = new JPanel();
		setDomainForm.setBorder(
			BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Set Domain"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			)
		);
		setDomainForm.setLayout(new GridBagLayout());

		// setSubclassPanel
		setSubclassForm = new JPanel();
		setSubclassForm.setBorder(
			BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Set Subclass"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			)
		);
		setSubclassForm.setLayout(new GridBagLayout());

		// createIndividual
		createIndividualForm = new JPanel();
		createIndividualForm.setBorder(
			BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Create Individual"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			)
		);
		createIndividualForm.setLayout(new GridBagLayout());

		// assignLabel
		assignLabelForm = new JPanel();
		assignLabelForm.setBorder(
			BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Assign Label"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			)
		);
		assignLabelForm.setLayout(new GridBagLayout());

		// assignObjectPropertyPanel
		assignObjectPropertyForm = new JPanel();
		assignObjectPropertyForm.setBorder(
			BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Assign Object Property"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			)
		);
		assignObjectPropertyForm.setLayout(new GridBagLayout());

		// assignDataPropertyPanel
		assignDataPropertyForm = new JPanel();
		assignDataPropertyForm.setBorder(
			BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Assign Data Property"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			)
		);
		assignDataPropertyForm.setLayout(new GridBagLayout());

		// save
		saveForm = new JPanel();
		saveForm.setBorder(
			BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Save"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			)
		);
		saveForm.setLayout(new GridBagLayout());

		// edit
		editPanel = new JPanel();
		editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));
		editPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		editPanel.add(createPropertyForm);
		editPanel.add(createClassForm);
		editPanel.add(setRangeForm);
		editPanel.add(setDomainForm);
		editPanel.add(setSubclassForm);
		editPanel.add(createIndividualForm);
		editPanel.add(assignLabelForm);
		editPanel.add(assignObjectPropertyForm);
		editPanel.add(assignDataPropertyForm);
		editPanel.add(saveForm);
	}

	public void createReasoning() {
		// getLabelForm
		getLabelForm = new JPanel();
		getLabelForm.setBorder(
			BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Get Label by IRI"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			)
		);
		getLabelForm.setLayout(new GridBagLayout());

		// filterByLabelForm
		filterByLabelForm = new JPanel();
		filterByLabelForm.setBorder(
			BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Filter Instances by Label"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			)
		);
		filterByLabelForm.setLayout(new GridBagLayout());

		// filterByObjectPropertyForm
		filterByObjectPropertyForm = new JPanel();
		filterByObjectPropertyForm.setBorder(
			BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Filter Instances by Object Property"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			)
		);
		filterByObjectPropertyForm.setLayout(new GridBagLayout());

		// filterByDataPropertyForm
		filterByDataPropertyForm = new JPanel();
		filterByDataPropertyForm.setBorder(
			BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Filter Instances by Data Property"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			)
		);
		filterByDataPropertyForm.setLayout(new GridBagLayout());

		// filterByClassForm
		filterByClassForm = new JPanel();
		filterByClassForm.setBorder(
			BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Filter Instances by Class"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			)
		);
		filterByClassForm.setLayout(new GridBagLayout());

		// getSubclassesForm
		getSubclassesForm = new JPanel();
		getSubclassesForm.setBorder(
			BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Get Subclasses"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			)
		);
		getSubclassesForm.setLayout(new GridBagLayout());

		// getDisjointForm
		getDisjointForm = new JPanel();
		getDisjointForm.setBorder(
			BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Get Disjoint Classes"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			)
		);
		getDisjointForm.setLayout(new GridBagLayout());

		// result
		resultPanel = new JPanel();
		resultPanel.setBorder(
			BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Result"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			)
		);
		resultPanel.setLayout(new GridBagLayout());

		// reasoning
		reasoningPanel = new JPanel();
		reasoningPanel.setLayout(new BoxLayout(reasoningPanel, BoxLayout.Y_AXIS));
		reasoningPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		reasoningPanel.add(getLabelForm);
		reasoningPanel.add(filterByLabelForm);
		reasoningPanel.add(filterByObjectPropertyForm);
		reasoningPanel.add(filterByDataPropertyForm);
		reasoningPanel.add(filterByClassForm);
		reasoningPanel.add(getSubclassesForm);
		reasoningPanel.add(getDisjointForm);
		reasoningPanel.add(resultPanel);
	}

	public void createFrame() {
		tabbedPanel = new JTabbedPane();

		tabbedPanel.addTab(
			"Edition",
			new JScrollPane(
				editPanel,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
			)
		);

		tabbedPanel.addTab(
			"Reasoning",
			new JScrollPane(
				reasoningPanel,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
			)
		);

		statusPanel = new JPanel();
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		statusLabel = new JLabel();
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		statusPanel.add(statusLabel);

		frame = new JFrame("OWL-API Interface");
		frame.setSize(800, 600);
		frame.add(tabbedPanel);
		frame.add(statusPanel, BorderLayout.SOUTH);
		centerFrame(frame);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void initializeForms() {
		String defaultWidth = "XXXXXXXXXXXXXXXX";

		// Constraints
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(2, 2, 2, 2);
		c.gridx = 0; c.gridy = 0;

		// createPropertyForm
		createPropertyType = new JComboBox<String>();
		createPropertyName = new JTextField(10);

		c.gridx = 0; c.gridy = 0; createPropertyForm.add(new JLabel("Type"), c);
		c.gridx = 1; c.gridy = 0; createPropertyForm.add(new JLabel("Name"), c);
		c.gridx = 0; c.gridy = 1; createPropertyForm.add(createPropertyType, c);
		c.gridx = 1; c.gridy = 1; createPropertyForm.add(createPropertyName, c);
		c.gridx = 2; c.gridy = 1; createPropertyForm.add(createPropertyButton, c);

		// createClassForm
		createClassName = new JTextField(10);

		c.gridx = 0; c.gridy = 0; createClassForm.add(new JLabel("Name"), c);
		c.gridx = 0; c.gridy = 1; createClassForm.add(createClassName, c);
		c.gridx = 1; c.gridy = 1; createClassForm.add(createClassButton, c);

		// setRangeForm
		setRangeType = new JComboBox<String>();
		setRangeProperty = new JComboBox<String>();
		setRangeRange = new JComboBox<String>();

		c.gridx = 0; c.gridy = 0; setRangeForm.add(new JLabel("Type"), c);
		c.gridx = 1; c.gridy = 0; setRangeForm.add(new JLabel("Property"), c);
		c.gridx = 2; c.gridy = 0; setRangeForm.add(new JLabel("Range"), c);
		c.gridx = 0; c.gridy = 1; setRangeForm.add(setRangeType, c);
		c.gridx = 1; c.gridy = 1; setRangeForm.add(setRangeProperty, c);
		c.gridx = 2; c.gridy = 1; setRangeForm.add(setRangeRange, c);
		c.gridx = 3; c.gridy = 1; setRangeForm.add(setRangeButton, c);

		// setDomainForm
		setDomainType = new JComboBox<String>();
		setDomainProperty = new JComboBox<String>();
		setDomainDomain = new JComboBox<String>();

		c.gridx = 0; c.gridy = 0; setDomainForm.add(new JLabel("Type"), c);
		c.gridx = 1; c.gridy = 0; setDomainForm.add(new JLabel("Property"), c);
		c.gridx = 2; c.gridy = 0; setDomainForm.add(new JLabel("Domain"), c);
		c.gridx = 0; c.gridy = 1; setDomainForm.add(setDomainType, c);
		c.gridx = 1; c.gridy = 1; setDomainForm.add(setDomainProperty, c);
		c.gridx = 2; c.gridy = 1; setDomainForm.add(setDomainDomain, c);
		c.gridx = 3; c.gridy = 1; setDomainForm.add(setDomainButton, c);

		// setSubclassForm
		setSubclassChild = new JComboBox<String>();
		setSubclassParent = new JComboBox<String>();

		c.gridx = 0; c.gridy = 0; setSubclassForm.add(new JLabel("Child"), c);
		c.gridx = 2; c.gridy = 0; setSubclassForm.add(new JLabel("Parent"), c);
		c.gridx = 0; c.gridy = 1; setSubclassForm.add(setSubclassChild, c);
		c.gridx = 1; c.gridy = 1; setSubclassForm.add(new JLabel("subclass of"), c);
		c.gridx = 2; c.gridy = 1; setSubclassForm.add(setSubclassParent, c);
		c.gridx = 3; c.gridy = 1; setSubclassForm.add(setSubclassButton, c);

		// createIndividualForm
		createIndividualIRI = new JTextField(10);
		createIndividualType = new JComboBox<String>();

		c.gridx = 0; c.gridy = 0; createIndividualForm.add(new JLabel("IRI"), c);
		c.gridx = 1; c.gridy = 0; createIndividualForm.add(new JLabel("Class"), c);
		c.gridx = 0; c.gridy = 1; createIndividualForm.add(createIndividualIRI, c);
		c.gridx = 1; c.gridy = 1; createIndividualForm.add(createIndividualType, c);
		c.gridx = 2; c.gridy = 1; createIndividualForm.add(createIndividualButton, c);

		// assignLabelForm
		assignLabelIRI = new JComboBox<String>(); assignLabelIRI.setRenderer(new ComboboxToolTipRenderer());
		assignLabelLabel = new JTextField(10);
		assignLabelLanguage = new JTextField(10);

		assignLabelIRI.setPrototypeDisplayValue(defaultWidth);

		c.gridx = 0; c.gridy = 0; assignLabelForm.add(new JLabel("IRI"), c);
		c.gridx = 1; c.gridy = 0; assignLabelForm.add(new JLabel("Label"), c);
		c.gridx = 2; c.gridy = 0; assignLabelForm.add(new JLabel("Language"), c);
		c.gridx = 0; c.gridy = 1; assignLabelForm.add(assignLabelIRI, c);
		c.gridx = 1; c.gridy = 1; assignLabelForm.add(assignLabelLabel, c);
		c.gridx = 2; c.gridy = 1; assignLabelForm.add(assignLabelLanguage, c);
		c.gridx = 3; c.gridy = 1; assignLabelForm.add(assignLabelButton, c);

		// assignObjectPropertyForm
		assignObjectPropertySubject = new JComboBox<String>(); assignObjectPropertySubject.setRenderer(new ComboboxToolTipRenderer());
		assignObjectPropertyProperty = new JComboBox<String>();
		assignObjectPropertyObject = new JComboBox<String>(); assignObjectPropertyObject.setRenderer(new ComboboxToolTipRenderer());

		assignObjectPropertySubject.setPrototypeDisplayValue(defaultWidth);
		assignObjectPropertyObject.setPrototypeDisplayValue(defaultWidth);

		c.gridx = 0; c.gridy = 0; assignObjectPropertyForm.add(new JLabel("Subject"), c);
		c.gridx = 1; c.gridy = 0; assignObjectPropertyForm.add(new JLabel("Property"), c);
		c.gridx = 2; c.gridy = 0; assignObjectPropertyForm.add(new JLabel("Object"), c);
		c.gridx = 0; c.gridy = 1; assignObjectPropertyForm.add(assignObjectPropertySubject, c);
		c.gridx = 1; c.gridy = 1; assignObjectPropertyForm.add(assignObjectPropertyProperty, c);
		c.gridx = 2; c.gridy = 1; assignObjectPropertyForm.add(assignObjectPropertyObject, c);
		c.gridx = 3; c.gridy = 1; assignObjectPropertyForm.add(assignObjectPropertyButton, c);

		// assignDataPropertyForm
		assignDataPropertySubject = new JComboBox<String>(); assignDataPropertySubject.setRenderer(new ComboboxToolTipRenderer());
		assignDataPropertyProperty = new JComboBox<String>();
		assignDataPropertyData = new JTextField(10);
		assignDataPropertyType = new JComboBox<String>();

		assignDataPropertySubject.setPrototypeDisplayValue(defaultWidth);

		c.gridx = 0; c.gridy = 0; assignDataPropertyForm.add(new JLabel("Subject"), c);
		c.gridx = 1; c.gridy = 0; assignDataPropertyForm.add(new JLabel("Property"), c);
		c.gridx = 2; c.gridy = 0; assignDataPropertyForm.add(new JLabel("Data"), c);
		c.gridx = 3; c.gridy = 0; assignDataPropertyForm.add(new JLabel("Type"), c);
		c.gridx = 0; c.gridy = 1; assignDataPropertyForm.add(assignDataPropertySubject, c);
		c.gridx = 1; c.gridy = 1; assignDataPropertyForm.add(assignDataPropertyProperty, c);
		c.gridx = 2; c.gridy = 1; assignDataPropertyForm.add(assignDataPropertyData, c);
		c.gridx = 3; c.gridy = 1; assignDataPropertyForm.add(assignDataPropertyType, c);
		c.gridx = 4; c.gridy = 1; assignDataPropertyForm.add(assignDataPropertyButton, c);

		// saveForm
		saveName = new JTextField(10);

		c.gridx = 0; c.gridy = 0; saveForm.add(new JLabel("Destination"), c);
		c.gridx = 0; c.gridy = 1; saveForm.add(saveName, c);
		c.gridx = 1; c.gridy = 1; saveForm.add(saveButton, c);

		// getLabelForm
		getLabelIRI = new JComboBox<String>(); getLabelIRI.setRenderer(new ComboboxToolTipRenderer());

		getLabelIRI.setPrototypeDisplayValue(defaultWidth);

		c.gridx = 0; c.gridy = 0; getLabelForm.add(new JLabel("IRI"), c);
		c.gridx = 0; c.gridy = 1; getLabelForm.add(getLabelIRI, c);
		c.gridx = 1; c.gridy = 1; getLabelForm.add(getLabelButton, c);

		// filterByLabelForm
		filterByLabelLabel = new JTextField(10);

		c.gridx = 0; c.gridy = 0; filterByLabelForm.add(new JLabel("Label"), c);
		c.gridx = 0; c.gridy = 1; filterByLabelForm.add(filterByLabelLabel, c);
		c.gridx = 1; c.gridy = 1; filterByLabelForm.add(filterByLabelButton, c);

		// filterByObjectPropertyForm
		filterByObjectPropertyProperty = new JComboBox<String>();
		filterByObjectPropertyObject = new JTextField(10);

		// filterByObjectPropertyForm
		filterByObjectPropertyProperty = new JComboBox<String>();
		filterByObjectPropertyObject = new JTextField(10);

		c.gridx = 0; c.gridy = 0; filterByObjectPropertyForm.add(new JLabel("Property"), c);
		c.gridx = 1; c.gridy = 0; filterByObjectPropertyForm.add(new JLabel("Value"), c);
		c.gridx = 0; c.gridy = 1; filterByObjectPropertyForm.add(filterByObjectPropertyProperty, c);
		c.gridx = 1; c.gridy = 1; filterByObjectPropertyForm.add(filterByObjectPropertyObject, c);
		c.gridx = 2; c.gridy = 1; filterByObjectPropertyForm.add(filterByObjectPropertyButton, c);

		// filterByDataPropertyForm
		filterByDataPropertyProperty = new JComboBox<String>();
		filterByDataPropertyData = new JTextField(10);
		filterByDataPropertyType = new JComboBox<String>();

		c.gridx = 0; c.gridy = 0; filterByDataPropertyForm.add(new JLabel("Property"), c);
		c.gridx = 1; c.gridy = 0; filterByDataPropertyForm.add(new JLabel("Value"), c);
		c.gridx = 2; c.gridy = 0; filterByDataPropertyForm.add(new JLabel("Type"), c);
		c.gridx = 0; c.gridy = 1; filterByDataPropertyForm.add(filterByDataPropertyProperty, c);
		c.gridx = 1; c.gridy = 1; filterByDataPropertyForm.add(filterByDataPropertyData, c);
		c.gridx = 2; c.gridy = 1; filterByDataPropertyForm.add(filterByDataPropertyType, c);
		c.gridx = 3; c.gridy = 1; filterByDataPropertyForm.add(filterByDataPropertyButton, c);

		// filterByClassForm
		filterByClassClass = new JComboBox<String>();

		c.gridx = 0; c.gridy = 0; filterByClassForm.add(new JLabel("Class"), c);
		c.gridx = 0; c.gridy = 1; filterByClassForm.add(filterByClassClass, c);
		c.gridx = 1; c.gridy = 1; filterByClassForm.add(filterByClassButton, c);

		// getSubclassesForm
		getSubclassesClass = new JComboBox<String>();
		getSubclassesShallow = new JCheckBox();

		c.gridx = 0; c.gridy = 0; getSubclassesForm.add(new JLabel("Parent"), c);
		c.gridx = 1; c.gridy = 0; getSubclassesForm.add(new JLabel("Shallow"), c);
		c.gridx = 0; c.gridy = 1; getSubclassesForm.add(getSubclassesClass, c);
		c.gridx = 1; c.gridy = 1; getSubclassesForm.add(getSubclassesShallow, c);
		c.gridx = 2; c.gridy = 1; getSubclassesForm.add(getSubclassesButton, c);

		// getDisjointForm
		getDisjointClass = new JComboBox<String>();

		c.gridx = 0; c.gridy = 0; getDisjointForm.add(new JLabel("Class"), c);
		c.gridx = 0; c.gridy = 1; getDisjointForm.add(getDisjointClass, c);
		c.gridx = 1; c.gridy = 1; getDisjointForm.add(getDisjointButton, c);

		// result
		resultText = new JTextArea();
		resultText.setColumns(60);
		resultText.setLineWrap(true);
		c.gridx = 0; c.gridy = 0; resultPanel.add(resultText, c);

		frame.revalidate();
	}

	public void reloadComboBox() {
		// Load
		classes = ontology.get_class_names();
		propertyTypes = ontology.get_property_types();
		dataPropertyNames = ontology.get_data_property_names();
		objectPropertyNames = ontology.get_object_property_names();
		dataRanges = ontology.get_data_ranges();
		annotationPropertyNames = ontology.get_annotation_property_names();
		namedIndividuals = ontology.get_named_individuals_iri();

		createPropertyType.setModel(new DefaultComboBoxModel<String>(propertyTypes));

		setRangeType.setModel(new DefaultComboBoxModel<String>(propertyTypes));
		setRangeProperty.setModel(new DefaultComboBoxModel<String>(objectPropertyNames));
		setRangeRange.setModel(new DefaultComboBoxModel<String>(classes));

		setDomainType.setModel(new DefaultComboBoxModel<String>(propertyTypes));
		setDomainProperty.setModel(new DefaultComboBoxModel<String>(objectPropertyNames));
		setDomainDomain.setModel(new DefaultComboBoxModel<String>(classes));

		setSubclassChild.setModel(new DefaultComboBoxModel<String>(classes));
		setSubclassParent.setModel(new DefaultComboBoxModel<String>(classes));

		createIndividualType.setModel(new DefaultComboBoxModel<String>(classes));

		assignLabelIRI.setModel(new DefaultComboBoxModel<String>(namedIndividuals));
		((ComboboxToolTipRenderer) assignLabelIRI.getRenderer()).setTooltips(namedIndividuals);

		assignObjectPropertySubject.setModel(new DefaultComboBoxModel<String>(namedIndividuals));
		assignObjectPropertyProperty.setModel(new DefaultComboBoxModel<String>(objectPropertyNames));
		assignObjectPropertyObject.setModel(new DefaultComboBoxModel<String>(namedIndividuals));

		((ComboboxToolTipRenderer) assignObjectPropertySubject.getRenderer()).setTooltips(namedIndividuals);
		((ComboboxToolTipRenderer) assignObjectPropertyObject.getRenderer()).setTooltips(namedIndividuals);

		assignDataPropertySubject.setModel(new DefaultComboBoxModel<String>(namedIndividuals));
		assignDataPropertyProperty.setModel(new DefaultComboBoxModel<String>(dataPropertyNames));
		assignDataPropertyType.setModel(new DefaultComboBoxModel<String>(dataRanges));

		((ComboboxToolTipRenderer) assignDataPropertySubject.getRenderer()).setTooltips(namedIndividuals);

		getLabelIRI.setModel(new DefaultComboBoxModel<String>(namedIndividuals));

		((ComboboxToolTipRenderer) getLabelIRI.getRenderer()).setTooltips(namedIndividuals);

		filterByObjectPropertyProperty.setModel(new DefaultComboBoxModel<String>(objectPropertyNames));

		filterByDataPropertyProperty.setModel(new DefaultComboBoxModel<String>(dataPropertyNames));
		filterByDataPropertyType.setModel(new DefaultComboBoxModel<String>(dataRanges));

		filterByClassClass.setModel(new DefaultComboBoxModel<String>(classes));

		getSubclassesClass.setModel(new DefaultComboBoxModel<String>(classes));

		getDisjointClass.setModel(new DefaultComboBoxModel<String>(classes));
	}

	public void createActions() {
		createPropertyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String temp = createPropertyName.getText().trim();
				if (!temp.isEmpty()) {
					ontology.create_property(
						createPropertyType.getSelectedItem().toString(),
						temp
					);
					reloadComboBox();
				}

				statusLabel.setText(ontology.request_result());
			}
		});

		createClassButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String temp = createClassName.getText().trim();
				if (!temp.isEmpty()) {
					ontology.create_class(
						temp
					);
					reloadComboBox();
				}

				statusLabel.setText(ontology.request_result());
			}
		});

		setRangeType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (setRangeType.getSelectedIndex()) {
					case 0:
						setRangeProperty.setModel(new DefaultComboBoxModel<String>(objectPropertyNames));
						setRangeRange.setModel(new DefaultComboBoxModel<String>(classes));
						break;
					case 1:
						setRangeProperty.setModel(new DefaultComboBoxModel<String>(dataPropertyNames));
						setRangeRange.setModel(new DefaultComboBoxModel<String>(dataRanges));
						break;
					default:
						break;
				}

				statusLabel.setText(ontology.request_result());
			}
		});

		setRangeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ontology.set_range(
					setRangeType.getSelectedItem().toString(),
					setRangeProperty.getSelectedItem().toString(),
					setRangeRange.getSelectedItem().toString()
				);

				statusLabel.setText(ontology.request_result());
			}
		});

		setDomainType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (setDomainType.getSelectedIndex()) {
					case 0:
						setDomainProperty.setModel(new DefaultComboBoxModel<String>(objectPropertyNames));
						break;
					case 1:
						setDomainProperty.setModel(new DefaultComboBoxModel<String>(dataPropertyNames));
						break;
					default:
						break;
				}

				statusLabel.setText(ontology.request_result());
			}
		});

		setDomainButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ontology.set_domain(
					setDomainType.getSelectedItem().toString(),
					setDomainProperty.getSelectedItem().toString(),
					setDomainDomain.getSelectedItem().toString()
				);

				statusLabel.setText(ontology.request_result());
			}
		});

		setSubclassButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ontology.set_subclass(
					setSubclassChild.getSelectedItem().toString(),
					setSubclassParent.getSelectedItem().toString()
				);

				statusLabel.setText(ontology.request_result());
			}
		});

		createIndividualButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String temp = createIndividualIRI.getText().trim();
				if (!temp.isEmpty()) {
					ontology.add_individual(
						temp,
						createIndividualType.getSelectedItem().toString()
					);
					reloadComboBox();
				}

				statusLabel.setText(ontology.request_result());
			}
		});

		assignLabelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String temp = assignLabelLabel.getText().trim();
				if (!temp.isEmpty()) {
					ontology.assign_label(
						assignLabelIRI.getSelectedItem().toString(),
						temp,
						assignLabelLanguage.getText().trim()
					);
				}

				statusLabel.setText(ontology.request_result());
			}
		});

		assignObjectPropertyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ontology.assign_object_property(
					assignObjectPropertySubject.getSelectedItem().toString(),
					assignObjectPropertyProperty.getSelectedItem().toString(),
					assignObjectPropertyObject.getSelectedItem().toString()
				);

				statusLabel.setText(ontology.request_result());
			}
		});

		assignDataPropertyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String temp = assignDataPropertyData.getText().trim();
				if (!temp.isEmpty()) {
					ontology.assign_data_property(
						assignDataPropertySubject.getSelectedItem().toString(),
						assignDataPropertyProperty.getSelectedItem().toString(),
						temp,
						assignDataPropertyType.getSelectedItem().toString()
					);
				}

				statusLabel.setText(ontology.request_result());
			}
		});

		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String temp = saveName.getText().trim();
				if (!temp.isEmpty()) {
					ontology.save(
						temp
					);
				}

				statusLabel.setText(ontology.request_result());
			}
		});

		getLabelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Vector<String> ans = ontology.get_label_from_iri(
					getLabelIRI.getSelectedItem().toString()
				);

				if (ans != null)
					resultText.setText(ans.toString());

				statusLabel.setText(ontology.request_result());
			}
		});

		filterByLabelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String temp = filterByLabelLabel.getText().trim();
				if (!temp.isEmpty()) {
					Vector<String> ans = ontology.filter_by_label(
						temp
					);

					if (ans != null)
						resultText.setText(ans.toString());

					statusLabel.setText(ontology.request_result());
				}
			}
		});

		filterByObjectPropertyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String temp = filterByObjectPropertyObject.getText().trim();
				Vector<String> ans = ontology.filter_by_object_property(
					filterByObjectPropertyProperty.getSelectedItem().toString(),
					temp
				);

				if (ans != null)
					resultText.setText(ans.toString());

				statusLabel.setText(ontology.request_result());
			}
		});

		filterByDataPropertyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String temp = filterByDataPropertyData.getText().trim();
				Vector<String> ans = ontology.filter_by_data_property(
					filterByDataPropertyProperty.getSelectedItem().toString(),
					temp,
					filterByDataPropertyType.getSelectedItem().toString()
				);

				if (ans != null)
					resultText.setText(ans.toString());

				statusLabel.setText(ontology.request_result());
			}
		});

		filterByClassButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Vector<String> ans = ontology.filter_by_class(
					filterByClassClass.getSelectedItem().toString()
				);

				if (ans != null)
					resultText.setText(ans.toString());

				statusLabel.setText(ontology.request_result());
			}
		});

		getSubclassesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Vector<String> ans = ontology.get_subclasses(
					getSubclassesClass.getSelectedItem().toString(),
					getSubclassesShallow.isSelected()
				);

				if (ans != null)
					resultText.setText(ans.toString());

				statusLabel.setText(ontology.request_result());
			}
		});

		getDisjointButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Vector<String> ans = ontology.get_disjoint_classes(
					getDisjointClass.getSelectedItem().toString()
				);

				if (ans != null)
					resultText.setText(ans.toString());

				statusLabel.setText(ontology.request_result());
			}
		});
	}

	public OwlInterface(String file) {
		ontology = new Ontology(file);

		createEdit();
		createReasoning();
		createFrame();
		initializeForms();
		reloadComboBox();
		createActions();

		statusLabel.setText(ontology.request_result());
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		OwlInterface owlInterface = new OwlInterface(args[0]);
	}
}

/**
 * https://stackoverflow.com/questions/480261/java-swing-mouseover-text-on-jcombobox-items
 */
@SuppressWarnings("serial")
class ComboboxToolTipRenderer extends DefaultListCellRenderer {
    Vector<String> tooltips;

    @Override
    public Component getListCellRendererComponent(JList list, Object value,
                        int index, boolean isSelected, boolean cellHasFocus) {

        JComponent comp = (JComponent) super.getListCellRendererComponent(list,
                value, index, isSelected, cellHasFocus);

        if (-1 < index && null != value && null != tooltips) {
            list.setToolTipText(tooltips.get(index));
        }

        return comp;
    }

    public void setTooltips(Vector<String> tooltips) {
        this.tooltips = tooltips;
    }
}
