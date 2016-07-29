/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.Map;

/**
 *
 * @author Nguyen Viet Bach
 */
public class FontPaths {

    private final Map<String, String> paths;

    public FontPaths(Map<String, String> paths) {
        this.paths = paths;
    }

    public String getPath(String fontName) {
        return paths.get(fontName);
    }
}
