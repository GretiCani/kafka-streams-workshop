package antonmry.exercise_1.util;

import antonmry.model.CorrelatedPurchase;
import antonmry.model.Purchase;
import antonmry.exercise_1.model.PurchasePattern;
import antonmry.exercise_1.model.RewardAccumulator;
import antonmry.util.serializer.JsonDeserializer;
import antonmry.util.serializer.JsonSerializer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;


public class StreamsSerdes {

    public static Serde<PurchasePattern> PurchasePatternSerde() {
        return new PurchasePatternsSerde();
    }

    public static Serde<RewardAccumulator> RewardAccumulatorSerde() {
        return new RewardAccumulatorSerde();
    }

    public static Serde<Purchase> PurchaseSerde() {
        return new PurchaseSerde();
    }

    public static final class PurchasePatternsSerde extends WrapperSerde<PurchasePattern> {
        public PurchasePatternsSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>(PurchasePattern.class));
        }
    }

    public static final class RewardAccumulatorSerde extends WrapperSerde<RewardAccumulator> {
        public RewardAccumulatorSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>(RewardAccumulator.class));
        }
    }

    public static final class PurchaseSerde extends WrapperSerde<Purchase> {
        public PurchaseSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>(Purchase.class));
        }
    }

    public static final class CorrelatedPurchaseSerde extends WrapperSerde<CorrelatedPurchase> {
        public CorrelatedPurchaseSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>(CorrelatedPurchase.class));
        }
    }

    private static class WrapperSerde<T> implements Serde<T> {

        private JsonSerializer<T> serializer;
        private JsonDeserializer<T> deserializer;

        WrapperSerde(JsonSerializer<T> serializer, JsonDeserializer<T> deserializer) {
            this.serializer = serializer;
            this.deserializer = deserializer;
        }

        @Override
        public void configure(Map<String, ?> map, boolean b) {

        }

        @Override
        public void close() {

        }

        @Override
        public Serializer<T> serializer() {
            return serializer;
        }

        @Override
        public Deserializer<T> deserializer() {
            return deserializer;
        }
    }
}