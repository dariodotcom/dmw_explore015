package it.polimi.dmw.cac.explore;

import it.polimi.dmw.cac.explore.controller.ControllerException;
import it.polimi.dmw.cac.explore.controller.ControllerException.Type;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class Utils {

    public static boolean isStringEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public static Key stringToKey(String s) throws ControllerException {
        try {
            return KeyFactory.stringToKey(s);
        } catch (IllegalArgumentException e) {
            throw new ControllerException(Type.BAD_REQUEST);
        }

    }

}
