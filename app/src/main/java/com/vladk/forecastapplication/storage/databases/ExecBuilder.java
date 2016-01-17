package com.vladk.forecastapplication.storage.databases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Builder for creating and drop table.
 * <p/>
 * ------------------------------------------------------
 *
 * @author Vlad Kraevskiy
 */
public class ExecBuilder {
    private Map<String, String> mValues;
    private List<String> mForeignKeys;

    private static Map<Types, String> mTypes;

    private String mTableName;

    private String mPrimaryKey = null;

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ";
    private static final String FOREIGN_KEY = "FOREIGN KEY";
    private static final String PRIMARY_KEY = "PRIMARY KEY";
    private static final String REFERENCES = "REFERENCES ";

    private static final String DROP_TABLE = "DROP TABLE ";

    public ExecBuilder(String tableName) {
        if (mTypes == null) {
            initTypes();
        }

        mValues = new HashMap<>();
        mForeignKeys = new ArrayList<>();

        mTableName = tableName;
    }

    public ExecBuilder addValue(String value, Types type) {
        mValues.put(value, mTypes.get(type));
        return this;
    }

    public ExecBuilder setPrimaryKey(String primaryKey) {
        if (mPrimaryKey == null) {
            mPrimaryKey = primaryKey;
        }

        return this;
    }

    public ExecBuilder addForeignKey(String value, String referencedTable, String referencedValue) {
        String s = FOREIGN_KEY + "(" + value + ") " +
                REFERENCES + referencedTable + "(" +
                referencedValue + ")";

        mForeignKeys.add(s);

        return this;
    }

    public String build() {
        StringBuilder sb = new StringBuilder(CREATE_TABLE);

        sb.append(mTableName).append(" (");

        for (Map.Entry<String, String> entry : mValues.entrySet()) {
            sb.append(" ").append(entry.getKey());
            sb.append(" ").append(entry.getValue());
            sb.append(", ");
        }

        if (mPrimaryKey != null) {
            sb.append(PRIMARY_KEY).append("(").append(mPrimaryKey).append(")");
            sb.append(", ");
        }

        for (String foreignKey : mForeignKeys) {
            sb.append(foreignKey).append(", ");
        }

        sb.deleteCharAt(sb.length() - 2).append(");");

        return sb.toString();
    }

    public String dropTableString() {
        return DROP_TABLE + mTableName + ";";
    }

    private void initTypes() {
        mTypes = new HashMap<>();

        mTypes.put(Types.Integer, "INTEGER");
        mTypes.put(Types.Real, "REAL");
        mTypes.put(Types.Text, "TEXT");
    }

    public enum Types {
        Integer,
        Real,
        Text
    }
}
