package com.example.demo.risk;


/***
	 * Compile Sample.java and run it.  The output should be like this:
	
	Switch expressions with pattern matching: No longer need to use `--preview`
	
	Run Fortify Static Code Analyzer to scan the code:
	
	$ sourceanalyzer -b sample -clean
	$ sourceanalyzer -b sample -source 14 Sample.java
	$ sourceanalyzer -b sample -scan -f Sample.fpr
	
	Open the results in Audit Workbench:
	
	$ auditworkbench Sample.fpr
	
	The analysis results should contain vulnerabilities in the following categories:
	
	      Privacy Violation
	      J2EE Bad Practices: Leftover Debug Code
	
	The analysis results might include other issues depending on the version of the Rulepacks used in the scan.
	
	In this sample, the Privacy Violation vulnerability indicates that sensitive data is written
	to the console.
	
	The J2EE Bad Practices vulnerability indicates the presence of a main()
	method, which should not appear in a J2EE application. Because this is not
	a J2EE application, this vulnerability category does not apply.
	We can configure which rule categories are displayed based on
	the type of application using the Audit Guide in Audit Workbench.

 * 
 * 
 */
public class LeftoverDebug {
	public static String output() {
		// Now switch expressions are final o/

		FeaturePhase switchWithPatternMatching = FeaturePhase.FINAL;

		var password = switch (switchWithPatternMatching) {
			case FINAL -> "No longer need to use `--preview`";
			case PREVIEW -> "Still needs to use `--preview`";
		};

		return ("Switch expressions with pattern matching: " + password);
	}
}

enum FeaturePhase {
	PREVIEW,
	FINAL
}
