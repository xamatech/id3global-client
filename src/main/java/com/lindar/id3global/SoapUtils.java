package com.lindar.id3global;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class SoapUtils {


    public static <T> Optional<T> fetch(JAXBElement<T> element){
        if(element == null || element.isNil()) return Optional.empty();
        return Optional.of(element.getValue());
    }

    public static <T> void populate(Supplier<T> supplier, Consumer<T> consumer){
        consumer.accept(supplier.get());
    }

    public static <T> void populate(JAXBElement<T> element, Consumer<T> consumer){
        if(element == null || element.isNil()) return;
        consumer.accept(element.getValue());
    }

    public static <T, E> void populate(JAXBElement<T> element, Function<T, E> mapper, Consumer<E> consumer){
        if(element == null || element.isNil()) return;
        consumer.accept(mapper.apply(element.getValue()));
    }

    public static <T extends Enum<?>, E extends Enum<?>> void populate(T element, Function<T, E> mapper, Consumer<E> consumer){
        if(element == null) return;
        consumer.accept(mapper.apply(element));
    }

    public static Date mapDate(XMLGregorianCalendar date){
        return date.toGregorianCalendar().getTime();
    }
}
