package com.example.android.slidingtabscolors;

import java.util.ArrayList;

public final class SearchResult {
    public final Metadata metadata;
    public final Result results[];

    public SearchResult(Metadata metadata, Result[] results){
        this.metadata = metadata;
        this.results = results;
    }

    public static final class Metadata {
        public final String provider;

        public Metadata(String provider){
            this.provider = provider;
        }

        @Override
        public String toString() {
            String result = "";
            if (provider != null) {
                result += "provider: " + provider + "\n";
            }
            return result;
        }
    }

    public ArrayList<Definition> getDefinitions() {
        if (results != null) {
            ArrayList<Definition> definitions = new ArrayList<>();
            for (int i = 0; i < results.length; i++) {
                results[i].getDefinitions(definitions);
            }
            return definitions;
        }
        return null;
    }


    @Override
    public String toString() {
        String result = "";
        if (metadata != null) {
            result += "Metadata: " + metadata + "\n";
        }
        if (results != null) {
            for(int i = 0; i < results.length; i++) {
                result += results[i].toString();
            }
        }
        return result;
    }

    public static final class Result {
        public final String id;
        public final String language;
        public final LexicalEntry lexicalEntries[];

        public Result(String id, String language, LexicalEntry[] lexicalEntries){
            this.id = id;
            this.language = language;
            this.lexicalEntries = lexicalEntries;
        }

        public void getDefinitions(ArrayList<Definition> definitions) {
            if (lexicalEntries != null) {
                for (int i = 0 ; i < lexicalEntries.length; i++) {
                    lexicalEntries[i].getDefinitions(definitions);
                }
            }
        }

        @Override
        public String toString() {
            String result = "";
            if (id != null) {
                result += "id: " + id + "\n";
            }
            if(language != null) {
                result += "language : " + language + "\n";
            }
            if(lexicalEntries != null) {
                for(int i = 0; i < lexicalEntries.length; i++) {
                    result += "lexicalentries: " + lexicalEntries[i].toString();
                }
            }
            return result;
        }

        public static final class LexicalEntry {
            public final Entry entries[];

            public LexicalEntry(Entry[] entries){
                this.entries = entries;
            }

            public void getDefinitions(ArrayList<Definition> definitions) {
                if (entries != null) {
                    for (int i = 0 ; i < entries.length; i++) {
                        entries[i].getDefinitions(definitions);
                    }
                }
            }

            @Override
            public String toString() {
                String result = "";
                if(entries != null) {
                    for (int i = 0; i < entries.length; i++) {
                        result += " entries: " + entries[i].toString();
                    }
                }
                return result;
            }

            public static final class Entry {
                public final String[] etymologies;
                public final String homographNumber;
                public final Sense senses[];

                public Entry(String[] etymologies, String homographNumber, Sense[] senses){
                    this.etymologies = etymologies;
                    this.homographNumber = homographNumber;
                    this.senses = senses;
                }

                public void getDefinitions(ArrayList<Definition> definitions) {
                    if (senses != null) {
                        for (int i = 0 ; i < senses.length; i++) {
                            senses[i].getDefinitions(definitions);
                        }
                    }
                }

                @Override
                public String toString() {
                    String result = "";
                    if (etymologies != null) {
                        for (int i = 0; i < etymologies.length; i++) {
                            result += "etymologies: [" + i + "]" + etymologies[i] + "\n";
                        }
                    }
                    if (homographNumber != null) {
                        result += "homographnumber: " + homographNumber + "\n";
                    }
                    if(senses != null) {
                        for (int i = 0; i < senses.length; i++) {
                            result += "senses: " + senses[i].toString();
                        }
                    }
                    return result;
                }

                public static final class Sense {
                    public final String[] definitions;
                    public final Example examples[];
                    public final String id;
                    public final String[] short_definitions;
                    public final Subsense subsenses[];

                    public Sense(String[] definitions, Example[] examples, String id, String[] short_definitions, Subsense[] subsenses){
                        this.definitions = definitions;
                        this.examples = examples;
                        this.id = id;
                        this.short_definitions = short_definitions;
                        this.subsenses = subsenses;
                    }

