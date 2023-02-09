package com.uis.publications.repository;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.spi.MetadataBuilderContributor;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.BooleanType;

public class SqlFunctionsMetadataBuilderContributor implements MetadataBuilderContributor {
    @Override
    public void contribute(MetadataBuilder metadataBuilder) {
        metadataBuilder.applySqlFunction("fts",
                new SQLFunctionTemplate(BooleanType.INSTANCE, "(to_tsvector('spanish', description) @@ to_tsquery('spanish', ?1) or \n" +
                        "to_tsvector('spanish', title) @@ to_tsquery('spanish', ?1))"));
    }
}
