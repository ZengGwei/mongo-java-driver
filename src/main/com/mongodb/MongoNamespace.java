package com.mongodb;

import org.bson.util.annotations.Immutable;

@Immutable
final class MongoNamespace {
    private static final String NAMESPACE_TEMPLATE = "%s.%s";
    public static final String COMMAND_COLLECTION_NAME = "$cmd";

    private final String databaseName;
    private final String collectionName;

    public MongoNamespace(final String databaseName, final String collectionName) {
        if (databaseName == null) {
            throw new IllegalArgumentException("database name can not be null");
        }
        if (collectionName == null) {
            throw new IllegalArgumentException("Collection name can not be null");
        }

        this.databaseName = databaseName;
        this.collectionName = collectionName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getFullName() {
        return getDatabaseName() + "." + getCollectionName();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final MongoNamespace that = (MongoNamespace) o;

        if (collectionName != null ? !collectionName.equals(that.collectionName) : that.collectionName != null) {
            return false;
        }
        if (databaseName != null ? !databaseName.equals(that.databaseName) : that.databaseName != null) {
            return false;
        }

        return true;
    }

    /**
     * Returns the standard MongoDB representation of a namespace, which is {@code &lt;database&gt;.&lt;collection&gt;}.
     *
     * @return string representation of the namespace.
     */
    @Override
    public String toString() {
        return databaseName + "." + collectionName;
    }

    @Override
    public int hashCode() {
        int result = databaseName != null ? databaseName.hashCode() : 0;
        result = 31 * result + (collectionName != null ? collectionName.hashCode() : 0);
        return result;
    }

    public static String asNamespaceString(final String databaseName, final String collectionName) {
        return String.format(NAMESPACE_TEMPLATE, databaseName, collectionName);
    }
}