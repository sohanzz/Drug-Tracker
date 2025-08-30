package com.asifahmedsohan.drugtracker.data.remote;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DrugResponse {

    @SerializedName("drugGroup")
    private DrugGroup drugGroup;

    public DrugGroup getDrugGroup() {
        return drugGroup;
    }

    public static class DrugGroup {
        @SerializedName("name")
        private String name;

        @SerializedName("conceptGroup")
        private List<ConceptGroup> conceptGroup;

        public String getName() {
            return name;
        }

        public List<ConceptGroup> getConceptGroup() {
            return conceptGroup;
        }
    }

    public static class ConceptGroup {
        @SerializedName("tty")
        private String tty;

        @SerializedName("conceptProperties")
        private List<ConceptProperty> conceptProperties;

        public String getTty() {
            return tty;
        }

        public List<ConceptProperty> getConceptProperties() {
            return conceptProperties;
        }
    }

    public static class ConceptProperty {
        @SerializedName("rxcui")
        private String rxcui;

        @SerializedName("name")
        private String name;

        @SerializedName("synonym")
        private String synonym;

        @SerializedName("tty")
        private String tty;

        @SerializedName("language")
        private String language;

        @SerializedName("suppress")
        private String suppress;

        @SerializedName("umlscui")
        private String umlscui;

        @SerializedName("psn")
        private String psn;

        public String getRxcui() {
            return rxcui;
        }

        public String getName() {
            return name;
        }

        public String getSynonym() {
            return synonym;
        }

        public String getTty() {
            return tty;
        }

        public String getLanguage() {
            return language;
        }

        public String getSuppress() {
            return suppress;
        }

        public String getUmlscui() {
            return umlscui;
        }

        public String getPsn() {
            return psn;
        }
    }
}
