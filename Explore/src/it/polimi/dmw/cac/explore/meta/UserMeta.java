package it.polimi.dmw.cac.explore.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2013-12-23 13:27:04")
/** */
public final class UserMeta extends org.slim3.datastore.ModelMeta<it.polimi.dmw.cac.explore.model.User> {

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<it.polimi.dmw.cac.explore.model.User, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<it.polimi.dmw.cac.explore.model.User, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<it.polimi.dmw.cac.explore.model.User> name = new org.slim3.datastore.StringAttributeMeta<it.polimi.dmw.cac.explore.model.User>(this, "name", "name");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<it.polimi.dmw.cac.explore.model.User> passwordHash = new org.slim3.datastore.StringAttributeMeta<it.polimi.dmw.cac.explore.model.User>(this, "passwordHash", "passwordHash");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<it.polimi.dmw.cac.explore.model.User> surname = new org.slim3.datastore.StringAttributeMeta<it.polimi.dmw.cac.explore.model.User>(this, "surname", "surname");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<it.polimi.dmw.cac.explore.model.User> username = new org.slim3.datastore.StringAttributeMeta<it.polimi.dmw.cac.explore.model.User>(this, "username", "username");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<it.polimi.dmw.cac.explore.model.User, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<it.polimi.dmw.cac.explore.model.User, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final UserMeta slim3_singleton = new UserMeta();

    /**
     * @return the singleton
     */
    public static UserMeta get() {
       return slim3_singleton;
    }

    /** */
    public UserMeta() {
        super("User", it.polimi.dmw.cac.explore.model.User.class);
    }

    @Override
    public it.polimi.dmw.cac.explore.model.User entityToModel(com.google.appengine.api.datastore.Entity entity) {
        it.polimi.dmw.cac.explore.model.User model = new it.polimi.dmw.cac.explore.model.User();
        model.setKey(entity.getKey());
        model.setName((java.lang.String) entity.getProperty("name"));
        model.setPasswordHash((java.lang.String) entity.getProperty("passwordHash"));
        model.setSurname((java.lang.String) entity.getProperty("surname"));
        model.setUsername((java.lang.String) entity.getProperty("username"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        it.polimi.dmw.cac.explore.model.User m = (it.polimi.dmw.cac.explore.model.User) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("name", m.getName());
        entity.setProperty("passwordHash", m.getPasswordHash());
        entity.setProperty("surname", m.getSurname());
        entity.setProperty("username", m.getUsername());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        it.polimi.dmw.cac.explore.model.User m = (it.polimi.dmw.cac.explore.model.User) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        it.polimi.dmw.cac.explore.model.User m = (it.polimi.dmw.cac.explore.model.User) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        it.polimi.dmw.cac.explore.model.User m = (it.polimi.dmw.cac.explore.model.User) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        it.polimi.dmw.cac.explore.model.User m = (it.polimi.dmw.cac.explore.model.User) model;
        long version = m.getVersion() != null ? m.getVersion().longValue() : 0L;
        m.setVersion(Long.valueOf(version + 1L));
    }

    @Override
    protected void prePut(Object model) {
    }

    @Override
    protected void postGet(Object model) {
    }

    @Override
    public String getSchemaVersionName() {
        return "slim3.schemaVersion";
    }

    @Override
    public String getClassHierarchyListName() {
        return "slim3.classHierarchyList";
    }

    @Override
    protected boolean isCipherProperty(String propertyName) {
        return false;
    }

    @Override
    protected void modelToJson(org.slim3.datastore.json.JsonWriter writer, java.lang.Object model, int maxDepth, int currentDepth) {
        it.polimi.dmw.cac.explore.model.User m = (it.polimi.dmw.cac.explore.model.User) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getName() != null){
            writer.setNextPropertyName("name");
            encoder0.encode(writer, m.getName());
        }
        if(m.getPasswordHash() != null){
            writer.setNextPropertyName("passwordHash");
            encoder0.encode(writer, m.getPasswordHash());
        }
        if(m.getSurname() != null){
            writer.setNextPropertyName("surname");
            encoder0.encode(writer, m.getSurname());
        }
        if(m.getUsername() != null){
            writer.setNextPropertyName("username");
            encoder0.encode(writer, m.getUsername());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected it.polimi.dmw.cac.explore.model.User jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        it.polimi.dmw.cac.explore.model.User m = new it.polimi.dmw.cac.explore.model.User();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("name");
        m.setName(decoder0.decode(reader, m.getName()));
        reader = rootReader.newObjectReader("passwordHash");
        m.setPasswordHash(decoder0.decode(reader, m.getPasswordHash()));
        reader = rootReader.newObjectReader("surname");
        m.setSurname(decoder0.decode(reader, m.getSurname()));
        reader = rootReader.newObjectReader("username");
        m.setUsername(decoder0.decode(reader, m.getUsername()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}