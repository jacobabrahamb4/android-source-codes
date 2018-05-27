package com.example.android.slidingtabscolors;

public class Definition {
    public String[] definitions;
    public String[] examples;

    @Override
    public String toString() {
        String def = "";
        if (definitions != null) {
            for (int i = 0; i < definitions.length; i++) {
                def += "definition: [" + i + "]" + definitions[i] + "\n";
            }
        }
        if (examples != null) {
            for (int i = 0; i < examples.length; i++) {
                def += "example: [" + i + "]" + examples[i] + "\n";
            }
        }
        return def;
    }
}
