package com.example.projetoes.projetoes.BD;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luiz on 09/04/16.
 */
public class Table {

    public String name;
    public ArrayList<Column> columns;
    public ArrayList<ForeignKey> foreignKeys;

    // Cria uma tabela com o nome dado, uma lista de colunas vazia e uma lista vazia de chaves estrangeiras
    public Table(String name){
        setName(name);
        setColumns(new ArrayList<Column>());
        setForeignKeys(new ArrayList<ForeignKey>());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private ArrayList<Column> getColumns() {
        return columns;
    }

    private void setColumns(ArrayList<Column> columns) {
        this.columns = columns;
    }

    public ArrayList<ForeignKey> getForeignKeys() {
        return foreignKeys;
    }

    public void setForeignKeys(ArrayList<ForeignKey> foreignKeys) {
        this.foreignKeys = foreignKeys;
    }

    public Table addColumn(Column column) {
        getColumns().add(column);

        return this;
    }

    public Table addForeignKey(ForeignKey foreignKey) {
        getForeignKeys().add(foreignKey);

        return this;
    }

    public String toSQL(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE " + getName() + " (");

        for (Column c : getColumns()) {
            stringBuilder.append(c.toSQL() + ", ");
        }

        List<Column> primaryKeys = getPrimaryKeys();
        if (primaryKeys.size() > 0) {
            stringBuilder.append("PRIMARY KEY (");
            for (Column c : primaryKeys) {
                stringBuilder.append(c.getName() + ", ");
            }

            stringBuilder.setLength(stringBuilder.length() - 2);
            stringBuilder.append("), ");
        }

        for (ForeignKey f : getForeignKeys()) {
            stringBuilder.append(f.toSQL() + ", ");
        }

        if (getColumns().size() != 0 || getForeignKeys().size() != 0) {
            stringBuilder.setLength(stringBuilder.length() - 2);
        }

        stringBuilder.append(");");

        return stringBuilder.toString();
    }

    public List<Column> getPrimaryKeys() {
        ArrayList<Column> primaryKeys = new ArrayList<>();

        for (Column column : getColumns()) {
            if (column.isPrimaryKey()) {
                primaryKeys.add(column);
            }
        }

        return primaryKeys;
    }
}