                    public void getDefinitions(ArrayList<Definition> definitions) {
                        if (this.definitions != null) {
                            Definition definition = new Definition();
                            definition.definitions = this.definitions;
                            if (this.examples != null) {
                                definition.examples = new String[this.examples.length];
                                for (int i = 0 ; i < this.examples.length; i++) {
                                    definition.examples[i] = examples[i].text;
                                }
                            }
                            definitions.add(definition);
                        }

                        if (subsenses != null) {
                            for (int i = 0 ; i < subsenses.length; i++) {
                                subsenses[i].getDefinitions(definitions);
                            }
                        }
                    }

                    @Override
                    public String toString() {
                        String result = "";
                        if(definitions != null) {
                            for (int i = 0 ; i < definitions.length; i++) {
                                result += "result: [" + i + "]" + definitions[i] + "\n";
                            }
                        }
                        if(examples!=null) {
                            for(int i = 0; i < examples.length; i++) {
                                result += "examples: [" + i + "]" + examples[i].toString();
                            }
                        }
                        if (id != null) {
                            result += "id: " + id + "\n";
                        }
                        if (short_definitions != null) {
                            for (int i = 0; i < short_definitions.length; i++) {
                                result += "short definitions: " + short_definitions[i] + "\n";
                            }
                        }
                        if (subsenses != null) {
                            for (int i = 0 ; i < subsenses.length; i++) {
                                result += "subsenses: [" + i + "]" + subsenses[i].toString();
                            }
                        }
                        return result;
                    }

                    public static final class Example {
                        public final String text;

                        public Example(String text){
                            this.text = text;
                        }

                        @Override
                        public String toString() {
                            String result = "";
                            if(text != null) {
                                result += "text: " + text + "\n";
                            }
                            return result;
                        }
                    }

                    public static final class Subsense {
                        public final String[] definitions;
                        public final Example examples[];
                        public final String id;
                        public final String[] regions;
                        public final String[] short_definitions;

                        public Subsense(String[] definitions, Example[] examples, String id, String[] regions, String[] short_definitions){
                            this.definitions = definitions;
                            this.examples = examples;
                            this.id = id;
                            this.regions = regions;
                            this.short_definitions = short_definitions;
                        }

                        public void getDefinitions(ArrayList<Definition> definitions) {
                            if (this.definitions != null) {
                                Definition definition = new Definition();
                                definition.definitions = this.definitions;
                                if (this.examples != null) {
                                    definition.examples = new String[this.examples.length];
                                    for (int i = 0 ; i < this.examples.length; i++) {
                                        definition.examples[i] = examples[i].text;
                                    }
                                }
                                definitions.add(definition);
                            }
                        }

                        @Override
                        public String toString() {
                            String result = "";
                            if(definitions != null) {
                                for(int i = 0; i < definitions.length; i++) {
                                    result += "definitions[" + i + "]" + definitions[i] + "\n";
                                }
                            }
                            if(examples != null) {
                                for(int i = 0; i < examples.length; i++) {
                                    result += "exmples[" + i + "]" + examples[i].toString() + "\n";
                                }
                            }
                            if (id != null) {
                                result += "id: " + id + "\n";
                            }
                            if(regions != null) {
                                for(int i = 0; i < regions.length; i++) {
                                    result += "regions[" + i + "]" + regions[i] + "\n";
                                }
                            }
                            if(short_definitions != null) {
                                for(int i = 0; i < short_definitions.length; i++) {
                                    result += "short definitions[" + i + "]" + short_definitions[i] + "\n";
                                }
                            }
                            return result;
                        }

                        public static final class Example {
                            public final String text;

                            public Example(String text){
                                this.text = text;
                            }

                            @Override
                            public String toString() {
                                String result = "";
                                if(text != null) {
                                    result += "text: " + text + "\n";
                                }
                                return result;
                            }
                        }
                    }
                }
            }
        }
    }
}
