package elmar.test.sample.maker.parser.composite;

import java.awt.List;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ListType implements ParameterizedType{

    @Override
    public Type[] getActualTypeArguments() {
        // TODO Auto-generated method stub
        return new Type[] {};
    }

    @Override
    public Type getRawType() {
        return List.class;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }

}
