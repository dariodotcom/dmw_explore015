package it.polimi.dmw.cac.explore.meta;

//@javax.annotation.Generated(value = { "slim3-gen", "@VERSION@" }, date = "2013-12-23 13:27:04")
/** */
public final class ExhibitionMeta extends org.slim3.datastore.ModelMeta<it.polimi.dmw.cac.explore.model.Exhibition> {

    /** */
    public final org.slim3.datastore.StringAttributeMeta<it.polimi.dmw.cac.explore.model.Exhibition> description = new org.slim3.datastore.StringAttributeMeta<it.polimi.dmw.cac.explore.model.Exhibition>(this, "description", "description");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<it.polimi.dmw.cac.explore.model.Exhibition, com.google.appengine.api.datastore.Key> key = new org.slim3.datastore.CoreAttributeMeta<it.polimi.dmw.cac.explore.model.Exhibition, com.google.appengine.api.datastore.Key>(this, "__key__", "key", com.google.appengine.api.datastore.Key.class);

    /** */
    public final org.slim3.datastore.StringAttributeMeta<it.polimi.dmw.cac.explore.model.Exhibition> name = new org.slim3.datastore.StringAttributeMeta<it.polimi.dmw.cac.explore.model.Exhibition>(this, "name", "name");

    /** */
    public final org.slim3.datastore.StringAttributeMeta<it.polimi.dmw.cac.explore.model.Exhibition> photoUrl = new org.slim3.datastore.StringAttributeMeta<it.polimi.dmw.cac.explore.model.Exhibition>(this, "photoUrl", "photoUrl");

    /** */
    public final org.slim3.datastore.CoreAttributeMeta<it.polimi.dmw.cac.explore.model.Exhibition, java.lang.Long> version = new org.slim3.datastore.CoreAttributeMeta<it.polimi.dmw.cac.explore.model.Exhibition, java.lang.Long>(this, "version", "version", java.lang.Long.class);

    private static final ExhibitionMeta slim3_singleton = new ExhibitionMeta();

    /**
     * @return the singleton
     */
    public static ExhibitionMeta get() {
       return slim3_singleton;
    }

    /** */
    public ExhibitionMeta() {
        super("Exhibition", it.polimi.dmw.cac.explore.model.Exhibition.class);
    }

    @Override
    public it.polimi.dmw.cac.explore.model.Exhibition entityToModel(com.google.appengine.api.datastore.Entity entity) {
        it.polimi.dmw.cac.explore.model.Exhibition model = new it.polimi.dmw.cac.explore.model.Exhibition();
        model.setDescription((java.lang.String) entity.getProperty("description"));
        model.setKey(entity.getKey());
        model.setName((java.lang.String) entity.getProperty("name"));
        model.setPhotoUrl((java.lang.String) entity.getProperty("photoUrl"));
        model.setVersion((java.lang.Long) entity.getProperty("version"));
        return model;
    }

    @Override
    public com.google.appengine.api.datastore.Entity modelToEntity(java.lang.Object model) {
        it.polimi.dmw.cac.explore.model.Exhibition m = (it.polimi.dmw.cac.explore.model.Exhibition) model;
        com.google.appengine.api.datastore.Entity entity = null;
        if (m.getKey() != null) {
            entity = new com.google.appengine.api.datastore.Entity(m.getKey());
        } else {
            entity = new com.google.appengine.api.datastore.Entity(kind);
        }
        entity.setProperty("description", m.getDescription());
        entity.setProperty("name", m.getName());
        entity.setProperty("photoUrl", m.getPhotoUrl());
        entity.setProperty("version", m.getVersion());
        entity.setProperty("slim3.schemaVersion", 1);
        return entity;
    }

    @Override
    protected com.google.appengine.api.datastore.Key getKey(Object model) {
        it.polimi.dmw.cac.explore.model.Exhibition m = (it.polimi.dmw.cac.explore.model.Exhibition) model;
        return m.getKey();
    }

    @Override
    protected void setKey(Object model, com.google.appengine.api.datastore.Key key) {
        validateKey(key);
        it.polimi.dmw.cac.explore.model.Exhibition m = (it.polimi.dmw.cac.explore.model.Exhibition) model;
        m.setKey(key);
    }

    @Override
    protected long getVersion(Object model) {
        it.polimi.dmw.cac.explore.model.Exhibition m = (it.polimi.dmw.cac.explore.model.Exhibition) model;
        return m.getVersion() != null ? m.getVersion().longValue() : 0L;
    }

    @Override
    protected void assignKeyToModelRefIfNecessary(com.google.appengine.api.datastore.AsyncDatastoreService ds, java.lang.Object model) {
    }

    @Override
    protected void incrementVersion(Object model) {
        it.polimi.dmw.cac.explore.model.Exhibition m = (it.polimi.dmw.cac.explore.model.Exhibition) model;
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
        it.polimi.dmw.cac.explore.model.Exhibition m = (it.polimi.dmw.cac.explore.model.Exhibition) model;
        writer.beginObject();
        org.slim3.datastore.json.Default encoder0 = new org.slim3.datastore.json.Default();
        if(m.getDescription() != null){
            writer.setNextPropertyName("description");
            encoder0.encode(writer, m.getDescription());
        }
        if(m.getKey() != null){
            writer.setNextPropertyName("key");
            encoder0.encode(writer, m.getKey());
        }
        if(m.getName() != null){
            writer.setNextPropertyName("name");
            encoder0.encode(writer, m.getName());
        }
        if(m.getPhotoUrl() != null){
            writer.setNextPropertyName("photoUrl");
            encoder0.encode(writer, m.getPhotoUrl());
        }
        if(m.getVersion() != null){
            writer.setNextPropertyName("version");
            encoder0.encode(writer, m.getVersion());
        }
        writer.endObject();
    }

    @Override
    protected it.polimi.dmw.cac.explore.model.Exhibition jsonToModel(org.slim3.datastore.json.JsonRootReader rootReader, int maxDepth, int currentDepth) {
        it.polimi.dmw.cac.explore.model.Exhibition m = new it.polimi.dmw.cac.explore.model.Exhibition();
        org.slim3.datastore.json.JsonReader reader = null;
        org.slim3.datastore.json.Default decoder0 = new org.slim3.datastore.json.Default();
        reader = rootReader.newObjectReader("description");
        m.setDescription(decoder0.decode(reader, m.getDescription()));
        reader = rootReader.newObjectReader("key");
        m.setKey(decoder0.decode(reader, m.getKey()));
        reader = rootReader.newObjectReader("name");
        m.setName(decoder0.decode(reader, m.getName()));
        reader = rootReader.newObjectReader("photoUrl");
        m.setPhotoUrl(decoder0.decode(reader, m.getPhotoUrl()));
        reader = rootReader.newObjectReader("version");
        m.setVersion(decoder0.decode(reader, m.getVersion()));
        return m;
    }
}