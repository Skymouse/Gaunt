/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rowf.gaunt.assets.definitions.syntax;

import java.util.regex.Pattern;

/**
 *
 * @author woeltjen
 */
public class Syntax {
    public static Pattern DECLARATOR    = Pattern.compile("\\s*(\\w+)\\s*\\((\\w+)\\)\\s*");
    public static Pattern PARAMETERIZED = Pattern.compile("\\s*(\\w+):\\s*(\\S+)\\s*");
    public static Pattern PARAMETERLESS = Pattern.compile("\\s*(\\w+)\\s*");
}
