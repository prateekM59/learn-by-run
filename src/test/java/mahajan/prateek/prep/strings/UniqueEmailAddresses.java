package mahajan.prateek.prep.strings;

import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by: pramahajan on 3/29/20 3:36 PM GMT+05:30
 */
//https://leetcode.com/problems/unique-email-addresses/
public class UniqueEmailAddresses {

    @Test
    // NOT VALIDATED - this solution using regexes
    // Non regex based code present in LeetCode submissions
    public void numUniqueEmails() {
        String[] emails = {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};

        Set<String> set = new HashSet<>();
        Pattern pattern = Pattern.compile("^(((.*)\\+(.*))|(.*))@(.*)$");
        for (String email: emails) {
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                System.out.println("Matched: " + email);
                printGroups(matcher);
                String group3 = matcher.group(3);
                String combined = "";
                if (StringUtils.isNotBlank(group3)) {  // matches first part of OR
                    combined = group3.replaceAll("\\.", "");
                } else { // matches second part
                    String group5 = matcher.group(5);
                    combined = group5.replaceAll("\\.", "");
                }
                combined = combined + '@' + matcher.group(6);
                System.out.println("Putting: " + combined);
                set.add(combined);
            }
        }

        System.out.println("Set: "+ set);
        System.out.println(set.size());
    }

    private void printGroups(Matcher matcher) {
        for(int i=1;i<=6;i++) {
            System.out.println("Group" + i + ": " + matcher.group(i));
        }
    }

}
