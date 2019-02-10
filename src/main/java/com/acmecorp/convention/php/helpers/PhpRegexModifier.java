package com.acmecorp.convention.php.helpers;

/**
 * (Class) PhpRegexModifier
 *     - Finds the regex modifier within string literal
 *
 * @author ghabxph (ghabxph.official@gmail.com
 */
public class PhpRegexModifier {

    /**
     * Checks if modifier exist in the pattern
     *
     * @param pattern   String pattern to be checked
     * @param modifier  Modifier character to look at
     * @return True if modifier exist, false if not
     */
    public static boolean hasModifier(String pattern, char modifier)
    {
        return getModifiers(pattern).indexOf(modifier) > -1;
    }

    /**
     * Returns all modifiers in regex
     *
     * @param pattern  Pattern to look at
     * @return  Returns all modifiers in string format
     */
    private static String getModifiers(String pattern)
    {
        String[] arr = pattern.split("/");
        return arr[arr.length - 1];
    }
}
