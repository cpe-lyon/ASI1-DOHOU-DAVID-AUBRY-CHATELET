package cpe.atelier1.serialization;

import cpe.atelier1.model.Card;

public interface CardSerializer<T> {
    T serialize(Card card);
    Card deserialize(T object);
}
