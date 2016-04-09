package com.example.projetoes.projetoes.BD;

/**
 * Created by luiz on 09/04/16.
 */
public class Column {

    private String name;
    private String type;
    private boolean isPrimaryKey;
    private boolean isAutoIncrement;

    public Column(String name, String type, boolean isPrimaryKey, boolean isAutoIncrement) {
        this(name, type, isPrimaryKey);
        setIsAutoIncrement(isAutoIncrement);
    }

    public Column(String name, String type, boolean isPrimaryKey) {
        this(name, type);
        setIsPrimaryKey(isPrimaryKey);
    }

    public Column(String name, String type) {
        setName(name);
        setType(type);
        setIsPrimaryKey(false);
        setIsAutoIncrement(false);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setIsPrimaryKey(boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }

    public boolean isAutoIncrement() {
        return isAutoIncrement;
    }

    public void setIsAutoIncrement(boolean isAutoIncrement) {
        this.isAutoIncrement = isAutoIncrement;
    }


    public String toSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append(getName() + " " + getType());
        if (isAutoIncrement) {
            builder.append(" " + "AUTOINCREMENT");
        }

        return builder.toString();
    }
}
