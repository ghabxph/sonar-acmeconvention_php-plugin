package com.acmecorp.convention.php.helpers;

/**
 * (Class) PhpStringLiteral
 *     - Describes string literal
 *
 * @author ghabxph [ghabxph.official@gmail.com]
 */
public class PhpStringLiteral {

    /**
     * String literal
     */
    private String stringLiteral;

    /**
     * String literal value (removed quotes)
     */
    private String value;

    /**
     * Quotation used
     */
    private char quote;

    /**
     * Constructor
     *
     * @param stringLiteral  String literal to be parsed
     */
    public PhpStringLiteral(String stringLiteral) {
        this.stringLiteral = stringLiteral;
        parseStringLiteral();
    }

    /**
     * Sets the string literal for this class
     *
     * @param stringLiteral String literal to be parsed
     * @return Returns PhpStringLiteral instance, containing the parsed string
     */
    public static PhpStringLiteral set(String stringLiteral) {

        PhpStringLiteral instance = new PhpStringLiteral(stringLiteral);
        return instance;
    }

    /**
     * Returns string literal
     *
     * @return Returns the string literal
     */
    public String value() {

        return value;
    }

    /**
     * Returns the quotation character used
     *
     * @return Returns the quotation character used
     */
    public char getQuote() {

        return quote;
    }

    /**
     * Parses string literal
     */
    private void parseStringLiteral() {
        quote = stringLiteral.charAt(0);
        value = stringLiteral.substring(1, stringLiteral.length() - 1);
    }

}
