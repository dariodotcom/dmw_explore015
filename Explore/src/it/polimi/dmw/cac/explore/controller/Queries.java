package it.polimi.dmw.cac.explore.controller;

import org.slim3.datastore.Datastore;

import it.polimi.dmw.cac.explore.meta.UserMeta;
import it.polimi.dmw.cac.explore.model.User;

public class Queries {

    public static User getUserByUsername(String username) {
        UserMeta meta = UserMeta.get();
        return Datastore
            .query(meta)
            .filter(meta.username.equal(username))
            .asSingle();
    }

}
