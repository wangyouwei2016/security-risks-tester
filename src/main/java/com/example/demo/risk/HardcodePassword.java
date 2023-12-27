package com.example.demo.risk;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
/**
 * Compile Sample.java and run it.  The output should be like this:

	<input
	     id='password'
	     type='password'
	     placeholder='n5*oseu7'q'
	     required
	/>
	
		where 'placeholder' value will differ because of Random mechanism.
	
	
	Run Fortify Static Code Analyzer to scan the code:
	
	$ sourceanalyzer -b sample -clean
	$ sourceanalyzer -b sample Sample.java
	$ sourceanalyzer -b sample -scan -f Sample.fpr
	
	Open the results in Audit Workbench:
	
	$ auditworkbench Sample.fpr
	
	The output should contain vulnerabilities in the following categories:
	
	      Privacy Violation
	      Insecure Randomness: Hardcoded Seed
	      J2EE Bad Practices: Leftover Debug Code
	
	The Fortify analysis might detect other issues depending on the Rulepack version 
	used in the scan.
	
	The Privacy Violation vulnerability indicates that sensitive data is written 
	to an external location. To console in our case.
	
	The Insecure Randomness: Hardcoded Seed vulnerability indicates that a function 
	getRandomCharacters() is passed a constant value for the seed. Functions that generate 
	random or pseudorandom values, which are passed a seed, should not be called with a 
	constant argument.
	
	The J2EE Bad Practices vulnerability indicates the presence of a main()
	method, which should not appear in a J2EE application. Because this is not
	a J2EE application, this category of vulnerabilities does not apply.
	We can configure which categories of rules are displayed based on
	the type of application using the Audit Guide in Audit Workbench.

 * 
 */
public class HardcodePassword {
    public static String output() {
        String password = generateSecureRandomPassword();
        String passwordBlock = """
                               <input
                                    id='password'
                                    type='password'
                                    placeholder='%s'
                                    required
                               />
                               """.formatted(password);
        return passwordBlock;
    }
	
	
	
    public static Stream<Character> getRandomSpecialChars(int count) {
        return getRandomCharacters(count, '!', '-');
    }

    public static Stream<Character> getRandomAlphabets(int count, boolean includesUppercase) {
        return getRandomCharacters(count, 'a', 'z');
    }

    public static Stream<Character> getRandomNumbers(int count) {
        return getRandomCharacters(count, '0', '9');
    }

    private static Stream<Character> getRandomCharacters(int count, int leftMargin, int rightMargin) {
        Random random = new Random();
        IntStream specialChars = random.ints(count, leftMargin, rightMargin);
        return specialChars.mapToObj(data -> (char) data);
    }

    public static String generateSecureRandomPassword() {
        Stream<Character> pwdStream = Stream.concat(getRandomNumbers(2),
                Stream.concat(getRandomSpecialChars(2),
                        Stream.concat(getRandomAlphabets(2, true), getRandomAlphabets(4, false))));
        List<Character> charList = pwdStream.collect(Collectors.toList());
        Collections.shuffle(charList);
        return charList.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }	
}
