package p8499.speech.fd;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonDeserialize(using = FilterDeserializer.class)
@JsonSerialize(using = FilterSerializer.class)
public interface FilterExpr {
    String toStringPostgresql();
    String toStringOracle();
}