package com.example.projetoes.projetoes.BD;

import java.util.TreeMap;

/**
 * Created by luiz on 09/04/16.
 */
public class ForeignKey {

    private TreeMap<String, String> references;
    private String referencedTable;

    public ForeignKey(String referencedTable) {
        setReferencedTable(referencedTable);
        setReferences(new TreeMap<String, String>());
    }

    private TreeMap<String, String> getReferences() {
        return references;
    }

    private void setReferences(TreeMap<String, String> references) {
        this.references = references;
    }

    public String getReferencedTable() {
        return referencedTable;
    }

    public void setReferencedTable(String referencedTable) {
        this.referencedTable = referencedTable;
    }

    public ForeignKey addReference(String from, String to) {
        getReferences().put(from, to);
        return this;
    }

    public void removeReference(String from) {
        getReferences().remove(from);
    }

    public String toSQL() {
        StringBuilder builder = new StringBuilder();

        builder.append("FOREIGN KEY (");
        for (String column : getReferences().keySet()) {
            builder.append(column + ", ");
        }
        builder.setLength(builder.length() - 2);

        builder.append(") REFERENCES " + referencedTable + "(");
        for (String column : getReferences().keySet()) {
            builder.append(getReferences().get(column) + ", ");
        }
        builder.setLength(builder.length() - 2);
        builder.append(")");
        return builder.toString();
    }
}

